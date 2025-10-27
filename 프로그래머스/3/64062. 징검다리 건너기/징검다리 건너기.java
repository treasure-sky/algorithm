import java.util.*;
class Solution {
    static int n;

    public int solution(int[] stones, int k) {
        n = stones.length;
        int low = 0;
        int high = 200000000;
        int mid = -1;
        int ans = -1;
        while(low <= high){
            mid = (high + low) / 2;
            if(check(stones, mid, k)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans + 1;
    }
    
    public static boolean check(int[] stones, int m, int k){ // m은 현재 지나간 사람 수
        int blank = 0; // 연속된 0 디딤돌
        boolean wasBlank = false;
        for(int i=0; i < n; i++){
            int cur = stones[i] - m; // 현재 디딤돌 숫자
            if(cur <= 0){
                if(wasBlank){
                    blank++;
                }else{
                    wasBlank = true;
                    blank = 1;
                }
            }else{
                wasBlank = false;
            }
            if(blank >= k) {
                return false;
            }
        }
        return true;
    }
}