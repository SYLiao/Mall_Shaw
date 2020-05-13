package me.shaw.mall.service;

import me.shaw.mall.model.ProductCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> listAllCategory();

    int createCategory(ProductCategory pc);

    int updateCategory(Long id, ProductCategory pc);

    int deleteCategory(Long id);

    Page<ProductCategory> pageCategory(int pageNum, int PageSize);

    ProductCategory getCategory(Long id);
}
