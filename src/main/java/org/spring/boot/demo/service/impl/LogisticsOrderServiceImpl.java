package org.spring.boot.demo.service.impl;

import org.spring.boot.demo.entity.LogisticsOrder;
import org.spring.boot.demo.service.LogisticsOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LogisticsOrderServiceImpl implements LogisticsOrderService {
    private static final Map<String, LogisticsOrder> STORE = new ConcurrentHashMap<String, LogisticsOrder>();

    @Override
    public LogisticsOrder create(LogisticsOrder order) {
        String orderNo = "LMS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        LocalDateTime now = LocalDateTime.now();
        order.setOrderNo(orderNo);
        order.setStatus("CREATED");
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        STORE.put(orderNo, order);
        return order;
    }

    @Override
    public List<LogisticsOrder> list() {
        return new ArrayList<LogisticsOrder>(STORE.values());
    }

    @Override
    public LogisticsOrder updateStatus(String orderNo, String status) {
        LogisticsOrder order = STORE.get(orderNo);
        if (order == null) {
            return null;
        }
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }
}
