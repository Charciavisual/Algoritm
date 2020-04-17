package org.example.algorithm.template.math;

/**
 * 최대공약수 구하기
 *
 * @author Changhee Choi
 * @since 17/04/2020
 */
public class GCD {
    public int calcGcd(int num1, int num2) {
        int temp;
        while (num2 > 0) {
            temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }
        return num1;
    }
}
