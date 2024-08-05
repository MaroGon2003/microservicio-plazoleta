package com.example.microservicio_plazoleta.infrastructure.out.jpa.adapter;

import com.example.microservicio_plazoleta.domain.model.CategoryModel;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.ICategoryRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.example.microservicio_plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public void saveDish(DishModel dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantEntityMapper.toModel(restaurantRepository.findById(id).orElse(null));
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        return categoryEntityMapper.toCategoryModel(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishEntityMapper.toDishModel(dishRepository.findById(id).orElse(null));
    }

    @Override
    public DishModel updateDish(Long id, DishModel dish) {
        DishEntity dishEntity = getDishToUpdate(id);
        dishEntity.setDescription(dish.getDescription());
        dishEntity.setPrice(dish.getPrice());
        return dishEntityMapper.toDishModel(dishRepository.save(dishEntity));
    }

    private DishEntity getDishToUpdate(Long id) {

        DishEntity dishEntity = dishRepository.findById(id).orElse(null);

        if (dishEntity == null) {
            throw  new NullPointerException();
        }

        return dishEntity;

    }
}
