package com.Blog.blog.service;

import com.Blog.blog.dto.AuthResponse;
import com.Blog.blog.dto.RefreshRequest;
import com.Blog.blog.dto.RequestLogin;
import com.Blog.blog.dto.UpdateProfileRequest;
import com.Blog.blog.model.RefreshToken;
import com.Blog.blog.model.User;
import com.Blog.blog.repository.RefreshTokenRepo;
import com.Blog.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RefreshTokenRepo refreshRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public User getUser(String userName) {

        User user = repo.findByUserName(userName);

        if(user == null){
            throw new RuntimeException("User Not Found");
        }

        return user;
    }

    public AuthResponse loginUser(RequestLogin request){

        User user = repo.findByUserName(request.getUserName());

        if(user == null){
            throw new RuntimeException("User Not Found");
        }

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String accessToken =
                jwtService.generateToken(user.getUserName());

        String refreshToken =
                jwtService.generateRefreshToken(user.getUserName());

        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setUserName(user.getUserName());
        token.setExpiryDate(LocalDateTime.now().plusDays(7));

        refreshRepo.save(token);

        return new AuthResponse(
                accessToken,
                refreshToken
        );
    }

    public User registerUser(User registerUser){

        User user =
                repo.findByUserName(
                        registerUser.getUserName());

        if(user != null){
            throw new RuntimeException(
                    "Username already exists");
        }

        registerUser.setPassword(
                encoder.encode(
                        registerUser.getPassword()));

        registerUser.setRole("USER");

        return repo.save(registerUser);
    }
    public void deleteUser(String userName){

        User user = repo.findByUserName(userName);

        if(user == null){
            throw new RuntimeException(
                    "User Not Found");
        }

        repo.delete(user);
    }

    public User updateUser(User userUpdate){

        User user =
                repo.findByUserName(
                        userUpdate.getUserName());

        if(user == null){
            throw new RuntimeException(
                    "User Not Found");
        }

        user.setEmail(userUpdate.getEmail());

        user.setPassword(
                encoder.encode(
                        userUpdate.getPassword()));

        return repo.save(user);
    }

    public AuthResponse refreshToken(
            RefreshRequest request) {

        RefreshToken token =
                refreshRepo.findByToken(
                                request.getRefreshToken())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Refresh Token"));

        if(token.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "Refresh Token Expired");
        }

        String accessToken =
                jwtService.generateToken(
                        token.getUserName());

        return new AuthResponse(
                accessToken,
                token.getToken()
        );
    }

    public void logout(String refreshToken){

        RefreshToken token =
                refreshRepo.findByToken(refreshToken)
                        .orElse(null);

        if(token != null){
            refreshRepo.delete(token);
        }
    }

    public User getProfile(){

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return repo.findByUserName(username);
    }
    public User updateProfile(
            UpdateProfileRequest request){

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                repo.findByUserName(username);

        if(user == null){
            throw new RuntimeException(
                    "User Not Found");
        }

        user.setEmail(request.getEmail());
        user.setBio(request.getBio());
        user.setPhone(request.getPhone());
        user.setProfileImage(
                request.getProfileImage());

        return repo.save(user);
    }
}