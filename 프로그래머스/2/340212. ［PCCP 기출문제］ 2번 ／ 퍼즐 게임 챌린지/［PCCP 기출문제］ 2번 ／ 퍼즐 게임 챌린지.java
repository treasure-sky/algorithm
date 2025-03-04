import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = -1;
        int maxL = Arrays.stream(diffs).max().getAsInt();
        int minL = 1;
        while(maxL >= minL){
            int level = (minL + maxL) / 2;
            
            long sumOfTime = times[0]; // 처음 문제푸는 시간
            for(int i=1; i < diffs.length; i++){ // 그 다음 문제푸는 시간
                int retryNum = diffs[i] - level > 0 ? diffs[i] - level : 0;
                sumOfTime += retryNum * (times[i-1] + times[i]) + times[i];
            }
            if(sumOfTime <= limit){
                answer = level;
                maxL = level - 1;
            }else{
                minL = level + 1;
            }
        }
        return answer;
    }
}

// class Solution {
//     public int solution(int[] diffs, int[] times, long limit) {
//         int answer = 1;
//         int maxL = Arrays.stream(diffs).max().getAsInt();
//         int minL = 1;
//         int level = (minL + maxL) / 2;
//         while(true){
//             long sumOfTime = times[0]; // 처음 문제푸는 시간
//             for(int i=1; i < diffs.length; i++){ // 그 다음 문제푸는 시간
//                 int retryNum = diffs[i] - level > 0 ? diffs[i] - level : 0;
//                 sumOfTime += retryNum * (times[i-1] + times[i]) + times[i];
//             }
//             if(sumOfTime <= limit){
//                 answer = level;
//                 maxL = level;
//                 if(level == (maxL + minL)/2) break;
//                 level = (maxL + minL) / 2;
//             }else{
//                 minL = level + 1;
//                 level = (maxL + minL) / 2;
//             }
//         }
//         return answer;
//     }
// }
