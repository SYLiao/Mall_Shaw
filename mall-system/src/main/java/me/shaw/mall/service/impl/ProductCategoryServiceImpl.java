package me.shaw.mall.service.impl;

import me.shaw.mall.model.ProductCategory;
import me.shaw.mall.repository.ProductCategoryRepository;
import me.shaw.mall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> listAllCategory() {
        Iterable<ProductCategory> iter = productCategoryRepository.findAll();
        return (List<ProductCategory>) iter;
    }

    @Override
    public int createCategory(ProductCategory pc) {
        productCategoryRepository.save(pc);
        return 1;
    }

    @Override
    public int updateCategory(Long id, ProductCategory pc) {
        pc.setId(id);
        productCategoryRepository.save(pc);
        return 1;
    }

    @Override
    public int deleteCategory(Long id) {
        productCategoryRepository.deleteById(id);
        return 1;
    }

    @Override
    public Page<ProductCategory> pageCategory(int pageNum, int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        return productCategoryRepository.findAll(pageable);
    }

    @Override
    public ProductCategory getCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElse(null);
        return productCategory;
    }
}
