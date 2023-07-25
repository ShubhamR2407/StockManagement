package com.assignment.StockManagementSystem.User.Controller;

import com.assignment.StockManagementSystem.Exceptions.DuplicateUserException;
import com.assignment.StockManagementSystem.Stock.Repository.Modals.Stock;
import com.assignment.StockManagementSystem.User.Dto.LoginDto;
import com.assignment.StockManagementSystem.User.Dto.RegisterDto;
import com.assignment.StockManagementSystem.User.Dto.UpdateUserDto;
import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import com.assignment.StockManagementSystem.User.Repository.Modals.UserStock;
import com.assignment.StockManagementSystem.User.Service.AuthenticationServiceImpl;
import com.assignment.StockManagementSystem.User.Service.UserService;
import com.assignment.StockManagementSystem.User.Service.UserStockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://localhost:5173", "http://192.168.1.82:5173"}, allowCredentials = "true")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserStockService userStockService;

    @PostMapping("/save")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto userDto) {
        try {
            User savedUser = authenticationService.saveUser(userDto);
            // Return a success response or whatever is applicable for successful registration.
            return ResponseEntity.ok(savedUser);
        } catch (DuplicateUserException ex) {
            // Return an error response with a meaningful message to inform the user about the duplicate user.
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            // Return a generic error response for other unexpected exceptions.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<Stock> buyStock(@RequestParam Long userId, @RequestParam Long stockId) {
        try {
            Stock stock = userStockService.buyStock(userId, stockId);

            return new ResponseEntity<>(stock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestParam Long userId, @RequestParam Long stockId) {
        try {
            userStockService.sellStock(userId, stockId);

            return new ResponseEntity<>("Stock removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        try {
            User user = userService.getUserByTokenOrName(name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
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

    @GetMapping("/profile")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            User user = userService.getUserByTokenOrName(jwtToken);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/admin/profile")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<User> getAdmin(@RequestHeader("Authorization") String token) {
//        try {
//            String jwtToken = token.substring(7);
//            User user = userService.getUserByTokenOrName(jwtToken);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/stocks/{userId}")
    public ResponseEntity<List<Stock>> getStockByUserId(@PathVariable Long userId) {
        try {
            List<Stock> stocksList = userStockService.getAllStockByUserId(userId);
            return new ResponseEntity<>(stocksList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        try {
            ResponseEntity<String> response = authenticationService.login(loginDto.getName(), loginDto.getPassword());
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<User> updateUser(@PathVariable String name,@Valid @RequestBody UpdateUserDto updateUserDto) {
        try {
            User updatedUser = userService.updateUser(name, updateUserDto);
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
