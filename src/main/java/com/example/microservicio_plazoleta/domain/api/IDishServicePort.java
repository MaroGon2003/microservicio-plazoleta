package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.DishModel;

public interface IDishServicePort {

    void saveDish(DishModel dish);

    DishModel updateDish(Long id, Long ownerId, DishModel dish);

    DishModel getDishById(Long id);

    void updateActiveDish(Long id, Long ownerId, DishModel dish);

}
