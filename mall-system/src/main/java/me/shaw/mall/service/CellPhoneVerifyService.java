package me.shaw.mall.service;

import me.shaw.mall.common.CommonResult;

public interface CellPhoneVerifyService {

    CommonResult generateVerificationCode(String phoneNumber);

    CommonResult verifyCode(String phoneNumber, String authCode);
}
