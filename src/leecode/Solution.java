package leecode;/*
// Definition for a Node.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    Map<Integer,Node> setNode = new HashMap<>();
    boolean[][]map;


    public void bsf(Node node){
        if(node==null || setNode.containsKey(node.val)){
            return ;
        }
        setNode.put(node.val, new Node(node.val));
        for(Node temp:node.neighbors){
            bsf(temp);
        }
    }
    public void mapMark(Node node){
        if(node==null || map[node.val][node.val]){
            return ;
        }
        map[node.val][node.val] = true;
        for(Node temp:node.neighbors){
            map[node.val][temp.val] = true;
            mapMark(temp);
        }
    }

    public Node buildTree(Node node){
        if(!map[node.val][node.val]){
            return node;
        }
        map[node.val][node.val] = false;

        //将这个结点的关系填充下
        for(int i=1;i<map.length;++i){
            if(map[node.val][i] && node.val!=i){
                node.neighbors.add(buildTree(setNode.get(i)));
            }
        }
        return node;
    }

    public Node cloneGraph(Node node) {
        bsf(node);//记录所有结点。
        map= new boolean[setNode.size()+1][setNode.size()+1];
        System.out.println(setNode);        
        return   buildTree(setNode.get(node.val));
    }

}