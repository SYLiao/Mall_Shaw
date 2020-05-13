package me.shaw.mall.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "mall_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productSn;

    private Long brandId;

    private String brandName;

    private Long productCategoryId;

    private String productCategoryName;

    private String name;

    private String subTitle;

    private String keywords;

    private Integer price;

    private Integer sale;

    private Integer newStatus;

    private Integer recommandStatus;

    private Integer stock;

    private Integer promotionType;
    
    private Integer sort;
}
