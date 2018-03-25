package com.glen.missingletters.generator;

import java.io.Serializable;
import java.util.function.Supplier;


public class SampleStringGenerator {



    public static class OrderSupplier implements Supplier<String>, Serializable {
        private int ordersGenerated;
        private String includeText = "a";
        private int totalNumberOfOrders = 1, stringLength = 10;


        public SampleStringGenerator.OrderSupplier withIncludeText(String includeText) {
            this.includeText = includeText;
            return this;
        }

        public SampleStringGenerator.OrderSupplier withTotalNumberOfOrders(int totalOrders) {
            this.totalNumberOfOrders = totalOrders;
            return this;
        }

        public SampleStringGenerator.OrderSupplier withStringLength(int stringLength) {
            this.stringLength = stringLength;
            return this;
        }

        @Override
        public String get() {


            if (++ordersGenerated <= totalNumberOfOrders) {

                StringBuilder sb = new StringBuilder("a" + ordersGenerated);

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


