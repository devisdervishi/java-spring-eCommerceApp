package devis.springboot.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotLogedInController {

    @GetMapping("/app")
    String  hello(){
        return "Welcome to our app";
    }
}
