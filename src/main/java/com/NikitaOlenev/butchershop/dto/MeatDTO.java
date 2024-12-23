package com.NikitaOlenev.butchershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeatDTO {
    private String name;
    private Integer price;
    private String text;
}
