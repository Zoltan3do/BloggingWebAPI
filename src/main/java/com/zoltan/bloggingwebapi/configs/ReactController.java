package com.zoltan.bloggingwebapi.configs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactController {

    @RequestMapping(value = {
            "/",
            "/{path:[^\\.]*}",
            "/**/{path:^(?!api$)[^\\.]*}"
    })
    public String forward() {
        return "forward:/index.html";
    }

}
