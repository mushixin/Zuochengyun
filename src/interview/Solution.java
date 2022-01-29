package interview;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {


        int[] cards;

        //将两个数字加减乘除，结果放到set中
        public Set<Double> operate(double a,double b){
            Set<Double> set  = new HashSet<>();
            set.add(a+b);
            set.add(a-b);
            set.add(b-a);
            set.add(a*b);
            set.add(a/(double)b);
            set.add(b/(double)a);
            return set;
        }

        public boolean judgePoint24(int[] cards) {
            this.cards = cards;
            Arrays.sort(cards);
            Set<Double>set12 = operate(cards[0],cards[1]);
            Set<Double>set13 = operate(cards[0],cards[2]);
            Set<Double>set14 = operate(cards[0],cards[3]);
            Set<Double>set23 = operate(cards[1],cards[2]);
            Set<Double>set24 = operate(cards[1],cards[3]);
            Set<Double>set34 = operate(cards[2],cards[3]);

            for(Double d1:set12){
                for(Double d2:set34){
                    if(operate(d1,d2).contains(24)){
                        return true;
                    }
                }
            }
            for(Double d1:set13){
                for(Double d2:set24){
                    if(operate(d1,d2).contains(24)){
                        return true;
                    }
                }
            }
            for(Double d1:set14){
                for(Double d2:set23){
                    if(operate(d1,d2).contains(24)){
                        return true;
                    }
                }
            }

            return false;

        }


    public static void main(String[] args) {
        System.out.println(new interview.Solution().judgePoint24(new int[]{4,1,8,7}));



    }
}
