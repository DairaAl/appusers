package com.users.appusers.service;


import com.users.appusers.domain.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> findAll();

    AppUser insert(AppUser user);

    AppUser findById(String userId);

    void deleteUser(String userId);
}
