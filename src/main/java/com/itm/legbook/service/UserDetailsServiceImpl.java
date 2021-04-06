package com.itm.legbook.service;

import com.itm.legbook.com.itm.legbook.model.User;
import com.itm.legbook.repository.UserRepositroy;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepositroy userRepositroy;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepositroy.findByEmail(username); //we will use findbyEmail inplace of findByuName because we dont have username column in User table
        User user=userOptional.orElseThrow(()->new UsernameNotFoundException("No user found with Email: "+username));

        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isActivated(), true, true,
                true, getAuthorities("USER"));


    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
