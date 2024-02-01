import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] mbti = new String[]{"ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP",
                "ENFJ", "ENFP", "ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP"};
        int[][] d = new int[16][16]; // 두 mbti의 거리

        for (int i = 0; i < 16; i++) { // 대각선은 0
            for (int j = i + 1; j < 16; j++) {
                int distance = 0;
                for (int k = 0; k < 4; k++) {
                    distance += (mbti[i].charAt(k) == mbti[j].charAt(k) ? 0 : 1); // 요소가 다르면 distance+=1
                }
                d[i][j] = distance;
                d[j][i] = distance;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
// ESTJ, ESTP, ESFJ, ESFP, ENTJ, ENTP, ENFJ, ENFP, ISTJ, ISTP, ISFJ, ISFP, INTJ, INTP, INFJ, INFP
            int[] A = new int[16]; // 해당 MBTI 사람 수
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int idx = 0;
                String input = st.nextToken();
                if(input.charAt(0) == 'I') idx += 8;
                if(input.charAt(1) == 'N') idx += 4;
                if(input.charAt(2) == 'F') idx += 2;
                if(input.charAt(3) == 'P') idx += 1;
                A[idx] += 1;
            }
            for (int j = 0; j < 16; j++) { // 3명이 초과할 경우 절사
                if(A[j] > 3) A[j] = 3;
            }
            int minD = 12;
            for (int one = 0; one < 16; one++) {
                if (A[one] > 0) {
                    A[one]--;
                    for (int two = 0; two < 16; two++) {
                        if (A[two] > 0) {
                            A[two]--;
                            for (int three = 0; three < 16; three++) {
                                if (A[three] > 0) {
                                    int nowMin = d[one][two] + d[one][three] + d[three][two];
                                    if (nowMin < minD) {
                                        minD = nowMin;
                                    }
                                }
                            }
                            A[two]++;
                        }
                    }
                    A[one]++;
                }
            }
            System.out.println(minD);
        }
    }
}
