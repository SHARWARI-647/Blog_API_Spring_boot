
package com.Blog.blog.service;

        import com.Blog.blog.model.Comment;
        import com.Blog.blog.repository.CommentRepo;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.context.SecurityContextHolder;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepo commentRepo;

    @InjectMocks
    private CommentService commentService;

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
    void testAddComment() {

        Comment savedComment = new Comment();
        savedComment.setId(1);
        savedComment.setBlogId(1);
        savedComment.setUserName("Sharwari");
        savedComment.setComment("Nice Blog");

        when(commentRepo.save(any(Comment.class)))
                .thenReturn(savedComment);

        Comment result =
                commentService.addComment(1, "Nice Blog");

        assertNotNull(result);
        assertEquals(1, result.getBlogId());
        assertEquals("Sharwari", result.getUserName());
        assertEquals("Nice Blog", result.getComment());

        verify(commentRepo).save(any(Comment.class));
    }

    @Test
    void testGetComments() {

        Comment c1 = new Comment();
        c1.setId(1);
        c1.setBlogId(1);
        c1.setUserName("Sharwari");
        c1.setComment("First Comment");

        Comment c2 = new Comment();
        c2.setId(2);
        c2.setBlogId(1);
        c2.setUserName("Amit");
        c2.setComment("Second Comment");

        when(commentRepo.findByBlogId(1))
                .thenReturn(List.of(c1, c2));

        List<Comment> result =
                commentService.getComments(1);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("First Comment",
                result.get(0).getComment());

        assertEquals("Second Comment",
                result.get(1).getComment());

        verify(commentRepo).findByBlogId(1);
    }

    @Test
    void testGetCommentsEmpty() {

        when(commentRepo.findByBlogId(1))
                .thenReturn(List.of());

        List<Comment> result =
                commentService.getComments(1);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(commentRepo).findByBlogId(1);
    }

    @Test
    void testAddCommentWithEmptyText() {

        Comment savedComment = new Comment();
        savedComment.setBlogId(1);
        savedComment.setUserName("Sharwari");
        savedComment.setComment("");

        when(commentRepo.save(any(Comment.class)))
                .thenReturn(savedComment);

        Comment result =
                commentService.addComment(1, "");

        assertNotNull(result);
        assertEquals("", result.getComment());

        verify(commentRepo).save(any(Comment.class));
    }
}