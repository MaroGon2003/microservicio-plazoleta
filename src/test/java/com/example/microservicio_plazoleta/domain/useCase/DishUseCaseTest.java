package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.domain.model.DishModel;
import com.example.microservicio_plazoleta.domain.model.RestaurantModel;
import com.example.microservicio_plazoleta.domain.spi.IDishPersistencePort;
import com.example.microservicio_plazoleta.domain.useCase.factory.DishTestDataFactory;
import com.example.microservicio_plazoleta.domain.useCase.factory.RestaurantTestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IUserFeignServicePort userFeignServicePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @Test
    void shouldSaveDish() {

        DishModel expect = DishTestDataFactory.getDishWithSetters();

        when(dishPersistencePort.getRestaurantById(anyLong())).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(dishPersistencePort.getCategoryById(anyLong())).thenReturn(DishTestDataFactory.getCategoryWithSetters());
        dishUseCase.saveDish(expect);

        verify(dishPersistencePort).saveDish(expect);

    }

    @Test
    void shouldThrowRestaurantNotFoundException() {

        DishModel dishModel = DishTestDataFactory.getDishWithSetters();

        when(dishPersistencePort.getRestaurantById(anyLong())).thenReturn(null);

        assertThrows(RestaurantNotFoundException.class, () -> dishUseCase.saveDish(dishModel));

    }

    @Test
    void shouldThrowCategoryNotFoundException() {

        DishModel dishModel = DishTestDataFactory.getDishWithSetters();

        when(dishPersistencePort.getRestaurantById(anyLong())).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(dishPersistencePort.getCategoryById(anyLong())).thenReturn(null);

        assertThrows(CategoryNotFoundException.class, () -> dishUseCase.saveDish(dishModel));

    }

    @Test
    void shouldGetDishById() {
        DishModel expect = DishTestDataFactory.getDishWithSetters();
        when(dishPersistencePort.getDishById(anyLong())).thenReturn(expect);
        dishUseCase.getDishById(1L);
        verify(dishPersistencePort).getDishById(anyLong());
    }

    @Test
    void shouldThrowDishNotFoundException() {
        assertThrows(DishNotFoundException.class, () -> dishUseCase.getDishById(100L));
    }

    @Test
    void shouldUpdateDish() {
        DishModel expect = DishTestDataFactory.getDishWithSetters();
        when(userFeignServicePort.validateOwner(anyLong())).thenReturn(true);
        when(dishPersistencePort.getDishById(anyLong())).thenReturn(expect);
        when(dishPersistencePort.getRestaurantById(anyLong())).thenReturn(RestaurantTestDataFactory.getRestaurantWithSetters());
        when(dishPersistencePort.updateDish(anyLong(), any(DishModel.class))).thenReturn(expect);

        dishUseCase.updateDish(1L,1L, expect);
        verify(dishPersistencePort).updateDish(1L, expect);
    }

    @Test
    void shouldThrowDishNotFoundExceptionUpdate() {
        DishModel expect = DishTestDataFactory.getDishWithSetters();
        when(userFeignServicePort.validateOwner(anyLong())).thenReturn(true);
        when(dishPersistencePort.getDishById(anyLong())).thenReturn(null);
        assertThrows(DishNotFoundException.class, () -> dishUseCase.updateDish(1L, 1L,expect));
    }

    @Test
    void shouldTrowNotFoundExceptionInUpdateDish() {
        DishModel nullDish = new DishModel();
        when(userFeignServicePort.validateOwner(anyLong())).thenReturn(true);

        assertThrows(DishNotFoundException.class, () -> dishUseCase.updateDish(1L, 1L, nullDish));
    }

    @Test
    void shouldThrowUserNotOwnerException() {
        DishModel expect = DishTestDataFactory.getDishWithSetters();
        when(userFeignServicePort.validateOwner(anyLong())).thenReturn(false);

        assertThrows(UserNotOwnerException.class, () -> dishUseCase.updateDish(1L, 1L, expect));
    }

    @Test
    void shouldThrowOwnerDishUpdateException() {
        DishModel dishModel = DishTestDataFactory.getDishWithSetters();
        RestaurantModel restaurantModel = RestaurantTestDataFactory.getRestaurantWithSetters();
        restaurantModel.setIdOwner(2L); // Set a different owner ID to trigger the exception

        when(userFeignServicePort.validateOwner(anyLong())).thenReturn(true);
        when(dishPersistencePort.getDishById(anyLong())).thenReturn(dishModel);
        when(dishPersistencePort.getRestaurantById(anyLong())).thenReturn(restaurantModel);

        assertThrows(OwnerDishUpdateException.class, () -> dishUseCase.updateDish(1L, 1L, dishModel));
    }

}
