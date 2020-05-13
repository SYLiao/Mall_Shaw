package me.shaw.mall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long parent_id;
    private String name;
    private int level;
    private int count;
    private String unit;
    private int sort;
    private String icon;
    private String keywords;
    private String description;

}
