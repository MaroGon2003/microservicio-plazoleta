package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderRankingDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderResponseDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderTimeResponseDto;
import com.example.microservicio_plazoleta.application.handler.IOrderHandler;
import com.example.microservicio_plazoleta.infrastructure.utils.Constans;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @Secured({"CUSTOMER"})
    @Operation(summary = "Create a new order")
    @PostMapping("/create-order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order created")
    })
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {

        orderHandler.saveOrder(orderRequestDto);

        return ResponseEntity.ok(Constans.ORDER_CREATED);
    }

    @Secured({"EMPLOYEE"})
    @Operation(summary = "Get all orders")
    @GetMapping("/get-all-orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "orders found")
    })
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@RequestParam int page, @RequestParam int size, @RequestParam Long restaurantId ,@RequestParam String status) {

        return ResponseEntity.ok(orderHandler.getAllOrdersByStatus(page, size, restaurantId, status));
    }

    @Secured({"EMPLOYEE"})
    @PatchMapping("/assign-employee/{id}")
    public ResponseEntity<String> assignEmployee(@PathVariable Long id) {
        orderHandler.assignEmployee(id);
        return ResponseEntity.ok(Constans.EMPLOYEE_ASSIGNED);
    }

    @Secured({"EMPLOYEE"})
    @PatchMapping("/ready-order/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable Long id) {
        orderHandler.readyOrder(id);
        return ResponseEntity.ok(Constans.ORDER_READY);
    }

    @Secured({"EMPLOYEE"})
    @PatchMapping("/deliver-order/{id}")
    public ResponseEntity<String> deliverOrder(@PathVariable Long id,@RequestParam int pin) {
        orderHandler.deliveryOrder(id, pin);
        return ResponseEntity.ok(Constans.ORDER_DELIVERED);
    }

    @Secured({"CUSTOMER"})
    @PatchMapping("/cancel-order/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        orderHandler.cancelOrder(id);
        return ResponseEntity.ok(Constans.ORDER_CANCELLED);
    }

    @Secured({"OWNER"})
    @GetMapping("/period")
    public ResponseEntity<List<OrderTimeResponseDto>> showOrderPeriod() {
        return ResponseEntity.ok(orderHandler.showOrderPeriod());
    }

    @Secured({"OWNER"})
    @GetMapping("/ranking")
    public ResponseEntity<List<OrderRankingDto>> showOrderRanking() {
        return ResponseEntity.ok(orderHandler.showOrderRanking());
    }


}
