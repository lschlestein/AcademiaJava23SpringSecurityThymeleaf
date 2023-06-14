package com.appregister.securtiyexample.config;

import com.appregister.securtiyexample.model.UserDtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.appregister.securtiyexample.repository.UserRepository;
import com.appregister.securtiyexample.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDtls user = userRepo.findByEmail(email);

        if (user != null) {
            return new CustomUserDetails(user);
        }
        throw new UsernameNotFoundException("user not available");
    }
}
