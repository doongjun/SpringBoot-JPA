package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    public Long id;

    public String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
