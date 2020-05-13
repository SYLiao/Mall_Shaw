package me.shaw.mall.dao;

import me.shaw.mall.elasticsearch.document.EsProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
