package org.example.algorithm.programmers.kakao.blind_2018;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 2018 KAKAO BLIND RECRUITMENT - 파일명 정렬
 * https://programmers.co.kr/learn/courses/30/lessons/17686
 *
 * @author Changhee Choi
 * @since 20/06/2020
 */
public class Kakao_FileSort {
    public String[] solution(String[] files) {
        String[] answer = {};
        LinkedList<FileInfo> fileInfoList = new LinkedList<>();

        for (int i = 0; i < files.length; i++) {
            fileInfoList.add(new FileInfo(files[i]));
        }

        Collections.sort(fileInfoList);

        answer = fileInfoList.stream().map(FileInfo::getFileName).toArray(String[]::new);
        return answer;
    }

    class FileInfo implements Comparable<FileInfo> {

        private String fileName;
        private Integer numberStartIdx;
        private Integer tailStartIdx;

        public FileInfo(String fileName) {
            init(fileName);
        }

        private void init(String fileName) {
            this.fileName = fileName;
            findStartIdx();
        }

        private void findStartIdx() {
            if (this.fileName == null) return;

            boolean beforeIsNumber = false;

            for (int i = 0; i < this.fileName.length(); i++) {
                if (!beforeIsNumber) {
                    if(Character.isDigit(fileName.charAt(i))){
                        beforeIsNumber = true;
                        this.numberStartIdx = i;
                    }
                } else {
                    //최대 5자리를 넘어가는 숫자들은 tail에 포함된다
                    if (!Character.isDigit(fileName.charAt(i)) || (i - this.numberStartIdx + 1) > 5) {
                        this.tailStartIdx = i;
                        break;
                    }
                }
            }
        }

        public String getFileName() {
            return this.fileName;
        }

        public String getHead() {
            return this.fileName.substring(0, this.numberStartIdx).toLowerCase();
        }

        public Integer getNumber() {
            String number = "";
            if (this.tailStartIdx == null) {
                number = this.fileName.substring(this.numberStartIdx);
            } else {
                number = this.fileName.substring(this.numberStartIdx, this.tailStartIdx);
            }
            return Integer.valueOf(number);
        }

        @Override
        public int compareTo(FileInfo o) {
            int compValue = this.getHead().compareTo(o.getHead());

            if (compValue == 0) {
                compValue = this.getNumber().compareTo(o.getNumber());
            }

            return compValue;
        }
    }
}

