package com.online.taxi.driver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.taxi.common.dto.BaseResponse;
import com.online.taxi.common.dto.ResponseResult;
import com.online.taxi.driver.service.RestTemplateRequestService;

/**
 * 司机抢单请求 ---> 订单服务
 *
 * @author yueyi2019
 */
@RestController
@RequestMapping("/grab")
public class GrabOrderController {

	@Autowired
	private RestTemplateRequestService restTemplateRequestService;

    /**
     * 抢单
     *
     * @param orderId 订单id
     * @param driverId 司机id
     * @return
     */
    @GetMapping("/do/{orderId}")
    public ResponseResult grab(@PathVariable("orderId") int orderId, int driverId){
    	// 调用订单服务（多实例）
        restTemplateRequestService.grabOrder(orderId,driverId);
        return ResponseResult.success("");
    }
}