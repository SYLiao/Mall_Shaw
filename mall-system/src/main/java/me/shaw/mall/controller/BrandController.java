package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonPage;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.ProductBrand;
import me.shaw.mall.service.impl.ProductBrandServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@Api(tags = "BrandController")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private ProductBrandServiceImpl productBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @ApiOperation("Create a new Brand")
    @RequestMapping(value = "/createBrand", method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody ProductBrand brand){
        CommonResult commonResult;
        int count = productBrandService.createBrand(brand);
        if(count == 0){
            commonResult = CommonResult.failed("Failed");
            LOGGER.debug("Create brand faild:{}", brand);
        }
        else{
            commonResult = CommonResult.success(brand);
            LOGGER.debug("Create brand success:{}", brand);
        }
        return commonResult;
    }

    @ApiOperation("List all exist Brands.")
    @RequestMapping(value = "/listAllBrand", method = RequestMethod.GET)
    public CommonResult<List<ProductBrand>> listAll(){
        return CommonResult.success(productBrandService.listAllBrand());
    }

    @ApiOperation("Update exist brand.")
    @RequestMapping(value = "/updateBrand/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody ProductBrand pb){
        int result = productBrandService.updateBrand(id, pb);
        if(result == 1){
            LOGGER.debug("Update brand Success:{}", pb);
            return CommonResult.success(pb);
        }
        else{
            LOGGER.debug("Update brand failed:{}", pb);
            return CommonResult.failed("Failed");
        }
    }

    @ApiOperation("Delete exist brand.")
    @RequestMapping(value = "/deleteBrand/{id}", method = RequestMethod.DELETE)
    public CommonResult deleteBrand(@PathVariable("id") Long id, @RequestBody ProductBrand pb){
        int result = productBrandService.deleteBrand(id, pb);
        if(result == 1){
            LOGGER.debug("Delete brand success:{}", pb);
            return CommonResult.success(pb);
        }
        else{
            LOGGER.debug("Delete brand failed:{}", pb);
            return CommonResult.failed("Failed");
        }
    }

    @ApiOperation("Show brands in a single page.")
    @RequestMapping(value = "/listBrand", method = RequestMethod.GET)
    public CommonResult<CommonPage<ProductBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "1") int pageSize){
        Page<ProductBrand> page = productBrandService.pageBrand(pageNum, pageSize);
        List<ProductBrand> brands = productBrandService.listBrand(page);
        return CommonResult.success(CommonPage.restPage(brands));
    }

    @ApiOperation("Show brand by searching ID.")
    @RequestMapping(value = "/getByBrandId/{id}", method = RequestMethod.GET)
    public CommonResult getById(@PathVariable("id") Long id){
        return CommonResult.success(productBrandService.getBrand(id));
    }
}
