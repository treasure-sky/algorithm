import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class region {
        public region(int row, int col, int level) {
            this.row = row;
            this.col = col;
            this.level = level;
        }

        int row;
        int col;
        int level;
    }

    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int curColor = 1;
    static ArrayList<Queue<region>> queues = new ArrayList<>();

    public static void main(String[] args) {
        // 입력
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];
        queues.add(new LinkedList<>());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        sc.close();

        // 섬 구분해서 숫자 할당

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    queues.add(new LinkedList<>());
                    dfsColor(i, j, curColor);
                    curColor++;
                }
            }
        }

        // 섬에서 bfs 하며 최소 다리 길이 구하기
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < curColor - 1; i++) { // 섬 개수만큼 (-1해도 되나??)
            Queue<region> queue = queues.get(i);
            visited = new boolean[N][N];
            while (queue.peek() != null && queue.peek().level < min) {
                region temp = queue.poll();
                int row = temp.row;
                int col = temp.col;
                int level = temp.level;
                if (row > 0 && !visited[row - 1][col]) { // 위에 지역에 방문하지 않았으면
                    if (map[row - 1][col] == 0) {
                        queue.add(new region(row - 1, col, level + 1));
                        visited[row - 1][col] = true;
                    } else if (map[row - 1][col] != i) {
                        if (min > level) {
                            min = level;
                            break;
                        }
                    }
                }
                if (col > 0 && !visited[row][col - 1]) {
                    if (map[row][col - 1] == 0) {
                        queue.add(new region(row, col - 1, level + 1));
                        visited[row][col - 1] = true;
                    } else if (map[row][col - 1] != i) {
                        if (min > level) {
                            min = level;
                            break;
                        }
                    }
                }
                if (row < N - 1 && !visited[row + 1][col]) {
                    if (map[row + 1][col] == 0) {
                        queue.add(new region(row + 1, col, level + 1));
                        visited[row + 1][col] = true;
                    } else if (map[row + 1][col] != i) {
                        if (min > level) {
                            min = level;
                            break;
                        }
                    }
                }
                if (col < N - 1 && !visited[row][col + 1]) {
                    if (map[row][col + 1] == 0) {
                        queue.add(new region(row, col + 1, level + 1));
                        visited[row][col + 1] = true;
                    } else if (map[row][col + 1] != i) {
                        if (min > level) {
                            min = level;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }

    static void dfsColor(int row, int col, int color) {
        map[row][col] = curColor;
        visited[row][col] = true;

        if (row > 0 && !visited[row - 1][col]) { // 위에 지역있고 방문 안했을 때
            if (map[row - 1][col] == 0) { // 근데 그게 바다일 때
                queues.get(color).add(new region(row, col, 0)); // 큐에 넣기
                visited[row - 1][col] = true;
            } else { // 이어진 땅일 때
                map[row - 1][col] = color;
                dfsColor(row - 1, col, color);
            }
        }

        if (col > 0 && !visited[row][col - 1]) {
            if (map[row][col - 1] == 0) {
                queues.get(color).add(new region(row, col, 0));
                visited[row][col - 1] = true;
            } else {
                map[row][col - 1] = color;
                dfsColor(row, col - 1, color);
            }
        }

        if (row < N - 1 && !visited[row + 1][col]) {
            if (map[row + 1][col] == 0) {
                queues.get(color).add(new region(row, col, 0));
                visited[row + 1][col] = true;
            } else {
                map[row + 1][col] = color;
                dfsColor(row + 1, col, color);
            }
        }

        if (col < N - 1 && !visited[row][col + 1]) {
            if (map[row][col + 1] == 0) {
                queues.get(color).add(new region(row, col, 0));
                visited[row][col + 1] = true;
            } else {
                map[row][col + 1] = color;
                dfsColor(row, col + 1, color);
            }
        }

    }

}
