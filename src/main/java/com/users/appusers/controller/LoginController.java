package com.users.appusers.controller;

import com.users.appusers.domain.AppUser;
import com.users.appusers.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AppUserService appUserService;

    @PostMapping("/users")
    public ResponseEntity<AppUser> createTutorial(@RequestBody AppUser user) {
        try {
            AppUser _user = appUserService.insert(new AppUser(user.getUsername(), user.getMail(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            //AppUser user = new AppUser("palmanza", "alg", "123");
            //appUserService.insert(user);

            List<AppUser> appUsers = new ArrayList<AppUser>();

            if (title == null)
                appUsers = appUserService.findAll();
            else
                appUsers = appUserService.findAll();

            if (appUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(appUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getTutorialById(@PathVariable("id") String id) {
        AppUser userData = appUserService.findById(id);

        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AppUser> updateTutorial(@PathVariable("id") String id, @RequestBody AppUser user) {
        AppUser userData = appUserService.findById(id);

        if (userData != null) {
            userData.setMail(user.getMail());
            userData.setPassword(user.getPassword());
            userData.setUsername(user.getUsername());
            return new ResponseEntity<>(appUserService.insert(userData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            appUserService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
