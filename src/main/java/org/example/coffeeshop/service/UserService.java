package org.example.coffeeshop.service;

import org.example.coffeeshop.model.entity.UserEntity;
import org.example.coffeeshop.model.service.UserServiceModel;
import org.example.coffeeshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);

    List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc();
}
