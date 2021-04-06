package com.itm.legbook.service;

import com.itm.legbook.com.itm.legbook.model.NotificationEmail;
import com.itm.legbook.com.itm.legbook.model.User;
import com.itm.legbook.com.itm.legbook.model.VerificationToken;
import com.itm.legbook.dto.AuthenticationResponse;
import com.itm.legbook.dto.LoginRequest;
import com.itm.legbook.dto.RegisterRequest;
import com.itm.legbook.exception.LegBookException;
import com.itm.legbook.repository.UserRepositroy;
import com.itm.legbook.repository.VerificationTokenRepository;
import com.itm.legbook.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepositroy userRepositroy;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @Transactional
    public boolean registerUser(RegisterRequest registerRequest)
    {
        Optional<User> optionaluser=userRepositroy.findByEmail(registerRequest.getEmail());

        if(optionaluser.isPresent())
        {
            //if user exists
            System.out.println("User"+optionaluser);
            return false;
        }

        //if no user exists
        User user=new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        //disable user now.It will be activated once user clicks on verification email
        user.setActivated(false);

        userRepositroy.save(user);

        String token=generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Please Activate your account",user.getEmail(),
                "click on url to activate your account: "+
                        "http://localhost:8080/api/auth/v1/accountVerification/"+token));
        return true;

    }

    private String generateVerificationToken(User user)
    {
        String token=UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token)
    {
       Optional<VerificationToken> verificationToken=verificationTokenRepository.findByToken(token);
       verificationToken.orElseThrow(()->new LegBookException("Invalid token"));
       fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken)
    {
        String email=verificationToken.getUser().getEmail();
        User user=userRepositroy.findByEmail(email).orElseThrow(()->new LegBookException("No user found with email:"+email));
        user.setActivated(true);
        userRepositroy.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest)
    {
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token=jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getEmail());
    }
}
