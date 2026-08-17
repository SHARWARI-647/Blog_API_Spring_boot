package com.Blog.blog.repository;

import com.Blog.blog.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepoTest {

    @Autowired
    private CommentRepo repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    @Test
    void testFindByBlogId() {

            Comment comment = new Comment();
            comment.setUserName("sharwari");
            comment.setComment("Very Nice");
            comment.setBlogId(1);

            repo.save(comment);


        List<Comment> result = repo.findByBlogId(1);

        assertEquals(1, result.size());
        assertEquals("sharwari", result.get(0).getUserName());
        assertEquals("Very Nice", result.get(0).getComment()); }
}