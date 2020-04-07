package me.shaw.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Date createTime;

    private float transactionValue;

    private Date expireTime;

    private String status;

    public Order(){
    }

    public Order(Long id, float value, String status){
        this.orderId = id;
        this.transactionValue = value;
        this.status = status;
    }
}
