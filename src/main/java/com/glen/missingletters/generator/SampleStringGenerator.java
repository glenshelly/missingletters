package com.glen.missingletters.generator;

import java.io.Serializable;
import java.util.function.Supplier;


/**
 * Generate sample strings
 */
public class SampleStringGenerator {

    public static class StringSupplier implements Supplier<String>, Serializable {
        private int stringsGenerated;
        private String includeText = "a";
        private int totalNumberOfOrders = 1, stringLength = 10;

        public StringSupplier withIncludeText(String includeText) {
            this.includeText = includeText;
            return this;
        }

        public StringSupplier withTotalNumberOfOrders(int totalOrders) {
            this.totalNumberOfOrders = totalOrders;
            return this;
        }

        public StringSupplier withStringLength(int stringLength) {
            this.stringLength = stringLength;
            return this;
        }

        @Override
        public String get() {

            if (++stringsGenerated <= totalNumberOfOrders) {

                StringBuilder sb = new StringBuilder("a" + stringsGenerated);
                while (sb.length() < stringLength) {
                    sb.append(includeText);
                }
                return sb.toString();
            } else {
                return null;
            }
        }
    }
}


