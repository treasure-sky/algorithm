import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        // routes 의 포인트를 1이 아닌 0이 시작이도록 변경
        for(int[] route : routes){
            for(int i=0; i < route.length; i++){
                route[i] = route[i] - 1;
            }
        }
        
        int robotCnt = routes.length;
        
        // 로봇의 경로 구하기
        List<List<int[]>> paths = new ArrayList<>();
        int maxTime = 0;
        for(int r=0; r<robotCnt; r++){ // 각 로봇들에 대해
            List<int[]> path = new ArrayList<>();
            int time = 0;
            int[] route = routes[r];
            int curR = points[route[0]][0];
            int curC = points[route[0]][1];
            path.add(new int[]{curR, curC});
            for(int p=1; p < route.length; p++){ // 해당 로봇이 지나는 포인트에 대해
                int start = route[p-1];
                int end = route[p];
                while(points[end][0] != curR || points[end][1] != curC){
                    if(points[end][0] == curR){
                        if(points[end][1] > curC){
                            curC++;
                        }else{
                            curC--;
                        }
                    }else if(points[end][0] > curR){
                        curR++;
                    }else{
                        curR--;
                    }
                    path.add(new int[]{curR, curC});
                    time++;
                }
            }
            paths.add(path);
            maxTime = Math.max(maxTime, time);
        }
        
        // 충돌횟수 구하기
        int collision = 0;
        for(int t=0; t<=maxTime; t++){
            Map<String, Integer> map = new HashMap<>();
            for(int i=0; i<robotCnt; i++){
                List<int[]> path = paths.get(i);
                if(path.size() <= t) continue;
                int[] pos = path.get(t);
                String s = pos[0] + "_" + pos[1];
                if(map.get(s) == null){
                    map.put(s, 1);
                }else{
                    map.put(s, map.get(s) + 1);
                }
            }
            for(int cnt : map.values()){
                if(cnt >= 2){
                    collision++;
                }
            }
        }
        
        
        
        return collision;
    }
}

// class Solution {
//     public int solution(int[][] points, int[][] routes) {
//         int answer = 0;
        
//         // 로봇의 현재 좌표
//         int[][] robotPos = new int[routes.length][2];
//         for(int i=0; i<robotPos.length; i++){
//             int pointIdx = routes[i][0] - 1;
//             robotPos[i][0] = points[pointIdx][0]; // r
//             robotPos[i][1] = points[pointIdx][1]; // c
//         }
        
//         // 로봇이 지나간 마지막 포인트의 인덱스
//         int[] passedPoint = new int[robotPos.length];
//         for(int i=0; i<passedPoint.length; i++){
//             passedPoint[i] = 0;
//         }
        
//         while(true){
//             int[][] map = new int[101][101];
//             boolean isMoved = false;
//             for (int i=0; i<robotPos.length;i++){
//                 if(robotPos[i][0] == -1) continue;
//                 if(routes[i].length - 1 == passedPoint[i]){ // 완주한 경우
//                     robotPos[i][0] = -1;
//                     robotPos[i][1] = -1;
//                     isMoved = true;
//                 }else{ // 완주하지 못한 경우
                    
//                     // 다음 목적지로 이동
//                     if(move(i, routes[i][passedPoint[i]+1] - 1, robotPos, points, map)){
//                         // 한 칸 전진한 경우
//                         isMoved = true;
//                         continue;
//                     }else{
//                         // 다음 목적지에 도달하여 전진하지 못한 경우
//                         passedPoint[i]++;
//                         if(routes[i].length - 1 == passedPoint[i]) continue;
//                         move(i, routes[i][passedPoint[i]+1] - 1, robotPos, points, map);
//                         isMoved = true;
//                     }
//                 }
//             }
//             if(isMoved == false) break; // 아무도 움직이지 않았을 때
//             for(int i=1; i<101; i++){
//                 for(int j=1; j <101; j++){
//                     if(map[i][j] > 1) answer++;
//                 }
//             }
//         }
//         return answer;
//     }
    
//     // 다음 포인트로 한 칸 이동하면 true, 이미 도착했다면 false
//     static boolean move(int robotIdx, int goalPointIdx, int[][] robotPos, int[][]points, int[][] map){
//         if(robotPos[robotIdx][0] == points[goalPointIdx][0]){ // 도착지와 r이 같으면
//             if(robotPos[robotIdx][1] == points[robotIdx][1]){ // c도 같으면
//                 return false;
//             }else{ // c만 다른 경우
//                 if(robotPos[robotIdx][1] > points[robotIdx][1]){
//                     robotPos[robotIdx][1]--;
//                 }else{
//                     robotPos[robotIdx][1]++;
//                 }
//             }
//         }else{ // r이 다른 경우
//             if(robotPos[robotIdx][0] > points[robotIdx][0]){
//                     robotPos[robotIdx][0]--;
//             }else{
//                     robotPos[robotIdx][0]++;
//             }
//         }
//         map[robotPos[robotIdx][0]][robotPos[robotIdx][1]]++;
//         return true;
//     }
// }