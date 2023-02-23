package com.w2m.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.w2m.persistence.RoleRepository;
import com.w2m.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.w2m.dto.JwtResponse;
import com.w2m.dto.LoginRequest;
import com.w2m.dto.MessageResponse;
import com.w2m.dto.SignupRequest;
import com.w2m.model.ERole;
import com.w2m.model.Role;
import com.w2m.model.User;
import com.w2m.security.JwtUtils;
import com.w2m.security.UserDetailsImpl;
import com.w2m.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;

	private static String ROL_NO_ENCONTRADO= "Error: Rol no encontrado.";

	private static String MAIL_EN_USO= "Error: El mail ya se encuentra en uso";

	private static String NOMBRE_USUARIO_EN_USO= "Error: El mail ya se encuentra en uso";

	private static String USUSARIO_REGISTRADO= "Usuario registrado";


	@Override
	public JwtResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		JwtResponse response=new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
		return response;
	}

	@Override
	public ResponseEntity<?> registrarse(SignupRequest signUpRequest) {
		Boolean existeNombreUsuario=userRepository.existsByUsername(signUpRequest.getUsername());
		if (existeNombreUsuario) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(NOMBRE_USUARIO_EN_USO));
		}

		Boolean existeElMail=userRepository.existsByEmail(signUpRequest.getEmail());
		if (existeElMail) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(MAIL_EN_USO));
		}

		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException(ROL_NO_ENCONTRADO));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(ROL_NO_ENCONTRADO));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException(ROL_NO_ENCONTRADO));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException(ROL_NO_ENCONTRADO));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);		
		return ResponseEntity.ok(new MessageResponse(USUSARIO_REGISTRADO));

	}

}
