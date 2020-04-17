package org.example.algorithm.template.math;

/**
 * 최소공배수 구하기
 *
 * @author Changhee Choi
 * @since 17/04/2020
 */
public class LCM {
    public int calcLcm(int num1, int num2) {
        GCD g = new GCD();
        int gcd = g.calcGcd(num1, num2);
        int lcm = num1 * num2 / gcd;
        return lcm;
    }
}
