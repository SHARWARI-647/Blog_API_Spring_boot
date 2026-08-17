package com.Blog.blog.service;

import com.Blog.blog.dto.BlogRequest;
import com.Blog.blog.dto.BlogStats;
import com.Blog.blog.model.Blog;
import com.Blog.blog.repository.BlogRepo;
import com.Blog.blog.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock
    private BlogRepo blogRepo;
    @InjectMocks
    private BlogService blogService;

    @Mock
    private SupabaseStorageService storageService;
    @BeforeEach
    void setup(){

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
    void testCreateBlog() throws Exception {

        MultipartFile image = mock(MultipartFile.class);

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("Sharwari");

        SecurityContextHolder.setContext(securityContext);

        when(storageService.uploadImage(image))
                .thenReturn("profile.jpeg");

        Blog savedBlog = new Blog();
        savedBlog.setId(1);
        savedBlog.setTitle("Spring Boot");
        savedBlog.setCategory("Programming");
        savedBlog.setDescription("Learning Spring Boot");
        savedBlog.setImgURL("profile.jpeg");
        savedBlog.setUserName("Sharwari");
        savedBlog.setLikes(0);
        savedBlog.setViews(0);

        when(blogRepo.save(any(Blog.class)))
                .thenReturn(savedBlog);

        ResponseEntity<Blog> response = blogService.createBlog(
                "Programming",
                "Spring Boot",
                "Learning Spring Boot",
                image
        );

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Blog result = response.getBody();

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Spring Boot", result.getTitle());
        assertEquals("Programming", result.getCategory());
        assertEquals("Learning Spring Boot", result.getDescription());
        assertEquals("profile.jpeg", result.getImgURL());
        assertEquals("Sharwari", result.getUserName());
        assertEquals(0, result.getLikes());
        assertEquals(0, result.getViews());

        verify(storageService).uploadImage(image);
        verify(blogRepo).save(any(Blog.class));
    }
    @Test
    void testUpdateBlog(){

        Blog existing = new Blog();

        existing.setId(1);
        existing.setTitle("Old");
        existing.setCategory("Programming");

        Blog updated = new Blog();

        updated.setTitle("Java Spring");
        updated.setCategory("Technology");
        updated.setDescription("Spring Boot");
        updated.setImgURL("new.jpeg");
        updated.setUserName("Sharwari");

        when(blogRepo.findById(1))
                .thenReturn(Optional.of(existing));

        when(blogRepo.save(any(Blog.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        Blog result =
                blogService.updateBlog(1,updated);

        assertNotNull(result);

        assertEquals("Java Spring",
                result.getTitle());

        assertEquals("Technology",
                result.getCategory());

        assertEquals("Spring Boot",
                result.getDescription());

        assertEquals("new.jpeg",
                result.getImgURL());

        assertEquals("Sharwari",
                result.getUserName());

        verify(blogRepo).findById(1);
        verify(blogRepo).save(existing);
    }
    @Test
    void testDeleteBlog(){

        doNothing().when(blogRepo)
                .deleteById(1);

        blogService.deleteBlog(1);

        verify(blogRepo)
                .deleteById(1);
    }
    @Test
    void testLikeBlog() {

        Blog blog = new Blog();
        blog.setId(1);
        blog.setLikes(20);

        when(blogRepo.findById(1))
                .thenReturn(Optional.of(blog));

        when(blogRepo.save(any(Blog.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Blog result = blogService.likeBlog(1);

        assertNotNull(result);
        assertEquals(21, result.getLikes());

        verify(blogRepo).findById(1);
        verify(blogRepo).save(blog);
    }@Test
    void testLikeBlogNotFound() {

        when(blogRepo.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> blogService.likeBlog(1)
                );

        assertEquals("Blog Not Found", exception.getMessage());

        verify(blogRepo).findById(1);
    }
    @Test
    void testGetStats() {

        Blog b1 = new Blog();
        b1.setLikes(10);
        b1.setViews(100);

        Blog b2 = new Blog();
        b2.setLikes(20);
        b2.setViews(200);

        Blog b3 = new Blog();
        b3.setLikes(30);
        b3.setViews(300);

        when(blogRepo.count())
                .thenReturn(3L);

        when(blogRepo.findAll())
                .thenReturn(List.of(b1, b2, b3));

        BlogStats stats = blogService.getStats();

        assertNotNull(stats);

        assertEquals(3, stats.getTotalBlogs());
        assertEquals(60, stats.getTotalLikes());
        assertEquals(600, stats.getTotalViews());

        verify(blogRepo).count();

        // Called twice (likes + views)
        verify(blogRepo, times(2)).findAll();
    }
    @Test
    void testGetBlogs() {

        Blog b1 = new Blog();
        Blog b2 = new Blog();

        Page<Blog> page =
                new PageImpl<>(List.of(b1, b2));

        when(blogRepo.findAll(any(PageRequest.class)))
                .thenReturn(page);

        Page<Blog> result =
                blogService.getBlogs(
                        0,
                        5,
                        "title",
                        "asc"
                );

        assertNotNull(result);

        assertEquals(2, result.getContent().size());

        verify(blogRepo)
                .findAll(any(PageRequest.class));
    }

    @Test
    void testGetAllBlogs(){

        Blog b1 = new Blog();
        Blog b2 = new Blog();
        Blog b3 = new Blog();

        when(blogRepo.findAll())
                .thenReturn(List.of(b1,b2,b3));

        List<Blog> blogs =
                blogService.getAllBlogs();

        assertNotNull(blogs);
        assertEquals(3,blogs.size());

        verify(blogRepo).findAll();
    }
    @Test
    void testGetBlogByIdNotFound(){

        when(blogRepo.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> blogService.getBlogById(1)
                );

        assertEquals(
                "Blog Not Found",
                exception.getMessage()
        );

        verify(blogRepo).findById(1);
    }
    @Test
    void testGetBlogById(){

        Blog blog = new Blog();

        blog.setId(1);
        blog.setViews(20);

        when(blogRepo.findById(1))
                .thenReturn(Optional.of(blog));

        when(blogRepo.save(any(Blog.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        Blog result =
                blogService.getBlogById(1);

        assertNotNull(result);
        assertEquals(21,result.getViews());

        verify(blogRepo).findById(1);
        verify(blogRepo).save(blog);
    }
    @Test
    void testGetMyBlogs() {

        Blog b1 = new Blog();
        b1.setUserName("Sharwari");

        Blog b2 = new Blog();
        b2.setUserName("Sharwari");

        when(blogRepo.findByUserName("Sharwari"))
                .thenReturn(List.of(b1, b2));

        List<Blog> blogs =
                blogService.getMyBlogs();

        assertNotNull(blogs);

        assertEquals(2, blogs.size());

        assertEquals(
                "Sharwari",
                blogs.get(0).getUserName()
        );

        verify(blogRepo)
                .findByUserName("Sharwari");
    }
    @Test
    void testGetTrendingBlogs() {

        Blog b1 = new Blog();
        b1.setLikes(100);

        Blog b2 = new Blog();
        b2.setLikes(90);

        Blog b3 = new Blog();
        b3.setLikes(80);

        List<Blog> blogs = List.of(b1, b2, b3);

        when(blogRepo.findTop5ByOrderByLikesDesc())
                .thenReturn(blogs);

        List<Blog> result = blogService.getTrendingBlogs();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(100, result.get(0).getLikes());

        verify(blogRepo).findTop5ByOrderByLikesDesc();
    }
    @Test
    void testGetMyBlogsEmpty() {

        when(blogRepo.findByUserName("Sharwari"))
                .thenReturn(List.of());

        List<Blog> blogs =
                blogService.getMyBlogs();

        assertTrue(blogs.isEmpty());

        verify(blogRepo)
                .findByUserName("Sharwari");
    }
    @Test
    void testLatestBlogs() {

        Blog b1 = new Blog();
        b1.setCreatedAt(LocalDateTime.now());

        Blog b2 = new Blog();
        b2.setCreatedAt(LocalDateTime.now().minusDays(1));

        List<Blog> blogs = List.of(b1, b2);

        when(blogRepo.findTop10ByOrderByCreatedAtDesc())
                .thenReturn(blogs);

        List<Blog> result = blogService.latestBlogs();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(blogRepo).findTop10ByOrderByCreatedAtDesc();
    }
    @Test
    void testGetCategoryBlogs() {

        Blog b1 = new Blog();
        b1.setCategory("Programming");

        Blog b2 = new Blog();
        b2.setCategory("Programming");

        when(blogRepo.getByCategory("Programming"))
                .thenReturn(List.of(b1, b2));

        List<Blog> result =
                blogService.getCategoryBlogs("Programming");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Programming",
                result.get(0).getCategory());

        verify(blogRepo).getByCategory("Programming");
    }
    @Test
    void testSearchBlogs() {

        Blog b1 = new Blog();
        b1.setTitle("Spring Boot");

        Blog b2 = new Blog();
        b2.setTitle("Spring Security");

        when(blogRepo.findByTitleContainingIgnoreCase("Spring"))
                .thenReturn(List.of(b1, b2));

        List<Blog> result =
                blogService.searchBlogs("Spring");

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(blogRepo)
                .findByTitleContainingIgnoreCase("Spring");
    }
    @Test
    void testSearchBlogsNoResult() {

        when(blogRepo.findByTitleContainingIgnoreCase("Python"))
                .thenReturn(List.of());

        List<Blog> result =
                blogService.searchBlogs("Python");

        assertTrue(result.isEmpty());

        verify(blogRepo)
                .findByTitleContainingIgnoreCase("Python");
    }
    @Test
    void testCategoryBlogsEmpty() {

        when(blogRepo.getByCategory("Sports"))
                .thenReturn(List.of());

        List<Blog> result =
                blogService.getCategoryBlogs("Sports");

        assertTrue(result.isEmpty());

        verify(blogRepo).getByCategory("Sports");
    }
}
