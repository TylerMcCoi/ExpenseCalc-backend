package com.example.demo.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/net")
public class NetController {

    private final NetService netService;

    @Autowired
    public NetController(NetService netService){this.netService = netService;}

    @GetMapping
    public Net getAccountNet() {
        return netService.getNetforAccount();
    }

    @GetMapping("{profileID}")
    public Net getProfileNet(@PathVariable Long profileID) {
        return netService.getNetforProfile(profileID);
    }

    @GetMapping("full")
    public NetAccount getAccountNetFull() {
        return netService.getTotalNetforAccount();
    }
}
