package com.web.machineversion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String say(HttpServletRequest request) {
        request.setAttribute("say", "heelasdasdasdo");
        return "test";
    }
}
