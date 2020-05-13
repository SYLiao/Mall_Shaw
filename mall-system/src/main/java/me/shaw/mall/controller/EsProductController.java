package me.shaw.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.shaw.mall.common.CommonResult;
import me.shaw.mall.elasticsearch.document.EsProduct;
import me.shaw.mall.service.impl.EsProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "ProductController")
@RequestMapping(value = "/product")
public class EsProductController {

    @Autowired
    private EsProductServiceImpl productService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = productService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "Delete product with id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "create new product")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public CommonResult createProduct(@PathVariable Long id){
        EsProduct esProduct = productService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "search product")
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public CommonResult searchProduct(@RequestParam(required = false) String keyword,
                                      @RequestParam(required = false, defaultValue = "0") int pageNum,
                                      @RequestParam(required = false, defaultValue = "5") int pageSize){
        Page<EsProduct> page = productService.search(keyword, pageNum, pageSize);
        return CommonResult.success(page);
    }
}
