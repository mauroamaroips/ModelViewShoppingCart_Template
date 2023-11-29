package com.pa.pattern.modelview.model;

import java.util.HashMap;
import java.util.Map;

public class ProductListFactory {

    private static Map<Integer, Product> products = generateProductList();

    private static Map<Integer, Product> generateProductList() {


        Map<Integer, Product> products = new HashMap();
        products.put(0, new Product(0,"Powerbank", 30));
        products.put(1, new Product(1,"PSP4", 350));
        products.put(2, new Product(2,"Gum", 0.5));
        products.put(3, new Product(3,"Milk", 1.2));
        products.put(4, new Product(4,"Pencil", 1));
        products.put(5, new Product(5,"Smartphone", 300));
        products.put(6, new Product(6,"BookA", 13));
        products.put(7, new Product(7,"Chocolat", 2.2));
        return products;

    }

    public static Product createProduct(int id) {

        Product p = products.get(id);
        if (p == null) throw new ShoppingCartException("product " + id + " does not exist");
        return p;

    }


}
