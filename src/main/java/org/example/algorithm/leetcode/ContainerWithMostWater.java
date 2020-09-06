package org.example.algorithm.leetcode;

/**
 * Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * @author Changhee Choi
 * @since 06/09/2020
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = calcArea(left, right, height);

        while (left < right) {
            if (height[left] <= height[right]) {
                left++;
            }else {
                right--;
            }

            area = Math.max(area, calcArea(left, right, height));
        }

        return area;
    }

    private int calcArea(int left, int right, int[] heights) {
        int width = right - left;
        int height = heights[left] < heights[right] ? heights[left] : heights[right];
        return width * height;
    }
}
