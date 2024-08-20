package com.examportal.backend.controller;


import com.examportal.backend.dto.UserDto;
import com.examportal.backend.model.Users;
import com.examportal.backend.service.UserServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {


    @Autowired
    private UserServiceIntf userService;


    // creating a user
    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UserDto userDto) {
        try {
            System.out.println(userDto.toString());
            Users newUser = userService.createUser(userDto);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }


    //fetching user

    @GetMapping("/{username}")
    public ResponseEntity<Users> getUser(@PathVariable("username") String username) {

        Users fetchUser = userService.getUser(username);
        return new ResponseEntity<>(fetchUser, HttpStatus.ACCEPTED);
    }

    //deleting the user
    @DeleteMapping("/{username}")
    public ResponseEntity<String> delUser(@PathVariable("username") String username) {
        try {
            String res = userService.deleteUsers(username);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error occured while deleting the user", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Users> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {

        try {

            Users updatedUsers = userService.updateUser(username, userDto);

            return new ResponseEntity<>(updatedUsers, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}


