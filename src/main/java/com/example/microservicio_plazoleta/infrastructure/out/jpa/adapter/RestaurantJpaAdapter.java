package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(RestaurantModel restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public boolean existsRestaurantByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantEntityMapper.toModel(restaurantRepository.findById(id).orElse(null));
    }
}
