package app.service;

import java.util.Map;

public interface PackingService {

    Map<Integer, Integer> packOrder(int orderSize, String productCode);

    String getPackingOutput(int orderSize, String productCode);

}
