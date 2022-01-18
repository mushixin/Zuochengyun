package leecode;

import java.util.HashMap;

class Trie {
    public static final char EMPTYCHAR = '#';
    public Node root = null;

    public static class Node {
        public char c;
        //当前结点的，某个char，对应某个子树，Node对应子树的Node
        public HashMap<Character, Node> map = new HashMap<>();
        boolean endChar = false;//是否当前结点是某个字符串的最终结点。

        public Node(char c) {
            this.c = c;
        }

        public Node() {
            c = EMPTYCHAR;
        }
    }

    /**
     * 这里搞一个多叉树，插入元素，就是会走多叉树的路径，若是节点没有，那么就插入节点。
     * 查询，也是走多叉树。匹配也是走多叉树。
     * 这样每个操作的时间复杂度，降低到logn，
     * <p>
     * 每个结点的子节点，这里要如何存储呢，我想这里可以存一个hashmap，key为Node，value为Node的hashmap 代表每个node的子节点。而且也便于查询是否有子节点。
     */

    public Trie() {
        root = new Node(EMPTYCHAR);
    }

    public void insert(String word) {
        Node tHead = root;
        int index = 0;
        while (index < word.length()) {
            if (tHead.map.get(word.charAt(index)) != null) {
                tHead = tHead.map.get(word.charAt(index));
            } else {
                Node n = new Node(word.charAt(index));
                //说明没有这个字符串，添加上这个字段。
                tHead.map.put(word.charAt(index), n);
                tHead = n;
            }
            index++;
        }
        tHead.endChar = true;
    }

    public boolean search(String word) {
        Node tHead = root;
        int index = 0;
        while (index < word.length()) {
            if (tHead.map.get(word.charAt(index)) != null) {
                tHead = tHead.map.get(word.charAt(index));
            } else {
                return false;
            }
            index++;
        }
        return tHead.endChar;
    }

    public boolean startsWith(String prefix) {
        Node tHead = root;
        int index = 0;
        while (index < prefix.length()) {
            if (tHead.map.get(prefix.charAt(index)) != null) {
                tHead = tHead.map.get(prefix.charAt(index));
            } else {
                return false;
            }
            index++;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 True
        System.out.println(trie.search("app"));   // 返回 False

        System.out.println(trie.startsWith("app"));// 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));  // 返回 True
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */