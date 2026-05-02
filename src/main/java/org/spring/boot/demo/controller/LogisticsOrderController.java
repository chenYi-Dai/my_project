package org.spring.boot.demo.controller;

import org.spring.boot.demo.entity.LogisticsOrder;
import org.spring.boot.demo.service.LogisticsOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistics/orders")
public class LogisticsOrderController {

    private final LogisticsOrderService logisticsOrderService;

    public LogisticsOrderController(LogisticsOrderService logisticsOrderService) {
        this.logisticsOrderService = logisticsOrderService;
    }

    @PostMapping
    public LogisticsOrder create(@RequestBody LogisticsOrder order) {
        return logisticsOrderService.create(order);
    }

    @GetMapping
    public List<LogisticsOrder> list() {
        return logisticsOrderService.list();
    }

    @PutMapping("/{orderNo}/status")
    public LogisticsOrder updateStatus(@PathVariable("orderNo") String orderNo, @RequestParam("status") String status) {
        return logisticsOrderService.updateStatus(orderNo, status);
    }
}
