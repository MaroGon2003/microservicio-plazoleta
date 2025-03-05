package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.RestaurantEmployeeModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurandEnployeePersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.example.microservicio_plazoleta.infrastructure.utils.JwtAuthorizationExtractor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class RestaurantEmployeeJpaAdapter implements IRestaurandEnployeePersistencePort {

    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;


    @Override
    public void contractEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        restaurantEmployeeRepository.save(restaurantEmployeeEntityMapper.toEntity(restaurantEmployeeModel));
    }

    @Override
    public boolean isEmployeeContracted(Long employeeId) {
        return restaurantEmployeeRepository.existsByEmployeeId(employeeId);
    }

    @Override
    public boolean existRestaurant(Long restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }

    @Override
    public String getAuthenticatedRole() {
        return JwtAuthorizationExtractor.extractUserRol();
    }
}
