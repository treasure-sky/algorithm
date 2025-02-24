import java.util.*;

class Solution {
    // DP[0][1] : 0번째 사람이 팀장인 서브트리에서 팀장이 워크숍에 가는 경우 최소 매출액
    // DP[0][0] : 0번째 사람이 팀장인 서브트리에서 팀장이 워크숍에 가지 않는 경우 최소 매출액
    static int[][] DP; 
    static List<Integer>[] A;
    static int[] salesArr; 
    
    public int solution(int[] sales, int[][] links) {
        int n = sales.length; // 총 노드 수

        // DP 배열 초기화
        DP = new int[n][2];
        for (int i = 0; i < n; i++){
            DP[i] = new int[2];
            DP[i][0] = -1;
            DP[i][1] = -1;
        }
        
        salesArr = sales; // 파라미터 static 영역으로 넘기기
        
        // 인접리스트 구성
        A = new ArrayList[n];
        for(int i = 0; i < n - 1; i++){ // 엣지는 총 n-1개 있으므로
            int parentIdx = links[i][0] - 1; // 부모의 인덱스
            if(A[parentIdx] == null){
                A[parentIdx] = new ArrayList<>();
            }
            A[parentIdx].add(links[i][1] - 1);
        }
        rec(0);
        return Math.min(DP[0][0], DP[0][1]);
        
    }
    
    private void rec(int parentIdx){
        // leafNode인 경우
        if(A[parentIdx] == null){
            DP[parentIdx][0] = 0;
            DP[parentIdx][1] = salesArr[parentIdx];
            return;
        }
        
        int sumOfAllChildMinSales = 0; // 자식들이 가질 수 있는 최솟값들의 합
        int goChildMinSales = Integer.MAX_VALUE; // 적어도 한 명의 자식이 워크숍에 가는 경우 
        int goChildMinSalesIdx = -1; // 자식들이 워크숍에 가는 경우 중 최솟값을 가지는 자식 번호
        
        // leafNode가 아닌 경우
        for (int childIdx : A[parentIdx]){
            rec(childIdx);
            sumOfAllChildMinSales += Math.min(DP[childIdx][0], DP[childIdx][1]);
            if (goChildMinSales > (DP[childIdx][1] - DP[childIdx][0])){
                goChildMinSales = DP[childIdx][1] - DP[childIdx][0];
                goChildMinSalesIdx = childIdx;
            }
        }
                
        DP[parentIdx][1] = salesArr[parentIdx] + sumOfAllChildMinSales;
        
        DP[parentIdx][0] = sumOfAllChildMinSales + DP[goChildMinSalesIdx][1] 
            - Math.min(DP[goChildMinSalesIdx][0], DP[goChildMinSalesIdx][1]);
        return;
    }
}