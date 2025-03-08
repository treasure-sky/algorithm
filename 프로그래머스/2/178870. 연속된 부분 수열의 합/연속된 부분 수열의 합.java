class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        int minLen = Integer.MAX_VALUE;
        int p1=0, p2=0, sum=sequence[0];
        while(true){
            if(sum < k){
                p2++;
                if(p2 >= sequence.length) break;
                sum += sequence[p2];
            }else if (sum > k){
                sum -= sequence[p1];
                p1++;
            }else{
                if((p2 - p1 + 1) < minLen){
                    minLen = p2 - p1 + 1;
                    answer = new int[]{p1, p2};
                }
                sum -= sequence[p1];
                p1++;
                p2++;
                if(p2 >= sequence.length) break;
                sum += sequence[p2];
            }
        }
        
        return answer;
    }
}