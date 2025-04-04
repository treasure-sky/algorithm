import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] state;
    static int[] topIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        state = new boolean[4][8]; // S극은 true N극은 false
        for (int i = 0; i < 4; i++) {
            String inputState = br.readLine();
            for (int j = 0; j < 8; j++) {
                state[i][j] = inputState.charAt(j) == '1';
            }
        }
        topIdx = new int[4]; // 각 톱니바퀴의 12시 방향 인덱스, 최초에는 0

        int k = Integer.parseInt(br.readLine());


        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int[] spinArray = new int[4];
            spinArray[gearNum] = Integer.parseInt(st.nextToken()); // 시계방향: 1 , 반시계방향: -1


            // 오른쪽 전파
            for (int j = gearNum + 1; j < 4; j++) {
                boolean prevRight = state[j - 1][calcIndex(topIdx[j - 1] + 2)]; // 전 톱니의 오른쪽 극
                boolean curLeft = state[j][calcIndex(topIdx[j] - 2)]; // 현재 톱니의 왼쪽 극
                if(prevRight != curLeft) { // 맞닿아 있는 게 다른 극이면
                    spinArray[j] = spinArray[j - 1] == 1 ? -1 : 1; // 서로 반대방향 회전
                }else{
                    break; // 같은 극이면 더이상 전파되지 않음
                }
            }

            // 왼쪽 전파
            for (int j = gearNum - 1; j >= 0 ; j--) {
                boolean prevLeft = state[j + 1][calcIndex(topIdx[j + 1] - 2)];
                boolean curRight = state[j][calcIndex(topIdx[j] + 2)];
                if(prevLeft != curRight) {
                    spinArray[j] = spinArray[j + 1] == 1 ? -1 : 1;
                }else{
                    break;
                }
            }

            // 회전 반영
            for (int j = 0; j < 4; j++) {
                if (spinArray[j] == 1) { // 시계방향 회전시
                    topIdx[j] = calcIndex(topIdx[j] - 1);
                } else if (spinArray[j] == -1) { // 반시계방향 회전시
                    topIdx[j] = calcIndex(topIdx[j] + 1);
                }
            }
        }

        int res = 0;

        for (int i = 0; i < 4; i++) {
            if(state[i][topIdx[i]]) {
                res += (int) Math.pow(2, i);
            }
        }

        System.out.println(res);


    }

    public static int calcIndex(int idx){
        if(idx >= 8){
            return idx - 8;
        }else if(idx < 0){
            return idx + 8;
        }
        return idx;
    }

}
