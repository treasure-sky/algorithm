import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int strLen1 = str1.length;
        int strLen2 = str2.length;

        int[][] A = new int[strLen2 + 1][strLen1 + 1];

        for (int i = strLen2 - 1; i >= 0; i--) {
            for (int j = strLen1 - 1; j >= 0; j--){
                if (str1[j] == str2[i]) {
                    A[i][j] = A[i + 1][j + 1] + 1;
                }else{
                    A[i][j] = Math.max(A[i][j + 1], A[i + 1][j]);
                }
            }
        }
        System.out.print(A[0][0]);
    }
}
