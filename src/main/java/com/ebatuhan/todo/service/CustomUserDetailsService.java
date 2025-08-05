package com.ebatuhan.todo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ebatuhan.todo.repository.TodoUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TodoUserRepository todoUserRepository;

    public CustomUserDetailsService(TodoUserRepository todoUserRepository) {

        this.todoUserRepository = todoUserRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return todoUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username with " + username + " not found."));
    }

}
