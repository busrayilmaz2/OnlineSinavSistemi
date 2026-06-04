package com.busra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
                <html>
                <head>
                    <title>Çevrimiçi Anket Sistemi</title>
                </head>
                <body>
                    <h1>Çevrimiçi Sınav ve Anket Sistemi</h1>
                    <p>Arayüz başarıyla çalışıyor.</p>
                </body>
                </html>
                """;
    }
}