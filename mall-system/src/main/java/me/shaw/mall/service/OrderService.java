package me.shaw.mall.service;

import me.shaw.mall.model.Order;

public interface OrderService {

    Order createOrder(int transaction, String status);

    void cancelOrder(Long OrderId);
}
