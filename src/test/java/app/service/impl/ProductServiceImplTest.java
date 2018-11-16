package app.service.impl;

import app.service.ProductService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceImplTest {

    private ProductService service = new ProductServiceImpl("products_test.json");

    @Test
    public void testGetPackSizes() {

        List<Integer> packSizes = service.getPackSizes("VS5");
        assertNotNull(packSizes);
        assertEquals(packSizes, Lists.newArrayList(5, 3));

        packSizes = service.getPackSizes("MB11");
        assertNotNull(packSizes);
        assertEquals(packSizes, Lists.newArrayList(8, 5, 2));

        packSizes = service.getPackSizes("CF");
        assertNotNull(packSizes);
        assertEquals(packSizes, Lists.newArrayList(9, 5, 3));

    }

    @Test
    public void testGetPackPrices() {

        Map<Integer, BigDecimal> packPrices = service.getPackPrices("VS5");

        assertNotNull(packPrices);

    }

}
