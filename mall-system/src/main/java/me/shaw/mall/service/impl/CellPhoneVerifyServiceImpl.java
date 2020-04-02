package me.shaw.mall.service.impl;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.service.CellPhoneVerifyService;
import me.shaw.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CellPhoneVerifyServiceImpl implements CellPhoneVerifyService {

    @Autowired
    private RedisServiceImpl redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long REDIS_EXPIRE_SECONDS;

    @Override
    public CommonResult generateVerificationCode(String phoneNumber) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_PREFIX_AUTH_CODE+phoneNumber, sb.toString());
        redisService.expire(REDIS_PREFIX_AUTH_CODE+phoneNumber, REDIS_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "You got auth code.");
    }

    @Override
    public CommonResult verifyCode(String phoneNumber, String authCode) {
        if(authCode.isEmpty()){
            return CommonResult.failed("Please input auth code.");
        }
        String realCode = redisService.get(REDIS_PREFIX_AUTH_CODE+phoneNumber);
        boolean result = authCode.equals(realCode);
        if(result){
            return CommonResult.success(null, "Your code is correct.");
        }
        else{
            return CommonResult.failed("Your code is not correct.");
        }
    }
}
