package org.spring.boot.demo.service;

import org.spring.boot.demo.entity.LogisticsOrder;

import java.util.List;

public interface LogisticsOrderService {
    LogisticsOrder create(LogisticsOrder order);
    List<LogisticsOrder> list();
    LogisticsOrder updateStatus(String orderNo, String status);
}
