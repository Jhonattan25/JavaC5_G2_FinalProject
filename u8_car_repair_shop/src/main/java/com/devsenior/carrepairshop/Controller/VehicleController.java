package com.devsenior.carrepairshop.Controller;

import com.devsenior.carrepairshop.dto.VehicleRequest;
import com.devsenior.carrepairshop.dto.VehicleResponse;
import com.devsenior.carrepairshop.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody VehicleRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehicleService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> list() {
        return ResponseEntity.ok(vehicleService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VehicleResponse>> listByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(vehicleService.listByCustomer(customerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
