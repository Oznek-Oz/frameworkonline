package framework.groupe6.cd_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping("string")
    public String getString(){ return "Chaine de caractère transmise par cd";}
}


