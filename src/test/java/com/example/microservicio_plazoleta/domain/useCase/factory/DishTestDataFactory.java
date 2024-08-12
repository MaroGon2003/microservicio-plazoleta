package com.example.microservicio_plazoleta.domain.useCase.factory;

import com.example.microservicio_plazoleta.domain.model.CategoryModel;
import com.example.microservicio_plazoleta.domain.model.DishModel;

import java.util.List;

public class DishTestDataFactory {

    public static DishModel getDishWithSetters() {
        DishModel dishModel = new DishModel();
        dishModel.setId(1L);
        dishModel.setName("Hamburguesa");
        dishModel.setDescription("Hamburguesa de carne");
        dishModel.setPrice(10000);
        dishModel.setRestaurantId(1L);
        dishModel.setCategoryId(1L);
        dishModel.setImageUrl("https://www.mcdonalds.com");

        return dishModel;
    }

    public static CategoryModel getCategoryWithSetters() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(1L);
        categoryModel.setName("Comida rapida");
        categoryModel.setDescription("Comida rapida");

        return categoryModel;
    }

    public static List<DishModel> getDishesList() {
        return List.of(getDishWithSetters());
    }

}
