package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.*;
import com.example.microservicio_plazoleta.domain.exception.*;
import com.example.microservicio_plazoleta.domain.model.*;
import com.example.microservicio_plazoleta.domain.spi.*;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderUseCase implements IOrderServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IDishToOrderPersistencePort dishToOrderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurandEnployeePersistencePort restauranEmployeePersistencePort;
    private final IUserFeignServicePort userFeignServicePort;
    private final ITraceabilityFeignClientPort traceabilityFeignClientPort;
    private final ITwilioFeignClientPort twilioFeignClientPort;
    private final IVerificationCodePort verificationCodePort;
    private final IVerificationCodePersistencePort verificationCodePersistencePort;

    public OrderUseCase(IRestaurantPersistencePort restaurantPersistencePort, IOrderPersistencePort orderPersistencePort, IDishToOrderPersistencePort dishToOrderPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurandEnployeePersistencePort restauranEmployeePersistencePort, IUserFeignServicePort userFeignServicePort, ITraceabilityFeignClientPort traceabilityFeignClientPort, ITwilioFeignClientPort twilioFeignClientPort, IVerificationCodePort verificationCodePort, IVerificationCodePersistencePort verificationCodePersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.dishToOrderPersistencePort = dishToOrderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restauranEmployeePersistencePort = restauranEmployeePersistencePort;
        this.userFeignServicePort = userFeignServicePort;
        this.traceabilityFeignClientPort = traceabilityFeignClientPort;
        this.twilioFeignClientPort = twilioFeignClientPort;
        this.verificationCodePort = verificationCodePort;
        this.verificationCodePersistencePort = verificationCodePersistencePort;
    }

    @Override
    public void saveOrder(Long restaurantId, List<DishToOrderModel> dishes) {

        if (restaurantId == null) {
            throw new NullPointerException();
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
            throw new NullPointerException();
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

        if (order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS) ||
                order.getStatus().equalsIgnoreCase(MessageConstants.DELIVERED_STATUS)) {
            throw new OrderAlreadyCanceledOrDeliveredException(MessageConstants.ORDER_ALREADY_CANCELED_OR_DELIVERED);
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

    @Override
    public void readyOrder(Long id) {

        OrderModel order = orderPersistencePort.getOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }

        if (order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS) ||
                order.getStatus().equalsIgnoreCase(MessageConstants.DELIVERED_STATUS)) {
            throw new OrderAlreadyCanceledOrDeliveredException(MessageConstants.ORDER_ALREADY_CANCELED_OR_DELIVERED);
        }

        Long employeeId = orderPersistencePort.getAuthenticatedUserId();

        if (!restauranEmployeePersistencePort.isEmployeeContracted(employeeId)) {
            throw new UserNotIsEmployeeException(MessageConstants.USER_NOT_IS_EMPLOYEE);
        }

        if (!Objects.equals(order.getEmployeeId(), employeeId)) {
            throw new OrderNotBelongEmployeeException(MessageConstants.ORDER_NOT_BELONG_EMPLOYEE);
        }

        UserModel customer = userFeignServicePort.getUserById(order.getCustomerId());

        int pin = verificationCodePort.generateVerificationCode();

        VerificationCodeModel verificationCodeModel = new VerificationCodeModel();
        verificationCodeModel.setPin(pin);
        verificationCodeModel.setIdOrder(order.getId());

        twilioFeignClientPort.sendMessage(new TwilioModel(MessageConstants.ORDER_READY_NOTIFICATION + String.valueOf(pin),customer.getPhone()));

        verificationCodePersistencePort.saveVerificationCode(verificationCodeModel);

        saveTraceability(order, MessageConstants.READY_STATUS);
        order.setStatus(MessageConstants.READY_STATUS);

        orderPersistencePort.updateOrder(order);

    }

    @Override
    public void deliveryOrder(Long idOrder, int pin) {

        OrderModel order = orderPersistencePort.getOrderById(idOrder);

        if (order == null) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }

        if (order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS)) {
            throw new OrderAlreadyCanceledException(MessageConstants.ORDER_ALREADY_CANCELED);
        }

        if (!order.getStatus().equalsIgnoreCase(MessageConstants.READY_STATUS)) {
            throw new OrderNotReadyException(MessageConstants.ORDER_NOT_READY);
        }

        Long employeeId = orderPersistencePort.getAuthenticatedUserId();

        if (!restauranEmployeePersistencePort.isEmployeeContracted(employeeId)) {
            throw new UserNotIsEmployeeException(MessageConstants.USER_NOT_IS_EMPLOYEE);
        }

        if (!Objects.equals(order.getEmployeeId(), employeeId)) {
            throw new OrderNotBelongEmployeeException(MessageConstants.ORDER_NOT_BELONG_EMPLOYEE);
        }

        if (order.getStatus().equalsIgnoreCase(MessageConstants.CANCELED_STATUS) ||
                order.getStatus().equalsIgnoreCase(MessageConstants.DELIVERED_STATUS)) {
            throw new OrderAlreadyCanceledOrDeliveredException(MessageConstants.ORDER_ALREADY_CANCELED_OR_DELIVERED);
        }

        if (pin == 0) {
            throw new PinIsRequiredException(MessageConstants.PIN_IS_REQUIRED);
        }

        VerificationCodeModel verificationCodeModel = verificationCodePersistencePort.getVerificationCode(idOrder);

        if (verificationCodeModel == null) {
            throw new VerificationCodeNotFoundException(MessageConstants.VERIFICATION_CODE_NOT_FOUND);
        }
        if (verificationCodeModel.getPin() != pin) {
            throw new VerificationCodeNotMatchException(MessageConstants.VERIFICATION_CODE_NOT_MATCH);
        }

        saveTraceability(order, MessageConstants.DELIVERED_STATUS);
        order.setStatus(MessageConstants.DELIVERED_STATUS);
        order.setEndTime(LocalDateTime.now());

        orderPersistencePort.updateOrder(order);
    }

    @Override
    public void cancelOrder(Long id) {

            OrderModel order = orderPersistencePort.getOrderById(id);

            if (order == null) {
                throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
            }

            if (!order.getStatus().equalsIgnoreCase(MessageConstants.PENDING_STATUS)) {
                throw new OrderCannotBeCanceledException(MessageConstants.ORDER_CANNOT_BE_CANCELED);
            }

            Long customerId = orderPersistencePort.getAuthenticatedUserId();

            if (!Objects.equals(order.getCustomerId(), customerId)) {
                throw new OrderNotBelongCustomerException(MessageConstants.ORDER_NOT_BELONG_CUSTOMER);
            }

            order.setStatus(MessageConstants.CANCELED_STATUS);
            order.setEndTime(LocalDateTime.now());

            orderPersistencePort.updateOrder(order);
    }

    @Override
    public List<OrderModel> showOrderPeriod() {
        Long ownerId = orderPersistencePort.getAuthenticatedUserId();

        List<OrderModel> orderModelList = orderPersistencePort.showOrderPeriod(ownerId);
        if (orderModelList.isEmpty()) {
            throw new OrderNotFoundException(MessageConstants.ORDER_NOT_FOUND);
        }

        return orderModelList;
    }

    @Override
    public List<Object[]> showOrderRanking() {

        Long ownerId = orderPersistencePort.getAuthenticatedUserId();

        RestaurantModel restaurant = restaurantPersistencePort.getRestaurantByOwnerId(ownerId);

        if (restaurant == null) {
            throw new RestaurantNotFoundException(MessageConstants.RESTAURANT_NOT_FOUND);
        }

        return orderPersistencePort.showOrderRanking(restaurant.getId());

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
        traceabilityModel.setDate(String.valueOf(LocalDateTime.now()));
        traceabilityModel.setPreviousStatus(order.getStatus());
        traceabilityModel.setNewStatus(newStatus);
        traceabilityModel.setEmployeeId(order.getEmployeeId());
        traceabilityModel.setEmployeeEmail(employee.getEmail());

        traceabilityFeignClientPort.saveTraceability(traceabilityModel);

    }

}
