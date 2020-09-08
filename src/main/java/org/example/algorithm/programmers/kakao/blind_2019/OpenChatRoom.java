package org.example.algorithm.programmers.kakao.blind_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 *
 * @author Changhee Choi
 * @since 08/09/2020
 */
public class OpenChatRoom {
    public String[] solution(String[] record) {
        String[] answer = {};

        Map<String, String> nicknameMap = new HashMap<>();
        List<Event> eventList = new ArrayList<>();

        for (String line : record) {

            String[] params = line.split(" ");
            String eventType = params[0];
            String userId = params[1];
            String nickname = params.length == 3 ? params[2] : null;

            if (eventType.equals("Enter")) {
                eventList.add(new Event(eventType, userId));
                nicknameMap.put(userId, nickname);
            } else if (eventType.equals("Leave")) {
                eventList.add(new Event(eventType, userId));
            } else {
                nicknameMap.replace(userId, nickname);
            }

        }

        answer = new String[eventList.size()];

        for (int i = 0; i < eventList.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(nicknameMap.get(eventList.get(i).getUserId()));
            sb.append(eventList.get(i).getEventMessage());
            answer[i] = sb.toString();
        }
        return answer;
    }
}

class Event {
    private String eventType;
    private String userId;

    public Event(String eventType, String userId) {
        this.eventType = eventType;
        this.userId = userId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getEventMessage() {

        if (this.eventType.equals("Enter")) {
            return "님이 들어왔습니다.";
        } else if (this.eventType.equals("Leave")) {
            return "님이 나갔습니다.";
        } else return null;
    }
}
