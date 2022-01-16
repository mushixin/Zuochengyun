package chapter_3_wanhao;

import java.util.ArrayList;
import java.util.List;

public class Problem_21_MaxHappy_wh {

    public static class Employee {
        public int happy;
        List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }


    public static class ReturnType {
        int maxhappyWithC;//包含当前结点的情况下的最大快乐值
        int maxhappyWithoutC;//没有当前节点的情况下的最大快乐值

        public ReturnType(int maxhappyWithC, int maxhappyWithoutC) {
            this.maxhappyWithC = maxhappyWithC;
            this.maxhappyWithoutC = maxhappyWithoutC;
        }
    }

    public static ReturnType process(Employee boss) {
        if (boss == null) {
            return new ReturnType(0, 0);
        }
        if (boss.subordinates.size() == 0) {
            return new ReturnType(boss.happy, 0);
        }
        List<ReturnType> returnTypes = new ArrayList<>(boss.subordinates.size());
        for (int i = 0; i < boss.subordinates.size(); i++) {
            returnTypes.add(process(boss.subordinates.get(i)));
        }
        int maxhappyWithC = boss.happy;
        int maxhappyWithoutC = 0;
        for (int i = 0; i < returnTypes.size(); i++) {
            maxhappyWithC = maxhappyWithC + returnTypes.get(i).maxhappyWithoutC;
            maxhappyWithoutC = maxhappyWithoutC + returnTypes.get(i).maxhappyWithC;
        }
        return new ReturnType(maxhappyWithC, maxhappyWithoutC);
    }

    /**
     * 这里的
     *
     * @param boss
     * @return
     */
    public static int getMaxHappy(Employee boss) {
		ReturnType r = process(boss);
        return Math.max(r.maxhappyWithC, r.maxhappyWithoutC);
    }

    public static void main(String[] args) {

        Employee boss1 = new Employee(1);
        Employee boss2 = new Employee(3);
        Employee boss3 = new Employee(2);
        boss1.subordinates.add(boss2);
        boss1.subordinates.add(boss3);

        Employee boss4 = new Employee(2);
        Employee boss5 = new Employee(1);
        Employee boss6 = new Employee(2);
        boss4.subordinates.add(boss5);
        boss4.subordinates.add(boss6);

        Employee boss = new Employee(5);
        boss.subordinates.add(boss1);
        boss.subordinates.add(boss4);

        System.out.println(getMaxHappy(boss));//13

        boss.happy = -6;
        System.out.println(getMaxHappy(boss));//3

		boss5.happy=3;
		System.out.println(getMaxHappy(boss));//4

	}
}
