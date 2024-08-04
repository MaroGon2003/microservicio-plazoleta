package com.example.microservicio_plazoleta.domain.api;

import com.example.microservicio_plazoleta.domain.model.DishModel;

public interface IDishServicePort {

    void saveDish(DishModel dish);

}
