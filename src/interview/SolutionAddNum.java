package interview;/*
 * Click `Run` to execute the snippet below!
 */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class SolutionAddNum {
  
  /*
   * 正整数相加
   */
  public static String add(String input1,String input2) {
    int[] num1 = getIntArray(input1);
    int[] num2 = getIntArray(input2);
    
    int[]result = addResult(num1,num2);
    return trans2Str(result);
  }
  
  public static int[] getIntArray(String input){
    int[] res = new int[input.length()];
    for(int i=0;i<input.length();i++){
      res[i]= Integer.parseInt(""+input.charAt(i));
    }
    return res;
  }  
  
  //判断是否可能有空闲的0
  public static String trans2Str(int[]input){
    String res="";
    for(int i=0;i<input.length;++i){
      res=res+input[i];
    }
    return res;
  }
  
  public static int[] addResult(int[] num1, int[] num2){
    if(num1.length<num2.length){
      int[]temp = num1;
      num1= num2;
      num2=temp;
    }
//    printArray(num1);
//    printArray(num2);

    for(int end2 = num2.length-1;end2>=0;end2--){
      int index = num2.length-1 - end2;
      num1[num1.length-1-index] = num1[num1.length-1-index]+num2[end2]; 
    }

    boolean add1 = false;
    for(int end1 = num1.length-1;end1>=0;end1--){
      if(add1){
        num1[end1]++;
        add1=false;
      }
      if(num1[end1]>=10){
         add1=true;
         num1[end1]-=10;
      }
    }
    if (add1) {
      num1[0]+=10; 
    }
    
    return num1;
  }
  public static void printArray(int[]array){
    for(int i=0;i<array.length;++i){
        System.out.print(array[i]+" ");
    }
    System.out.println();
  }
  
  
  public static void main(String[] args) {
   // System.out.println(add("667","766").equals("1433"));

    for (int i = 1; i < 1000; i++) {
      int random = (int)(Integer.MAX_VALUE/2  * Math.random());
      int random2 = (int)(Integer.MAX_VALUE/2  * Math.random());
      if (!add(random + "", random2 + "").equals(random2+random+"")){
          System.out.println(random+" "+random2+" " +(random2+random) );
      }
    }
    System.out.println(add("667","1766"));//2433
    System.out.println(add("1667","766"));//2433
    System.out.println(add("1667","766"));//2433
    
  }
}
