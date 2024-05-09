package com.dshop.datn.utils;

import com.dshop.datn.models.OrderItem;
import com.dshop.datn.models.Orders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private OtpService otpService;
    private String Otp;

    @Autowired
    public EmailUtils(JavaMailSender javaMailSender, OtpUtil otpUtil, OtpService otpService) {
        this.javaMailSender = javaMailSender;
        this.otpUtil = otpUtil;
        this.otpService = otpService;
    }

    public String getCityName(Orders orders) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("https://vapi.vnappmob.com/api/province/", String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode resultsNode = jsonNode.get("results");
                if (resultsNode.isArray()) {
                    for (JsonNode resultNode : resultsNode) {
                        if (resultNode.has("province_id") && resultNode.has("province_name")) {
                            String provinceId = resultNode.get("province_id").asText();
                            String provinceName = resultNode.get("province_name").asText();
                            if (provinceId.equals(orders.getProvince())) {
                                return provinceName;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Log lỗi hoặc thông báo lỗi
            e.printStackTrace();
        }
        return null;
    }

    public String getDistrictName(Orders orders) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("https://vapi.vnappmob.com/api/province/district/" + orders.getProvince(), String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode resultsNode = jsonNode.get("results");
                if (resultsNode.isArray()) {
                    for (JsonNode resultNode : resultsNode) {
                        if (resultNode.has("district_id") && resultNode.has("district_name")) {
                            String districtId = resultNode.get("district_id").asText();
                            String districtName = resultNode.get("district_name").asText();
                            if (districtId.equals(orders.getDistrict())) {
                                return districtName;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Log lỗi hoặc thông báo lỗi
            e.printStackTrace();
        }
        return null;
    }

    public String getWardName(Orders orders) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("https://vapi.vnappmob.com/api/province/ward/" + orders.getDistrict(), String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode resultsNode = jsonNode.get("results");
                if (resultsNode.isArray()) {
                    for (JsonNode resultNode : resultsNode) {
                        if (resultNode.has("ward_id") && resultNode.has("ward_name")) {
                            String wardId = resultNode.get("ward_id").asText();
                            String wardName = resultNode.get("ward_name").asText();
                            if (wardId.equals(orders.getWards())) {
                                return wardName;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Log lỗi hoặc thông báo lỗi
            e.printStackTrace();
        }
        return null;
    }

    public void sendPassword(String email, String password) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Thay Đổi Mật Khẩu");
        mimeMessageHelper.setText("""
                <div style="font-family: Arial, sans-serif;">
                  <p>Mật khẩu đã được thay đổi:</p>
                  <h2>%s</h2>
                  <a href="http://localhost:8080/api/login" target="_blank">Quay lại trang đăng nhập</a>
                </div>
                """.formatted(password), true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }

    public void sendMailOrder(String email, Orders orders) throws MessagingException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String createdDate = dateFormatter.format(orders.getModifiedDate());
        int totalOrderAmount = totalOrderAmount(orders);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Thông Báo Đặt Hàng Thành Công");
        StringBuilder sb = new StringBuilder()
                .append("<h3>Cảm ơn bạn " + orders.getUser().getUsername()+ " đã tin tưởng đặt đơn hàng của DKING. </h3>").append("<br/>")
                .append("Bạn vui lòng kiểm tra lại thông tin đơn hàng gồm:").append("<br/>")
                .append("Mã đơn hàng: " + orders.getCodeOrders()).append("<br/>")
                .append("Tổng tiền: " + totalOrderAmount+ " VNĐ").append("<br/>")
                .append("Ngày tạo: " + createdDate).append("<br/>")
                .append("Người nhận: " + orders.getFullName()).append("<br/>")
                .append("Số điện thoại: " + orders.getPhone()).append("<br/>")
                .append("Địa chỉ: " + orders.getAddressDetail())
                .append(", " + getWardName(orders))
                .append(", " + getDistrictName(orders))
                .append(", " + getCityName(orders)).append("<br/>")
                .append("Với trường hợp sai thông tin đặt hàng bạn có thể báo lại cho shop để hỗ trợ kịp thời.").append("<br/>")
                .append("Chúc bạn một ngày tốt lành!").append("<br/>")
                .append("Tiếp tục mua sắm tại: ")
                .append("http://localhost:3000/");
        mimeMessageHelper.setText(sb.toString(), true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }
    public void sendMailOrderCancel(String email, Orders orders) throws MessagingException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String createdDate = dateFormatter.format(orders.getModifiedDate());
        int totalOrderAmount = totalOrderAmount(orders);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Thông Báo Hủy Đơn Hàng");
        StringBuilder sb = new StringBuilder()
                .append("<h3>Bạn " + orders.getUser().getUsername() + " vùa hủy đơn hàng </h3>").append("<br/>")
                .append("Mã đơn hàng: " + orders.getCodeOrders()).append("<br/>")
                .append("Tổng tiền: " + totalOrderAmount+ " VNĐ").append("<br/>")
                .append("Ngày tạo: " + createdDate).append("<br/>")
                .append("Người nhận: " + orders.getFullName()).append("<br/>")
                .append("Số điện thoại: " + orders.getPhone()).append("<br/>")
                .append("Địa chỉ: " + orders.getAddressDetail())
                .append(", " + orders.getWards())
                .append(", " + orders.getDistrict())
                .append(", " + orders.getProvince()).append("<br/>")
                .append("Tiếp tục mua sắm tại đây: ")
                .append("http://localhost:3000/");
        mimeMessageHelper.setText(sb.toString(), true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }
    public void sendMailOrderShipping(String email, Orders orders) throws MessagingException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String createdDate = dateFormatter.format(orders.getModifiedDate());
        int totalOrderAmount = totalOrderAmount(orders);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Đơn Hàng Đang Trên Đường Đến Với Bạn");
        StringBuilder sb = new StringBuilder()
                .append("<h3>Đơn hàng của bạn " + orders.getUser().getUsername() + " đang trên đường giao đến bạn</h3>").append("<br/>")
                .append("Mã đơn hàng: " + orders.getCodeOrders()).append("<br/>")
                .append("Tổng tiền: " + totalOrderAmount + " VNĐ").append("<br/>")
                .append("Ngày tạo: " + createdDate).append("<br/>")
                .append("Người nhận: " + orders.getFullName()).append("<br/>")
                .append("Số điện thoại: " + orders.getPhone()).append("<br/>")
                .append("Địa chỉ: " + orders.getAddressDetail())
                .append(", " + getWardName(orders))
                .append(", " + getDistrictName(orders))
                .append(", " + getCityName(orders)).append("<br/>")
                .append("Đơn hàng sẽ sớm được giao, vui lòng chú ý điện thoại.").append("<br/>")
                .append("Tiếp tục mua sắm tại đây: ")
                .append("http://localhost:3000");
        mimeMessageHelper.setText(sb.toString(), true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }
    public void sendMailOrderSuccess(String email, Orders orders) throws MessagingException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String createdDate = dateFormatter.format(orders.getCreateDate());
        int totalOrderAmount = totalOrderAmount(orders);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Cản Ơn Bạn Đã Đặt Hàng");
        StringBuilder sb = new StringBuilder()
                .append("<h3> Cảm ơn bạn " + orders.getUser().getUsername() + " đã đặt hàng</h3>").append("<br/>")
                .append("Cảm ơn bạn " + orders.getFullName() + " đã đặt hàng bên chúng tôi").append("<br/>")
                .append("Bạn có thể đổi trả trong vòng 7 ngày nếu gặp lỗi từ nhà sản xuất.").append("<br/>")
                .append("Nếu có vấn đề cần thắc mắc hãy liên hệ lại cho chúng tôi 0965695182.").append("<br/>")
                .append("Tiếp tục mua sắm tại đây: ")
                .append("http://localhost:3000/");
        mimeMessageHelper.setText(sb.toString(), true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }
    private int totalOrderAmount(Orders orders) {
        int totalAmount = 0;
        for (OrderItem item : orders.getOrderItems()) {
            totalAmount += item.getQuantity() * item.getSellPrice();
        }
        return totalAmount;
    }
}
