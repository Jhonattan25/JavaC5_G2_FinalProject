package com.devsenior.carrepairshop.service;

import com.devsenior.carrepairshop.dto.ServiceOrderRequest;
import com.devsenior.carrepairshop.dto.ServiceOrderResponse;
import com.devsenior.carrepairshop.model.OrderStatus;

import java.util.List;

public interface ServiceOrderService {

    ServiceOrderResponse create(ServiceOrderRequest request);

    List<ServiceOrderResponse> list();

    ServiceOrderResponse findById(Long id);

    ServiceOrderResponse updateStatus(Long id, OrderStatus orderStatus);

    List<ServiceOrderResponse> listByStatus(OrderStatus orderStatus);
}
