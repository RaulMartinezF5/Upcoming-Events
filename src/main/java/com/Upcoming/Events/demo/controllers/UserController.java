package com.Upcoming.Events.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Upcoming.Events.demo.models.User;
import com.Upcoming.Events.demo.services.UserServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/users")

public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @Transactional
  @PostMapping(value = "", consumes = "application/*")
  public ResponseEntity<?> create(@RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }

  @Transactional
  @GetMapping("/{id}")
  public ResponseEntity<?> read(@PathVariable Long id) {
    Optional<User> oUser = userService.findById(id);
    if (!oUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(oUser);

  }

  @Transactional
  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id) {
    Optional<User> user = userService.findById(id);
    if (!user.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    user.get().setUsername(userDetails.getUsername());
    user.get().setPassword(userDetails.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
  }

  @Transactional
  @PostMapping("/subscribe/{eventId}")
  public ResponseEntity<String> addParticipantToEvent(@PathVariable Long eventId) {
    try {
      userService.registerUserToEvent(eventId);
      return ResponseEntity.ok().body("User with id " + " added to event with id " + eventId);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (!userService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
