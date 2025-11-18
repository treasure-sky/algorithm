import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int maxAcc = 0;

    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1}; // 0 ~ 7
    static int[] dy = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {

        int[][] A = new int[4][4]; // 물고기 num 저장
        int[] fishX = new int[17]; // i번째 물고기 x좌표
        int[] fishY = new int[17]; // i번째 물고기 y좌표
        int[] fishDir = new int[17]; // i번째 물고기 방향 (0~7)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                A[i][j] = num;
                fishX[num] = i;
                fishY[num] = j;
                fishDir[num] = dir - 1; // 편의를 위해 1~8 -> 0~7 변경
            }
        }

        int initFishNum = A[0][0];
        int sharkDir = fishDir[initFishNum];
        fishDir[initFishNum] = -1; // 0, 0 물고기 사망처리
        A[0][0] = 0;
        bt(0, 0, sharkDir, initFishNum, fishX, fishY, fishDir, A);
        System.out.println(maxAcc);

    }

    public static void bt(int sharkX, int sharkY, int sharkDir, int acc, int[] fishX, int[] fishY,
        int[] fishDir, int[][] A) {
        maxAcc = Math.max(acc, maxAcc);

        for (int i = 1; i <= 16; i++) { // 물고기 움직이기
            if(fishDir[i] == -1) continue; // 죽은 물고기는 넘어가기
            int dir = fishDir[i];
            int curX = fishX[i];
            int curY = fishY[i];
            for (int j = 0; j < 8; j++) {
                int nextX = curX + dx[dir];
                int nextY = curY + dy[dir];

                // 범위를 벗어나거나, 상어의 위치이면 다른 방향 탐색
                if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4
                    || (sharkX == nextX && sharkY == nextY)) {
                    dir = (dir + 1) % 8;
                    continue;
                }
                fishDir[i] = dir;

                // 빈칸이면
                if (A[nextX][nextY] == 0){
                    A[curX][curY] = 0;
                    fishX[i] = nextX;
                    fishY[i] = nextY;
                    A[nextX][nextY] = i;
                    break;
                }

                // 물고기 있으면 교체
                int changedFishNum = A[nextX][nextY];
                A[nextX][nextY] = A[curX][curY];
                A[curX][curY] = changedFishNum;
                fishX[i] = nextX;
                fishY[i] = nextY;
                fishX[changedFishNum] = curX;
                fishY[changedFishNum] = curY;
                break;

            }
        }

        // 상어 움직이기
        while (true) {
            sharkX += dx[sharkDir];
            sharkY += dy[sharkDir];
            if (sharkX < 0 || sharkX >= 4 || sharkY < 0 || sharkY >= 4) return; // 나가버리면 종료
            if (A[sharkX][sharkY] == 0) continue; // 물고기 없으면 패스

            // 물고기 먹는 경우
            int fishNum = A[sharkX][sharkY];
            int newSharkDir = fishDir[fishNum];
            int[][] newA = mapCopy(A);
            int[] newFishX = new int[17];
            int[] newFishY = new int[17];
            int[] newFishDir = new int[17];
            System.arraycopy(fishX, 0, newFishX, 0, 17);
            System.arraycopy(fishY, 0, newFishY, 0, 17);
            System.arraycopy(fishDir, 0, newFishDir, 0, 17);

            newFishDir[fishNum] = -1;
            newA[sharkX][sharkY] = 0;

            bt(sharkX, sharkY, newSharkDir, acc + fishNum, newFishX, newFishY, newFishDir, newA);

            // 물고기 안먹으면 패스
        }

    }

    public static int[][] mapCopy(int[][] A) {
        int[][] newMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(A[i], 0, newMap[i], 0, 4);
        }
        return newMap;
    }

}
