package com.rainea.troubleshoot.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liulang
 * @date 2021-08-04
 **/

@EqualsAndHashCode
@Setter
@Getter
public class Product {

    private String id;

    private Double price;
}
