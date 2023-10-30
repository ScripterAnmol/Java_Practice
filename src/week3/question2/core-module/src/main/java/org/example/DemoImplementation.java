package org.example;

import org.apache.commons.lang3.StringUtils;
public class DemoImplementation {
    public static void main(String[] args) {
        String input = "Hello, Commons Lang!";

        // Using StringUtils from Commons Lang to reverse the string
        String reversed = StringUtils.reverse(input);

        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }
}
