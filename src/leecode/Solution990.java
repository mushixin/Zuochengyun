package leecode;

import java.util.*;

//https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
class Solution990 {
    /**
     * 这里可以搞多个集合，相等的元素放到一个集合中，A
     * 和A相同的放到集合中。
     * <p>
     * 拆分所有元素到不同的set中，若是两个元素相同，则将set合并
     * 然后处理不同的情乱。
     * 1.所有元素，打散放到set中
     * 2.相等的情况，set之间互相合并
     * 3.不等的情况，判断若是两边在同一个set中报错，不同set中正常。
     *
     * @param equations
     * @return
     */
    public static boolean equationsPossible(String[] equations) {
        Map<Character, Set<Character>> allSet = new HashMap<Character, Set<Character>>();
        for (int i = 0; i < equations.length; i++) {
            String eq = equations[i];
            Character c = eq.charAt(0);
            Character d = eq.charAt(3);
            HashSet<Character> cSet = new HashSet<>();
            cSet.add(c);
            allSet.put(c, cSet);

            HashSet<Character> dSet = new HashSet<>();
            dSet.add(d);
            allSet.put(d, dSet);
        }
        /**
         * 相等的场景
         */
        for (int i = 0; i < equations.length; i++) {
            String eq = equations[i];
            Character c = eq.charAt(0);
            Character d = eq.charAt(3);
            Character ch = eq.charAt(1);
            if (ch == '=') {
                Set<Character> set = allSet.get(c);
                set.addAll(allSet.get(d));
//                allSet.put(d, set);//合并成为同一个set修改set中所有元素，对应set
                for (Character cTemp : set) {
                    allSet.put(cTemp, set);
                }
            }
        }
        /**
         * 不相等的场景
         */
        for (int i = 0; i < equations.length; i++) {
            String eq = equations[i];
            Character c = eq.charAt(0);
            Character d = eq.charAt(3);
            Character ch = eq.charAt(1);
            if (ch == '!') {
                Set<Character> set = allSet.get(c);
                if (set.contains(d)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] equations = {"a==b", "c==d", "e==f", "a!=e", "d!=f", "a!=d"};
        System.out.println(equationsPossible(equations));//true

        equations[5] = "a==d";
        System.out.println(equationsPossible(equations));//true

        String[] equations2 = {"a==b", "c==d", "e==f", "a!=e", "d==f"};
        System.out.println(equationsPossible(equations2));//true

        String[] equations3 = {"a==b", "c==d", "e==f", "a!=e", "d==f", "a==c"};
        System.out.println(equationsPossible(equations3));//false

    }

}