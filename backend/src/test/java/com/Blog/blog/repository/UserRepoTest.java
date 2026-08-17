package com.Blog.blog.repository;

import com.Blog.blog.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepoTest {
    @Autowired
    UserRepo repo;
    @BeforeEach
    void setup(){
        repo.deleteAll();
    }
    @Test
    void testFindByUserName() {
        User user = new User();
        user.setUserName("rani");
        user.setEmail("sg@gmail.com");
        user.setPassword("1234");
        user.setBio("IT ENG");
        user.setRole("user");
        user.setPhone("9832537812");
        user.setProfileImage("profile.jpeg");

        repo.save(user);

        User result = repo.findByUserName("rani");

        assertNotNull(result);
        assertEquals("rani", result.getUserName());
        assertEquals("sg@gmail.com", result.getEmail());
        assertEquals("1234", result.getPassword());
        assertEquals("IT ENG", result.getBio());
        assertEquals("user", result.getRole());
        assertEquals("9832537812", result.getPhone());
        assertEquals("profile.jpeg", result.getProfileImage());
    }
}
