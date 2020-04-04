package org.example.algorithm.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 프로그래머스 - N으로 표현
 *
 * @author Changhee Choi
 * @since 04/04/2020
 */
public class ExpressAsN {

    /**
     * @param N      표현에 이용될 숫자 1<= N <=9
     * @param number 원본 숫자 1<= number <= 32000
     * @return N 사용횟수의 최솟값
     * 제한사항
     * 나누기 연산에서 나머지는 무시.
     * 최솟값이 8보다 크면 -1을 return 합니다.
     */
    public int solution(int N, int number) {
        int answer = 0;

        /*
        1) N을 1번쓸 때 만들 수 있는 경우의 수 : N
        2) N을 2번          "             : 1)과 1)의 숫자들로 만들수 있는 경우의 수
        3) N을 3번          "             : 1)과 2), 2)와 1)
        4) N을 4번          "             : 1)과 3), 2)와 2), 3)과 1)
        ...
        8) 까지 이렇게 구한다.

        그 다음 1번 Set 에서 부터 number가 포함된 곳을 찾는다
        */

        ArrayList<HashSet<String>> numberSetList = makeNumberSetList(N);

        answer = findNumber(numberSetList, number);

        return answer;
    }
    
    private ArrayList<HashSet<String>> makeNumberSetList(int N) {
        //덧셈, 곱셈은 순서가 바뀌어도 값이 같으므로 중복된 값 처리를 위해 HashSet 사용
        ArrayList<HashSet<String>> numberSetList = new ArrayList<>();
        numberSetList.add(new HashSet<>()); //0번 인덱스에 더미 Set을 추가

        //N을 1번 사용할때
        HashSet<String> numberSet = new HashSet<>();
        numberSet.add(makeNumberByN(N, 1));
        numberSetList.add((HashSet) numberSet.clone());

        //N을 2회이상 사용할때
        for (int i = 2; i < 9; i++) {

            numberSet = new HashSet<>();

            numberSet.add(makeNumberByN(N, i)); // N을 i번 사용했을때의 기본 숫자 생성 - N, NN, NNN...

            for (int j = 1; j < i; j++) {
                HashSet<String> setA = numberSetList.get(j);
                HashSet<String> setB = numberSetList.get(i - j);

                Iterator<String> itrSetA = setA.iterator();

                while (itrSetA.hasNext()) {
                    int numberOfSetA = Integer.parseInt(itrSetA.next());
                    Iterator<String> itrSetB = setB.iterator();

                    while (itrSetB.hasNext()) {
                        int numberOfSetB = Integer.parseInt(itrSetB.next());
                        numberSet.add(String.valueOf(numberOfSetA + numberOfSetB));
                        numberSet.add(String.valueOf(numberOfSetA - numberOfSetB));
                        numberSet.add(String.valueOf(numberOfSetA * numberOfSetB));

                        //나누는 값이 0이 아닐때만
                       if (numberOfSetB > 0) {
                            numberSet.add(String.valueOf(numberOfSetA / numberOfSetB));
                        }
                    }
                }
            }
            
            numberSetList.add((HashSet)numberSet.clone());
        }

        return (ArrayList)numberSetList.clone();
    }

    private int findNumber(ArrayList<HashSet<String>> numberSetList, int number) {
        int ret = -1;

        for (int i = 1; i < numberSetList.size(); i++) {
            HashSet<String> numberSet = numberSetList.get(i);
            Iterator<String> itrNumberSet = numberSet.iterator();
            boolean findNumber = false;

            while(itrNumberSet.hasNext()) {
                if(itrNumberSet.next().equals(String.valueOf(number))) {
                    ret = i;
                    findNumber = true;
                    break;
                }
            }

            if(findNumber) break;
        }

        return ret;
    }

    private String makeNumberByN(int N, int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(N);
        }
        return sb.toString();
    }
}
