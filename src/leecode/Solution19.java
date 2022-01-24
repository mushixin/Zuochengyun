package leecode;

class Solution19 {

    boolean[][]dp;
    public boolean nextIsXing(String p, int indexCur){
        if(indexCur+1<p.length()){
            return p.charAt(indexCur+1)=='*';
        }
        return false;
    }

    public boolean charMatch(char c,char p){
        if(c==p||p=='.'||c=='.'){
            return true;
        }
        return false;
    }

    //8点。
    public boolean isMatch(String s, String p) {
        if(s.length()==0 && p.length()==0){
            return true;
        }else if(p.length()==0){
            return false;
        }

        //dp[x][y] 前x个字符串，和p的前y个元素是否匹配。若y的下一个元素为*则当前为false
        dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        // for(int x=0;x<s.length();++x){
        //     dp[x][1] = (x==0?s.charAt(0)==p.charAt(0):false);
        // }
        for(int y=2;y<=p.length();++y){
            dp[0][y] = (p.charAt(y-1)=='*'? dp[0][y-2]:false);
        }
        if (s.length()==0){
            return dp[0][p.length()];
        }

        for(int x=1;x<=s.length();++x){
            for(int y=1;y<=p.length();++y){
                if(charMatch(s.charAt(x-1),p.charAt(y-1))){
                    dp[x][y] = dp[x-1][y-1];
                }else if(p.charAt(y-1)=='*'){
                    char tempP = p.charAt(y-2);
                    boolean result = false;
                    if(y>=2){
                        result =  dp[x][y-2];
                    }
                    for(int i=1;i<=x;++i){
                        //s的前x个字符，这里也就index是第x-1,x-i-1
                        if(!result && charMatch(tempP,s.charAt(x-i))){
                            result = dp[x-i][y-2];
                        }else{
                            break;
                        }
                    }
                    dp[x][y] = result;
//                    if(dp[x][y]){//向后更新。
//                        for(int i=1;x-1+i<s.length();++i){
//                            //s的前x个字符，这里也就index是第x-1
//                            if(charMatch(tempP, s.charAt(x-1+i))){
//                                dp[x+i][y] =true;
//                            }else{
//                                break;
//                            }
//                        }
//                    }

                }
            }
        }


        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Solution19().isMatch("ab",".*"));
    }
}