package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.Order;
import me.shaw.mall.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Order Controller")
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ApiOperation("create new order")
    public CommonResult createOrder(@RequestBody Order order){
        Order result = orderService.createOrder(order.getTransactionValue(), order.getStatus());
        if(result != null){
            return CommonResult.success(result);
        }
        return CommonResult.failed("Create order failed");
    }
}
