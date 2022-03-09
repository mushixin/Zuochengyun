package leecode;

import java.util.*;

class AllOne {
    /**
    Node{
        String key;
        int times;
        Node bef;
        Node next;
    }
    搞一个双向链表，将所有Node都串起来，然后，每次添加一个次数的时候，将Node移动到
    Map<String,Node>keyMap = new HashMap<>();

    Map<Integer,Set<Node>> timesMap ; 次数和对应的Node列表，


    优先级队列，根绝keyMap来排序，
    PriorityQueue<Node>maxpq
    PriorityQueue<Node>minpq

    */

    PriorityQueue<Integer> minTimes = new PriorityQueue<>();
    //最多次数
    PriorityQueue<Integer>maxTimes = new PriorityQueue<>(  new Comparator<Integer>(){
        public int compare(Integer a,Integer b){
            return b-a;
        }
    } );


    //次数对应的Node集合
    Map<Integer, Set<String>> timesMap = new HashMap<>();
    //key对应Node
    Map<String,Integer>keyMap = new HashMap<>();

    public AllOne() {
    }

    public void inc(String key) {
        Integer val = keyMap.get(key);
        if(val==null){
            keyMap.put(key, 1);

            Set<String> nodes = timesMap.getOrDefault(1,new HashSet<>());
            nodes.add(key);
            if(nodes.size()==1){
                timesMap.put(1, nodes);
                minTimes.add(1);
                maxTimes.add(1);
            }
        }else{
            keyMap.put(key, val+1);

            timesMap.get(val).remove(key);
            Set<String> nodes = timesMap.getOrDefault(val+1,new HashSet<>());
            nodes.add(key);
            if(nodes.size()==1){
                timesMap.put(val+1, nodes);
                minTimes.add(val+1);
                maxTimes.add(val+1);
            }
            if(timesMap.get(val).size()==0){
                minTimes.remove((Integer)(val));
                maxTimes.remove((Integer)(val));
            }
        }

    }

    public void dec(String key) {
        Integer times = keyMap.get(key);
        if(times==1){
            keyMap.remove(key);
        }else{
            keyMap.put(key,times-1);
        }
        Set<String>nodes = timesMap.get(times);
        nodes.remove(key);//val的值改了，不知道这里是否还可以删除。
        if(nodes.size()==0){
            minTimes.remove((Integer)times);
            maxTimes.remove((Integer)times);
        }
        if(times!=1){
            nodes = timesMap.getOrDefault(times-1,new HashSet<>());
            nodes.add(key);
            if(nodes.size()==1){
                timesMap.put(times-1, nodes);
                minTimes.add(times-1);
                maxTimes.add(times-1);
            }
        }

    }

    public String getMaxKey() {
        if(maxTimes.size()==0){
            return "";
        }
        return timesMap.get(maxTimes.peek()).iterator().next();
    }

    public String getMinKey() {
        if(minTimes.size()==0){
            return "";
        }
        return timesMap.get(minTimes.peek()).iterator().next();
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();

//        ["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
//[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]

        allOne.inc("leet");
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.dec("hello");
        allOne.dec("hello");
        System.out.println(allOne.getMaxKey()); // 返回 "hello"
        System.out.println(allOne.getMinKey());// 返回 "hello"
//        System.out.println(allOne.getMaxKey()); // 返回 "hello"
//        System.out.println(allOne.getMinKey());// 返回 "leet"
//        System.out.println(allOne.getMaxKey()); // 返回 "leet"
//        System.out.println(allOne.getMinKey());// 返回 "leet"
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */