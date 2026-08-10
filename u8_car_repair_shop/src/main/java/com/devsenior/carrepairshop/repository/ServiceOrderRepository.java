package com.devsenior.carrepairshop.repository;

import com.devsenior.carrepairshop.model.OrderStatus;
import com.devsenior.carrepairshop.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    List<ServiceOrder> findByVehicleId(Long vehicleId);

    List<ServiceOrder> findByStatus(OrderStatus orderStatus);
}
