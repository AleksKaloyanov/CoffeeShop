package org.example.coffeeshop.web;

import jakarta.validation.Valid;
import org.example.coffeeshop.model.binding.OrderAddBindingModel;
import org.example.coffeeshop.model.service.OrderServiceModel;
import org.example.coffeeshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel"
                            , bindingResult);

            return "redirect:add";
        }

        orderService.addOrder(modelMapper
                .map(orderAddBindingModel, OrderServiceModel.class));

        return "redirect:/";

    }


    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}
