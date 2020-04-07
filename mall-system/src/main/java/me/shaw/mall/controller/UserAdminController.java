package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.Permission;
import me.shaw.mall.model.UserAdmin;
import me.shaw.mall.service.impl.UserAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "UserAdminController")
@RequestMapping("/admin")
public class UserAdminController {

    @Autowired
    private UserAdminServiceImpl userAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "User register")
    public CommonResult register(@RequestBody UserAdmin userAdmin){
        UserAdmin result = userAdminService.register(userAdmin.getUsername(), userAdmin.getPassword());
        if(result == null) {
            return CommonResult.failed("Your register is failed.");
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value = "User login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserAdmin userAdmin){
        String jwt = userAdminService.login(userAdmin.getUsername(), userAdmin.getPassword());
        if(jwt == null){
            return CommonResult.failed("Your login is failed, please check your username and password.");
        }
        Map<String , String> map = new HashMap<>();
        map.put("tokenHead", tokenHead);
        map.put("token", jwt);
        return CommonResult.success(map);
    }

    @ApiOperation(value = "Get user permission")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<Permission>> getPermission(@PathVariable Long adminId){
        List<Permission> result = userAdminService.getPermissionList(adminId);
        if(result == null){
            return CommonResult.failed("Your request is failed");
        }
        return CommonResult.success(result);
    }
}
