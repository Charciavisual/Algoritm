package template.tree;

public class FenwickTree {

    private int[] tree;

    public FenwickTree(int n) {
        this.tree = new int[n+1];
    }

    // [0...pos] 구간 합
    public int sum(int pos) {
        pos++; // 배열 인덱스를 [1...n]으로 바꿔주기 위한 코드
        int ret = 0;
        while(pos > 0) {
            ret += tree[pos];
            pos &= (pos -1);
        }
        return ret;
    }

    // 값의 추가 및 업데이트
    public boolean add(int pos, int val) {
        pos++; // 배열 인덱스를 [1...n]으로 바꿔주기 위한 코드
        while(pos<tree.length){
            tree[pos] += val;
            pos += (pos & -pos);
        }
        return true;
    }
}
