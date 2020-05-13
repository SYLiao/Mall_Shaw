package me.shaw.mall.service.impl;

import me.shaw.mall.common.CommonResult;
import me.shaw.mall.model.Product;
import me.shaw.mall.repository.ProductRepository;
import me.shaw.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductById(Long id) {
        Product result = productRepository.findById(id).orElse(null);
        return result;
    }
}
