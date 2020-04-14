package me.shaw.mall.repository;

import me.shaw.mall.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ElasticsearchRepository<Product, Long> {

    Page<Product> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
