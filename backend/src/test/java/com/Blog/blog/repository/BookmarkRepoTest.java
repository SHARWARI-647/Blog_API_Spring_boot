package com.Blog.blog.repository;

import com.Blog.blog.model.Bookmark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookmarkRepoTest {
    @Autowired
    BookmarkRepo repo;
    @BeforeEach
    void setup(){
        repo.deleteAll();
    }
    @Test
    void testFindByUsername(){
        for(int i=0;i<5;i++){
            Bookmark book=new Bookmark();
            book.setUserName("sharwari");
            book.setBlogId(i+1);

            repo.save(book);
        }
        List<Bookmark> result= repo.findByUserName("sharwari");
        assertTrue(result.size()>0);
        assertEquals("sharwari", result.get(0).getUserName());
    }
    @Test
    void testFindByUserNameAndBlogId(){
        for(int i=0;i<5;i++){
            Bookmark book=new Bookmark();
            book.setUserName("sharwari");
            book.setBlogId(i+1);
            repo.save(book);
        }
        Optional<Bookmark> result=repo.findByUserNameAndBlogId("sharwari",2);
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getBlogId());
        assertEquals("sharwari", result.get().getUserName());
    }

}
