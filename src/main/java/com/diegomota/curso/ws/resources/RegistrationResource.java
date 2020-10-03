package com.diegomota.curso.ws.resources;

import com.diegomota.curso.ws.domain.User;
import com.diegomota.curso.ws.domain.VerificationToken;
import com.diegomota.curso.ws.dto.UserDTO;
import com.diegomota.curso.ws.resources.util.GenericResponse;
import com.diegomota.curso.ws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/public")
public class RegistrationResource {

    @Autowired
    UserService userService;

    @PostMapping("/registration/users")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
        User user = this.userService.fromDTO(userDTO);
        this.userService.registerUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/registrationConfirm/users")
    public ResponseEntity<GenericResponse> confirmRegistrationUser(@RequestParam("token") String token) {
        final Object result = this.userService.validateVerificationToken(token);

        if (result == null) {
            return ResponseEntity.ok().body(new GenericResponse(("Success")));
        }
        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new GenericResponse(result.toString()));
    }

    @GetMapping(value = "/resendRegistrationToken/users")
    public ResponseEntity<Void> resendRegistrationToken (@RequestParam("email") String email){
        this.userService.generateNewVerificationToken(email, 0);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/resetPassword/users")
    public ResponseEntity<Void> resetPassword (@RequestParam("email") final String email) {
        this.userService.generateNewVerificationToken(email, 1);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/changePassword/users")
    public ResponseEntity<GenericResponse> changePassword (@RequestParam("idUser") String idUser, @RequestParam("token") String token){
        
        final String result = userService.validationPasswordResetToken(idUser, token);

        if(result == null){
            return ResponseEntity.ok().body(new GenericResponse("Sucess"));
        }

        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new GenericResponse(result.toString()));
    }

    @PostMapping(value = "/savePassword/users")
    public ResponseEntity<GenericResponse> savePassword (@RequestParam("token") String token, @RequestParam("password") String password){
        

        final Object result = this.userService.validateVerificationToken(token);

        if(result != null){
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new GenericResponse(result.toString()));
        }

        final VerificationToken verificationToken = userService.getVerificationToken(token); 

        if (verificationToken != null){
            userService.changePassword(verificationToken.getUser(), password);
        }
        
        return ResponseEntity.noContent().build();
    }
    
}
