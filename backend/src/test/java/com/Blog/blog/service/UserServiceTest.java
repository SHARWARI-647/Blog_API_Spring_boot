package com.Blog.blog.service;

import com.Blog.blog.model.User;
import com.Blog.blog.repository.RefreshTokenRepo;
import com.Blog.blog.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
        @Mock
        private UserRepo userRepo;

        @Mock
        private RefreshTokenRepo refreshTokenRepo;

        @Mock
        private BCryptPasswordEncoder encoder;

        @Mock
        private JwtService jwtService;

        @InjectMocks
        private UserService userService;

        @BeforeEach
        void setup() {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            "Sharwari",
                            null,
                            List.of()
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        }
    @Test
    void testGetUser() {

        User user = new User();
        user.setUserName("Sharwari");

        when(userRepo.findByUserName("Sharwari"))
                .thenReturn(user);

        User result = userService.getUser("Sharwari");

        assertNotNull(result);
        assertEquals("Sharwari", result.getUserName());

        verify(userRepo).findByUserName("Sharwari");
    }
    @Test
    void testGetUserNotFound() {

        when(userRepo.findByUserName("Sharwari"))
                .thenReturn(null);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> userService.getUser("Sharwari")
                );

        assertEquals("User Not Found", exception.getMessage());
    }

    @Test
    void testRegisterUserAlreadyExists() {

        User user = new User();
        user.setUserName("Sharwari");

        when(userRepo.findByUserName("Sharwari"))
                .thenReturn(user);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> userService.registerUser(user)
                );

        assertEquals(
                "Username already exists",
                exception.getMessage()
        );
    }
}
