package com.glen.missingletters.generator;

import com.glen.missingletters.MissingLetters;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSubmitter {


    public static void main(String[] args) {

        // Set these variables for what will be submitted
        final String includeText = "abcdefghijklmnopqruvwxy";
        final int numOrders = 1;
        final int stringLength = 100_000_000;
        //////////


        Supplier<String> thisSupplier = new SampleStringGenerator.OrderSupplier()
                .withIncludeText(includeText)
                .withTotalNumberOfOrders(numOrders)
                .withStringLength(stringLength);

        MissingLetters ml = new MissingLetters();
        long start = System.currentTimeMillis();
        final int truncateAt = 50;
        Map<String, String> inputToResults = Stream.generate(thisSupplier)
                .limit(numOrders)
                .collect(Collectors.toMap(key -> key.length() > truncateAt ? key.substring(0, truncateAt)
                        + "... [truncated; full size=" + key.length() + "]" : key, ml::getMissingLetters));

        for (Map.Entry<String, String> mapEntry : inputToResults.entrySet()) {
            final String key = mapEntry.getKey();

            System.out.println("for " + key + "...");
            System.out.println(" --> " + mapEntry.getValue());
        }

        System.out.println("\n\n" + (System.currentTimeMillis() - start) + "ms to complete");

    }


}


