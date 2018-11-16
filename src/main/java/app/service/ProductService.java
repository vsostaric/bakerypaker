package app.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Integer> getPackSizes(String productCode);

    Map<Integer, BigDecimal> getPackPrices(String productCode);

}
