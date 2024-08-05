package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IDishServicePort;
import com.example.microservicio_plazoleta.domain.exception.CategoryNotFoundException;
import com.example.microservicio_plazoleta.domain.exception.DishNotFoundException;
import com.example.microservicio_plazoleta.domain.exception.RestaurantNotFoundException;
import com.example.microservicio_plazoleta.domain.model.CategoryModel;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.utils.ErrorMessages;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dish) {

        if (dish == null) {
            throw new NullPointerException(ErrorMessages.DISH_IS_NULL);
        }

        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(dish.getRestaurantId());

        if (restaurant == null) {
            throw new RestaurantNotFoundException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }

        CategoryModel category = dishPersistencePort.getCategoryById(dish.getCategoryId());

        if (category == null) {
            throw new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public DishModel updateDish(Long id, DishModel dish) {

        if (dish == null) {
            throw new NullPointerException(ErrorMessages.DISH_IS_NULL);
        }
        DishModel dishModel = dishPersistencePort.getDishById(id);
        if (dishModel == null) {
            throw new DishNotFoundException(ErrorMessages.DISH_NOT_FOUND);
        }
        dishModel.setDescription(dish.getDescription());
        dishModel.setPrice(dish.getPrice());

        return dishPersistencePort.updateDish(id, dishModel);

    }

    @Override
    public DishModel getDishById(Long id) {
        DishModel dish = dishPersistencePort.getDishById(id);
        if(dish == null) {
            throw new DishNotFoundException(ErrorMessages.DISH_NOT_FOUND);
        }
        return dish;
    }
}
