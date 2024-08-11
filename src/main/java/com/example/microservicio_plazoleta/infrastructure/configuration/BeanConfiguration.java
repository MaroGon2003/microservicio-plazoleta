package com.example.microservicio_plazoleta.infrastructure.configuration;

import com.example.microservicio_plazoleta.domain.api.IDishServicePort;
import com.example.microservicio_plazoleta.domain.api.IRestaurantServicePort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.spi.IRestaurantPersistencePort;
import com.example.microservicio_plazoleta.domain.useCase.DishUseCase;
import com.example.microservicio_plazoleta.domain.useCase.RestaurantUseCase;
import com.example.microservicio_plazoleta.infrastructure.input.feign.IUserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.impl.UserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.ICategoryRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
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

}
