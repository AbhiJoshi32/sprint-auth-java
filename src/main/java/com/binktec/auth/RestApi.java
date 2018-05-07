package com.binktec.auth;

import com.binktec.auth.Exception.BadTokenException;
import com.binktec.auth.Exception.EmailExistsException;
import com.binktec.auth.Exception.UsernameExistsException;
import com.binktec.auth.model.RegisterUserApi;
import com.binktec.auth.model.UserInfoApi;
import com.binktec.auth.model.Users;
import com.binktec.auth.model.VerificationToken;
import com.binktec.auth.registration.OnRegistrationCompleteEvent;
import com.binktec.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@RestController
@RequestMapping("/")
public class RestApi {
    private final IUserService service;
    private final
    ApplicationEventPublisher eventPublisher;

    @Autowired
    public RestApi(IUserService service, ApplicationEventPublisher eventPublisher) {
        this.service = service;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping("/user")
    public UserInfoApi user(Authentication authentication) throws UsernameNotFoundException{
        return new UserInfoApi(service.getUserByUsername(authentication.getName()));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public ResponseEntity register(@RequestBody RegisterUserApi registerUserApi,WebRequest request) throws EmailExistsException, UsernameExistsException{
        Users users = service.registerNewUserAccount(registerUserApi);
        String appUrl = request.getContextPath();
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (users, request.getLocale(), appUrl));
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
    public UserInfoApi confirmRegistration
            (@RequestParam("token") String token) throws BadTokenException{

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            throw new BadTokenException();
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new BadTokenException();
        }

        Users user = service.verifyUser(verificationToken);
        return new UserInfoApi(user);
    }
}