package me.shaw.mall.service;

import me.shaw.mall.elasticsearch.document.EsProduct;
import me.shaw.mall.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {

    int importAll();

    void delete(Long id);

    EsProduct create(Long id);

    void delete(List<Long> ids);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

}
