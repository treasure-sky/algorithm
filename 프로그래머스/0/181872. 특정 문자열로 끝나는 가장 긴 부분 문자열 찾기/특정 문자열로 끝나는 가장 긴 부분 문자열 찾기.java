class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        for(int i=0; i<myString.length();i++){
            if(myString.substring(i,myString.length()).contains(pat)){
                answer = myString.substring(0,i+pat.length());
            }
        }
        return answer;
    }
}