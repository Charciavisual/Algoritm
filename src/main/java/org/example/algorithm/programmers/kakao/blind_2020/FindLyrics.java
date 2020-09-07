package org.example.algorithm.programmers.kakao.blind_2020;

import java.util.HashMap;
import java.util.Map;

/**
 * 2020 카카오 공채 - 가사 검색
 * https://programmers.co.kr/learn/courses/30/lessons/60060
 *
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class FindLyrics {
    private Trie[] tries = new Trie[10001];
    private Trie[] reverseTries = new Trie[10001];

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        for (int i = 1; i < 10001; i++) {
            tries[i] = new Trie();
            reverseTries[i] = new Trie();
        }

        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            int wordLen = word.length();
            tries[wordLen].insert(word);
            reverseTries[wordLen].insert(sb.reverse().toString());
        }

        int idx = 0;
        for (String query : queries) {
            int count = 0;
            if (query.startsWith("?")) {
                StringBuilder reverseQuery = new StringBuilder(query).reverse();
                int nonWildcardCharsLen = reverseQuery.indexOf("?");
                count = reverseTries[query.length()].find(reverseQuery.substring(0, nonWildcardCharsLen));
            } else {
                int nonWildcardCharsLen = query.indexOf("?");
                count = tries[query.length()].find(query.substring(0, nonWildcardCharsLen));
            }
            answer[idx++] = count;
        }
        return answer;
    }
}

class TrieNode {
    private final Map<Character, TrieNode> children;
    private int count;

    public TrieNode() {
        this.children = new HashMap<>();
        this.count = 0;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /*단어 추가*/
    public void insert(String word) {
        TrieNode current = root; //1.루트 노드를 시작 노드로 지정한다.
        for (int i = 0; i < word.length(); i++) {
            current.increaseCount();
            //computeIfAbsent(key, mappingFunction) : 만일 키가 없으면 매핑된 함수를 실행하여 계산된 value로 (key,value)쌍을 만들고 맵에 저장한 후 반환한다.
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());

        }
    }

    /*단어 검색*/
    public int find(String word) {
        //루트 노드에서 시작
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            //현재 노드의 서브트리 중 현재 문자에 매핑되는 노드가 있는지 검사한다.
            TrieNode node = current.getChildren().get(ch);
            //만약 찾을 수 없다면 검색을 종료
            if (node == null) {
                return 0;
            }
            current = node;
        }

        return current.getCount();
    }
}
