import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {
    public static class Node{
        TreeMap<Character, Node> child;
        boolean isEnd;

        public Node() {
            child = new TreeMap<>();
            isEnd = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while((input = br.readLine()) != null){ // 케이스별로 반복됨
            int n = Integer.parseInt(input);
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(br.readLine());
            }

            Node root = new Node();

            for (String curWord : words) { // 단어별로 반복
                Node curNode = root;
                for (int i=0; i<curWord.length(); i++) { // 단어의 글자별로 반복
                    char c = curWord.charAt(i);
                    Node nextNode;
                    if (!curNode.child.containsKey(c)) { // 자식이 다음 글자를 가지고 있지 않으면
                        nextNode = new Node();
                        curNode.child.put(c, nextNode);
                    }else{
                        nextNode = curNode.child.get(c);
                    }
                    curNode = nextNode;
                }
                curNode.isEnd = true;
            }

            int acc = 0;
            for (String curWord : words) {
                Node curNode = root.child.get(curWord.charAt(0));
                acc++; // 첫 글자는 무조건 타이핑

                for (int i = 1; i<curWord.length(); i++){

                    if (curNode.child.size() != 1 || curNode.isEnd) acc++; // 갈래가 1개가 아님.

                    curNode = curNode.child.get(curWord.charAt(i));
                }
            }
            System.out.printf("%.2f",(double) acc / words.size());
            System.out.println();

        }

    }

}
