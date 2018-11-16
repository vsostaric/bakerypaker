package app;

import app.service.PackingService;
import app.service.ProductService;
import app.service.impl.PackingServiceImpl;
import app.service.impl.ProductServiceImpl;

public class Main {

    public static void main(String[] args) {

        boolean argsOK = checkArgs(args);

        if(!argsOK) {
            System.out.println("Invalid arguments. Two arguments needed: ordersSize and productCode.");
            System.out.println("Example: java -jar *.jar 10 VS5");
            return;
        }

        ProductService productService = new ProductServiceImpl("products.json");
        PackingService packingService = new PackingServiceImpl(productService);

        int orderSize = Integer.parseInt(args[0]);
        String productCode = args[1];

        System.out.println(packingService.getPackingOutput(orderSize, productCode));


    }

    private static boolean checkArgs(String[] args) {
        return (args != null && args.length >= 2);
    }

}
