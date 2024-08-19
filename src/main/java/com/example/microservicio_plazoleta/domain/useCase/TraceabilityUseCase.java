package com.example.microservicio_plazoleta.domain.useCase;

import com.example.microservicio_plazoleta.domain.api.ITraceabilityFeignClientPort;
import com.example.microservicio_plazoleta.domain.api.ITraceabilityServicePort;
import com.example.microservicio_plazoleta.domain.exception.CustomerWithoutOrdersException;
import com.example.microservicio_plazoleta.domain.model.OrderModel;
import com.example.microservicio_plazoleta.domain.model.TraceabilityModel;
import com.example.microservicio_plazoleta.domain.spi.IOrderPersistencePort;
import com.example.microservicio_plazoleta.domain.utils.MessageConstants;

import java.util.List;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final ITraceabilityFeignClientPort traceabilityFeignClientPort;

    public TraceabilityUseCase(IOrderPersistencePort orderPersistencePort, ITraceabilityFeignClientPort traceabilityFeignClientPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.traceabilityFeignClientPort = traceabilityFeignClientPort;
    }

    @Override
    public List<TraceabilityModel> getHistory() {
        Long customerId = orderPersistencePort.getAuthenticatedUserId();
        List<OrderModel> ordersDb = orderPersistencePort.findOrderByCustomerId(customerId);
        if (ordersDb.isEmpty()) {
            throw new CustomerWithoutOrdersException(MessageConstants.CUSTOMER_WITHOUT_ORDERS);
        }
        return traceabilityFeignClientPort.getTraceability(
                customerId
        );
    }

}
