package com.app.tracko.auth;

import com.app.tracko.entity.Role;
import com.app.tracko.entity.SystemUserEntity;
import com.app.tracko.exception.SystemUserNotFoundException;
import com.app.tracko.exception.UserAlreadyExistsException;
import com.app.tracko.repository.SystemUserRepository;
import com.app.tracko.service.SystemUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final SystemUserRepository systemUserRepository;
  private final SystemUserService systemUserService;


@PostMapping("/register")
public ResponseEntity<?> register(
        @RequestBody RegisterRequest request
) {
  if(!userAlreadyExists(request.getEmailId())) {
    try {
      AuthenticationResponse response = authenticationService.register(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
  }
  else {
    return ResponseEntity.badRequest().body("User already exists");
  }

}

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }


  @GetMapping("/role")
  public ResponseEntity<String> getUserRole(Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof SystemUserEntity) {
        SystemUserEntity user = (SystemUserEntity) principal;
        String userRole = user.getRole().name();
        return ResponseEntity.ok(userRole);
      }
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }


  @PostMapping("/adminAssign")
  public ResponseEntity<String> assignAdmin(@RequestParam Long id){

  SystemUserEntity user = systemUserRepository.findById(id).get();
  user.setRole(Role.ADMIN);

  return ResponseEntity.ok("succesfully given Admin role");
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    authenticationService.refreshToken(request, response);
  }


  public boolean userAlreadyExists(String email) {
    Optional<SystemUserEntity> existingUser = systemUserRepository.findByEmailId(email);
    return existingUser.isPresent();
  }

}

