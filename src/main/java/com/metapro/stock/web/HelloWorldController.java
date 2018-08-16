package com.metapro.stock.web;

import com.metapro.stock.service.impl.GatewayServiceImpl;
import com.metapro.stock.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;
    @Autowired
    private GatewayServiceImpl gatewayServiceImpl;

    @RequestMapping("home")
    public String helloWorld(Model model){
        String msg = helloWorldService.getHelloMessage();
        model.addAttribute("msg", msg);
        model.addAttribute("name", 123456);

        gatewayServiceImpl.requestStockstar();
        return "greeting";
    }
}
