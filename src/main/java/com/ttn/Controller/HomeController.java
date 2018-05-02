package com.ttn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @Autowired
    NotificationService notificationService;
    @GetMapping("/dummy")
    public @ResponseBody String dummy()
    {
        return "without login";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() throws InterruptedException {
        User user=new User();
        user.setUserName("asd");

//        notificationService.sendNotificaitoin(user);
//        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        System.out.println( bCryptPasswordEncoder.encode("123"));

        return "index";
    }
    @RequestMapping(value = "/angular",method = RequestMethod.GET)
    public String angularPage()
    {
        return "angular/index";
    }
    @RequestMapping(value = "/Callback",method = RequestMethod.GET)
    @ResponseBody
    public String message()
    {
        return "login success";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home()
    {
        sendSMS sms=new sendSMS();
//        sms.sendSms();
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       if(!(authentication instanceof AnonymousAuthenticationToken))
       {
           return "redirect:/success";
       }else {
           return "customloginpage";

       }
    }
    @GetMapping("/success")
    public String succ(HttpServletResponse httpServletResponse)
    {
        return "successpage";
    }
    @GetMapping("/failure")
    public @ResponseBody String fail()
    {
        return "login not done";
    }


}
