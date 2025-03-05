package com.example.microservicio_plazoleta.application.handler.impl;

import com.example.microservicio_plazoleta.application.dto.request.OrderRequestDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderRankingDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderResponseDto;
import com.example.microservicio_plazoleta.application.dto.response.OrderTimeResponseDto;
import com.example.microservicio_plazoleta.application.handler.IOrderHandler;
import com.example.microservicio_plazoleta.application.mapper.IDishToOrderRequestMapper;
import com.example.microservicio_plazoleta.application.mapper.IDishToOrderResponseMapper;
import com.example.microservicio_plazoleta.application.mapper.IOrderTimeResponseMapper;
import com.example.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.example.microservicio_plazoleta.domain.model.DishToOrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IDishToOrderRequestMapper dishToOrderRequestMapper;
    private final IDishToOrderResponseMapper dishToOrderResponseMapper;
    private final IOrderTimeResponseMapper orderTimeResponseMapper;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        orderServicePort.saveOrder(orderRequestDto.getRestaurantId(), dishToOrderRequestMapper.toDishToOrderModelList(orderRequestDto.getDishes()));
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByStatus(int page, int size, Long restaurantId, String status) {
        List<DishToOrderModel> dishToOrderModelList= orderServicePort.getAllOrdersByStatus(page, size, restaurantId, status);
        return dishToOrderResponseMapper.toOrderResponseList(dishToOrderModelList);
    }

    @Override
    public void assignEmployee(Long id) {
        orderServicePort.assignEmployee(id);
    }

    @Override
    public void readyOrder(Long id) {
        orderServicePort.readyOrder(id);
    }

    @Override
    public void deliveryOrder(Long id, int pin) {
        orderServicePort.deliveryOrder(id, pin);
    }

    @Override
    public void cancelOrder(Long id) {
        orderServicePort.cancelOrder(id);
    }

    @Override
    public List<OrderTimeResponseDto> showOrderPeriod() {
        return orderTimeResponseMapper.toResponseList(orderServicePort.showOrderPeriod());
    }

    @Override
    public List<OrderRankingDto> showOrderRanking() {
        List<Object[]> responseList = orderServicePort.showOrderRanking();

        List<OrderRankingDto> rankingList = new ArrayList<>();
        for (Object[] obj :
                responseList) {
            String formattedDuration = formatDuration((Double) obj[1]);
            rankingList.add(new OrderRankingDto((Long) obj[0], formattedDuration));
        }

        return rankingList;
    }

    private String formatDuration(double seconds) {
        Duration duration = Duration.ofSeconds((long) seconds);
        long hour = duration.toHours();
        long min = duration.toMinutes() % 60;
        long sec = duration.getSeconds() % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}
