package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        } else {
            return ResponseEntity.ok(user);
        }
    }


    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deteleUser(id);
    }

    @PostMapping("/{id}/upload")
    public User uploadProfilePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return userService.uploadUserProfilePicture(id, file);
    }

    @GetMapping("/{id}/upload")
    public String fetchUserProfilePicture(@PathVariable Long id) {
        String filename = userService.fetchUserProfilePicture(id);
        if (filename != null) {
            return "/Profile-pictures/" + filename;
        } else {
            return null;
        }
    }

}
