package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.dto.request.RestaurantEmployeeRequestDto;
import com.example.microservicio_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.microservicio_plazoleta.application.handler.IRestaurantEmployeeHandler;
import com.example.microservicio_plazoleta.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;
    private final IRestaurantEmployeeHandler restaurantEmployeeHandler;


    @Secured("ADMIN")
    @Operation(summary = "Add a new restaurant", description = "Creates a new restaurant in the system if the restaurant does not already exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "restaurant created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "restaurant already exists", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/create-restaurant")
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.ok().build();
    }

    @Secured({"CUSTOMER"})
    @GetMapping("/all-restaurants")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants(pageNumber, pageSize));
    }

    @Secured({"OWNER"})
    @PostMapping("/contract-employee")
    public ResponseEntity<String> contractEmployee(@RequestBody @Valid RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurantEmployeeHandler.contractEmployee(restaurantEmployeeRequestDto);
        return ResponseEntity.ok("Employee contracted successfully");
    }

}
