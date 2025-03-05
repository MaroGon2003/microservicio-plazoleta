package com.example.microservicio_plazoleta.application.mapper;

import com.example.microservicio_plazoleta.application.dto.response.OrderDishResponseDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderResponseDto;
import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ofPattern;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishToOrderResponseMapper {

    default List<OrderResponseDto> toOrderResponseList(List<DishToOrderModel> orderDishModelList) {
        Map<OrderModel, List<DishToOrderModel>> orderDetailMap = new LinkedHashMap<>();

        for (DishToOrderModel orderDishModel : orderDishModelList) {
            OrderModel orderModel = orderDishModel.getOrderModel();
            List<DishToOrderModel> detailList = orderDetailMap.getOrDefault(orderModel, new ArrayList<>());
            detailList.add(orderDishModel);
            orderDetailMap.put(orderModel, detailList);
        }

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Map.Entry<OrderModel, List<DishToOrderModel>> entry : orderDetailMap.entrySet()) {
            OrderModel orderModel = entry.getKey();
            List<DishToOrderModel> detailList = entry.getValue();

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setStartTime(orderModel.getStartTime().format(ofPattern("yyyy-MM-dd HH:mm:ss")));
            orderResponseDto.setEndTime(orderModel.getEndTime() != null ? orderModel.getEndTime().format(ofPattern("yyyy-MM-dd HH:mm:ss")) : null);
            orderResponseDto.setStatus(orderModel.getStatus());
            orderResponseDto.setIdCustomer(orderModel.getCustomerId());
            orderResponseDto.setIdEmployee(orderModel.getEmployeeId());
            orderResponseDto.setRestaurantName(orderModel.getRestaurantModel().getName());

            List<OrderDishResponseDto> orderDishResponseDtoList = new ArrayList<>();
            for (DishToOrderModel dishToOrderModel : detailList) {
                OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();
                orderDishResponseDto.setDishName(dishToOrderModel.getDishModel().getName());
                orderDishResponseDto.setPrice(dishToOrderModel.getDishModel().getPrice());
                orderDishResponseDto.setAmount(dishToOrderModel.getAmount());

                orderDishResponseDtoList.add(orderDishResponseDto);
            }
            orderResponseDto.setDishes(orderDishResponseDtoList);

            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }

    @Mapping(target = "dishName", source = "dishModel.name")
    @Mapping(target = "price", source = "dishModel.price")
    @Mapping(target = "amount", source = "amount")
    OrderDishResponseDto toOrderDishResponse(DishToOrderModel dishToOrderModel);


}
