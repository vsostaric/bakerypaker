package app.service.impl;

import app.service.PackingService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackingServiceImplTest {

    private PackingService service = new PackingServiceImpl(
            new ProductServiceImpl("products_test.json")
    );

    @Test
    public void testPackingService() {

        Map<Integer, Integer> packed = service.packOrder(10, "VS5");

        assertNotNull(packed);
        assertTrue(packed.size() == 1);
        assertTrue(packed.get(5) == 2);


    }

    @Test
    public void testPackingService2() {

        Map<Integer, Integer> packed = service.packOrder(14, "MB11");

        assertNotNull(packed);
        assertTrue(packed.size() == 2);
        assertTrue(packed.get(8) == 1);
        assertTrue(packed.get(2) == 3);


    }

    @Test
    public void testPackingService3() {

        Map<Integer, Integer> packed = service.packOrder(13, "CF");

        assertNotNull(packed);
        assertTrue(packed.size() == 2);
        assertTrue(packed.get(5) == 2);
        assertTrue(packed.get(3) == 1);


    }

}
