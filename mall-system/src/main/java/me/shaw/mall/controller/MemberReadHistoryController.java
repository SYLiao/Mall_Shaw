package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.mongodb.document.MemberReadHistory;
import me.shaw.mall.service.MemberHistoryService;
import org.elasticsearch.monitor.os.OsStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Control member's read history")
@RequestMapping(value = "/memberReadHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberHistoryService memberHistoryService;

    @ApiOperation("Create record")
    @RequestMapping(value = "/createHistory", method = RequestMethod.POST)
    public CommonResult createHistory(@RequestBody MemberReadHistory memberReadHistory){
        memberHistoryService.createMemberHistory(memberReadHistory);
        return CommonResult.success(memberReadHistory);
    }

    @ApiOperation("Delete record")
    @RequestMapping(value = "/deleteHistory/{id}", method = RequestMethod.GET)
    public CommonResult deleteHistory(@PathVariable Long id){
        memberHistoryService.deleteById(id);
        return CommonResult.success("successful deleted");
    }

    @ApiOperation("Find all record")
    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    public CommonResult findById(@RequestParam  Long memberId){
        return CommonResult.success(memberHistoryService.listAll(memberId));
    }
}
