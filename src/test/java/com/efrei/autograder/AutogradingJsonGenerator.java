package com.efrei.autograder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import magasin.ProduitTest;

public class AutogradingJsonGenerator {

    public static void main(String[] args) {
        // Specify the class containing the tests
        Class<?> testClass = ProduitTest.class; // Change this to your actual test class

        // Generate the autograding.json content
        String jsonContent = generateAutogradingJson(testClass);

        // Write the content to autograding.json
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".github/classroom/autograding.json"))) {
            writer.write(jsonContent);
            System.out.println("autograding.json generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateAutogradingJson(Class<?> testClass) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n  \"tests\": [\n");

        Method[] methods = testClass.getDeclaredMethods();
        List<String> testMethods = new ArrayList<>();

        // Collect test method names
        for (Method method : methods) {
            if (method.isAnnotationPresent(org.junit.jupiter.api.Test.class)) {
                testMethods.add(method.getName());
            }
        }

        // Generate JSON entries for each test method
        for (int i = 0; i < testMethods.size(); i++) {
            String methodName = testMethods.get(i);
            jsonBuilder.append("    {\n")
                    .append("      \"name\": \"Test ")
                    .append(methodName)
                    .append("\",\n")
                    .append("      \"setup\": \"\",\n")
                    .append("      \"run\": \"./gradlew test --tests magasin.ProduitTest.")
                    .append(methodName)
                    .append("\",\n")
                    .append("      \"input\": \"\",\n")
                    .append("      \"output\": \"tests completed\",\n")
                    .append("      \"comparison\": \"included\",\n")
                    .append("      \"timeout\": 10,\n")
                    .append("      \"points\": 1\n")
                    .append("    }");

            if (i < testMethods.size() - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }
        }

        jsonBuilder.append("  ]\n}");
        return jsonBuilder.toString();
    }
}