package org.example.algorithm.acmicpc.math2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 수학2 - 골드바흐의 추측
 * https://www.acmicpc.net/problem/9020
 *
 * @author Changhee Choi
 * @since 02/07/2020
 */
public class GoldbachConjecture {

    private int count;
    private int[] numbers;

    public int[][] solution() {
        init2();
        int[][] answer = new int[count][2];

        for (int i = 0; i < count; i++) {
            int number = numbers[i];
            ArrayList<Integer> primes = getPrimes(number);
            Partition partition = selectPartition(number, primes);

            answer[i][0] = partition.prime1;
            answer[i][1] = partition.prime2;
        }

        return answer;
    }

    public void init2() {
        count = 5;
        numbers = new int[count];
        numbers[0] = 8;
        numbers[1] = 10;
        numbers[2] = 16;
        numbers[3] = 10000;
        numbers[4] = 4;
    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        count = sc.nextInt();

        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = sc.nextInt();
        }
        sc.close();
    }

    private ArrayList<Integer> getPrimes(int number) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private Partition selectPartition(int number, ArrayList<Integer> primes) {

        PriorityQueue<Partition> pq = new PriorityQueue<>();

        for (int i = 0; i < primes.size(); i++) {
            if (2 * primes.get(i) == number) {
                return new Partition(primes.get(i), primes.get(i));
            }

            for (int j = i; j < primes.size() - 1; j++) {
                if (primes.get(i) + primes.get(j) == number) {
                    pq.add(new Partition(primes.get(i), primes.get(j)));
                }
            }
        }

        return pq.peek();
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;

        //2를 제외한 모든 짝수는 소수가 아님
        if (num % 2 == 0) return false;

        int sqrtn = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrtn; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    class Partition implements Comparable<Partition> {
        private int prime1;
        private int prime2;

        public Partition(int prime1, int prime2) {
            this.prime1 = prime1;
            this.prime2 = prime2;
        }

        public int getDiff() {
            return this.prime2 - this.prime1;
        }

        @Override
        public int compareTo(Partition o) {
            return this.getDiff() - o.getDiff();
        }
    }
}
