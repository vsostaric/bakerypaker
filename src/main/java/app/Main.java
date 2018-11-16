package app;

import app.service.PackingService;
import app.service.ProductService;
import app.service.impl.PackingServiceImpl;
import app.service.impl.ProductServiceImpl;

public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductServiceImpl("products.json");
        PackingService packingService = new PackingServiceImpl(productService);

        int orderSize = Integer.parseInt(args[0]);;
        String productCode = args[1];

        System.out.println(packingService.getPackingOutput(orderSize, productCode));


    }

}
