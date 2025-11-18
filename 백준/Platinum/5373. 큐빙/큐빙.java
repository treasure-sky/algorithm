import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static char[][] L, R, U, D, B, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            L = newFace('g');
            R = newFace('b');
            U = newFace('w');
            D = newFace('y');
            B = newFace('o');
            F = newFace('r');

            int spinCnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < spinCnt; j++) {
                String now = st.nextToken();
                char face = now.charAt(0);
                char dir = now.charAt(1);
                if(dir == '+'){
                    spinClock(face);
                }else{
                    spinClock(face);
                    spinClock(face);
                    spinClock(face);
                }

            }
            sb.append(printFace(U));
        }
        System.out.println(sb);

    }

    public static void spinClock(char criteria) {
        if(criteria == 'U'){
            char temp1 = B[2][0];
            char temp2 = B[2][1];
            char temp3 = B[2][2];

            B[2][0] = L[2][2];
            B[2][1] = L[1][2];
            B[2][2] = L[0][2];

            L[2][2] = F[0][2];
            L[1][2] = F[0][1];
            L[0][2] = F[0][0];

            F[0][2] = R[0][0];
            F[0][1] = R[1][0];
            F[0][0] = R[2][0];

            R[0][0] = temp1;
            R[1][0] = temp2;
            R[2][0] = temp3;

            U = spinFaceClock(U);
        } else if (criteria == 'R') {
            char temp1 = B[2][2];
            char temp2 = B[1][2];
            char temp3 = B[0][2];

            B[2][2] = U[2][2];
            B[1][2] = U[1][2];
            B[0][2] = U[0][2];

            U[2][2] = F[2][2];
            U[1][2] = F[1][2];
            U[0][2] = F[0][2];

            F[2][2] = D[0][0];
            F[1][2] = D[1][0];
            F[0][2] = D[2][0];

            D[0][0] = temp1;
            D[1][0] = temp2;
            D[2][0] = temp3;

            R = spinFaceClock(R);
        } else if (criteria == 'D') {
            char temp1 = R[2][2];
            char temp2 = R[1][2];
            char temp3 = R[0][2];

            R[2][2] = F[2][0];
            R[1][2] = F[2][1];
            R[0][2] = F[2][2];

            F[2][0] = L[0][0];
            F[2][1] = L[1][0];
            F[2][2] = L[2][0];

            L[0][0] = B[0][2];
            L[1][0] = B[0][1];
            L[2][0] = B[0][0];

            B[0][2] = temp1;
            B[0][1] = temp2;
            B[0][0] = temp3;

            D = spinFaceClock(D);
        } else if (criteria == 'L') {
            char temp1 = U[0][0];
            char temp2 = U[1][0];
            char temp3 = U[2][0];

            U[0][0] = B[0][0];
            U[1][0] = B[1][0];
            U[2][0] = B[2][0];

            B[0][0] = D[2][2];
            B[1][0] = D[1][2];
            B[2][0] = D[0][2];

            D[2][2] = F[0][0];
            D[1][2] = F[1][0];
            D[0][2] = F[2][0];

            F[0][0] = temp1;
            F[1][0] = temp2;
            F[2][0] = temp3;

            L = spinFaceClock(L);
        } else if (criteria == 'B') {
            char temp1 = U[0][2];
            char temp2 = U[0][1];
            char temp3 = U[0][0];

            U[0][2] = R[0][2];
            U[0][1] = R[0][1];
            U[0][0] = R[0][0];

            R[0][2] = D[0][2];
            R[0][1] = D[0][1];
            R[0][0] = D[0][0];

            D[0][2] = L[0][2];
            D[0][1] = L[0][1];
            D[0][0] = L[0][0];

            L[0][2] = temp1;
            L[0][1] = temp2;
            L[0][0] = temp3;

            B = spinFaceClock(B);
        } else{
            char temp1 = U[2][0];
            char temp2 = U[2][1];
            char temp3 = U[2][2];

            U[2][0] = L[2][0];
            U[2][1] = L[2][1];
            U[2][2] = L[2][2];

            L[2][0] = D[2][0];
            L[2][1] = D[2][1];
            L[2][2] = D[2][2];

            D[2][0] = R[2][0];
            D[2][1] = R[2][1];
            D[2][2] = R[2][2];

            R[2][0] = temp1;
            R[2][1] = temp2;
            R[2][2] = temp3;

            F = spinFaceClock(F);
        }
    }

    public static char[][] newFace(char color){
        char[][] face = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[i][j] = color;
            }
        }
        return face;
    }

    public static char[][] spinFaceClock(char[][] face){
        char[][] newFace = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newFace[j][2-i] = face[i][j];
            }
        }
        return newFace;
    }

    public static StringBuilder printFace(char[][] face) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(face[i][j]);
            }
            sb.append("\n");
        }
        return sb;
    }

}
