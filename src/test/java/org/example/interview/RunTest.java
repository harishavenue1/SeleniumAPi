package org.example.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunTest {

    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 1, 1, 0, 0 ,0};
        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] == 1 && arr[right] == 0) {
                // Swap 1 on left with 0 on right
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
            if (arr[left] == 0) {
                left++;
            }
            if (arr[right] == 1) {
                right--;
            }
        }

        System.out.println("Array finalized is "+ Arrays.toString(arr));

        String str = "a1b2c3"; // example string
        int sum = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                sum += ch - '0'; // convert char digit to int
            }
        }

        System.out.println("Sum of digits: " + sum); // Output: 6


        List<Number> list = new ArrayList<>();
        list.add(5);           // autoboxed to Integer
        list.add(3.14);        // autoboxed to Double

        double sumD = 0;
        for (Number n : list) {
            sumD += n.doubleValue(); // Works for all Number types
        }
        System.out.println("SumD is "+ sumD);

    }
}
