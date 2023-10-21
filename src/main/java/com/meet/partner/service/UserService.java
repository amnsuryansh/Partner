package com.meet.partner.service;

import com.meet.partner.model.Country;
import com.meet.partner.model.User;
import com.meet.partner.pojo.request.CreateUserRequest;
import com.meet.partner.pojo.request.GetUserRequest;
import com.meet.partner.pojo.request.UpdateUserRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface UserService {

    User createUser(CreateUserRequest createUserRequest, HttpServletRequest servletRequest);

    User updateUserById(UpdateUserRequest updateUserRequest, HttpServletRequest servletRequest);

    User updateUserByEmail(UpdateUserRequest updateUserRequest, HttpServletRequest servletRequest);

    User getByUserId (Long userId);

    Page<User> getAllUserPagination(GetUserRequest getUserRequest);

}
