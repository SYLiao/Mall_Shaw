package me.shaw.mall.service;

import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.Product;

public interface ProductService {

    public Product createProduct(Product product);

    public void deleteProductById(Long id);

    public Product findProductById(Long id);
}
