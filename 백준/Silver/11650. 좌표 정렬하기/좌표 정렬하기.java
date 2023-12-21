import java.util.*;

class coordinate {
    int x;
    int y;

    public coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<coordinate> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new coordinate(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(list, Comparator.comparingInt((coordinate c) -> c.x)
                .thenComparing(c -> c.y));
        for (coordinate c : list) {
            System.out.println(c.x + " " + c.y);
        }
    }
}
