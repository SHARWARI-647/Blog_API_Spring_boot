package com.Blog.blog.repository;

import com.Blog.blog.model.RefreshToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class RefreshTokenRepoTest {
    @Autowired
    RefreshTokenRepo repo;

    @BeforeEach
    void setup(){
        repo.deleteAll();
    }

    @Test
    void testFindByToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("abc123");
        refreshToken.setUserName("sharwari");
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(1));

        repo.save(refreshToken);

        Optional<RefreshToken> result = repo.findByToken("abc123");

        assertTrue(result.isPresent());
        assertEquals("abc123", result.get().getToken());
        assertEquals("sharwari", result.get().getUserName());
    }
    @Test
    void testFindByTokenNotFound() {

        Optional<RefreshToken> result = repo.findByToken("invalidToken");

        assertFalse(result.isPresent());
    }
}
