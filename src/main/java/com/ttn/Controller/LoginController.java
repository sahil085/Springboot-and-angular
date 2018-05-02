package com.ttn.Controller;


import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UserRepo userRepo;
@Autowired
cutomeUserDetailsService detailsService;
    @RequestMapping(value = "/token",method = RequestMethod.POST)
    @ResponseBody
    public String  home(@RequestBody String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(token);

        if (request.getHeader("X-Requested-With") == null) {
            // Without the `X-Requested-With` header, this request could be forged. Aborts.
        }

// Set path to the Web application client_secret_*.json file you downloaded from the
// Google API Console: https://console.developers.google.com/apis/credentials
// You can also find your Web application client ID and client secret from the
// console and specify them directly when you create the GoogleAuthorizationCodeTokenRequest
// object.
        String CLIENT_SECRET_FILE = "/home/sahil/Documents/springbootdemo/src/main/resources/client_secret.json";

// Exchange auth code for access token
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(
                        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
        System.out.println(clientSecrets.getDetails().getRedirectUris().get(2));
        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance(),
                        "https://www.googleapis.com/oauth2/v4/token",
                        clientSecrets.getDetails().getClientId(),
                        clientSecrets.getDetails().getClientSecret(),
                        token,
                        clientSecrets.getDetails().getRedirectUris().get(1))  // Specify the same redirect URI that you use with your web
                        // app. If you don't have a web version of your app, you can
                        // specify an empty string.
                        .execute();

        String accessToken = tokenResponse.getAccessToken();
        User user=new User();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        // Use access token to call API
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

//        Drive drive =
//                new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//                        .setApplicationName("Auth Code Exchange Demo")
//                        .build();
//        File file = drive.files().get("appfolder").execute();

// Get profile info from ID token
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();  // Use this value as a key to identify a user.
        String email = payload.getEmail();
            UserDetails userDetails=detailsService.loadUserByUsername(email);
        String name = (String) payload.get("name");

        if(userDetails!=null)
        {
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            System.out.println(pictureUrl);
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            return "login radhe radhe";
        }else {
            User user1=new User();
            user1.setEmail(email);
                    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        System.out.println( bCryptPasswordEncoder.encode("123"));
            user1.setAdminType("SYSTEM ADMIN");
        user1.setPassword(bCryptPasswordEncoder.encode("123"));
        user1.setState("Active");
        user1.setUserName(name);
             userRepo.save(user1);
            UserDetails userDetails1=detailsService.loadUserByUsername(email);

            Authentication auth =
                    new UsernamePasswordAuthenticationToken(userDetails1, null, userDetails1.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            return "login with create new user";
        }

    }

}


