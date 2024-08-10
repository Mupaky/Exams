package com.supplyboost;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shopping {
    private static LinkedHashMap<String, LinkedHashMap<String, Boolean>> markets;
    public static void main(String[] args) {
        markets = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Go Shopping")) {
                for (Map.Entry<String, LinkedHashMap<String, Boolean>> market : markets.entrySet()) {
                    System.out.println(market.getKey() + ':');
                    for (Map.Entry<String, Boolean> product : market.getValue().entrySet()) {
                        System.out.println(" - " + product.getKey());
                    }
                }
                break;
            }

            String regex = "^([^->]+)->([^->]+)(?:->(.*))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String command = matcher.group(1);
                String category = matcher.group(2);
                String products = matcher.group(3);


                switch (command) {
                    case "Add":
                        add(category, stringToMap(products));
                        break;
                    case "Important":
                        important(category, stringToMap(products));
                        break;
                    case "Remove":
                        remove(category);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
        }
    }

    private static void add(String shopName, LinkedHashMap<String, Boolean> products) {
        LinkedHashMap<String, Boolean> currentProducts = markets.getOrDefault(shopName, new LinkedHashMap<>());

        if (products != null) {
            for (Map.Entry<String, Boolean> entry : products.entrySet()) {
                String product = entry.getKey();
                if (!isProductInAnyMarket(product)) {
                    currentProducts.put(product, false);
                }
            }
        }

        markets.put(shopName, currentProducts);
    }

    private static void important(String shopName, LinkedHashMap<String, Boolean> products) {
        if (products != null) {
            for (String product : products.keySet()) {
                boolean foundInOtherMarket = false;
                for (Map.Entry<String, LinkedHashMap<String, Boolean>> entry : markets.entrySet()) {
                    LinkedHashMap<String, Boolean> currentProducts = entry.getValue();
                    if (currentProducts.containsKey(product)) {
                        currentProducts.put(product, true);
                        foundInOtherMarket = true;
                    }
                }
                if (!foundInOtherMarket) {
                    LinkedHashMap<String, Boolean> currentProducts = markets.getOrDefault(shopName, new LinkedHashMap<>());
                    currentProducts.putFirst(product, true);
                    markets.put(shopName, currentProducts);
                }
            }
        }
    }

    private static void remove(String shopName){
        LinkedHashMap<String, Boolean> currentProducts = markets.getOrDefault(shopName, new LinkedHashMap<>());
        if(!currentProducts.containsValue(true)){
            markets.remove(shopName);
        }
    }

    private static LinkedHashMap<String, Boolean> stringToMap(String products) {
        LinkedHashMap<String, Boolean> productMap = new LinkedHashMap<>();
        if (products == null || products.isEmpty()) {
            return productMap;
        }
        String[] pr = products.split(",");
        for (String product : pr) {
            productMap.put(product, false);
        }
        return productMap;
    }

    private static boolean isProductInAnyMarket(String product) {
        for (LinkedHashMap<String, Boolean> productList : markets.values()) {
            if (productList.containsKey(product)) {
                return true;
            }
        }
        return false;
    }

}
