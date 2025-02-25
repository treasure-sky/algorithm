
class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // 1단계
        answer = new_id.toLowerCase();
        
        // 2단계
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < answer.length(); i++){
            char c = answer.charAt(i);
            if (Character.isDigit(c) || (c >= 'a' && c <= 'z') 
                || c == '-' || c == '_' || c == '.'){
                sb.append(c);
            }
        }
        answer = sb.toString();
        
        // 3단계
        sb = new StringBuilder();
        boolean leftDots = false;
        for (int i=0; i < answer.length(); i++){
            char c = answer.charAt(i);
            if (c == '.'){
                if(leftDots){
                    continue;
                }
                leftDots = true;
                sb.append('.');
            } else{
                leftDots = false;
                sb.append(c);
            }
        }
        answer = sb.toString();
        
        // 4단계
        if(answer.length() != 0 && answer.charAt(0) == '.') answer = answer.substring(1);
        if(answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, answer.length() - 1);
        
        // 5단계
        if(answer.length() == 0) answer = "a";
        
        // 6단계
        if(answer.length() >= 16) answer = answer.substring(0, 15);
        if(answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') answer = answer.substring(0, answer.length() - 1);
        
        // 7단계
        if(answer.length() <= 2){
            while(answer.length() != 3){
                answer = answer + answer.charAt(answer.length() - 1);
            }
        }
        
        return answer;
    }
}