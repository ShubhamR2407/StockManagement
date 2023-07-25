package com.assignment.StockManagementSystem.User.Service;

import com.assignment.StockManagementSystem.User.Dto.UpdateUserDto;
import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import com.assignment.StockManagementSystem.User.Repository.UserRepository;
import com.assignment.StockManagementSystem.User.Repository.UserStockRepository;
import com.assignment.StockManagementSystem.config.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtServiceImpl jwtService;


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String name) {
                User user =  userRepository.findByIdOrName(null,name);
                if(user == null)
                    throw new UsernameNotFoundException("User not found");

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                if(user.isAdmin()){
                    authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
                } else if(user.isCompany()){
                    authorities.add(new SimpleGrantedAuthority(Role.ROLE_COMPANY.name()));
                }else{
                    authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
                }
                return new org.springframework.security.core.userdetails.User(
                        user.getName(),user.getPassword(), authorities);
            }
        };
    }

    public User updateUser(String name, UpdateUserDto updateUserDto) {
        User user = userRepository.findByIdOrName(null,name);

        if(user == null) {
            return null;
        }
        user.setEmail(updateUserDto.getEmail());
        user.setNumber(updateUserDto.getNumber());
        user.setAadharNumber(updateUserDto.getAadharNumber());
        return userRepository.save(user);
    }

    public ResponseEntity<String> deleteUser(String name) {
        User user = userRepository.findByIdOrName(null,name);

        if(user==null) {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        } else {
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserByTokenOrName(String tokenOrName) {
        User user = new User();
        if(tokenOrName.length() > 20 ) {
            String name = jwtService.extractUserName(tokenOrName);
            user = userRepository.findByIdOrName(null,name);
        } else {
            user = userRepository.findByIdOrName(null,tokenOrName);
        }
        if(user == null) {
            return null;
        } else {
            return user;
        }
    }

}
