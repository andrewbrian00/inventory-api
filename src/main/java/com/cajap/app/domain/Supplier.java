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
public class Supplier {

    private Long id;
    private String name;
    private String address;

}
