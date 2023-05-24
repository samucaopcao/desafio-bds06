package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return new UserDTO(user.orElseThrow(() -> new ResourceNotFoundException("User not found!")));
    }

    @Transactional(readOnly = true)
    public UserDTO findProfileUserLogged() {
        User user = authService.authenticated();
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            log.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }

        log.info("User found: " + username);
        return user;
    }
}
