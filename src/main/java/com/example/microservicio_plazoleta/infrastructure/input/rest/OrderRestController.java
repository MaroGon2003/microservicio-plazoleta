package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
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

    @Secured({"CUSTOMER"})
    @Operation(summary = "Create a new order")
    @PostMapping("/create-order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order created")
    })
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {

        orderHandler.saveOrder(orderRequestDto);

        return ResponseEntity.ok().build();
    }


}
