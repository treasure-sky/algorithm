import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            Map<Integer, Integer> valid = new HashMap<>();
            int validCnt = 0;
            PriorityQueue<Integer> minQueue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            int k = Integer.parseInt(br.readLine());
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                // 삽입
                if (st.nextToken().charAt(0) == 'I') {
                    int n = Integer.parseInt(st.nextToken());
                    if (valid.containsKey(n)) {
                        valid.put(n, valid.get(n) + 1);
                    } else {
                        valid.put(n, 1);
                    }
                    validCnt++;
                    maxQueue.add(n);
                    minQueue.add(n);
                }
                //삭제
                else {
                    if (validCnt != 0) { // 유효값이 있을 때만
                        int deleted = 0;
                        if (st.nextToken().length() == 1) { // 최댓값 삭제
                            while (true) {
                                deleted = maxQueue.poll();
                                if (valid.containsKey(deleted) && valid.get(deleted) != 0) {
                                    break;
                                }
                            }

                        } else { // -1일 때
                            while (true) {
                                deleted = minQueue.poll();
                                if (valid.containsKey(deleted) && valid.get(deleted) != 0) {
                                    break;
                                }
                            }
                        }
                        validCnt--;
                        valid.put(deleted, valid.get(deleted) - 1);
                    }
                }
            }
            if (validCnt == 0) {
                System.out.println("EMPTY");
            } else {
                int max = 0;
                int min = 0;
                while (true) {
                    int temp = maxQueue.poll();
                    if (valid.containsKey(temp) && valid.get(temp) != 0) {
                        max = temp;
                        break;
                    }
                }
                while (true) {
                    int temp = minQueue.poll();
                    if (valid.containsKey(temp) && valid.get(temp) != 0) {
                        min = temp;
                        break;
                    }
                }
                System.out.println(max + " " + min);
            }
        }
    }
}
