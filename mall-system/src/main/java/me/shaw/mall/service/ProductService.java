package me.shaw.mall.service;

import me.shaw.mall.model.Product;
import me.shaw.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    int importAll();

    void delete(Long id);

    Product create(Product product);

    void delete(List<Long> ids);

    Page<Product> search(String keyword, Integer pageNum, Integer pageSize);

}
