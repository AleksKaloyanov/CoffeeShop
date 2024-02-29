package org.example.coffeeshop.service;

import org.example.coffeeshop.model.service.OrderServiceModel;
import org.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrdersOrderByDescending();
}
