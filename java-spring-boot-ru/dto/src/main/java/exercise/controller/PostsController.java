package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping()
    public List<PostDTO> index() {
        var posts = postRepository.findAll()
                .stream()
                .map(this::toPostDTO)
                .toList();
        return posts;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        var foundPost = postRepository.findById(id)
                .map(this::toPostDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return foundPost;
    }

    private PostDTO toPostDTO(Post post) {
        var dto = new PostDTO();
        List<CommentDTO> comments = commentRepository.findByPostId(post.getId())
                .stream()
                .map(this::commentDTO)
                .toList();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(comments);
        return dto;
    }

    private CommentDTO commentDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }
}
// END
