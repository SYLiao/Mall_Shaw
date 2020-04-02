package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.service.impl.CellPhoneVerifyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "MemberVerifyController")
@RequestMapping(value = "/member")
public class MemberVerifyController {

    @Autowired
    private CellPhoneVerifyServiceImpl cellPhoneVerifyService;

    @ApiOperation("Generate Verification code.")
    @RequestMapping(value = "/generateCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String phoneNumbe){
        return cellPhoneVerifyService.generateVerificationCode(phoneNumbe);
    }

    @ApiOperation("Verification")
    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public CommonResult verifyAuthCOde(@RequestParam String phoneNumber, @RequestParam String authCode){
        return cellPhoneVerifyService.verifyCode(phoneNumber, authCode);
    }
}
