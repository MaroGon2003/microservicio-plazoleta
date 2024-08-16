package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.example.microservicio_plazoleta.domain.api.ITraceabilityFeignClientPort;
import com.example.microservicio_plazoleta.domain.api.IUserFeignServicePort;
import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.domain.model.*;
import com.example.microservicio_plazoleta.domain.spi.*;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IDishToOrderPersistencePort dishToOrderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurandEnployeePersistencePort restauranEmployeePersistencePort;
    private final IUserFeignServicePort userFeignServicePort;
    private final ITraceabilityFeignClientPort traceabilityFeignClientPort;

    public OrderUseCase(IRestaurantPersistencePort restaurantPersistencePort, IOrderPersistencePort orderPersistencePort, IDishToOrderPersistencePort dishToOrderPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurandEnployeePersistencePort restauranEmployeePersistencePort, IUserFeignServicePort userFeignServicePort, ITraceabilityFeignClientPort traceabilityFeignClientPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.dishToOrderPersistencePort = dishToOrderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restauranEmployeePersistencePort = restauranEmployeePersistencePort;
        this.userFeignServicePort = userFeignServicePort;
        this.traceabilityFeignClientPort = traceabilityFeignClientPort;
    }

    @Override
    public void saveOrder(Long restaurantId, List<DishToOrderModel> dishes) {

        if (restaurantId == null) {
            throw new NullPointerException("restaurantId is required");
        }
        RestaurantModel restaurant = restaurantPersistencePort.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new RestaurantNotFoundException(MessageConstants.RESTAURANT_NOT_FOUND);
        }

        if (dishes.isEmpty()) {
            throw new DishesRequiredException(MessageConstants.DISHES_REQUIRED);
        }

        Long customerId = orderPersistencePort.getAuthenticatedUserId();

        if (customerId == null) {
            throw new NullPointerException("costumerId is required");
        }

        List<OrderModel> orders = orderPersistencePort.findOrderByCustomerIdAndRestaurantId(customerId, restaurantId);

        if (!orders.isEmpty()) {
            for (OrderModel order :
                    orders) {
                if (!(order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS) ||
                        order.getStatus().equalsIgnoreCase(MessageConstants.DELIVERED_STATUS))) {
                    throw new OrderAlreadyExistsException(MessageConstants.ORDER_ALREADY_EXISTS);
                }
            }
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setRestaurantModel(restaurant);
        orderModel.setCustomerId(customerId);
        orderModel.setStartTime(LocalDateTime.now());
        orderModel.setStatus(MessageConstants.PENDING_STATUS);

        Long orderId = orderPersistencePort.saveOrder(orderModel);

        saveDishToOrder(orderId, customerId, restaurantId , dishes);

    }

    @Override
    public List<DishToOrderModel> getAllOrdersByStatus(int page, int size, Long restaurantId , String status) {

        Long employeeId = orderPersistencePort.getAuthenticatedUserId();

        if (!restauranEmployeePersistencePort.isEmployeeContracted(employeeId)) {
            throw new UserNotIsEmployeeException(MessageConstants.USER_NOT_IS_EMPLOYEE);
        }

        List<DishToOrderModel> dishToOrderModelList = dishToOrderPersistencePort.getAllOrdersByStatus(page, size, restaurantId, status);

        if (dishToOrderModelList.isEmpty()) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }

        return dishToOrderModelList;
    }

    @Override
    public void assignEmployee(Long id) {

        OrderModel order = orderPersistencePort.getOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }
        Long employeeId = orderPersistencePort.getAuthenticatedUserId();

        if (!restauranEmployeePersistencePort.isEmployeeContracted(employeeId)) {
            throw new UserNotIsEmployeeException(MessageConstants.USER_NOT_IS_EMPLOYEE);
        }

        order.setEmployeeId(employeeId);
        saveTraceability(order, MessageConstants.PREPARING_STATUS);
        order.setStatus(MessageConstants.PREPARING_STATUS);

        orderPersistencePort.updateOrder(order);

    }

    private void saveDishToOrder(Long orderId, Long customerId,Long restaurantId, List<DishToOrderModel> dishes) {

        OrderModel order = orderPersistencePort.getOrderById(orderId);

        if(!dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerId(orderId, customerId)) {
            throw new OrderNotBelongCustomerException(MessageConstants.ORDER_NOT_BELONG_CUSTOMER);
        }
        if (!dishToOrderPersistencePort.existsOrderByOrderIdAndCustomerIdAndStatus(orderId, customerId)) {
            throw new OrderReceivesNoMoreDishesException(MessageConstants.ORDER_RECEIVES_NO_MORE_DISHES);
        }

         dishes = dishes.stream().peek(d -> {
            d.setOrderModel(order);
            DishModel dish = dishPersistencePort.getDishById(d.getDishModel().getId());
            if(dish == null) {
                throw new DishNotFoundException(MessageConstants.DISH_NOT_FOUND);
            }
            if (dishPersistencePort.getDishByRestaurantIdAndDishId(restaurantId, d.getDishModel().getId()) == null) {
                throw new DishNotBelongRestaurantException(MessageConstants.DISH_NOT_BELONG_RESTAURANT);
            }
         }).toList();

        dishToOrderPersistencePort.saveAll(dishes);

    }

    private void saveTraceability(OrderModel order,String newStatus) {

        UserModel customer = userFeignServicePort.getUserById(order.getCustomerId());
        UserModel employee = userFeignServicePort.getUserById(order.getEmployeeId());

        TraceabilityModel traceabilityModel = new TraceabilityModel();
        traceabilityModel.setOrderId(order.getId());
        traceabilityModel.setCustomerId(order.getCustomerId());
        traceabilityModel.setCustomerEmail(customer.getEmail());
        traceabilityModel.setPreviousStatus(order.getStatus());
        traceabilityModel.setNewStatus(newStatus);
        traceabilityModel.setEmployeeId(order.getEmployeeId());
        traceabilityModel.setEmployeeEmail(employee.getEmail());

        traceabilityFeignClientPort.saveTraceability(traceabilityModel);

    }
}
