package com.ashish.pack.Service;

import com.ashish.pack.Config.MyUserDetails;
import com.ashish.pack.Entity.Users;
import com.ashish.pack.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByName(username);
        return users.map(MyUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
