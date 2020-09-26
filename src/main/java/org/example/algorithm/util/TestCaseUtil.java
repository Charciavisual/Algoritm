package org.example.algorithm.util;

import java.util.*;

/**
 * @author Changhee Choi
 * @since 27/08/2020
 */
public class TestCaseUtil {

    public static String generateString(int len) {
        return generateRandomString(len, Arrays.asList(0, 1, 2));
    }

    public static String generateString(int len, boolean lower, boolean upper, boolean digit) {
        if (!lower && !upper && !digit) {
            throw new IllegalArgumentException("At least one option must be true");
        }

        List<Integer> options = new ArrayList<>();

        if (lower) {
            options.add(0);
        }
        if (upper) {
            options.add(1);
        }
        if (digit) {
            options.add(2);
        }

        return generateRandomString(len, options);
    }

    private static String generateRandomString(int len, List<Integer> options) {
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        int bound = options.size();
        for (int i = 0; i < len; i++) {
            int rIndex = rnd.nextInt(bound);
            switch (options.get(rIndex)) {
                case 0:
                    // a-z
                    builder.append((char) ((rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    builder.append((char) ((rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    builder.append((rnd.nextInt(10)));
                    break;
            }
        }
        return builder.toString();
    }

    public static int generateRandomNumber(int min, int max) {
        Random rnd = new Random();
        int number = rnd.nextInt(max - min + 1) + min;
        return number;
    }

    public static int[] generateRandomNumbers(int size, int min, int max) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = generateRandomNumber(min, max);
        }
        return numbers;
    }

    public static int[] generateRandomUniqueNumbers(int size, int min, int max) {
        if (size > (max - min + 1)) {
            throw new IllegalArgumentException(size + "보다 범위가 작습니다.");
        }
        Set<Integer> numberSet = new HashSet<>(size);
        while (numberSet.size() < size) {
            numberSet.add(generateRandomNumber(min, max));
        }
        int[] numbers = new int[size];
        int idx = 0;
        for (Integer number : numberSet) {
            numbers[idx++] = Integer.valueOf(number);
        }
        return numbers;
    }
}
