package template.string;

import java.util.ArrayList;

public class KMP {

    public ArrayList<Integer> kmpSearch(final String S, final String keyword) {
        int hLen = S.length(), kLen = keyword.length();
        ArrayList<Integer> ret = new ArrayList<>();
        int[] pi = getPartialMatch(keyword);
        int begin = 0, matched = 0;

        while (begin <= hLen - kLen) { //남은 문자열이 부분 문자열 길이보다 짧으면 종료
            if (matched < kLen && S.charAt(begin + matched) == keyword.charAt(matched)) { //글자가 일치하면 matched 증가
                matched++;
                if (matched == kLen) ret.add(begin); //keyword와 모두 일치하면 답에 추가
            } else {
                if (matched == 0) begin++; //예외: matched 가 0인 경우 다음 칸에서 부터 진행
                else {
                    begin += matched - pi[matched - 1]; //일치하는 접미사의 시작위치로 begin 이동
                    matched = pi[matched - 1]; //matched 를 일치하는 접미사의 길이로 변경
                }
            }
        }
        return ret;
    }

    /**
     * 전체 문자열의 부분 문자열과 keyword가 부분적으로 일치하는 문자열은 keyword의 부분 문자열과 같다.
     * keyword에서 자기 자신을 찾으면서 나타나는 부분 일치를 이용해 pi[]를 계산
     * pi[i] : keyword[...i]의 접미사도 되고 접두사도 되는 문자열의 최대길이
     */
    private int[] getPartialMatch(final String keyword) {
        int len = keyword.length();
        int[] pi = new int[len];

        // begin == 0 이면 자기 자신을 찾는다.
        int begin = 1, matched = 0;

        // 비교할 문자가 N의 끝에 도달할때까지 찾으면서 부분일치를 모두 기록
        while (begin + matched < len) {
            if (keyword.charAt(begin + matched) == keyword.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) begin++;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }
}

