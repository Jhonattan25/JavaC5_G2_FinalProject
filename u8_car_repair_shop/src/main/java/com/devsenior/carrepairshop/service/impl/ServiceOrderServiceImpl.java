package com.devsenior.carrepairshop.service.impl;

import com.devsenior.carrepairshop.dto.ServiceOrderRequest;
import com.devsenior.carrepairshop.dto.ServiceOrderResponse;
import com.devsenior.carrepairshop.exception.NotFoundException;
import com.devsenior.carrepairshop.exception.UnauthorizedException;
import com.devsenior.carrepairshop.model.OrderStatus;
import com.devsenior.carrepairshop.model.ServiceOrder;
import com.devsenior.carrepairshop.model.Vehicle;
import com.devsenior.carrepairshop.repository.ServiceOrderRepository;
import com.devsenior.carrepairshop.repository.VehicleRepository;
import com.devsenior.carrepairshop.service.ServiceOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class ServiceOrderServiceImpl implements ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceOrderServiceImpl(ServiceOrderRepository serviceOrderRepository,
                                   VehicleRepository vehicleRepository) {

        this.serviceOrderRepository = serviceOrderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public ServiceOrderResponse create(ServiceOrderRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new NotFoundException(
                        "No existe un vehiculo con id " + request.vehicleId()));

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setDescription(request.description());
        serviceOrder.setCost(request.cost());
        serviceOrder.setVehicle(vehicle);
        serviceOrder.setEntryDate(LocalDate.now());
        serviceOrder.setStatus(OrderStatus.RECIBIDO);

        return toResponse(serviceOrderRepository.save(serviceOrder));
    }

    @Override
    public List<ServiceOrderResponse> list() {
        return serviceOrderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ServiceOrderResponse findById(Long id) {
        return toResponse(getServiceOrder(id));
    }

    @Override
    @Transactional
    public ServiceOrderResponse updateStatus(Long id, OrderStatus orderStatus) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        if (serviceOrder.getStatus() == OrderStatus.ENTREGADO) {
            throw new UnauthorizedException(
                    "No se puede modificar una orden ya entregada");
        }

        serviceOrder.setStatus(orderStatus);

        if (orderStatus == OrderStatus.ENTREGADO) {
            serviceOrder.setDeliveryDate(LocalDate.now());
        }

        return toResponse(serviceOrderRepository.save(serviceOrder));
    }

    @Override
    public List<ServiceOrderResponse> listByStatus(OrderStatus orderStatus) {
        return serviceOrderRepository.findByStatus(orderStatus)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ServiceOrder getServiceOrder(Long id) {
        return serviceOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "No existe una orden con id " + id));
    }

    private ServiceOrderResponse toResponse(ServiceOrder serviceOrderRequest) {
        return new ServiceOrderResponse(
                serviceOrderRequest.getId(),
                serviceOrderRequest.getDescription(),
                serviceOrderRequest.getEntryDate(),
                serviceOrderRequest.getDeliveryDate(),
                serviceOrderRequest.getStatus().name(),
                serviceOrderRequest.getCost(),
                serviceOrderRequest.getVehicle().getLicensePlate(),
                serviceOrderRequest.getVehicle().getCustomer().getName()
        );
    }
}
