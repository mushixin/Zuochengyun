package leecode;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    public int capacity;
    public Map<Integer,Node> cache;
    public Node head;
    public Node tair;

    public static class Node{
        int key;
        int value;
        Node before,after;
        public Node(int key,int val){
            this.key = key;
            this.value = val;
        }
    }

    public void add2List(Node n){
        if (head==null){
            head = n;
            tair = n;
            return ;
        }
        resetNode(n);
        head.before = n;
        n.after=head;
        head = n;
    }

    public Integer removeLast(){
        Node toDel = tair;
        Node bef = tair.before;
        tair = bef;
        toDel.before=null;
        toDel.after=null;
        if (tair!=null){
            tair.after=null;
        }else{
            head=null;
        }
        return toDel.key;
    }

    public void move2First(Node n){
        Node bef = n.before;
        Node aft = n.after;

        //n在头部
        if (bef==null){
            return;
        }
        resetNode(n);
        //n在尾部
        if (aft==null){
            bef.after = aft;
            add2List(n);
            tair = bef;
            return;
        }

        //n在中间
        bef.after = aft;
        aft.before = bef;
        add2List(n);
    }

    public void resetNode(Node n){
        n.before=null;
        n.after=null;
    }

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        this.capacity=capacity;
    }

    public int get(int key) {
        Node n = cache.get(key);
        if (n==null){
            return -1;
        }
        move2First(n);
        return n.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            cache.get(key).value =value;
            return;
        }
        if (cache.size()>=capacity){
            Integer keyT = removeLast();
            cache.remove(keyT);
        }
        Node n = new Node(key,value);
        cache.put(key,n);
        add2List(n);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */