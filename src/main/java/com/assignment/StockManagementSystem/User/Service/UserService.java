package com.assignment.StockManagementSystem.User.Service;

import com.assignment.StockManagementSystem.User.Dto.RegisterDto;
import com.assignment.StockManagementSystem.User.Repository.Modals.User;
import com.assignment.StockManagementSystem.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(RegisterDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setNumber(userDto.getNumber());
        user.setAadharNumber(userDto.getAadharNumber());
        user.setPassword(userDto.getPassword());

        return userRepository.save(user);
    }

    public ResponseEntity<String> loginUser(String name, String password) {
        User user = userRepository.findByName(name);
        System.out.println(user);
        if (user == null) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        } else if (!user.getPassword().equals(password)) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(name, HttpStatus.OK);
        }
    }

//    public ResponseEntity<String> loginUser(String name, String password) {
//        User user = userRepository.findByName(name);
//        System.out.println(user);
//        if (user == null) {
//            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
//        } else if (!user.getPassword().equals(password)) {
//            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
//        } else {
//            // User login successful, generate and return a JWT
//            System.out.println("1");
////            String accessToken = generateAccessToken(name, user.getEmail());
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//    }
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

//    private String generateAccessToken(String name, String email) {
//        Date expirationTime = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
//        System.out.println("2");
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("name", name);
//        claims.put("email", email);
//        System.out.println("3");
//        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);
//        SecretKey key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
//        System.out.println("4");
//        String accessToken = Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(expirationTime)
//                .signWith(key, SignatureAlgorithm.HS512) // Use HS512 algorithm
//                .compact();
//        System.out.println("5");
//        return accessToken;
//    }

    public User updateUser(String name, RegisterDto userDto) {
        User user = userRepository.findByName((name));

        if(user == null) {
            return null;
        }
        user.setEmail(userDto.getEmail());
        user.setNumber(userDto.getNumber());
        user.setAadharNumber(userDto.getAadharNumber());
//        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public ResponseEntity<String> deleteUser(String name) {
        User user = userRepository.findByName(name);

        if(user==null) {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        } else {
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted Successfullt", HttpStatus.OK);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUser(String name) {
        User user = userRepository.findByName(name);

        if(user == null) {
            return null;
        } else {
            return user;
        }
    }
}
