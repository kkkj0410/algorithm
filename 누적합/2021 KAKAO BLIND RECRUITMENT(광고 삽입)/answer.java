class Solution {
    int MAX = 0;
    int[] logsTime;
    int[] timeUnit = {60*60, 60, 1};
    
    public int getTimeLine(String adv_time){
        long answer = 0;
        int timeLine = 0;
        
        
        int advLength = 0;
        String[] temp = adv_time.split(":");
        for(int i = 0; i<3; i++){
            advLength += Integer.parseInt(temp[i]) * timeUnit[i];
        }
        
        long sum = 0;
        for(int i = 0; i<advLength; i++){
            sum += logsTime[i];
        }
        
        answer = sum;        
        for(int i = 1; i < MAX - advLength; i++){
            sum += logsTime[i + advLength - 1] - logsTime[i-1];
            
            if(answer < sum){
                answer = sum;
                timeLine = i;
            }
        }
        
        return timeLine;

    }
    
    public void setLogsTime(String[] logs){
        logsTime = new int[MAX];
        int[] temp = new int[MAX];
        
        for(int i = 0; i<logs.length; i++){
            logs[i] = logs[i].replace('-', ':');
            String[] log = logs[i].split(":");
            
            int start = 0;
            for(int j = 0; j<3; j++){
                start += Integer.parseInt(log[j]) * timeUnit[j];
            }
            
            int end = 0;
            for(int j = 3; j<6; j++){
                end += Integer.parseInt(log[j]) * timeUnit[j-3];
            }
            
            temp[start]++;
            temp[end]--;
        }
        
        int sum = 0;
        for(int i = 0; i<MAX; i++){
            sum += temp[i];
            logsTime[i] = sum;
        }
    }
    
    public String setResult(int timeLine){
        int hour = timeLine / timeUnit[0];
        timeLine -= hour * timeUnit[0];
        int minute = timeLine / timeUnit[1];
        timeLine -= minute * timeUnit[1];
        int second = timeLine;
        
        String time = "";
        if(hour < 10){
            time += ("0" + hour);
        }
        else{
            time += "" + hour;
        }
        time += ":";
        
        
        if(minute < 10){
            time += ("0" + minute);
        }
        else{
            time += "" + minute;
        }
        time += ":";
        
        if(second < 10){
            time += ("0" + second);
        }
        else{
            time += "" + second;
        }
        
        
        return time;
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        String[] play_times = play_time.split(":");
        for(int i = 0; i<3; i++){
            MAX += Integer.parseInt(play_times[i]) * timeUnit[i];
        }
        MAX += 2;
 
        setLogsTime(logs);
        int timeLine = getTimeLine(adv_time);
        answer = setResult(timeLine);
        
        
        
        return answer;
    }
}
