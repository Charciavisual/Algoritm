package org.example.algorithm.programmers.swcoding2018;

/**
 * Summer/Winter Coding(~2018) - 스킬트리
 * https://programmers.co.kr/learn/courses/30/lessons/49993/solution_groups?language=java&page=64
 *
 * @author Changhee Choi
 * @since 24/06/2020
 */
public class SkillTree {

    private final int NOT_FOUND = -1;

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skill_tree : skill_trees) {
            if (checkSkillTree(skill, skill_tree)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean checkSkillTree(String skill, String skillTree) {
        int[] pos = new int[skill.length()];

        for (int i = 0; i < skill.length(); i++) {
            pos[i] = skillTree.indexOf(skill.charAt(i));
        }

        for (int i = 0; i < pos.length - 1; i++) {
            if ((pos[i] == NOT_FOUND && pos[i + 1] >= 0) || (pos[i + 1] != NOT_FOUND && pos[i] > pos[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
