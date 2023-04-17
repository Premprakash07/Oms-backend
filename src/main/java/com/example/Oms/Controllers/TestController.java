package com.example.Oms.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TestController {

    @PostMapping("/test")
    public void testApi(HttpServletResponse response, @RequestBody HashMap<String, Object> body) throws IOException {
        System.out.println(body.toString());
        response.getWriter().write("hey kamal");
    }
}
