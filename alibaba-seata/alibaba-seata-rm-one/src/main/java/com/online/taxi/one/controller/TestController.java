package com.online.taxi.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.one.service.RmOneInterface;
import com.online.taxi.one.service.RmOneService;

import io.seata.spring.annotation.GlobalTransactional;

/**
 * @author yueyi2019
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/api2")
    public String test() {
        return "service-sms started";
    }

    /**
     * seata AT模式
     */
    @Autowired
    private RmOneService rmOneService;

    @GetMapping("/rm1")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String rm1() {
        rmOneService.rm1();
        return "rm1 成功";
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @GetMapping("/rm1-update")
    public String rm1Update() {
        rmOneService.rm2Update();
        return "rm1 update success";
    }

    /**
     * seata TCC模式
     */
    @Autowired
    private RmOneInterface rmOneInterface;

    @GetMapping("/one-tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        rmOneInterface.rm1(null);
        return "success";
    }
}
