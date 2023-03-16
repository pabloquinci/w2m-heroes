package com.w2m.security;

import com.w2m.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2m.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    StringBuilder strUserNoEncontrado= new StringBuilder("Usuario no encontrado: ");
    strUserNoEncontrado.append(username);
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(strUserNoEncontrado.toString()));

    return UserDetailsImpl.build(user);
  }

}