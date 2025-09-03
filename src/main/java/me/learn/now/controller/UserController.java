package me.learn.now.controller;

import me.learn.now.model.User;
import me.learn.now.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService us;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(us.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        Optional<User> u = us.getUser(id);
        return u.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(us.getUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Optional<User> u = us.deleteUser(id);
        return u.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Object>> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(Optional.ofNullable(us.updateUser(id, user)));
    }

    @PutMapping("/pass/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> body){
        String newPassword = body.get("uPass");
        return ResponseEntity.ok(us.updatePassword(id, newPassword));
    }
}
