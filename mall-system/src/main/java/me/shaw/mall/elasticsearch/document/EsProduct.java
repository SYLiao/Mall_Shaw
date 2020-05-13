package me.shaw.mall.elasticsearch.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Document(indexName = "mall_shaw", type = "mall_product", shards = 1, replicas = 0)
public class EsProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(type = FieldType.Keyword)
    private String productSn;

    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    private Long productCategoryId;

    @Field(type = FieldType.Keyword)
    private String productCategoryName;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;

    private Integer price;

    private Integer sale;

    private Integer newStatus;

    private Integer recommandStatus;

    private Integer stock;

    private Integer promotionType;
    
    private Integer sort;

    public EsProduct(Long id, String productSn, String brandName, String productCategoryName, String name, String subTitle, String keywords){
        this.id = id;
        this.productSn = productSn;
        this.brandName = brandName;
        this.productCategoryName = productCategoryName;
        this.name = name;
        this.subTitle = subTitle;
        this.keywords = keywords;
    }

    public EsProduct(){}
}
