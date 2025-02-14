package com.az.edadi.user.service;

import com.az.edadi.user.model.request.RegisterUserRequest;
import com.az.edadi.user.model.request.UpdateUserEducationInfo;
import com.az.edadi.user.model.request.UpdateUserPersonalInfoRequest;
import org.hibernate.sql.Update;

public interface UserService {
    void registerUser(RegisterUserRequest request);
    void updateEducationalDegree(UpdateUserEducationInfo request);
    void updatePersonalInfo(UpdateUserPersonalInfoRequest request);
}
