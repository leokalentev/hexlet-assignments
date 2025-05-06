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
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public CommentDTO commentDTO(Comment comment) {
        CommentDTO res = new CommentDTO();
        res.setId(comment.getId());
        res.setBody(comment.getBody());

        return res;
    }

    public PostDTO toDTO(Post post) {
        PostDTO res = new PostDTO();
        res.setId(post.getId());
        res.setTitle(post.getTitle());
        res.setBody(post.getBody());

        List<CommentDTO> comments = commentRepository.findByPostId(post.getId()).stream()
                .map(this::commentDTO).toList();
        res.setComments(comments);

        return res;
    }

    @GetMapping
    public List<PostDTO> index() {
        var posts = postRepository.findAll().stream().map(this::toDTO).toList();
        return posts;
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + "not found"));
        var res = toDTO(post);

        return res;
    }
}
// END
