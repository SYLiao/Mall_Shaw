package me.shaw.mall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class WebLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String username;
    private long startTime;
    private Integer spendTime;
    private String basePath;
    private String uri;
    private String url;
    private String ip;
    private String method;
}
