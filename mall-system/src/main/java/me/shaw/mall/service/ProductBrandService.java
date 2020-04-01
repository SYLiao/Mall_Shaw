package me.shaw.mall.service;


import me.shaw.mall.model.ProductBrand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductBrandService {

    List<ProductBrand> listAllBrand();

    int createBrand(ProductBrand pb);

    int updateBrand(Long id, ProductBrand pb);

    int deleteBrand(Long id, ProductBrand pb);

    Page<ProductBrand> pageBrand(int pageNum, int PageSize);

    ProductBrand getBrand(Long id);
}
