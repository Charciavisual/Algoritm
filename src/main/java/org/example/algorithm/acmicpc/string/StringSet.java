package org.example.algorithm.acmicpc.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문자열 - 문자열 집합
 *
 * @author Changhee Choi
 * @since 17/07/2020
 */
public class StringSet {

    private Trie trie;
    private List<String> words;

    public int solution() {
        int answer = 0;
        init();
        answer = findWords();
        return answer;
    }

    private void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        trie = new Trie();
        words = new ArrayList<>();

        try {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < nums[0]; i++) {
                trie.insert(br.readLine());
            }

            for (int i = 0; i < nums[1]; i++) {
                words.add(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("입력을 초기화 하는 과정에서 오류가 발생했습니다.");
        }

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("BufferedReader close failed.");
        }
    }

    private int findWords() {
        int count = 0;
        for (String word : words) {
            if (trie.find(word)) count++;
        }
        return count;
    }

    class Trie {
        private static final int ALPHABET_SIZE = 26;

        class TrieNode {
            private final TrieNode[] children = new TrieNode[ALPHABET_SIZE];
            private boolean isEndOfWord;

            public TrieNode() {
                this.isEndOfWord = false;
                for (int i = 0; i < ALPHABET_SIZE; i++)
                    children[i] = null;
            }
        }

        private TrieNode root = new TrieNode();

        /*단어 추가*/
        public void insert(String word)
        {
            int level;
            int length = word.length();
            int index;

            TrieNode current = root;

            for (level = 0; level < length; level++)
            {
                index = word.charAt(level) - 'a';
                if (current.children[index] == null)
                    current.children[index] = new TrieNode();

                current = current.children[index];
            }

            // mark last node as leaf
            current.isEndOfWord = true;
        }

        /*단어 검색*/
        public boolean find(String word) {
            int level;
            int length = word.length();
            int index;

            //루트 노드에서 시작
            TrieNode current = root;

            for (level = 0; level < length; level++)
            {
                index = word.charAt(level) - 'a';

                if (current.children[index] == null)
                    return false;

                current = current.children[index];
            }

            return (current != null && current.isEndOfWord);
        }
    }
}
