class Solution {
    public int solution(int n) {
        int SUM = 1000000007;
        if(n % 2 == 1) return 0;
        int k = n / 2;
        long[] DP = new long[k + 2];
        long acc = 1;
        DP[1] = 3;
        DP[2] = 11;
        
        for(int i=3; i<= k; i++){
            DP[i] = ((DP[i-1]*3)%SUM + (DP[i-2]*2)%SUM + 2*(acc%SUM)%SUM)%SUM;
            acc = (acc + DP[i-2])%SUM;
        }
        
        return (int) DP[k];
    }
}