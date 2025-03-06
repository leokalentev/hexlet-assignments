package exercise.dto.users;

import exercise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
public class UsersPage {
    private final List<User> users;

    public UsersPage(List<User> users) {
        this.users = users;
    }
}
// END
