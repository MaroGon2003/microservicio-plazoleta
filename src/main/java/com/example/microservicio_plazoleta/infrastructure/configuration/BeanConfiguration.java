package com.example.microservicio_plazoleta.infrastructure.configuration;

import com.example.microservicio_plazoleta.domain.api.*;
import com.example.microservicio_plazoleta.domain.spi.*;
import com.example.microservicio_plazoleta.domain.useCase.*;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.ITraceabilityFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.ITwilioFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.IUserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl.TraceabilityFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl.TwilioFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.client.impl.UserFeignClient;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.ITraceabilityDtoMapper;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.ITwilioRequestMapper;
import com.example.microservicio_plazoleta.infrastructure.input.feign.mapper.IUserResponseMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter.*;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.*;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.*;
import com.example.microservicio_plazoleta.infrastructure.out.verification.adapter.VerificationCodeAdapter;
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
    private final IUserResponseMapper userResponseMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;
    private final ITraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityDtoMapper traceabilityDtoMapper;
    private final ITwilioFeignClient twilioFeignClient;
    private final ITwilioRequestMapper twilioRequestMapper;
    private final IVerificationCodeRepository verificationCodeRepository;
    private final IVerificationCodeEntityMapper verificationCodeEntityMapper;



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
        return new UserFeignClient(userFeignClient, userResponseMapper);
    }

    @Bean
    public ITraceabilityFeignClientPort traceabilityFeignClientPort(){
        return new TraceabilityFeignClient(traceabilityFeignClient, traceabilityDtoMapper);
    }

    @Bean
    public ITwilioFeignClientPort twilioFeignClientPort(){
        return new TwilioFeignClient(twilioFeignClient,twilioRequestMapper);
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(restaurantPersistencePort(), orderPersistencePort(), dishToOrderPersistencePort(), dishPersistencePort(), restauranEmployeePersistencePort(),userFeignServicePort(), traceabilityFeignClientPort(),twilioFeignClientPort(), verificationCodePort(), verificationCodePersistencePort());
    }

    @Bean
    public IDishToOrderPersistencePort dishToOrderPersistencePort(){
        return new DishToOrderJpaAdapter(dishToOrderRepository, dishToOrderEntityMapper, orderRepository);
    }

    @Bean
    public IRestaurandEnployeePersistencePort restauranEmployeePersistencePort(){
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository, restaurantRepository, restaurantEmployeeEntityMapper);
    }

    @Bean
    public IRestaurandEnployeeServicePort restaurandEnployeeServicePort(){
        return new RestaurantEmployeeUseCase(restauranEmployeePersistencePort(), userFeignServicePort());
    }

    @Bean
    public IVerificationCodePort verificationCodePort(){
        return new VerificationCodeAdapter();
    }

    @Bean
    public IVerificationCodePersistencePort verificationCodePersistencePort(){
        return new VerificationCodeJpaAdapter(verificationCodeRepository, verificationCodeEntityMapper);
    }

    @Bean
    public ITraceabilityServicePort traceabilityServicePort(){
        return new TraceabilityUseCase(orderPersistencePort(), traceabilityFeignClientPort());
    }

}
