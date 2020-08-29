package org.example.algorithm.leetcode;

/**
 * Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author Changhee Choi
 * @since 29/08/2020
 */
public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArr = new int[nums1.length + nums2.length];
        int nums1Idx = 0, nums2Idx = 0, mergedIdx = 0;
        double answer = 0;

        while (nums1Idx < nums1.length && nums2Idx < nums2.length) {
            if (nums1[nums1Idx] < nums2[nums2Idx]) {
                mergedArr[mergedIdx++] = nums1[nums1Idx++];
            } else if (nums1[nums1Idx] > nums2[nums2Idx]) {
                mergedArr[mergedIdx++] = nums2[nums2Idx++];
            } else {
                mergedArr[mergedIdx++] = nums1[nums1Idx++];
                mergedArr[mergedIdx++] = nums2[nums2Idx++];
            }
        }

        if (nums1Idx >= nums1.length) {
            while (nums2Idx < nums2.length) {
                mergedArr[mergedIdx++] = nums2[nums2Idx++];
            }
        } else {
            while (nums1Idx < nums1.length) {
                mergedArr[mergedIdx++] = nums1[nums1Idx++];
            }
        }

        if (mergedArr.length == 0) {
            return 0;
        }

        int mid = mergedArr.length / 2;

        if (mergedArr.length % 2 == 0) {
            answer = (double) (mergedArr[mid] + mergedArr[mid - 1]) / 2.0;
        } else {
            answer = mergedArr[mid];
        }

        return answer;
    }

    public void runTest(int[] nums1, int[] nums2) {
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
