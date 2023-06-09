package com.synpulse8.transactionpagination.service;

import com.synpulse8.transactionpagination.model.Transaction;
import com.synpulse8.transactionpagination.model.User;
import com.synpulse8.transactionpagination.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity addUser(User user) {
        try{
            userRepository.save(user);
            return ResponseEntity.ok().body("Added user!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user id not found"));
    }


}
