package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.Product;
import me.shaw.mall.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "ProductControllrt")
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @ApiOperation(value = "Delete product with id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "create new product")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public CommonResult createProduct(@RequestBody Product product){
        Product newProduct = productService.create(product);
        return CommonResult.success(newProduct);
    }

    @ApiOperation(value = "search product")
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public CommonResult searchProduct(@RequestParam(required = false) String keyword,
                                      @RequestParam(required = false, defaultValue = "0") int pageNum,
                                      @RequestParam(required = false, defaultValue = "5") int pageSize){
        Page<Product> page = productService.search(keyword, pageNum, pageSize);
        return CommonResult.success(page);
    }
}
