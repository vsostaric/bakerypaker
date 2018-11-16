package app.service.impl;

import app.service.PackingService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackingServiceImplTest {

    private PackingService service = new PackingServiceImpl();

    @Test
    public void testPackingService() {

        List<Integer> packs = Lists.newArrayList(5, 3);
        Map<Integer, Integer> packed = service.packOrder(10, packs);

        assertNotNull(packed);
        assertTrue(packed.size() == 1);
        assertTrue(packed.get(5) == 2);


    }

    @Test
    public void testPackingService2() {

        List<Integer> packs = Lists.newArrayList(8, 5, 2);
        Map<Integer, Integer> packed = service.packOrder(14, packs);

        assertNotNull(packed);
        assertTrue(packed.size() == 2);
        assertTrue(packed.get(8) == 1);
        assertTrue(packed.get(2) == 3);


    }

    @Test
    public void testPackingService3() {

        List<Integer> packs = Lists.newArrayList(9, 5, 3);
        Map<Integer, Integer> packed = service.packOrder(13, packs);

        assertNotNull(packed);
        assertTrue(packed.size() == 2);
        assertTrue(packed.get(5) == 2);
        assertTrue(packed.get(3) == 1);


    }

}
