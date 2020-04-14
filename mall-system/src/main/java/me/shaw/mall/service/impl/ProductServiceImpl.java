package me.shaw.mall.service.impl;

import me.shaw.mall.model.Product;
import me.shaw.mall.repository.ProductRepository;
import me.shaw.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public int importAll() {
        return 0;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product create(@RequestBody Product product) {
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public Page<Product> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
