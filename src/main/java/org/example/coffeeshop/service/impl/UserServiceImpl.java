package org.example.coffeeshop.service.impl;

import org.example.coffeeshop.model.entity.UserEntity;
import org.example.coffeeshop.model.service.UserServiceModel;
import org.example.coffeeshop.model.view.UserViewModel;
import org.example.coffeeshop.repository.UserRepository;
import org.example.coffeeshop.service.UserService;
import org.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        return modelMapper
                .map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id)
                .setUsername(username);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc() {
        return userRepository
                .findAllByOrderCountDesc()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = new UserViewModel();
                    userViewModel.setUsername(user.getUsername())
                            .setCountOfOrders(user.getOrders().size());
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }
}
