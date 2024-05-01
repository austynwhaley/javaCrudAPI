package com.example.springapi.controller;

import com.example.springapi.models.User;
import com.example.springapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value="/")
    public String getPage() {
        return "Hello World";
    }

    @GetMapping(value="/users")
   public List<User> getUsers() {
        return userRepo.findAll();
   }
    @PostMapping("/save")
   public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved to the database...";
   }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updateUser = userRepo.findById(id).get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setOccupation(user.getOccupation());
        updateUser.setAge(user.getAge());
        userRepo.save(updateUser);
        return "User updated successfully...";
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "User deleted successfully...";
    }
}
