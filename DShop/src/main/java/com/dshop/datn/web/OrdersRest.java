package com.dshop.datn.web;

import com.dshop.datn.config.Constants;
import com.dshop.datn.helper.ApiResponse;
import com.dshop.datn.web.dto.request.CancelOrdersRequest;
import com.dshop.datn.web.dto.request.OrderItemRequest;
import com.dshop.datn.web.dto.request.OrdersRequest;
import com.dshop.datn.web.dto.response.OrdersResponse;
import com.dshop.datn.services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class OrdersRest {
    private final OrdersService ordersService;

    public OrdersRest(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<?> getCart(@PathVariable("id") long userId) {
        try {
            OrdersResponse ordersResponse = ordersService.getOrderByType(userId, Constants.CART_TYPE);
            if (ordersResponse == null) {
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thành công", "Chưa có sản phẩm nào trong giỏ hàng"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", ordersResponse), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable(name = "id") Long orderId) {
        try {
            OrdersResponse ordersResponse = ordersService.getOrder(orderId);
            if (ordersResponse != null) {
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", ordersResponse), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(ApiResponse.build(200, false, "Thất bại", "Có lỗi xảy ra"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hủy đơn hàng không thành công! Lỗi " + e.getMessage());
        }
    }

    @GetMapping("/order/user/{id}")
    private ResponseEntity<?> getOrder(@PathVariable("id") long userId) {
        try {
            List<OrdersResponse> ordersResponses = ordersService.getOrders(userId, Constants.ORDERS_TYPE);
            if (ordersResponses == null) {
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thành công", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", ordersResponses), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addToCart/{id}")
    public ResponseEntity<?> addItemToOrders(@PathVariable("id") long userId, @RequestBody OrderItemRequest orderItemRequest) {
        try {
            Object ordersResponse = ordersService.addItemToCart(userId, orderItemRequest);
            if (ordersResponse instanceof String) {
                String errorMessage = (String) ordersResponse;
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thất bại", errorMessage), HttpStatus.OK);
            } else {
                OrdersResponse orders = (OrdersResponse) ordersResponse;
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", orders), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteItemFormCart")
    public ResponseEntity<?> deleteItemFormCart(@RequestParam(value = "id") Long orderItemId) {
        try {
            Object orders = ordersService.deleteItemFromCart(orderItemId);
            return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", orders), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa không thành công! Lỗi " + e.getMessage());
        }
    }

    @DeleteMapping("/delete1Item")
    public ResponseEntity<?> delete1Item(@RequestParam(value = "id") long orderItemId) {
        try {
            Object object = ordersService.delete1Item(orderItemId);
            if (object instanceof String) {
                String errorMessage = (String) object;
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thất bại", errorMessage), HttpStatus.OK);
            } else {
                OrdersResponse orders = (OrdersResponse) object;
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", orders), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa không thành công! Lỗi " + e.getMessage());
        }
    }

    @PostMapping("/plusItem")
    public ResponseEntity<?> plus1Item(@RequestParam(value = "id") long orderItemId) {
        try {
            Object ordersResponse = ordersService.plus1Item(orderItemId);
            // Logic trả về
            if (ordersResponse instanceof String) {
                String errorMessage = (String) ordersResponse;
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thất bại", errorMessage), HttpStatus.OK);
            } else {
                OrdersResponse orders = (OrdersResponse) ordersResponse;
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", orders), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/checkOrder/{id}")
    public ResponseEntity<?> checkCreateOrder(@PathVariable("id") Long orderId) {
        try {
            Object object = ordersService.checkCreateOrder(orderId);
            if (object instanceof String) {
                String errorMessage = (String) object;
                return new ResponseEntity<>(ApiResponse.build(201, false, "Thất bại", errorMessage), HttpStatus.OK);
            } else {
                OrdersResponse orders = (OrdersResponse) object;
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", orders), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi " + e.getMessage());
        }
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createOrder(@PathVariable("id") Long orderId,
                                         @RequestBody OrdersRequest ordersRequest) {
        try {
            Object object = ordersService.createOrder(orderId, ordersRequest);
            return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", object), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đặt hàng không thành công! Lỗi " + e.getMessage());
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(
            @RequestBody CancelOrdersRequest cancelOrdersRequest) {
        try {
            Object object = ordersService.cancelOrder(cancelOrdersRequest);
            return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", object), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hủy đơn hàng không thành công! Lỗi " + e.getMessage());
        }
    }
}
