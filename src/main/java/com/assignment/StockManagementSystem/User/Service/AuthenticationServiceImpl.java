package com.assignment.StockManagementSystem.User.Service;

import com.assignment.StockManagementSystem.Exceptions.DuplicateUserException;
import com.assignment.StockManagementSystem.User.Dto.RegisterDto;
import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import com.assignment.StockManagementSystem.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public User saveUser(RegisterDto registerDto) {
        // Check for duplicate user here (e.g., by email, Aadhar number, or name)
        boolean emailExists = userRepository.existsByEmail(registerDto.getEmail());
        boolean aadharNumberExists = userRepository.existsByAadharNumber(registerDto.getAadharNumber());
        boolean nameExists = userRepository.existsByName(registerDto.getName());

        if (emailExists || aadharNumberExists || nameExists) {
            throw new DuplicateUserException("User with the same email, Aadhar number, or name already exists.");
        }

        // Continue with user creation if no duplicate is found
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setNumber(registerDto.getNumber());
        user.setAadharNumber(registerDto.getAadharNumber());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return userRepository.save(user);
    }


    public ResponseEntity<String> login(String name, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, password));

        User user = userRepository.findByIdOrName(null,name);
        System.out.println(user);
        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(
        user.getName(),user.getPassword(), new ArrayList<>());

        return new ResponseEntity<>(jwtService.generateToken(userDetails), HttpStatus.OK);
    }
}
