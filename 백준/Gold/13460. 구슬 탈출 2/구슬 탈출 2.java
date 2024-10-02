/**
 * 13460 구슬 탈출 2
 *
 * 보드판의 크기는 최대 10*10, 동작은 4가지, 최대 시행 10번
 * 4^10 * 18(구슬2개 * 구슬이 움직일 수 있는 지 비교 최대 9번)
 * = 2^20 * 18 < 1억
 * 큐에 구슬 상태를 삽입하면서 BFS
 */
// 13460 구슬 탈출 2
//
// 구슬의 상태를 저장하는 자료구조를 만들어야 함
// 두 구슬의 상태는 위치를 나타내는 x,y 쌍 두개와 몇번 째 시도인지로 나타낼 수 있음
// 구슬 하나를 굴릴 때 다른 구슬이 막고 있는 상황을 고려해야함 (막고 있는 구슬을 굴리고 다시 원래 구슬을 굴리는 방식)
// -> 빨 파 빨 이런식으로 처음 시도했던 구슬 마지막에 다시 시도하면 해결
// 동시에 들어가는 경우도 고려



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static class state {
    byte redR, redC, bluR, bluC, time;

    public state(byte redR, byte redC, byte bluR, byte bluC, byte time) {
      this.redR = redR;
      this.redC = redC;
      this.bluR = bluR;
      this.bluC = bluC;
      this.time = time;
    }
  }
  // {위쪽, 왼쪽, 아래쪽, 오른쪽}
  static byte dr[] = new byte[]{-1, 0, 1, 0};
  static byte dc[] = new byte[]{0, -1, 0, 1};

  static int n, m;
  static char[][] A;
  static byte holR = -1, holC = -1; // 초기 구슬, 구멍 위치

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken()) - 2;
    m = Integer.parseInt(st.nextToken()) - 2;
    A = new char[n][m];
    byte redR = -1, redC = -1, bluR = -1, bluC = -1;
    Queue<state> queue = new LinkedList<>();

    br.readLine(); // 맨 윗줄 ####버림
    for (byte i = 0; i < n; i++) {
      String str = br.readLine();
      for (byte j = 0; j < m; j++) {
        char input = str.charAt(j + 1);
        if (input == '#' || input == '.') {
          A[i][j] = input;
          continue;
        } else if (input == 'R') {
          redR = i;
          redC = j;
        } else if (input == 'B') {
          bluR = i;
          bluC = j;
        } else if (input == 'O') {
          holR = i;
          holC = j;
        }
        A[i][j] = '.';
      }
    }
    br.readLine(); // 맨 아랫줄 ####버림

    queue.add(new state(redR, redC, bluR, bluC, (byte) 0));

    while (!queue.isEmpty()) {
      state now = queue.poll();

//      char[][] tempA = new char[n][m];
//      for (int i = 0; i < n; i++) {
//        for (int j = 0; j < m; j++) {
//          tempA[i][j] = A[i][j];
//        }
//      }
//      tempA[now.redR][now.redC] = 'R';
//      tempA[now.bluR][now.bluC] = 'B';
//      tempA[holR][holC] = 'O';
//      for (int i = 0; i < n; i++) {
//        for (int j = 0; j < m; j++) {
//          System.out.print(tempA[i][j]);
//        }
//        System.out.println();
//      }
//      System.out.print(now.time);
//      System.out.println();
//      System.out.println("");


      if(now.time == 10) { // 이미 10번 동작을 시행한 경우. -1 출력
        System.out.printf("-1");
        System.exit(0);
      }
      for (int i = 0; i < 4; i++) { // 4가지 동작
        state newState = new state(now.redR, now.redC, now.bluR, now.bluC, now.time);
        newState = redAction(newState, i);
        newState = blueAction(newState, i);
        newState = redAction(newState, i);
        if(newState.time == -1) continue;
        newState.time++;
        queue.add(newState);
      }
    }



  }

  // 빨간 공 움직이기
  public static state redAction (state nowState, int direction){
    byte nextRedR = nowState.redR;
    byte nextRedC = nowState.redC;

    while (true) {
      nextRedR += dr[direction];
      nextRedC += dc[direction];
      if (nextRedR < 0 || nextRedR >= n || nextRedC < 0 || nextRedC >= m) break; // 범위를 넘어가는 경우
      if(nextRedR == holR && nextRedC == holC) { // 구멍 만나면
        nowState.redR = nextRedR;
        nowState.redC = nextRedC;
        if(blueAction(nowState, direction).time == -1){ // 파란 구슬도 구멍에 들어간다면,
          nowState.time = -1;
          break;
        }else{
          System.out.print(nowState.time + 1);
          System.exit(0);
        }
      } // 벽에 막혀있거나, 파란 구슬이 경로를 막는 경우 종료
      else if (A[nextRedR][nextRedC] == '#' || (nextRedR == nowState.bluR && nextRedC == nowState.bluC)) break;

      nowState.redR = nextRedR;
      nowState.redC = nextRedC;
    }

    return  nowState;
  }

  // 파란 공 움직이기
  public static state blueAction (state nowState, int direction){
    byte nextBluR = nowState.bluR;
    byte nextBluC = nowState.bluC;

    while (true) {
      nextBluR += dr[direction];
      nextBluC += dc[direction];
      if (nextBluR < 0 || nextBluR >= n || nextBluC < 0 || nextBluC >= m) break; // 범위를 넘어가는 경우
      if(nextBluR == holR && nextBluC == holC) { // 구멍 만나면 이 경우는 고려하지 않아야 함.
        nowState.time = -1;
        break;
      }
      else if (A[nextBluR][nextBluC] == '#' || (nextBluR == nowState.redR && nextBluC == nowState.redC)) break;

      nowState.bluR = nextBluR;
      nowState.bluC = nextBluC;
    }

    return  nowState;
  }
}
