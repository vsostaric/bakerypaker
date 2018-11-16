package app.service;

import java.util.List;
import java.util.Map;

public interface PackingService {

    Map<Integer, Integer> packOrder(int orderSize, List<Integer> packSizes);

    String getPackingOutput(String productCode, int orderSize);

}
