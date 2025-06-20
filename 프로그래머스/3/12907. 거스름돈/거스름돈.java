class Solution {
    public int solution(int n, int[] money) {
        
        long[] dp = new long[n+1];
        
        dp[0]=1;
        for(int coin : money){
            for(int i = coin; i <= n; i++){
                dp[i] += dp[i - coin];
            }
        }      
        
        return (int) dp[n]%1000000007;
    }
}