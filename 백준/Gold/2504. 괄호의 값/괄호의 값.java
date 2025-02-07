import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int inputLength = input.length();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < inputLength; i++) {
            // 다음 문자가 여는 괄호일 때
            if (input.charAt(i) == '(' || input.charAt(i) == '[') {
                stack.push(Character.toString(input.charAt(i)));
                continue;
            }

            // 다음 문자가 닫는 괄호일 때
            int sum = 0;
            try {
                if (input.charAt(i) == ')'){
                    while(!stack.peek().equals("(")){
                        sum += Integer.parseInt(stack.pop());
                    }
                    if(sum == 0) sum = 1;
                    sum *= 2;
                } else if (input.charAt(i) == ']') {
                    while(!stack.peek().equals("[")){
                        sum += Integer.parseInt(stack.pop());
                    }
                    if(sum == 0) sum = 1;
                    sum *= 3;
                }
                stack.pop(); // 여는 괄호 삭제
                stack.push(Integer.toString(sum));
            } catch (Exception e) {
                System.out.println("0");
                System.exit(0);
            }
        }
        int res = 0;
        while (!stack.isEmpty()){
            try{
                res += Integer.parseInt(stack.pop());
            } catch (Exception e) {
                System.out.println("0");
                System.exit(0);
            }
        }
        System.out.println(res);
    }
    public static boolean isNumber (String s){

        if (s.equals("(") || s.equals(")") || s.equals("[") || s.equals("]")){
            return false;
        }else{
            return true;
        }
    }
}
