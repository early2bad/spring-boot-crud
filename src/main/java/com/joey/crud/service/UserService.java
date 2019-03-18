package com.joey.crud.service;


import com.joey.crud.entity.User;


public interface UserService extends BaseService<User> {

    User findByName(String name);
}
