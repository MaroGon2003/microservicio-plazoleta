package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IDishServicePort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.domain.model.CategoryModel;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.utils.ErrorMessages;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserFeignServicePort userFeignServicePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IUserFeignServicePort userFeignServicePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.userFeignServicePort = userFeignServicePort;
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
    public DishModel updateDish(Long id, Long ownerId, DishModel dish) {

        if(!userFeignServicePort.validateOwner(ownerId)){
            throw new UserNotOwnerException(ErrorMessages.USER_NOT_OWNER);
        }

        if (dish == null) {
            throw new NullPointerException(ErrorMessages.DISH_IS_NULL);
        }
        DishModel dishModel = dishPersistencePort.getDishById(id);
        if (dishModel == null) {
            throw new DishNotFoundException(ErrorMessages.DISH_NOT_FOUND);
        }

        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(dishModel.getRestaurantId());
        if (restaurant == null) {
            throw new RestaurantNotFoundException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }

        if (!restaurant.getIdOwner().equals(ownerId)) {
            throw new OwnerDishUpdateException(ErrorMessages.OWNER_DISH_UPDATE);
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

    @Override
    public void updateActiveDish(Long id, Long ownerId, DishModel dish) {

        if(!userFeignServicePort.validateOwner(ownerId)){
            throw new UserNotOwnerException(ErrorMessages.USER_NOT_OWNER);
        }

        DishModel dishModel = dishPersistencePort.getDishById(id);

        if (dishModel == null) {
            throw new DishNotFoundException(ErrorMessages.DISH_NOT_FOUND);
        }

        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(dishModel.getRestaurantId());
        if (restaurant == null) {
            throw new RestaurantNotFoundException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }

        if (!restaurant.getIdOwner().equals(ownerId)) {
            throw new OwnerDishUpdateException(ErrorMessages.OWNER_DISH_UPDATE);
        }

        dishModel.setActive(dish.isActive());
        dishPersistencePort.updateActiveDish(id, dishModel);
    }

    @Override
    public List<DishModel> getAllDishes(Long restaurantId, int pageNumber, int pageSize, Long categoryId) {

        List<DishModel> dishesList = dishPersistencePort.getAllDishes(restaurantId, pageNumber, pageSize, categoryId);

        if (dishesList.isEmpty()) {
            throw new NullPointerException(ErrorMessages.DISH_LIST_IS_EMPTY);
        }

        return dishesList;

    }
}
