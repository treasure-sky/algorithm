import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        class Elem {

            short num;
            ArrayList<Character> arrayList;

            Elem(short now, ArrayList<Character> arrayList) {
                this.num = now;
                this.arrayList = arrayList;
            }

        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Queue<Elem> queue = new LinkedList<>();
            Set<Short> visited = new HashSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            short start = Short.parseShort(st.nextToken());
            short end = Short.parseShort(st.nextToken());
            Elem elem = new Elem(start, new ArrayList<>());
            queue.add(elem);
            visited.add(elem.num);

            while (true) {
                Elem now = queue.poll();
                short num = now.num;
                ArrayList<Character> arr = now.arrayList;
                if (num == end) {
                    for (char c : arr) {
                        sb.append(c);
                    }
                    sb.append("\n");
                    break;
                }

                Short D = (short) ((num * 2) % 10000);
                Short S = (short) (num == 0 ? 9999 : num - 1);
                Short L = (short) ((num * 10) % 10000 + num / 1000);
                Short R = (short) ((num / 10) + (num % 10) * 1000);

                if (!visited.contains(D)) {
                    ArrayList<Character> arrNew = new ArrayList<>(arr);
                    arrNew.add('D');
                    queue.add(new Elem(D, arrNew));
                    visited.add(D);
                }
                if (!visited.contains(S)) {
                    ArrayList<Character> arrNew = new ArrayList<>(arr);
                    arrNew.add('S');
                    queue.add(new Elem(S, arrNew));
                    visited.add(S);
                }
                if (!visited.contains(L)) {
                    ArrayList<Character> arrNew = new ArrayList<>(arr);
                    arrNew.add('L');
                    queue.add(new Elem(L, arrNew));
                    visited.add(L);
                }
                if (!visited.contains(R)) {
                    ArrayList<Character> arrNew = new ArrayList<>(arr);
                    arrNew.add('R');
                    queue.add(new Elem(R, arrNew));
                    visited.add(R);
                }

            }


        }
        System.out.println(sb.toString());

    }
}
