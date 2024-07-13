package com.cajap.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.DateTimeAtCreation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Long id;
    private String orderDescription;
    private Integer order_quantity;
    private Integer product_id;
    private DateTimeAtCreation dateCreated;

}
