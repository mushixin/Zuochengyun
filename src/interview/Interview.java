package interview;
//
//class ListNode {
//    public int val;
//    public ListNode next;
//    public ListNode(int x) {
//        val = x;
//    }
//}

public class Interview {

//    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//
//        ListNode newNode = ReverseList(node1);
//        ListNode currentNode = newNode;
//        while (currentNode != null) {
//            System.out.println(currentNode.val);
//            currentNode = currentNode.next;
//        }
//        System.out.println();
//        node1 = new ListNode(1);
//        node2 = new ListNode(2);
//        node1.next = node2;
//        newNode = ReverseList(node1);
//        currentNode = newNode;
//        while (currentNode != null) {
//            System.out.println(currentNode.val);
//            currentNode = currentNode.next;
//        }
//    }
//
//    public static ListNode ReverseList(ListNode head) {
//        if(head==null || head.next==null){
//            return  head;
//        }
//        ListNode n1 = head;
//        ListNode n2 = head.next;
//        n1.next = null;
//        while(n2.next!=null){
//            ListNode temp = n2.next;
//            n2.next = n1;
//            n1 = n2;
//            n2 = temp;
//        }
//        n2.next = n1;
//        return n2;
//    }


//    If the hash algorithm is defined by the following pseudo-code:
//
//    Int64 hash (String s) {
//    Int64 h = 7
//    String letters = "acdegiklmnoprstuw"
//    for(Int32 i = 0; i < s.length; i++) {
//        h = (h * 37 + letters.indexOf(s[i]))
//    }
//    return h
//    } 
//    For example, provide string  “leepadg”, the hash value is 682697436409Now provide hash value 24626831736490. could you find out original string?

    public static long hash(String input){
        long h = 7L;
        String letters = "acdegiklmnoprstuw";

        for(int i=0;i<input.length();++i){
            h = (h*37 + letters.indexOf(input.charAt(i)));
        }
        return  h;
    }

    public static void main(String[] args) {
        System.out.println(hash("leepadg"));
        System.out.println(reHash(24626831736490L));
        System.out.println(hash("autodesk"));
    }

    public static String reHash(long input){
        if(input<=7L){
            return  "";
        }

        String letters = "acdegiklmnoprstuw";
        String result = new String();

        while(input>7L){
            long befH = input/37;
            char current = letters.charAt((int)(input - befH*37));//
            result = current+result;
            input = befH;
        }
        return  result;
    }


}

