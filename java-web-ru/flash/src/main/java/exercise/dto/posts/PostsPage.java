package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;
import lombok.NoArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsPage extends BasePage{
    private List<Post> posts;
}
// END
