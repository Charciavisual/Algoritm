package org.example.algorithm.codility;

/**
 * @link https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 * @author Changhee Choi
 * @since 01/01/2021
 */
public class BinaryGap {
  public int solution(int N) {
    int ans = 0;
    int count = 0;

    String binaryString = toBinaryString(N);
    char[] charArray = binaryString.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      int curBinaryNumber = charArray[i] - '0';
      if (curBinaryNumber == 1) {
        ans = Math.max(ans, count);
        count = 0;
      } else {
        count++;
      }
    }

    return ans;
  }

  private String toBinaryString(int n) {
    int base = 2;
    StringBuilder builder = new StringBuilder();

    while (n > 0) {
      int remainder = n % base;
      builder.insert(0, remainder);
      n = n / base;
    }

    return builder.toString();
  }
}
