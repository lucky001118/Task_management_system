package com.lucky.controller;

import com.lucky.config.JwtProvider;
import com.lucky.model.User;
import com.lucky.repository.UserRepository;
import com.lucky.request.LoginRequest;
import com.lucky.responce.AuthResponce;
import com.lucky.service.CustomUserServiceImplementation;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserServiceImplementation customUserDetail;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponce> register(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole();

//        if user is already exist in this email then throws user already exist in this email id
        User isEmailExist=userRepository.findByEmail(user.getEmail());
        if (isEmailExist!=null){
            throw new Exception("The email is already exist with another account");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        newUser.setRole(role);

        User savedUser = userRepository.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponce res = new AuthResponce();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

//    method for login
    @PostMapping("/signin")
    public ResponseEntity<AuthResponce> signin(@RequestBody LoginRequest loginRequest){
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication auth = authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);
        AuthResponce res = new AuthResponce();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login success");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customUserDetail.loadUserByUsername(userName);

        if (userDetails==null){
            throw new BadCredentialsException("invalid username "+userDetails);
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            System.out.println("signing userDetail - password not match "+userDetails);
            throw  new BadCredentialsException("invalid password "+userDetails);
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }
}
