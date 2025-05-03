package exercise.controller.users;

import java.util.List;

import lombok.Setter;
import org.springframework.http.HttpStatus;

import exercise.model.Post;
import exercise.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    @Setter
    private static List<Post> posts = Data.getPosts();
    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> show(@PathVariable int id) {
        var result = posts.stream()
                .filter(post -> post.getUserId() == id)
                .toList();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@RequestBody Post post, @PathVariable int id) {
        post.setUserId(id);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
// END
