package com.tejasnirmal.journalApp.controller;


import com.tejasnirmal.journalApp.model.JournalEntry;
import com.tejasnirmal.journalApp.model.User;
import com.tejasnirmal.journalApp.repository.UserRepository;
import com.tejasnirmal.journalApp.service.JournalEntryService;
import com.tejasnirmal.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private UserRepository userRepository;



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userservice.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userservice.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}















