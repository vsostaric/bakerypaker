package app.service.impl;

import app.service.PackingService;

import java.util.*;
import java.util.stream.Collectors;

public class PackingServiceImpl implements PackingService {

    public Map<Integer, Integer> packOrder(int orderSize, List<Integer> packSizes) {

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

    @Override
    public String getPackingOutput(String productCode, int orderSize) {
        return null;
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
