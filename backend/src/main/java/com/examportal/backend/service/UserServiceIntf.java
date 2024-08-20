package com.examportal.backend.service;

import com.examportal.backend.RoleName;
import com.examportal.backend.dto.UserDto;
import com.examportal.backend.model.UserRoles;
import com.examportal.backend.model.Users;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserServiceIntf {

    Users createUser(UserDto userDto) throws Exception;

    Users addRole(String username, RoleName roleName) throws Exception;

    Users getUser(String username);

    String deleteUsers(String username) throws  Exception;

    Users updateUser(String username,UserDto userDto) throws Exception;



}
