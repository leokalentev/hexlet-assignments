package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(int userId, User newUser) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(user -> {
                    if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                        user.setEmail(newUser.getEmail());
                    }
                    if (newUser.getFirstName() != null && !newUser.getFirstName().isEmpty()) {
                        user.setFirstName(newUser.getFirstName());
                    }
                    if (newUser.getLastName() != null && !newUser.getLastName().isEmpty()) {
                        user.setLastName(newUser.getLastName());
                    }
                    return userRepository.save(user);
                });
    }

    public Mono<User> destroy(int userId) {
        return userRepository.findById(userId)
                .flatMap(user -> userRepository.deleteById(userId).thenReturn(user))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    // END
}
