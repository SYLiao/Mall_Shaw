package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.service.impl.S3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "OssController")
@RequestMapping(value = "/S3")
public class S3Controller {

    @Autowired
    private S3ServiceImpl s3Service;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ApiOperation(value = "download file from S3")
    public CommonResult downloadFile(@RequestParam String keyName){
        try{
            return CommonResult.success(s3Service.downloadFile(keyName));
        } catch (Exception e){
            return CommonResult.failed("download failed.");
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "upload file to S3")
    public CommonResult uploadFile(@RequestParam MultipartFile file){
        try{
            s3Service.uploadFile(file);
            return CommonResult.success("Upload successful.");
        } catch (Exception e){
            return CommonResult.failed("download failed.");
        }
    }

}
