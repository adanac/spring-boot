package com.feagle.learn.controller.rest;

import com.feagle.learn.domain.User;
import com.feagle.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by feagle on 2017/5/31.
 */
@RestController
public class ExportApi {
    @Autowired
    UserService userService;

    /**
     * Handle request to the default page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> viewHome() {
        return userService.findAllUsers();
    }
}
