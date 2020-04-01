package me.shaw.mall.model;

import com.sun.tools.javah.Gen;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Brand")
public class ProductBrand implements Serializable {

    @ApiModelProperty(value = "Brand's ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Brand's name")
    private String brandName;

    @ApiModelProperty(value = "First letter of brand")
    private String firstLetter;

    @ApiModelProperty(value = "Brand's priority")
    private int sort;

    @ApiModelProperty(value = "Brand's factory status")
    private int factoryStatus;

    @ApiModelProperty(value = "Brand's products' number")
    private int  productCount;

    @ApiModelProperty(value = "Brand's comments' number")
    private int productConmmentCount;

    @ApiModelProperty(value = "Brand's logo")
    private String logoUrl;

    @ApiModelProperty(value = "Brand's picture")
    private String bigPic;

    @ApiModelProperty(value = "Brand's story")
    private String brandStory;
}
