package app.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String code;

    private List<Pack> packs;

}
