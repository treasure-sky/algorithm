import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            boolean[] isStair = new boolean[N];
            int prev = A[i][0];
            boolean isPossible = true;
            for (int j = 1; j < N; j++) {
                if(prev + 1 < A[i][j] || prev - 1 > A[i][j]) { // 높이차가 1 초과면 안됨
                    isPossible = false;
                    break;
                }
                if(prev < A[i][j]){ // 올라가야할 때
                    // 경사로 범위 체크 j-L 이 0이상이어야 함
                    if(j-L < 0){
                        isPossible = false;
                        break;
                    }

                    // j-L ~ j-1 이 경계값 포함해서 prev으로 동일해야함.
                    // 기존 경사로가 있으면 안됨.
                    for (int k = j - L; k <= j - 1; k++) {
                        if(A[i][k] != prev || isStair[k]){
                            isPossible = false;
                            break;
                        }
                        isStair[k] = true;
                    }
                    if(!isPossible) break;
                    prev = A[i][j];
                }else if(prev > A[i][j]) { // 내려가야할 때
                    // 경사로 범위 체크 j+L-1 이 N-1 을 초과하면 안됨
                    if(j+L-1 > N-1) {
                        isPossible = false;
                        break;
                    }

                    // j ~ j+L-1 이 경계값 포함해서 A[i][j]로 동일해야함.
                    for (int k = j; k <= j+L-1 ; k++) {
                        if(A[i][k] != A[i][j]){
                            isPossible = false;
                            break;
                        }
                        isStair[k] = true;
                    }
                    if(!isPossible) break;
                    prev = A[i][j];
                    j = j + L - 1; // 경사로 이후로 점프
                }

            }
            if(isPossible) {
                res++;
            }

        }
        for (int j = 0; j < N; j++) {
            boolean[] isStair = new boolean[N];
            int prev = A[0][j];
            boolean isPossible = true;
            for (int i = 1; i < N; i++) {
                if(prev + 1 < A[i][j] || prev - 1 > A[i][j]) { // 높이차가 1 초과면 안됨
                    isPossible = false;
                    break;
                }
                if(prev < A[i][j]){ // 올라가야할 때
                    // 경사로 범위 체크 i-L 이 0이상이어야 함
                    if(i-L < 0){
                        isPossible = false;
                        break;
                    }

                    // i-L ~ i-1 이 경계값 포함해서 prev으로 동일해야함.
                    // 기존 경사로가 있으면 안됨.
                    for (int k = i - L; k <= i - 1; k++) {
                        if(A[k][j] != prev || isStair[k]){
                            isPossible = false;
                            break;
                        }
                        isStair[k] = true;
                    }
                    if(!isPossible) break;
                    prev = A[i][j];
                }else if(prev > A[i][j]) { // 내려가야할 때
                    // 경사로 범위 체크 i+L-1 이 N-1 을 초과하면 안됨
                    if(i+L-1 > N-1) {
                        isPossible = false;
                        break;
                    }

                    // i ~ i+L-1 이 경계값 포함해서 A[i][j]로 동일해야함.
                    for (int k = i; k <= i+L-1 ; k++) {
                        if(A[k][j] != A[i][j]){
                            isPossible = false;
                            break;
                        }
                        isStair[k] = true;
                    }
                    if(!isPossible) break;
                    prev = A[i][j];
                    i = i + L - 1; // 경사로 이후로 점프
                }

            }
            if(isPossible) {
                res++;
            }

        }
        System.out.println(res);

    }

}
