import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int modeValue = 1; // 최빈값 빈도
        ArrayList<Integer> modeList = new ArrayList<>(); // 최빈값 저장
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(br.readLine());
            list.add(temp);
            sum += temp;
            if (max < temp) {
                max = temp;
            }
            if (min > temp) {
                min = temp;
            }
            if (!map.containsKey(temp)) {
                map.put(temp, 1);
                if (modeValue == 1) {
                    modeList.add(temp);
                }
            } else {
                int freqNew = map.get(temp) + 1;
                map.put(temp, freqNew);
                if (modeValue == freqNew) {
                    modeList.add(temp);
                } else if (modeValue < freqNew) {
                    modeList = new ArrayList<>();
                    modeList.add(temp);
                    modeValue = freqNew;
                }
            }
        }


        System.out.println(Math.round((double) sum / n));
        Collections.sort(list);
        System.out.println(list.get(n / 2));

        if (modeList.size() > 1) {
            Collections.sort(modeList);
            System.out.println(modeList.get(1));
        } else {
            System.out.println(modeList.get(0));
        }

        System.out.println(max - min);

    }
}
