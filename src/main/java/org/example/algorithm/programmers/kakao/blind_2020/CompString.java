package org.example.algorithm.programmers.kakao.blind_2020;

public class CompString {
    public int solution(String s) {
        int answer = 987654321;

        //문자열의 길이가 1이면
        if(s.length() == 1) return 1;

        //자르는 단위가 전체 문자열 길이의 절반을 넘어가면 압축되지 않으므로 범위를 줄여준다
        for(int cutLen = 1; cutLen <= s.length()/2; cutLen ++) {
            answer = Math.min(answer, compString(cutLen, s, ""));
        }

        return answer;
    }

    private int compString(int cutLen, String remains, String comp) {

        //남은 문자열이 없다면 압축된 문자열의 길이를 반환
        if (remains.length() == 0) return comp.length();
        //남은 문자열이 자르는 단위보다 짧다면 남은 문자열의 길이를 더한 뒤 길이를 반환
        if (remains.length() < cutLen) {
            return comp.length() + remains.length();
        }

        int ret = 987654321;
        int compCount = 1;
        String word = remains.substring(0, cutLen);
        String nextString = remains.substring(cutLen);

        //압축할 단어가 연속적으로 몇번 나오는지 카운트
        while (true) {
            //남은 문자열이 압축할 단어로 시작하지 않는다면 반복문 종료
            if (!nextString.startsWith(word)) break;

            compCount++;
            nextString = nextString.substring(cutLen);
        }

        //압축할 단어의 횟수가 1(자기 자신뿐)이라면 다음 시작 위치의 문자열을 넘긴다
        if (compCount == 1)
            ret = Math.min(ret, compString(cutLen, remains.substring(cutLen), comp + word));
        //압축할 단어가 2번이상 출현하는 경우 압축된 단어들이 지워진 문자열을 넘긴다 "aab..." -> "b..."
        else
            ret = Math.min(ret, compString(cutLen, nextString, comp + compCount + word));

        return ret;
    }
}
