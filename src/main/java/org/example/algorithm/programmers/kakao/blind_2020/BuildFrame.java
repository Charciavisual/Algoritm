package org.example.algorithm.programmers.kakao.blind_2020;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2020 카카오 공채 - 기둥과 보 설치
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 *
 * @author Changhee Choi
 * @since 06/09/2020
 */
public class BuildFrame {

    private int n;
    private boolean[][][] map;
    private ArrayList<Frame> frames = new ArrayList<>();
    private final int REMOVE = 0, BUILD = 1, PILLAR = 0, BARRAGE = 1;

    /**
     * 제한사항
     * - 교차점 좌표를 기준으로 보는 오른쪽, 기둥은 위쪽 방향으로 설치 또는 삭제
     *
     * @param n           5 이상 100 이하인 자연수
     * @param build_frame [x,y,a,b]
     *                    - x, y: 기둥, 보를 설치 또는 삭제할 교차점의 좌표
     *                    - a: 0 = 기둥, 1 = 보
     *                    - b: 0 = 삭제, 1 = 설치
     * @return 최종 구조물의 상태
     * - 가로(열) 길이가 3인 2차원 배열, [x, y, a] 형식
     * - x, y = 기둥, 보의 교차점 좌표, a = 0은 기둥, 1은 보
     */
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        this.n = n;
        map = new boolean[n + 1][n + 1][2];

        for (int[] frameParams : build_frame) {
            Frame frame = new Frame(frameParams);
            if (frameParams[3] == BUILD) {
                buildFrame(frame);
                frames.add(frame);

                boolean isValid = isValidState();
                if (!isValid) {
                    removeFrame(frame);
                    frames.remove(frame);
                    continue;
                }
            } else {
                removeFrame(frame);
                int idx = frames.indexOf(frame);
                frames.remove(idx);

                boolean isValid = isValidState();
                if (!isValid) {
                    buildFrame(frame);
                    frames.add(idx, frame);
                    continue;
                }
            }
        }
        answer = makeAnswer();
        return answer;
    }

    private void buildFrame(Frame frame) {
        if (frame.type == PILLAR) {
            map[frame.x][frame.y][PILLAR] = true;
        } else {
            map[frame.x][frame.y][BARRAGE] = true;
        }
    }

    private void removeFrame(Frame frame) {
        if (frame.type == PILLAR) {
            map[frame.x][frame.y][PILLAR] = false;
        } else {
            map[frame.x][frame.y][BARRAGE] = false;
        }
    }

    /**
     * - 기둥: 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
     * - 보: 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
     */
    private boolean isValidState() {
        for (Frame frame : frames) {
            if (frame.type == PILLAR) {
                if (frame.y == 0 ||
                        map[frame.x][frame.y - 1][PILLAR] ||
                        map[frame.x][frame.y][BARRAGE] ||
                        (frame.x > 0 && map[frame.x - 1][frame.y][BARRAGE])) {
                    continue;
                } else {
                    return false;
                }
            } else if (frame.type == BARRAGE) {
                if (map[frame.x][frame.y - 1][PILLAR] ||
                        map[frame.x + 1][frame.y - 1][PILLAR] ||
                        ((frame.x > 0 && map[frame.x - 1][frame.y][BARRAGE]) &&
                                (frame.x < n - 1 && map[frame.x + 1][frame.y][BARRAGE]))) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] makeAnswer() {
        Collections.sort(frames);

        int[][] answer = new int[frames.size()][3];
        int idx = 0;
        for (Frame frame : frames) {
            answer[idx++] = frame.paramsToArray();
        }
        return answer;
    }

    class Frame implements Comparable<Frame> {
        int x, y;
        int type;

        public Frame(int[] params) {
            this.x = params[0];
            this.y = params[1];
            this.type = params[2];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Frame frame = (Frame) o;
            return x == frame.x &&
                    y == frame.y &&
                    type == frame.type;
        }

        @Override
        public int compareTo(Frame o) {
            if (this.x != o.x) {
                return this.x - o.x;
            }

            if (this.y != o.y) {
                return this.y - o.y;
            }

            return this.type - o.type;
        }

        public int[] paramsToArray() {
            return new int[]{this.x, this.y, this.type};
        }
    }
}
