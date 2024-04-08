package com.dshop.datn.scheduledtasks;

import com.dshop.datn.repositories.NotificationRepository;
import com.dshop.datn.repositories.ProductRepository;
import com.dshop.datn.repositories.SaleRepository;
import com.dshop.datn.services.NotificationService;
import com.dshop.datn.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    public ScheduledTasks(SaleRepository saleRepository,
                            ProductRepository productRepository,
                          NotificationRepository notificationRepository,
                          NotificationService notificationService) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;

    }

    @Scheduled(cron = "59 59 23 ? * * ")
    public void updateSaleStatus() {
        // Lấy thời gian hiện tại
        Date currentDate = new Date();
        // Lấy danh sách tất cả các Sale
        List<Sale> activeSales = saleRepository.findAll();
        // Kiểm tra và cập nhật trạng thái (isActive) của Sale
        for (Sale sale : activeSales) {
            if (sale.getEndDate().before(currentDate)) {
                sale.setIsActive(0);
                saleRepository.save(sale);
            }
        }
    }
    @Scheduled(cron = "0 30 20 ? * * ")
    public void scanProduct(){
        List<Product> products = productRepository.findAll();
        for(Product p: products){
            List<Color> colors = p.getColors();
            for(Color color: colors){
                List<Size> sizes = color.getSizes();
                for (Size size : sizes){
                    Notification notification = null;
                    if((size.getTotal() - size.getSold()) <= 100){
                        notification = new Notification();
                        notification.setIsRead(false);
                        notification.setDeliverStatus(false);
                        notification.setType(3);
                        notification.setContent(String.format("Sản phẩm %s màu %s size %s sắp hết, kiểm tra ngay nào", p.getName(), color.getValue(), size.getValue()));
                        notification.setProduct(p);
                        notificationService.createNotification(notification);
                    }
                }
            }
        }
    }
}
