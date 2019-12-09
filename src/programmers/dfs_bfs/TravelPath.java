package programmers.dfs_bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class TravelPath {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        ArrayList<Ticket> ticketSet = new ArrayList<>();
        for(String[] ticket : tickets) {
            String start = ticket[0];
            String dest = ticket[1];

            Ticket t = new Ticket(start, dest);
            ticketSet.add(t);
        }

        Collections.sort(ticketSet);

        LinkedList<String> result = new LinkedList<>();
        result.add("ICN");
        makePath("ICN", ticketSet, result, 0, ticketSet.size());
        answer = result.toArray(new String[result.size()]);

        for(String s : answer) {
            System.out.print(s + " ");
        }

        return answer;
    }

    private boolean makePath(String start, ArrayList<Ticket> ticketSet, LinkedList<String> paths, int count, int n) {

        // 티켓 모두 사용
        if(count == n) return true;

        boolean ret = false;
        Ticket now = null;

        Iterator<Ticket> itrTicket = ticketSet.iterator();

        while(itrTicket.hasNext()) {
            Ticket t = itrTicket.next();
            if(t.getStart().equals(start) && !t.getUsed()) {
                now = t;

                now.useTicket();
                paths.add(now.getDest());

                ret = makePath(now.getDest(), ticketSet, paths, count + 1, n);

                if(ret == false) { //티켓 모두 사용 실패,,, 백트래킹
                    now.restoreTicket();
                    paths.removeLast(); //remove(now.getDest()) 로 하면 중복된 이름의 경로가 모두 삭제된다. testcase 1번 실패 원인
                }
                else break; //티켓모두 사용 성공,,, 반복문 종료
            }
        }

        if(now == null) return false; // 티켓 모두 사용 실패
        return ret;
    }
}

class Ticket implements Comparable<Ticket>{
    private String start;
    private String dest;
    private boolean used;

    public Ticket(String start, String dest) {
        this.start = start;
        this.dest = dest;
        this.used = false;
    }

    public String getStart() {
        return this.start;
    }

    public String getDest() {
        return this.dest;
    }

    public boolean getUsed(){
        return this.used;
    }

    public void useTicket() {
        this.used = true;
    }

    public void restoreTicket() {
        this.used = false;
    }

    // start 기준 오름차순, dest 기준 오름차순 정렬
    // 경로가 2개 이상일 경우에는 알파벳 순서에 따라 방문하게 되는데 start 를 알파벳 순서로 정렬해놓으면 티켓을 탐색할때 가능한 앞 순서에서 발견할 수 있다.
    @Override
    public int compareTo(Ticket t) {
        int diff = this.start.compareTo(t.getStart());
        if(diff == 0) {
            return this.dest.compareTo(t.getDest());
        }
        return diff;
    }
}