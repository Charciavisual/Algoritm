package org.example.algorithm.leetcode;

/**
 * Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author Changhee Choi
 * @since 29/08/2020
 */
public class MedianTwoSortedArrays {
    /**
     * 두 배열을 아래 두 조건을 만족하도록 partition 한다면 median = (max_left + min_right) / 2
     * 조건1. len(left_part_set1) + len(left_part_set2) == len(right_part_set1) + len(right_part_set2)
     * 조건2. max(left_part_set1, left_part_set2) <= min(right_part_set1, right_part_set2)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //nums1.length <= nums2.length 인 상태가 되도록 재귀 호출
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int nums1Len = nums1.length, nums2Len = nums2.length;
        int halfLen = (nums1Len + nums2Len + 1) / 2;

        int left = 0, right = nums1.length;

        while (left <= right) {
            //0 ~ (partitionNums1 - 1) : left part of nums1
            //partitionNums1 ~ (nums1Len - 1): right part of nums1
            int partitionNums1 = (left + right) / 2;

            //nums1, nums2 배열을 merge 했을 때의 중간 지점에서 num1의 left part element 개수를 빼면
            //nums2의 left part element 개수가 된다.
            int partitionNums2 = halfLen - partitionNums1;

            //if partition == 0 (left part is empty set), then maxLeft = -INF
            int maxLeftNums1 = (partitionNums1 == 0) ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
            //if partition == length (right part is empty set), then minRight = INF
            int minRightNums1 = (partitionNums1 == nums1Len) ? Integer.MAX_VALUE : nums1[partitionNums1];

            int maxLeftNums2 = (partitionNums2 == 0) ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
            int minRightNums2 = (partitionNums2 == nums2Len) ? Integer.MAX_VALUE : nums2[partitionNums2];

            //두 배열의 left part의 합집합의 원소가 right part의 합집합의 원소보다 같거나 작으려면
            //maxLeftSet1 <= minRightSet2, maxLeftSet2 <= minRightSet1 조건을 만족하여야 한다
            //조건을 만족하는 partition 위치를 찾는다
            if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
                if ((nums1Len + nums2Len) % 2 == 0) {
                    return (double) (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2;
                } else {
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            } else if (maxLeftNums1 > minRightNums2) {
                right = partitionNums1 - 1;
            } else {
                left = partitionNums1 + 1;
            }
        }

        return -1;
    }

    public void runTest(int[] nums1, int[] nums2) {
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
