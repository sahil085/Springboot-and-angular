package com.ttn.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AngularController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/user/getUser/{id}")

    public User getuserByid(@PathVariable Integer id)
    {
        return userRepo.findUserById(id);
    }

    @GetMapping("/isloggedin")
    public boolean checkIfAlreadyLoggedIn()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken)
        {
            return false;
        }
        return true;
    }
}
