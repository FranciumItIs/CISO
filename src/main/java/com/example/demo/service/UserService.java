package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    public boolean isNewUser(String email) {
        return !userRepository.existsByEmail(email);
    }

//    public void saveUser(OAuth2User oAuth2User) {
//        User user = new User();
//        user.setEmail(oAuth2User.getAttribute("email"));
//        user.setName(oAuth2User.getAttribute("name"));
//        // Set other user properties as needed
//        userRepository.save(user);
//    }
	
}
