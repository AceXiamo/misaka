package com.misaka.web;

import com.misaka.config.MisakaConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiamo
 * @Description:
 * @ClassName: TestController
 * @date 2021/11/30 15:05
 */
@CrossOrigin
@RestController
@RequestMapping("common")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return MisakaConfiguration.token;
    }

}
