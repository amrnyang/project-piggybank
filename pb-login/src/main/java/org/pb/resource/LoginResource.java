package org.pb.resource;

import lombok.Data;
import org.pb.config.JwtHelper;
import org.pb.model.AuthRequest;
import org.pb.model.AuthResponse;
import org.pb.service.LoginUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Data
//@RestController
public class LoginResource {

    private final AuthenticationManager authenticationManager;

    private final LoginUserDetailService loginUserDetailService;

    private final JwtHelper jwtHelper;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            final Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }

        final UserDetails userDetails = loginUserDetailService.loadUserByUsername(authRequest.getUsername());

        final String jwtToken = jwtHelper.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwtToken);
        return ResponseEntity.ok(response);
    }
}
