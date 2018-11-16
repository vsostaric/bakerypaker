package app.service.impl;

import app.service.PackingService;
import app.service.ProductService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PackingServiceImpl implements PackingService {

    private ProductService productService;

    public PackingServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Map<Integer, Integer> packOrder(int orderSize, String productCode) {
        List<Integer> productPackSizes = productService.getPackSizes(productCode);
        return packOrder(orderSize, productPackSizes);
    }

    @Override
    public String getPackingOutput(int orderSize, String productCode) {

        Map<Integer, BigDecimal> prices = productService.getPackPrices(productCode);
        Map<Integer, Integer> packs = packOrder(orderSize, productCode);
        BigDecimal price = calculateFullPrice(packs, prices);

        StringBuilder builder = new StringBuilder();

        builder.append(orderSize);
        builder.append(" ");
        builder.append(productCode);
        builder.append(" ");
        builder.append(price);
        builder.append("\n");

        for (Integer pack : packs.keySet()) {
            builder.append("\t");
            builder.append(packs.get(pack));
            builder.append(" * ");
            builder.append(pack);
            builder.append(" $");
            builder.append(prices.get(pack));
            builder.append("\n");
        }

        return builder.toString();
    }

    private BigDecimal calculateFullPrice(Map<Integer, Integer> packed, Map<Integer, BigDecimal> prices) {

        BigDecimal price = BigDecimal.ZERO;

        for (Integer pack : packed.keySet()) {
            price = price.add(prices.get(pack).multiply(new BigDecimal(packed.get(pack))));
        }

        return price;
    }

    private Map<Integer, Integer> packOrder(int orderSize, List<Integer> packSizes) {

        List<Integer> sortedPackSizes = packSizes.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Map<Integer, Integer> packed = new HashMap<>();

        int i = 0;
        int j = 1;
        while (i < sortedPackSizes.size()) {
            List<Integer> combination = new ArrayList<>();
            combination.add(sortedPackSizes.get(i));
            combination.addAll(sortedPackSizes.subList(j, sortedPackSizes.size()));
            if (pack(orderSize, combination, packed)) {
                return packed;
            }
            packed = new HashMap<>();
            if (j == sortedPackSizes.size() - 1) {
                i++;
                j = i + 1;
            } else {
                j++;
            }
        }

        return packed;
    }

    private boolean pack(int orderSize, List<Integer> sortedPackSizes, Map<Integer, Integer> packed) {
        int i = 0;
        while (orderSize > 0 && i < sortedPackSizes.size()) {

            int packSize = orderSize / sortedPackSizes.get(i);
            packed.put(sortedPackSizes.get(i), packSize);
            orderSize -= sortedPackSizes.get(i) * packSize;
            i++;

        }

        if (orderSize == 0) {
            return true;
        }
        return false;
    }

}
