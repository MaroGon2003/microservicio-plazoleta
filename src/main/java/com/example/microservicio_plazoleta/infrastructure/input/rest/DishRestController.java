package com.example.microservicio_plazoleta.infrastructure.input.rest;

import com.example.microservicio_plazoleta.application.dto.request.DishRequestDto;
import com.example.microservicio_plazoleta.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "restaurant created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveDish(@RequestBody @Valid DishRequestDto dishRequestDto) {
        dishHandler.saveDish(dishRequestDto);
        return ResponseEntity.ok().build();
    }

}
