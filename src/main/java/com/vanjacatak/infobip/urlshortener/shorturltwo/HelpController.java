package com.vanjacatak.infobip.urlshortener.shorturltwo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {

    @RequestMapping("/help")
    public String help() {

        return "help.html";
    }

}
