package leecode;

import java.util.HashMap;
import java.util.Map;

class Solution76 {
//    String moveRes;
//    String minStr;
//    String s;
//    String t;
//    int minCharIndex = -1;//可以后移的index中最小的那个。
//    char minCharchar = ' ';
//
//    int minStart =-1;
//    int maxEnd=-1;
//
//    List<Character> chars = new ArrayList<>();
//    Map<Character,Integer> charTimes = new HashMap<>();
//    Map<Character,List<Integer>> charIndexs = new HashMap<>();
//
//    //每个字符的起始和终止。 和chars对应起来, 初始化,这里直接放置index列表的索引，
//    List<Integer>indexStart = new ArrayList<>();
//
//
//    public String moveFirst(){
//        if(minCharIndex==-1){
//            return "";
//        }
//        indexStart.set(minCharIndex, indexStart.get(minCharIndex)+1);
//
//        minCharIndex = -1;//可以后移的index中最小的那个。
//        minCharchar = ' ';
//        minStart =-1;
//        maxEnd=-1;
//
//        for(int i=0;i<chars.size();++i){
//            char curr = chars.get(i);
//            int currTimes = charTimes.get(curr);
//
//            List<Integer>currIndex = charIndexs.get(curr);
//            if(currIndex==null || currTimes>currIndex.size()){
//                return "";
//            }
//            int start = indexStart.get(i);
//            int end = indexEnd.get(i);
//            if(minStart==-1){
//                minStart = currIndex.get(start);
//                maxEnd = currIndex.get(end);
//            }else{
//                minStart = Math.min(minStart, currIndex.get(start));
//                maxEnd = Math.max(maxEnd, currIndex.get(end));
//            }
//            if(end<currIndex.size()-1){
//                if(minCharIndex== -1){
//                    minCharIndex = i;
//                    minCharchar = curr;
//                }else{
//                    if(charIndexs.get(minCharchar).get(minCharIndex)>currIndex.get(indexStart.get(i))){
//                        minCharIndex = i;
//                        minCharchar = curr;
//                    }
//                }
//            }
//        }
//        return  s.substring(minStart, maxEnd+1);
//    }
//
//    //35+5+5+5+5+5+5+5
//    public String minWindow(String s, String t) {
//        this.s = s;
//        this.t = t;
//        for(int i=0;i<t.length();++i){
//            Integer time = charTimes.get(t.charAt(i));
//            if(time==null){
//                charTimes.put(t.charAt(i),1);
//                chars.add(t.charAt(i));
//            }else{
//                charTimes.put(t.charAt(i),1+time);
//            }
//        }
//        for(int i=0;i<s.length();++i){
//            if(!charTimes.containsKey(s.charAt(i))){
//                continue;
//            }
//            List<Integer>indexs = charIndexs.get(s.charAt(i));
//            if(indexs==null){
//                indexs = new ArrayList<>();
//                indexs.add(i);
//                charIndexs.put(s.charAt(i), indexs);
//            }else{
//                indexs.add(i);
//            }
//        }
//
//        for(int i=0;i<chars.size();++i){
//            char curr = chars.get(i);
//            int currTimes = charTimes.get(curr);
//
//            List<Integer>currIndex = charIndexs.get(curr);
//            if(currIndex==null || currTimes>currIndex.size()){
//                return "";
//            }
//            int start = 0;
//            int end = currTimes-1;
//            indexStart.add(start);
//            indexEnd.add(end);//currIndex.get
//            if(minStart==-1){
//                minStart = currIndex.get(start);
//                maxEnd = currIndex.get(end);
//            }else{
//                minStart = Math.min(minStart, currIndex.get(start));
//                maxEnd = Math.max(maxEnd, currIndex.get(end));
//            }
//            if(currTimes-1<currIndex.size()-1){
//                if(minCharIndex== -1){
//                    minCharIndex = i;
//                    minCharchar = curr;
//                }else{
//                    if(indexStart.get(minCharIndex)>indexStart.get(i)){
//                        minCharIndex = i;
//                        minCharchar = curr;
//                    }
//                }
//            }
//        }
//        minStr  = s.substring(minStart, maxEnd+1);
//        while(true){
//            if(minStr.length()<=t.length()){
//                return minStr;
//            }
//            moveRes = moveFirst();
//            if(moveRes.equals("")){
//                break;
//            }
//            if(moveRes.length()<minStr.length()){
//                minStr = moveRes;
//            }
//        }
//
//        return minStr;
//    }
//
    public static void main(String[] args) {
//        System.out.println(new Solution().minWindow("cabwefgewcwaefgcf","cae"));
        Map<String,String>map = new HashMap<>();
        System.out.println( map.getOrDefault("11","1") );
    }

}