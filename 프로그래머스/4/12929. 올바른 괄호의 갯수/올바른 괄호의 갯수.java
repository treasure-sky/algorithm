import java.util.*;
// cnt를 두고 ( 가 얼마나 사용됐는지를 기록
// ( 가 사용된 만큼만 )를 사용 가능하도록 하기
// 백트래킹으로 가능한 조합 만들기

class Solution {
    static int ans = 0;
    static int N;
    public int solution(int n) {
        N = n;
        bt(0, N, N, 0);
        return ans;
    }
    
    // size는 채워진 괄호 개수
    // leftCnt, rightCnt는 사용할 수 있는 괄호 개수
    // possible은 ) 를 넣을 수 있는 개수
    public static void bt(int size, int leftCnt, int rightCnt, int possible){
        if(size == N*2 || leftCnt == 0){ // 꽉 찼거나 '(' 다 썼으면 종료
            ans++;
            return;
        }
        
        if(possible > 0){ // ')' 사용 가능할 때
            bt(size+1, leftCnt, rightCnt-1, possible-1);
        }
    
        bt(size+1, leftCnt-1, rightCnt, possible+1);
    }
}