import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0, 0};
        
        int wordCnt = words.length;
        int currentPerson = 2;
        int round = 1;
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for (int i = 1; i < wordCnt; i++){
            if(set.contains(words[i])){
                answer = new int[]{currentPerson, round};
                return answer;
            }
            
            if(words[i].charAt(0) != words[i-1].charAt(words[i-1].length() - 1)){
                answer = new int[]{currentPerson, round};
                return answer;
            }
            
            set.add(words[i]);
            
            if(currentPerson == n){
                currentPerson = 1;
                round++;
            }else{
                currentPerson++;
            }
        }
        
        
        System.out.println("Hello Java");

        return answer;
    }
}