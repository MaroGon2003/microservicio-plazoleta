package com.example.microservicio_plazoleta.domain.spi;

import com.example.microservicio_plazoleta.domain.model.CategoryModel;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;

public interface IDishPersistencePort {

    void saveDish(DishModel dish);

    RestaurantModel getRestaurantById(Long id);

    CategoryModel getCategoryById(Long id);

    DishModel getDishById(Long id);

    DishModel updateDish(Long id, DishModel dish);

    void updateActiveDish(Long id, DishModel dish);
}
