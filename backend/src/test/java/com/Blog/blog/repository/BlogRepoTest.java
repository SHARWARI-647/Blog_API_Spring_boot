package com.Blog.blog.repository;

import com.Blog.blog.model.Blog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BlogRepoTest {
    @Autowired
    private BlogRepo repo;
    @BeforeEach
    void setup(){
        repo.deleteAll();
    }

    @Test
    void testSaveBlog(){
        Blog blog =new Blog();
        blog.setTitle("Spring Blog");
        blog.setCategory("Programming");
        blog.setImgURL("Spring.jpeg");
        blog.setDescription("Spring boot is a java framework ");
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blog.setUserName("Sharwari");
        blog.setLikes(100);
        blog.setViews(500);

        blog.setCreatedAt(LocalDateTime.now());

        Blog saved=repo.save(blog);

        assertNotNull(saved.getId());
        assertEquals("Spring Blog", saved.getTitle());
    }

    @Test
    void testGetByCatagory(){
        Blog blog=new Blog();
        blog.setTitle("Java");
        blog.setCategory("Programming");
        repo.save(blog);
        List<Blog> blogs=repo.getByCategory("Programming");
        assertEquals(1, blogs.size());
        assertEquals("Java", blogs.get(0).getTitle());
    }

    @Test
    void testFindByTitleContainingIgnoreCase(){
        Blog blog = new Blog();
        blog.setTitle("Spring Boot Tutorial");

        repo.save(blog);
        List<Blog> result =
                repo.findByTitleContainingIgnoreCase("spring");

        assertEquals("Spring Boot Tutorial",
                result.get(0).getTitle());
    }
    @Test
    void testFindByUserName() {

        Blog blog = new Blog();
        blog.setTitle("My Blog");
        blog.setUserName("Sharwari");

       repo.save(blog);

        List<Blog> blogs =
                repo.findByUserName("Sharwari");

        assertEquals(1, blogs.size());

    }
    @Test
    void testTop5ByLikes() {

        for (int i = 1; i <= 6; i++) {

            Blog blog = new Blog();
            blog.setTitle("Blog " + i);
            blog.setLikes(i * 10);

            repo.save(blog);

        }

        List<Blog> blogs =
               repo.findTop5ByOrderByLikesDesc();

        assertEquals(5, blogs.size());

        assertTrue(
                blogs.get(0).getLikes() >
                        blogs.get(1).getLikes());

    }@Test
    void testMostLikedBlog() {

        Blog blog1 = new Blog();
        blog1.setLikes(10);

        Blog blog2 = new Blog();
        blog2.setLikes(100);

        repo.save(blog1);
        repo.save(blog2);

        Optional<Blog> result =
                repo.findTopByOrderByLikesDesc();

        assertTrue(result.isPresent());
        assertEquals(100, result.get().getLikes());

    }@Test
    void testMostViewedBlog() {

        Blog blog1 = new Blog();
        blog1.setViews(500);

        Blog blog2 = new Blog();
        blog2.setViews(1200);

        repo.save(blog1);
        repo.save(blog2);

        Optional<Blog> result =
                repo.findTopByOrderByViewsDesc();

        assertTrue(result.isPresent());
        assertEquals(1200, result.get().getViews());

    }@Test
    void testLatestBlogs() {

        Blog oldBlog = new Blog();
        oldBlog.setCreatedAt(LocalDateTime.now().minusDays(2));

        Blog newBlog = new Blog();
        newBlog.setCreatedAt(LocalDateTime.now());

        repo.save(oldBlog);
        repo.save(newBlog);

        List<Blog> blogs =
                repo.findTop10ByOrderByCreatedAtDesc();

        assertEquals(newBlog.getCreatedAt(),
                blogs.get(0).getCreatedAt());

    }

}
