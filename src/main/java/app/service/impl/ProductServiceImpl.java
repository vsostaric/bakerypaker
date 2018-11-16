package app.service.impl;

import app.model.Product;
import app.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private Gson gson;

    private String productsResourcePath;

    public ProductServiceImpl(String productsResourcePath) {
        this.gson = new GsonBuilder().create();
        this.productsResourcePath = productsResourcePath;
    }

    @Override
    public List<Integer> getPackSizes(String productCode) {

        return getProduct(productCode)
                .getPacks()
                .stream()
                .map(pack -> pack.getSize())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, BigDecimal> getPackPrices(String productCode) {

        return getProduct(productCode)
                .getPacks()
                .stream()
                .collect(Collectors.toMap(
                        pack -> pack.getSize(),
                        pack -> pack.getPrice()
                ));

    }

    private Product getProduct(String code) {
        return Arrays.stream(readProducts())
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    private Product[] readProducts() {
        return gson.fromJson(readProductFile(), Product[].class);
    }

    private String readProductFile() {
        final StringBuilder json = new StringBuilder();
        new BufferedReader
                (new InputStreamReader(
                        ClassLoader.getSystemResourceAsStream(productsResourcePath)))
                .lines()
                .forEach((line) -> json.append(line));

        return json.toString();
    }

}
