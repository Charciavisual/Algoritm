package org.example.algorithm.programmers.kakao.blind_2018;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2018 KAKAO BLIND RECRUITMENT - 방금그곡
 * https://programmers.co.kr/learn/courses/30/lessons/17683
 *
 * @author Changhee Choi
 * @since 21/06/2020
 */
public class FindMusic {

    public String solution(String m, String[] musicinfos) {
        String answer = "";

        ArrayList<MusicInfo> candidateAnswerList = new ArrayList<>();

        for (String s : musicinfos) {
            MusicInfo musicInfo = new MusicInfo(s);
            if (musicInfo.isMatchedNotes(m)) {
                candidateAnswerList.add(musicInfo);
            }
        }

        if (candidateAnswerList.size() > 0) {
            Collections.sort(candidateAnswerList);
            answer = candidateAnswerList.get(0).getTitle();
        } else {
            answer = "(None)";
        }

        return answer;
    }

    class MusicInfo implements Comparable<MusicInfo> {
        private String title;
        private String startTime;
        private String endTime;
        private String notes;

        public MusicInfo(String musicInfo) {
            String[] musicInfoTokens = musicInfo.split(",");
            this.startTime = musicInfoTokens[0];
            this.endTime = musicInfoTokens[1];
            this.title = musicInfoTokens[2];
            this.notes = musicInfoTokens[3];
        }

        public String getTitle() {
            return title;
        }

        private int getPlayTime() {
            String[] startTimeTokens = this.startTime.split(":");
            String[] endTimeTokens = this.endTime.split(":");
            int startTimeHour = Integer.parseInt(startTimeTokens[0]);
            int startTimeMin = Integer.parseInt(startTimeTokens[1]);
            int endTimeHour = Integer.parseInt(endTimeTokens[0]);
            int endTimeMin = Integer.parseInt(endTimeTokens[1]);

            return (endTimeHour * 60 + endTimeMin) - (startTimeHour * 60 + startTimeMin);
        }

        private String convertSharpNotes(String notes) {
            return notes.replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a");
        }

        private String getPlayedNoteString() {
            String convertedNotes = convertSharpNotes(this.notes);
            int playTime = getPlayTime();
            int musicLength = convertedNotes.length();

            int fullPlayedCount = playTime / musicLength;
            int partialPlayedNoteCount = playTime % musicLength;

            StringBuilder noteBuilder = new StringBuilder();

            for (int i = 0; i < fullPlayedCount; i++) {
                noteBuilder.append(convertedNotes);
            }

            if(partialPlayedNoteCount > 0) {
                noteBuilder.append(convertedNotes, 0, partialPlayedNoteCount);
            }
            return noteBuilder.toString();
        }

        public boolean isMatchedNotes(String findNotes) {
            return getPlayedNoteString().contains(convertSharpNotes(findNotes));
        }

        @Override
        public int compareTo(MusicInfo o) {
            return o.getPlayTime() - this.getPlayTime();
        }
    }
}
