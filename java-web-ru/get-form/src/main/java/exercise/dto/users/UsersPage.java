package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor
public class UsersPage {
    private final List<User> users;
    private String term;
}
// END
