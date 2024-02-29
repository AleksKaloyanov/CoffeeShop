package org.example.coffeeshop.service.impl;

import org.example.coffeeshop.model.entity.OrderEntity;
import org.example.coffeeshop.model.service.OrderServiceModel;
import org.example.coffeeshop.model.view.OrderViewModel;
import org.example.coffeeshop.repository.OrderRepository;
import org.example.coffeeshop.service.CategoryService;
import org.example.coffeeshop.service.OrderService;
import org.example.coffeeshop.service.UserService;
import org.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ModelMapper modelMapper,
                            CurrentUser currentUser,
                            UserService userService,
                            CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity order = modelMapper.map(orderServiceModel, OrderEntity.class);
        order.setEmployee(userService.findById(currentUser.getId()))
                .setCategory(categoryService.findByCategoryNameEnum(orderServiceModel.getCategory()));


        return modelMapper.map(orderRepository.save(order), OrderServiceModel.class);
    }

    @Override
    public List<OrderViewModel> findAllOrdersOrderByDescending() {
        return orderRepository
                .findAllOrderByPriceDesc()
                .stream()
                .map(order -> modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }
}
