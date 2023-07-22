package com.assignment.StockManagementSystem.User.Controller;

import com.assignment.StockManagementSystem.User.Dto.LoginDto;
import com.assignment.StockManagementSystem.User.Dto.RegisterDto;
import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import com.assignment.StockManagementSystem.User.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@Valid @RequestBody RegisterDto userDto) {
        try {
            User createdUser = userService.addUser(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = userService.getAllUsers();

            if(userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        try {
            User user = userService.getUser(name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        try {
            ResponseEntity<String> response = userService.loginUser(loginDto.getName(), loginDto.getPassword());
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<User> updateUser(@PathVariable String name,@Valid @RequestBody RegisterDto userDto) {
        try {
            User updatedUser = userService.updateUser(name, userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        try {
            ResponseEntity<String > response = userService.deleteUser(name);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
