import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> monsterRooms = new Stack<>();
        Stack<Integer> armerRooms = new Stack<>();

        int n = Integer.parseInt(st.nextToken());
        long d = Long.parseLong(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int roomInfo = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            if (roomInfo == 1) { // 몬스터가 있는 방일 때
                monsterRooms.add(power);
            } else { // 장비가 있는 방일 때
                armerRooms.add(power);
            }
        }
        monsterRooms.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        armerRooms.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int maxRoomCnt =  0;

        for (int i = 0; i < n; i++) {
            if (!monsterRooms.isEmpty() && d > monsterRooms.peek()) {
                d += monsterRooms.pop();
            }else{
                if(armerRooms.isEmpty()) break;
                d *= armerRooms.pop();
            }
            maxRoomCnt++;
        }
        System.out.println(maxRoomCnt);


    }
}
