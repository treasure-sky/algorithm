import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] A = new boolean[1_000_001];
        int[] input = new int[n];
        int[] score = new int[1_000_001];
        for (int i = 0; i < n; i++) {
            int inputNum = Integer.parseInt(st.nextToken());
            input[i] = inputNum;
            A[inputNum] = true;
        }

        for (int i = 0; i < n; i++) {
            int multNum = 2 * input[i];
            while(multNum <= 1_000_000){
                if(A[multNum]){
                    score[input[i]]++;
                    score[multNum]--;
                }
                multNum += input[i];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(score[input[i]] + " ");
        }

    }

}
