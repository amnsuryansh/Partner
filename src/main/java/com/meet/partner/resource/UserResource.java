package com.meet.partner.resource;

import com.meet.partner.pojo.request.CreateUserRequest;
import com.meet.partner.pojo.request.GetUserRequest;
import com.meet.partner.pojo.request.UpdateUserRequest;
import com.meet.partner.service.ResponseMessageService;
import com.meet.partner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/partner/app")
public class UserResource {

    @Autowired
    private ResponseMessageService responseMessageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity createUser(@Validated @RequestBody CreateUserRequest createUserRequest, HttpServletRequest httpServletRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(userService.createUser(createUserRequest, httpServletRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/update-user/email", method = RequestMethod.POST)
    public ResponseEntity updateUserByEmail(@Validated @RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest httpServletRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(userService.updateUserByEmail(updateUserRequest, httpServletRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/update-user/id", method = RequestMethod.POST)
    public ResponseEntity updateUserById(@Validated @RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest httpServletRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(userService.updateUserById(updateUserRequest, httpServletRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/get-user/all", method = RequestMethod.POST)
    public ResponseEntity getAllUsers(@Validated @RequestBody GetUserRequest getUserRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(userService.getAllUserPagination(getUserRequest),
                                null, HttpStatus.OK));
    }

}
