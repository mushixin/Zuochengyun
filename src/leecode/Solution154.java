package leecode;

class Solution154 {
    //index这个元素 是否小于index-1的元素，若是相等，则再往左。[from,to]
    public boolean minLeft(int[] nums,int index,int from,int to){
        int tempT = index-1;
        while(tempT>=from){
            if(nums[index]<nums[tempT]){
                return true;
            }else if(nums[index]>nums[tempT]){
                return false;
            }
            tempT--;
        }
        return true;
    }

    //index这个元素 是否小于index-1的元素，若是相等，则再往右。[from,to]
    //若是没有更小的，返回true
    public boolean minRight(int[] nums,int index,int from,int to){
        int tempT = index+1;
        while(tempT<=to){
            if(nums[index]<nums[tempT]){
                return true;
            }else if(nums[index]>nums[tempT]){
                return false;
            }
            tempT++;
        }
        return true;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid =(left+right)/2;
             boolean mL = minLeft(nums,mid,left,mid);
             boolean mR = minRight(nums,mid,mid,right);
             if(mL && mR){
                 return nums[mid];
             }else if(nums[mid]<nums[left]){
                right = mid-1;
            }else if(nums[mid]>nums[right]){
                left = mid+1;
            }else if(nums[mid]==nums[left]){
                right = mid-1;
            }else if(nums[mid]==nums[right]){
                if(minLeft(nums,mid,left,mid)){
                    return nums[mid];
                }else{
                    right = mid-1;
                }
            }else{
                right = mid-1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
//        int[]input = {1,3,5};
//        System.out.println(new Solution154().findMin(input));

        char[]input = {'a','b','c'};
        System.out.println(new String(input));

    }
}