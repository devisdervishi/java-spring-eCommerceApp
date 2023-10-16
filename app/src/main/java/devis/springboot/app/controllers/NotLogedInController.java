package devis.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(origins = "*")
public class NotLogedInController {

    @GetMapping("/app/signin")
    String  login(Model model){
        return "login";
    }

    @GetMapping("/error")
    String  error(Model model){
        return "<h1>Some error occurred<h1>";
    }
}
