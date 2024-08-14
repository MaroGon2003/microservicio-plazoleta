package com.example.microservicio_plazoleta.infrastructure.configuration;

import com.example.microservicio_plazoleta.domain.api.*;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IDishToOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.domain.useCase.DishUseCase;
import com.example.microservicio_plazoleta.domain.useCase.OrderUseCase;
import com.example.microservicio_plazoleta.domain.useCase.RestaurantUseCase;
import com.example.microservicio_plazoleta.infrastructure.input.feign.IUserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.impl.UserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.DishToOrderJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.*;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IUserFeignClient userFeignClient;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IDishToOrderRepository dishToOrderRepository;
    private final IDishToOrderEntityMapper dishToOrderEntityMapper;


    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(), userFeignServicePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository,dishEntityMapper,restaurantEntityMapper,restaurantRepository,categoryEntityMapper,categoryRepository);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), userFeignServicePort());
    }

    @Bean
    public IUserFeignServicePort userFeignServicePort(){
        return new UserFeignClient(userFeignClient);
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(restaurantPersistencePort(), orderPersistencePort(), dishToOrderPersistencePort(), dishPersistencePort());
    }

    @Bean
    public IDishToOrderPersistencePort dishToOrderPersistencePort(){
        return new DishToOrderJpaAdapter(dishToOrderRepository, dishToOrderEntityMapper, orderRepository);
    }

}
