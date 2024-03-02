import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] arr = new boolean[21];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String first = st.nextToken();
            if (first.equals("all")) {
                Arrays.fill(arr, true);
            } else if (first.equals("empty")) {
                Arrays.fill(arr, false);
            } else {
                int second = Integer.parseInt(st.nextToken());
                switch (first) {
                    case "add" -> {
                        arr[second] = true;
                    }
                    case "remove" -> {
                        arr[second] = false;
                    }
                    case "check" -> {
                        if (arr[second]) {
                            sb.append("1\n");
                        } else {
                            sb.append("0\n");
                        }
                    }
                    case "toggle" -> {
                        if (arr[second]) {
                            arr[second] = false;
                        } else {
                            arr[second] = true;
                        }
                    }
                }
            }
        }
        System.out.print(sb.toString());
    }
}
