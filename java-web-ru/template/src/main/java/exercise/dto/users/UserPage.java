package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// BEGIN
@Getter
public class UserPage {
    public final User user;

    public UserPage(User user) {
        this.user = user;
    }
}
// END
