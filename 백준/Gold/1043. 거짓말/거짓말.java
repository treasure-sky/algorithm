import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[] visitedParty;
    static Set<Integer> visitedPerson;
    static List<HashSet<Integer>> party;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        party = new ArrayList<>();
        visitedParty = new boolean[m];
        visitedPerson = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int truthPersonNum = Integer.parseInt(st.nextToken());
        List<Integer> truth = new ArrayList<>();
        for (int i = 0; i < truthPersonNum; i++) {
            int temp = Integer.parseInt(st.nextToken());
            truth.add(temp);
            visitedPerson.add(temp);
        }


        for (int i = 0; i < m; i++) {
            party.add(new HashSet<>());
            st = new StringTokenizer(br.readLine());
            int personNum = Integer.parseInt(st.nextToken());
            Set<Integer> nowSet = party.get(i);
            for (int j = 0; j < personNum; j++) {
                nowSet.add(Integer.valueOf(st.nextToken()));
            }

        }


        for (int i : truth) { // 이미 알고있는 거짓말 못하는 사람들로 다른 거짓말 못하는 사람 알아내기
            dfs(i);
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if(!visitedParty[i]) cnt++;
        }

        System.out.println(cnt);


    }

    public static void dfs(int person) {
        for (int i = 0; i < m; i++) {
            if(visitedParty[i]) continue; // 거짓말 못하는 파티인지 알고있는 파티면 패스
            Set<Integer> nowSet = party.get(i);
            if(!nowSet.contains(person)) continue;
            for (int j : nowSet) {
                if(visitedPerson.contains(j)) continue;
                visitedPerson.add(j);
                dfs(j);
            }
            visitedParty[i] = true;
        }
    }
}
