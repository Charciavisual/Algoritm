package template.tree.trie;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /*단어 추가*/
    public void insert(String word) {
        TrieNode current = root; //1.루트 노드를 시작 노드로 지정한다.

        //2~3.현재 문자에 대한 기존 참조가 이미 있는 경우 현재 노드를 해당 참조 노드로 설정.
        //현재 문자에 대한 기존 참조가 없다면 새 노드를 생성하고 문자를 현재 문자와 동일하게 설정하고 현재 노드를 이 새 노드로 초기화.
        //4. 모든 문자를 순회할때까지 반복
        for (int i = 0; i < word.length(); i++) {
            //computeIfAbsent(key, mappingFunction) : 만일 키가 없으면 매핑된 함수를 실행하여 계산된 value로 (key,value)쌍을 만들고 맵에 저장한 후 반환한다.
            current = current.getChildren().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        //현재 노드가 완료 노드임을 표시
        current.setEndOfWord(true);
    }

    /*단어 검색*/
    public boolean find(String word) {
        //루트 노드에서 시작
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            //현재 노드의 서브트리 중 현재 문자에 매핑되는 노드가 있는지 검사한다.
            TrieNode node = current.getChildren().get(ch);
            //만약 찾을 수 없다면 검색을 종료하고 false 리턴
            if (node == null) {
                return false;
            }
            current = node;
        }
        //단어를 찾았다면 결과를 리턴
        //단어를 찾았지만 리프 노드가 아닌경우 isEndOfWord 값이 false (다른 단어의 prefix 인 경우)
        return current.isEndOfWord();
    }

    /*단어 삭제*/
    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        //재귀 종료 case : 단어의 모든 문자를 삭제
        if (index == word.length()) {
            // 찾는 단어의 마지막 문자가 종료노드가 아니라면 해당 단어는 다른 단어의 prefix 이므로 삭제해서는 안된다.(그 단어를 찾을 수 없게 된다.)
            if (!current.isEndOfWord()) {
                return false;
            }
            // 찾는 단어의 마지막 문자노드를 종료 노드가 아닌 상태로 변경
            // 트라이 내에 abc, abcd 단어가 들어있을때
            // abc 를 삭제한다면
            // c 노드를 종료노드가 아니라고 표시하여 abc 가 검색되지 않도록 한다
            current.setEndOfWord(false);
            // c 노드의 자식노드로 d 노드가 존재하므로 false 가 리턴된다.
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        //문자를 찾을 수 없는 경우
        if (node == null) {
            return false;
        }
        //node를 삭제해야 하는지 검사
        //node의 서브트리가 존재하지 않고 node가 어떤 단어의 종료노드가 아니라면 삭제
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        //current 에서 node에 매핑되는 서브트리를 삭제하고
        // if 더이상 서브트리가 남아있지 않다면 true 리턴
        // else false 리턴
        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }
}
