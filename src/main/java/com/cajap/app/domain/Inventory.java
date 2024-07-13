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
public class Inventory {

    private Long id;
    private String itemName;
    private Integer itemDescription;
    private Integer productId;
    private Integer supplierId;
    private DateTimeAtCreation dateCreated;

}
