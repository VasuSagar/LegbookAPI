package com.itm.legbook.controller;

import com.itm.legbook.dto.AuthenticationResponse;
import com.itm.legbook.dto.LoginRequest;
import com.itm.legbook.dto.RegisterRequest;
import com.itm.legbook.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://cs.neonsolutions.xyz")
@RequestMapping("/api/auth/v1")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest)
    {
        if(authService.registerUser(registerRequest))
        {
            //if user registration is successful
            return new ResponseEntity<>(HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>("User Exists with same email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifiyAccount(@PathVariable String token)
    {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        return authService.login(loginRequest);
    }
}
