package com.example.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springapp.configuration.UserPrinciple;
import com.example.springapp.exceptions.UseralreadyExists;
import com.example.springapp.model.User;
import com.example.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService , UserDetailsService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User u = userRepo.getUserByUsername(user.getUsername()).orElse(null);
        if(u != null)
        {
            throw new UseralreadyExists("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).get();
        // return userRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("No user with this id"));
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User u = userRepo.getUserByUsername(username).orElse(null);
        if(u==null)
        {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new UserPrinciple(u);
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepo.getUserByUsername(name).orElse(null);
    }

    // @Override
    // public User loginUser(User user) {
    //     Optional<User> u = userRepo.getUserByUsername(user.getUsername());
    //     if(u.isPresent())
    //     {
    //         User myUser = u.get();
    //         if(passwordEncoder.matches(user.getPassword(), myUser.getPassword()))
    //         {
    //             return myUser;
    //         }
    //     }
    //     return null;

    // }

}