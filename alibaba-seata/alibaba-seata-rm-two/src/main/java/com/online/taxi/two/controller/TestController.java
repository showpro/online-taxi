package com.online.taxi.two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.two.service.RmTwoInterface;
import com.online.taxi.two.service.RmTwoService;

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
	
	@Autowired
	private RmTwoService rmTwoService;
	
	@GetMapping("/rm2")
	public String rm2() {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        rmTwoService.rm2();
		return "rm2 成功";
	}

    @GetMapping("/rm2-update")
    public String rm2Update(){
        rmTwoService.rm2Update();
        return "rm2 update success";
    }

    @Autowired
    private RmTwoInterface rmTwoInterface;

    @GetMapping("/rm2-tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String twoTcc(){

        rmTwoInterface.rm2(null);
        //        int i = 1/0;
        return "success";
    }
}
