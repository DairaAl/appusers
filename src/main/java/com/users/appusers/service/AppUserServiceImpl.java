package com.users.appusers.service;

import com.users.appusers.domain.AppUser;
import com.users.appusers.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service("AppUserService")
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    public void deleteUser(String userId) {
        Assert.notNull(userId, "user id must not be null");
        appUserRepository.deleteById(userId);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser insert(AppUser user) {
        return appUserRepository.insert(user);
    }

    public AppUser findById(String userId) {
        Assert.notNull(userId, "user id must not be null");
        Optional user = appUserRepository.findById(userId);
        return user.isPresent() ? (AppUser) user.get() : null;
    }
}
