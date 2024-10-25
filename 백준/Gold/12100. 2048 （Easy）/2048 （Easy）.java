import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static byte log2(int num) {
        if (num == 0) return 0;
        return (byte) Math.round(Math.log(num) / Math.log(2));
    }

    // board를 deepcopy해서 복사본을 반환하는 메서드
    public static byte[][] boardBackup(){
        byte[][] copy = new byte[boardSize][];

        for (int i = 0; i < boardSize; i++) {
            copy[i] = board[i].clone();
        }

        return copy;
    }

    public static void moveUp(){
        for (int j = 0; j < boardSize; j++) {
            byte notCombinedNum = board[0][j];
            int nextIdx = notCombinedNum == 0 ? 0 : 1;
            for (int i = 1; i < boardSize; i++) {
                byte nowNum = board[i][j];
                if(nowNum == 0) continue; // 0이면 무시

                board[i][j] = 0;
                if (notCombinedNum == nowNum) {
                    notCombinedNum = 0;
                    byte combinedNum = (byte) (nowNum + 1);
                    if(combinedNum > max) max = combinedNum;
                    board[nextIdx - 1][j] = combinedNum;
                } else {
                    notCombinedNum = nowNum;
                    board[nextIdx][j] = nowNum;
                    nextIdx++;
                }
            }
        }
    }

    public static void moveDown(){
        for (int j = 0; j < boardSize; j++) {
            byte notCombinedNum = board[boardSize - 1][j];
            int nextIdx = notCombinedNum == 0 ? boardSize - 1 : boardSize - 2;
            for (int i = boardSize - 2; i >= 0; i--) {
                byte nowNum = board[i][j];
                if(nowNum == 0) continue; // 0이면 무시

                board[i][j] = 0;
                if (notCombinedNum == nowNum) {
                    notCombinedNum = 0;
                    byte combinedNum = (byte) (nowNum + 1);
                    if(combinedNum > max) max = combinedNum;
                    board[nextIdx + 1][j] = combinedNum;
                } else {
                    notCombinedNum = nowNum;
                    board[nextIdx][j] = nowNum;
                    nextIdx--;
                }
            }
        }
    }

    public static void moveRight(){
        for (int i = 0; i < boardSize; i++) {
            byte notCombinedNum = board[i][boardSize - 1];
            int nextIdx = notCombinedNum == 0 ? boardSize - 1 : boardSize - 2;
            for (int j = boardSize - 2; j >= 0; j--) {
                byte nowNum = board[i][j];
                if(nowNum == 0) continue; // 0이면 무시

                board[i][j] = 0;
                if (notCombinedNum == nowNum) {
                    notCombinedNum = 0;
                    byte combinedNum = (byte) (nowNum + 1);
                    if(combinedNum > max) max = combinedNum;
                    board[i][nextIdx + 1] = combinedNum;
                } else {
                    notCombinedNum = nowNum;
                    board[i][nextIdx] = nowNum;
                    nextIdx--;
                }
            }
        }
    }

    public static void moveLeft(){
        for (int i = 0; i < boardSize; i++) {
            byte notCombinedNum = board[i][0];
            int nextIdx = notCombinedNum == 0 ? 0 : 1;
            for (int j = 1; j < boardSize; j++) {
                byte nowNum = board[i][j];
                if(nowNum == 0) continue; // 0이면 무시

                board[i][j] = 0;
                if (notCombinedNum == nowNum) {
                    notCombinedNum = 0;
                    byte combinedNum = (byte) (nowNum + 1);
                    if(combinedNum > max) max = combinedNum;
                    board[i][nextIdx - 1] = combinedNum;
                } else {
                    notCombinedNum = nowNum;
                    board[i][nextIdx] = nowNum;
                    nextIdx++;
                }
            }
        }
    }

    public static void bt(int cnt) {
        if(cnt == 5) return;
        byte[][] backUp = boardBackup();
        moveUp();
        bt(cnt + 1);
        board = backUp;

        backUp = boardBackup();
        moveDown();
        bt(cnt + 1);
        board = backUp;

        backUp = boardBackup();
        moveRight();
        bt(cnt + 1);
        board = backUp;

        backUp = boardBackup();
        moveLeft();
        bt(cnt + 1);
        board = backUp;

    }


    static byte[][] board;
    static int boardSize;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boardSize = Byte.parseByte(br.readLine());
        board = new byte[boardSize][boardSize];
        max = 0;

        for (int i = 0; i < boardSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < boardSize; j++) {
                // 로그를 이용해 데이터 축소
                board[i][j] = log2(Integer.parseInt(st.nextToken()));
                max = Math.max(board[i][j], max);
            }
        }

        bt(0);
        System.out.println((int) Math.pow(2, max));

    }
}
