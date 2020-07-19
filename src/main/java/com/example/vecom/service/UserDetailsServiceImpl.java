//package com.example.vecom.service;
//
//import com.example.vecom.model.User;
//import com.example.vecom.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.transaction.annotation.Transactional;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User userResult = userRepository.findByEmail(email);
//        if(userResult == null) throw new UsernameNotFoundException(email);
//
//        return org.springframework.security.core.userdetails.User.withUsername(userResult.getEmail()).password(userResult.getPassword()).build();
//    }
//}
