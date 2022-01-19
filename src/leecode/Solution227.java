package leecode;

import java.util.regex.Pattern;

class Solution227 {
    //全部放到linkedlist中，每个数字一个元素，或者是一个+-*/
    //找到第一个乘号除号，然后去分界，然后去乘除，重新拼接字符串。
    public int calculate(String s) {
        while(s.indexOf(" ")>-1){
            s = s.replaceAll(" ","");
        }
        StringBuilder sb = new StringBuilder(s);
        int m =  sb.indexOf("*");
        int d =  sb.indexOf("/");
        while(m!=-1||d!=-1){
            if(m==-1){
                operate(sb,d);
            }else if(d==-1){
                operate(sb,m);
            }else{
                operate(sb,m<d?m:d);
            }
            m =  sb.indexOf("*");
            d =  sb.indexOf("/");
        }
        int a =  sb.indexOf("+");
        int minus =  sb.indexOf("-",1);

        while(a!=-1||minus!=-1){
            if(a==-1){
                operate(sb,minus);
            }else if(minus==-1){
                operate(sb,a);
            }else{
                operate(sb,a<minus?a:minus);
            }
            a =  sb.indexOf("+");
            minus =  sb.indexOf("-",1);
        }
        return Integer.parseInt(sb.toString());
    }

    public int lastIndexOfOperate(StringBuilder sb,int index){
        while(index>=0){
            if(sb.charAt(index)<'0' || sb.charAt(index)>'9'){
                return index;
            }
            index--;
        }
        return -1;
    }
    public int indexOfOperate(StringBuilder sb,int index){
        while(index<sb.length()){
            if(sb.charAt(index)<'0' || sb.charAt(index)>'9'){
                return index;
            }
            index++;
        }
        return -1;
    }
    //字符串，以及*/+-操作
    public void operate(StringBuilder sb,int index){
        int t2= lastIndexOfOperate(sb,index-1);
        if (t2==0 || (t2>0&&(sb.charAt(t2-1)<'0'||sb.charAt(t2-1)>'9'))){
            t2--;
        }
        int startNum = t2;
        int firnum = Integer.parseInt(sb.substring(startNum+1,index));

        int s2 = indexOfOperate(sb,index+1);
        if (s2==0 || (s2>0&&(sb.charAt(s2-1)<'0'||sb.charAt(s2-1)>'9'))){
            s2--;
        }
        s2 = s2!=-1?s2:Integer.MAX_VALUE;
        int num2 = s2;
        num2= num2!=Integer.MAX_VALUE?num2:sb.length();
        int secnum = Integer.parseInt(sb.substring(index+1,num2));
        int res = 0;
        if (sb.charAt(index)=='*') {
            res = firnum * secnum;
        }else if(sb.charAt(index)=='/'){
            res = firnum / secnum;
        }else if(sb.charAt(index)=='+'){
            res = firnum + secnum;
        }else if(sb.charAt(index)=='-'){
            res = firnum - secnum;
        }
        sb.delete(startNum+1,num2);
        sb.insert(startNum+1,res);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("012345");
        System.out.println(sb.lastIndexOf("5",4)==-1);
        sb.delete(1,2);
        System.out.println(sb.toString().equals("02345"));
        sb.insert(0,1);
        System.out.println(sb.toString().equals("102345"));
        sb.insert(0,'c');
        System.out.println(sb.toString().equals("c102345"));

    }
}