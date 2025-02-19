package com.az.edadi.user.service.impl;

import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import com.az.edadi.user.adapter.UserAdapter;
import com.az.edadi.user.model.RegisterUserRequest;
import com.az.edadi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    public void registerUser(RegisterUserRequest request) {
        User user = new User();
        userAdapter.map(user, request);
        userRepository.save(user);
    }
}
