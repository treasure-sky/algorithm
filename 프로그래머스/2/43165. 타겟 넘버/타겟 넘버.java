class Solution {
    static int numberCnt;
    static int t;
    static int[] n;
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        numberCnt = numbers.length;
        t = target;
        n = numbers;
        dfs(0,0);
        
        return answer;
    }
    
    public static void dfs(int depth, int sum){
        if(depth == numberCnt){
            if(sum == t) answer++;
            return;
        }
        dfs(depth + 1, sum + n[depth]);
        dfs(depth + 1, sum - n[depth]);

    }
}