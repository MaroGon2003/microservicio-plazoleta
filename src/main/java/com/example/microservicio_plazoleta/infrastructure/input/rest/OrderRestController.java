package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.dto.request.DishToOrderRequestDto;
import com.example.microservicio_plazoleta.application.handler.IDishToOrderHandler;
import com.example.microservicio_plazoleta.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class OrderRestController {

    private final IOrderHandler orderHandler;
    private final IDishToOrderHandler dishToOrderHandler;

    @Secured({"CUSTOMER"})
    @Operation(summary = "Create a new order")
    @PostMapping("/create-order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order created")
    })
    public ResponseEntity<Void> createOrder(@RequestParam Long restaurantId) {

        orderHandler.saveOrder(restaurantId);

        return ResponseEntity.ok().build();
    }

    @Secured({"CUSTOMER"})
    @Operation(summary = "Add a dish to an order")
    @PostMapping("/add-dish")
    public ResponseEntity<Void> addDishToOrder(@Valid @RequestBody DishToOrderRequestDto dishToOrderRequestDto) {
        dishToOrderHandler.addDishToOrder(dishToOrderRequestDto);
        return ResponseEntity.ok().build();
    }



}
