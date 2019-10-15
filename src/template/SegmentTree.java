package template;

public class SegmentTree {
    private int[] st;
    private int[] data;

    private int stIdxToDIdx(int stIdx) {
        return stIdx - st.length / 2 + 1;
    }

    private int dIdxToStIdx(int dIdx) {
        return dIdx + st.length / 2;
    }

    private void initStArr(int size) {
        int count = 0, cur = size;
        while ((cur = (cur >> 1)) > 0) count++;
        count += 1;
        st = new int[(int) Math.pow(2, count + 1)];
    }

    private void insertData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            st[dIdxToStIdx(i)] = data[i];
        }
    }

    private void construct() {
        insertData(data);

        //리프 노드들(실제 데이터들)의 깊이 -1 부터 루트까지 순회하며 부분합을 저장하는 전처리
        for (int i = dIdxToStIdx(0) - 1; i > 0; i--) {
            st[i] = st[i * 2] + st[i * 2 + 1];
        }
    }

    public void init(int[] data) {
        this.data = data;
        initStArr(data.length);
        construct();
    }

    public void update(int idx, int val) {
        int cur = idx + st.length / 2;
        st[cur] = val;

        while (cur > 0) {
            st[cur] += val;
            cur = cur / 2;
        }
    }

    public int findPartialSum(int L, int R) {
        if (L < 1 || R > stIdxToDIdx(st.length - 1)) throw new RuntimeException("Escape boundary");
        return partialSum(L, R, 1, 1, data.length);
    }

    private int partialSum(int L, int R, int node, int nodeL, int nodeR) {

        //노드가 포함하는 구간이 구하려는 구간과 겹치지 않을때
        if (L > nodeR || R < nodeL) return 0;
        //노드가 포함하는 구간이 구하려는 구간에 완전히 포함될때
        if (L <= nodeL && R >= nodeR) return st[node];
        //노드가 포함하는 구간이 구하려는 범위중 일부만 포함될때
        int mid = (nodeL + nodeR) / 2;
        return partialSum(L, R, node * 2, nodeL, mid) + partialSum(L, R, node * 2 + 1, mid + 1, nodeR);

    }
}
