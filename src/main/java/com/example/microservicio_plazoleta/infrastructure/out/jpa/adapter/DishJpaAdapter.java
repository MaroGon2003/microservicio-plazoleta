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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Override
    public void updateActiveDish(Long id, DishModel dish) {
        DishEntity dishEntity = getDishToUpdate(id);
        dishEntity.setActive(dish.isActive());
        dishRepository.save(dishEntity);
    }

    @Override
    public List<DishModel> getAllDishes(Long restaurantId, int pageNumber, int pageSize, Long categoryId) {
        Pageable dishPageable = PageRequest.of(pageNumber, pageSize);
        Page<DishEntity> dishEntityPage = dishRepository.findByIdRestaurantIdAndIdCategoryId(restaurantId, categoryId, dishPageable);
        return dishEntityMapper.toDishModelList(dishEntityPage.getContent());
    }

    @Override
    public DishModel getDishByRestaurantIdAndDishId(Long restaurantId, Long dishId) {
        return dishEntityMapper.toDishModel(dishRepository.findByIdAndIdRestaurantId(dishId, restaurantId).orElse(null));
    }


    private DishEntity getDishToUpdate(Long id) {

        DishEntity dishEntity = dishRepository.findById(id).orElse(null);

        if (dishEntity == null) {
            throw  new NullPointerException();
        }

        return dishEntity;

    }
}
