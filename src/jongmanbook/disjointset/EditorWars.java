package jongmanbook.disjointset;

import java.util.Arrays;
import java.util.StringTokenizer;

public class EditorWars {

    public void solve() {

        //문제 입력값
        int numOfTestCase = 4;
        int[][] nums = {
                {4, 4},
                {100, 4},
                {3, 3},
                {8, 6}
        };
        String[][] replyInfo = {
                {"ACK 0 1", "ACK 1 2", "DIS 1 3", "ACK 2 0"},
                {"ACK 0 1", "ACK 1 2", "ACK 2 3", "ACK 3 4"},
                {"ACK 0 1", "ACK 1 2", "DIS 2 0"},
                {"DIS 0 1", "ACK 1 2", "ACK 1 4", "DIS 4 3", "DIS 5 6", "ACK 5 7"}
        };

        //
        UnionFind uf;
        StringTokenizer st;
        for (int _case = 0; _case < numOfTestCase; _case++) {
            int N = nums[_case][0]; //회원수
            int M = nums[_case][1]; //조사한 댓글 수
            boolean flag = true; //while 문이 중간에 종료되었는지를 판별하는 변수
            int reply = 0; //댓글 정보 인덱스

            //초기화
            uf = new UnionFind(N);

            while (flag && reply < M) {
                st = new StringTokenizer(replyInfo[_case][reply]);
                boolean result; //모순이 발생했는지 판별하는 변수

                if (st.nextToken().equals("ACK"))
                    result = uf.ack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                else
                    result = uf.dis(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                //모순이 발생했으면
                if (!result) {
                    flag = false;
                    System.out.println("CONTRADICTION AT " + (reply + 1));
                }
                reply++;
            }

            //while 문이 정상적으로 종료된 경우
            if (flag) {
                System.out.println("MAX PARTY SIZE IS " + maxPartyMember(uf, N));
            }
        }

    }

    public int maxPartyMember(UnionFind uf, int n) {
        int ret = 0;
        int[] parent = uf.getParent(), enemies = uf.getEnemy(), size = uf.getSize();
        for (int node = 0; node < n; node++) {
            //루트노드 일때만
            if (parent[node] == node) {
                int enemy = enemies[node];
                //같은 조합을 두번 세지 않기 위해 enemy < node 인 경우만 센다.
                if (enemy > node) continue;

                int mySize = size[node];
                int enemySize = (enemy == -1 ? 0 : size[enemy]);

                ret += Math.max(mySize, enemySize);
            }
        }
        return ret;
    }

    class UnionFind {

        private int[] parent, rank, enemy, size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            this.enemy = new int[n];

            Arrays.fill(rank, 1);
            Arrays.fill(size, 1);
            Arrays.fill(enemy, -1);

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int[] getParent() {
            return parent;
        }

        public int[] getEnemy() {
            return enemy;
        }

        public int[] getSize() {
            return size;
        }

        private int find(int node) {
            // 최상위 루트이면 리턴
            if (node == parent[node]) return node;
            // 재귀적으로 최상위 루트를 찾고 복귀할때 부모노드를 최상위 루트 노드로 변경
            return parent[node] = find(parent[node]);
        }

        private void swap(int[] arr, int idx1, int idx2) {
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }

        public int union(int node1, int node2) {
            //enemy 와 집합을 구성할 때 공집합 처리
            if (node1 == -1 || node2 == -1) return Math.max(node1, node2);

            int[] roots = new int[2];
            roots[0] = find(node1);
            roots[1] = find(node2);

            if (roots[0] == roots[1]) return roots[0];

            //높이가 더 높은 쪽으로 합친다.
            if (rank[roots[0]] > rank[roots[1]]) swap(roots, 0, 1);

            parent[roots[0]] = roots[1];
            size[roots[1]] += size[roots[0]];

            //높이가 같은 경우 높이를 1 증가시켜준다.
            if (rank[roots[0]] == rank[roots[1]])
                rank[roots[1]]++;

            return roots[1];
        }

        //node1 과 node2 가 서로 적대하는 집합에 속한다.
        public boolean dis(int node1, int node2) {
            node1 = find(node1);
            node2 = find(node2);

            //서로 같은 집합이면 모순
            if (node1 == node2) return false;
            //node1 은 node2의 적대집합에 속한다.
            int group1 = union(node1, enemy[node2]);
            //node2 은 node1의 적대집합에 속한다.
            int group2 = union(node2, enemy[node1]);

            //집합1과 집합2의 적대관계 표시
            enemy[group1] = group2;
            enemy[group2] = group1;
            return true;
        }

        //node1 과 node2 가 서로 같은 집합에 속한다.
        public boolean ack(int node1, int node2) {
            node1 = find(node1);
            node2 = find(node2);

            //서로 적대관계이면 모순
            if (node1 == enemy[node2]) return false;

            int group1 = union(node1, node2);
            int group2 = union(enemy[node1], enemy[node2]);

            enemy[group1] = group2;

            //적대하는 집합이 없으면 group2는 -1일 수 있다.
            //enemy[node1] == -1 이고 enemy[node2] == -1 인 경우
            if (group2 != -1)
                enemy[group2] = group1;

            return true;
        }
    }
}


