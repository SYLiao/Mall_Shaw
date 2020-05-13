package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.ProductCategory;
import me.shaw.mall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Product_Category_Controller")
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation("Create new product category")
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public CommonResult createProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.createCategory(productCategory);
        return CommonResult.success(productCategory);
    }

    @ApiOperation("Delete product category")
    @RequestMapping(value = "/deleteCategory/{id}")
    public CommonResult deleteProductCategory(@RequestParam long id){
        int result = productCategoryService.deleteCategory(id);
        if(result == 1){
            return CommonResult.success("Delete successful.");
        }
        else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("Update exist product category")
    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.PUT)
    public CommonResult updateProductCategory(@RequestParam long id, @RequestBody ProductCategory productCategory){
        int result = productCategoryService.updateCategory(id, productCategory);
        if(result == 1){
            return CommonResult.success(productCategory);
        }
        else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("List all product category")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult listAllCategory(){
        List<ProductCategory> result = productCategoryService.listAllCategory();
        if(result == null){
            return CommonResult.failed();
        }
        else{
            return CommonResult.success(result);
        }
    }
}
