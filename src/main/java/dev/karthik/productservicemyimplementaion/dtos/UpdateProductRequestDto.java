package dev.karthik.productservicemyimplementaion.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}

