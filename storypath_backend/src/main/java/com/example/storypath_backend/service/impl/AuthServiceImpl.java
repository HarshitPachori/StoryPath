package com.example.storypath_backend.service.impl;

import com.example.storypath_backend.constant.AppConstants;
import com.example.storypath_backend.exception.ApiException;
import com.example.storypath_backend.model.User;
import com.example.storypath_backend.payload.LoginRequest;
import com.example.storypath_backend.payload.LoginResponse;
import com.example.storypath_backend.payload.SignupRequest;
import com.example.storypath_backend.payload.SignupResponse;
import com.example.storypath_backend.repository.UserRepository;
import com.example.storypath_backend.service.AuthService;
import com.example.storypath_backend.service.EmailService;
import com.example.storypath_backend.utils.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtTokenHelper jwtTokenHelper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Optional<User> userByEmail = userRepository.findByEmail(request.email());
        if (userByEmail.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "No user registered with this email. Please create account first.");
        }
        if (!userByEmail.get().isVerified()) {
            return LoginResponse.builder()
                    .message("Email is not verified. Please verify your email first.")
                    .isSuccess(false)
                    .isVerified(false)
                    .build();
        }
        Authentication authentication = authenticate(request.email(), request.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.email());
        String jwtToken = jwtTokenHelper.generateToken(userDetails.getUsername());
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .isSuccess(true)
                .message("Login successfully " + userByEmail.get().getUsername())
                .isVerified(true)
                .build();
    }

    @Transactional
    @Override
    public SignupResponse signupUser(SignupRequest request) {
        Optional<User> userByUsername = userRepository.findByUsername(request.username());
        if (userByUsername.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already taken.");
        }
        Optional<User> userByEmail = userRepository.findByEmail(request.email());
        if (userByEmail.isPresent() && userByEmail.get().isVerified()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already registered and verified");
        } else if (userByEmail.isPresent()) {
            String otp = generateEmailVerificationOtp();
            userByEmail.get().setEmailVerificationOtp(otp);
            userRepository.save(userByEmail.get());
            sendVerificationEmail(userByEmail.get().getUsername(), userByEmail.get().getEmail(), otp);
            return SignupResponse.builder()
                    .isSuccess(true)
                    .message("An OTP is sent to your email. Please verify your account.")
                    .isVerified(false)
                    .build();
        }
        String hashedPassword = passwordEncoder.encode(request.password());
        String otp = generateEmailVerificationOtp();
        User user = User
                .builder()
                .username(request.username())
                .email(request.email())
                .password(hashedPassword)
                .emailVerificationOtp(otp)
                .isVerified(false)
                .wantsSentimentAnalysis(false)
                .roles(List.of(AppConstants.ROLE_USER))
                .build();
        User savedUser = userRepository.save(user);
        sendVerificationEmail(savedUser.getUsername(), savedUser.getEmail(), otp);
        return SignupResponse.builder()
                .isSuccess(true)
                .message("An OTP is sent to your email. Please verify your account.")
                .isVerified(false)
                .build();
    }

    @Override
    public Map<String, Object> verifyAccount(String email, String otp) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "User not found with this email : " + email);
        }
        if (userByEmail.get().isVerified()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already verified");
        }
        if (!userByEmail.get().getEmailVerificationOtp().equals(otp)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Incorrect verification code");
        }

        User verifyUser = userByEmail.get();
        verifyUser.setVerified(true);
        userRepository.save(verifyUser);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("message", "Account Verified Successfully");
        response.put("isVerified", true);
        return response;
    }

    private Authentication authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, "username or password is incorrect");
        }
    }

    private String generateEmailVerificationOtp() {
        Random random = new Random();
        Integer otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    public void sendVerificationEmail(String username, String email, String otp) {
        String subject = "Verification code";
        // String body = "Hi, " + username + " !\n" +
        // "Thanks for registering with us.\n" +
        // "Please verify your account using the following code:\n" +
        // otp + "\n\n" +
        // "Happy Hacking,\n" +
        // "Team Journal App !";
        String body = """
                   <p>Hi %s,</p>
                   <p>Thanks for registering with Journal App! We're excited to have you on board.</p>
                   <p>To complete your registration and start journaling, please verify your account using the following code:</p>
                   <h2>%s</h2>
                   <p>To complete your registration, enter this code on the verification page</p>
                   <p>Please keep this code confidential and do not share it with anyone.</p>
                   <p>Warm regards,</p>
                   <p>Team Journal App</p>
                """
                .formatted(username, otp);
        emailService.sendEmail(email, subject, body);
    }
}
