package com.devsenior.carrepairshop.Controller;

import com.devsenior.carrepairshop.dto.ServiceOrderRequest;
import com.devsenior.carrepairshop.dto.ServiceOrderResponse;
import com.devsenior.carrepairshop.model.OrderStatus;
import com.devsenior.carrepairshop.service.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> create(@Valid @RequestBody ServiceOrderRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(serviceOrderService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrderResponse>> list() {
        return ResponseEntity.ok(serviceOrderService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceOrderService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceOrderResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus orderStatus) {
        return ResponseEntity.ok(serviceOrderService.updateStatus(id, orderStatus));
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<ServiceOrderResponse>> listByStatus(@PathVariable OrderStatus orderStatus) {
        return ResponseEntity.ok(serviceOrderService.listByStatus(orderStatus));
    }
}
