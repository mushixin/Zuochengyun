package leecode;

import java.util.*;

class Solution {
    Set<String> dead = new HashSet<>();
    //从最终字符串，走到key这一步，最少需要走几步
    Map<String,Integer> map = new HashMap<>();


    public int openLock(String[] deadends, String target) {
        for(String str:deadends){
            dead.add(str);
        }
        
        map.put(target,0);
        LinkedList<String> temp = new LinkedList<>();
        LinkedList<String>tempNext = new LinkedList<>();

        temp.add(target);
        int currentStep = 0;
        while(!temp.isEmpty()){
            String first = temp.removeFirst();
            if(first.equals("0000")){
                return currentStep;
            }
            addNextStep(first,tempNext,currentStep+1);

            if(temp.isEmpty()){
                temp = tempNext;
                tempNext = new LinkedList<>();
                currentStep++;
            }
        }
        return -1;
    }

    //若是这个str的下一个操作，没有操作过，则操作，并加入到链表中。并且存入map
    public void addNextStep(String str, LinkedList<String>tempNext, int step){
        StringBuilder sb = new StringBuilder(str);
        for(int pos=0;pos<=3;++pos){
            String s1 = addMinus(sb,pos,-1);
            String s2 = addMinus(sb,pos,1);
            if(!map.containsKey(s1) && !dead.contains(s1)){
                map.put(s1,step);
                tempNext.addLast(s1);
            }
            if(!map.containsKey(s2)  && !dead.contains(s1)){
                map.put(s2,step);
                tempNext.addLast(s2);
            }
        }
    }

    //pos=0,1,2,3   val=1/-1
    public String addMinus(StringBuilder str,int pos,int val){
        String bef = str.substring(0,pos);
        char c= (char)(str.charAt(pos)+val);
        if(c<'0'){
            c+=10;
        }else if(c>'9'){
            c-=10;
        }
        String temp = str.substring(pos+1);
        return  bef+c+temp;
    }

    public static void main(String[] args) {
        String[]dead = {"0201","0101","0102","1212","2002"};
        System.out.println(new Solution().openLock(dead,"0202"));
    }
}