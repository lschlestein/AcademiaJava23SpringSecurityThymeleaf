package com.appregister.securtiyexample.service;

import com.appregister.securtiyexample.model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean checkEmail(String email);

}
