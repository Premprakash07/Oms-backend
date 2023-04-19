package com.example.Oms.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test")
    public void testApi(HttpServletResponse response) throws IOException {
        response.getWriter().write("hey kamal");
    }
}