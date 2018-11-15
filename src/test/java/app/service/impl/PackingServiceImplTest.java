package app.service.impl;

import app.service.PackingService;
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


        List<Integer> packs = new ArrayList<Integer>();
        packs.add(3);
        packs.add(5);
        Map<Integer, Integer> packed = service.packOrder(10, packs);

        assertNotNull(packed);
        assertTrue(packed.size() == 1);
        assertTrue(packed.get(5) == 2);


    }

}
