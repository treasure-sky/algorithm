class Solution { 
    static int posSec;
    static int opStartSec;
    static int opEndSec;
    static int videoSec;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        posSec = translateToSec(pos);
        opStartSec = translateToSec(op_start);
        opEndSec = translateToSec(op_end);
        videoSec = translateToSec(video_len);
        
        openingCheck();
        
        for(String s : commands){
            if(s.equals("next")){
                addTenSeconds();
            }else if(s.equals("prev")){
                minusTenSeconds();
            }
        }
        
        
        
        int min = posSec / 60;
        int sec = posSec % 60;
        answer += String.format("%02d", min);
        answer += ":";
        answer += String.format("%02d", sec);
        
        return answer;
    }
    
    public int translateToSec(String time){
        int min = Integer.parseInt(time.substring(0,2));
        int sec = Integer.parseInt(time.substring(3,5));
        return min*60 + sec;
    }
    
    public void addTenSeconds(){
        posSec += 10;
        boundaryCheck();
        openingCheck();
    }
    
    public void minusTenSeconds(){
        posSec -= 10;
        boundaryCheck();
        openingCheck();
    }
    
    public void boundaryCheck(){
        if(posSec < 0){
            posSec = 0;
        }else if(posSec > videoSec){
            posSec = videoSec;
        }
    }
    
    
    static public void openingCheck(){
        if(posSec >= opStartSec && posSec <= opEndSec){
            posSec = opEndSec;
        }
    }
}