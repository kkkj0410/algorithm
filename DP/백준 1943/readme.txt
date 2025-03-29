## 문제 유형

- DP

## 문제 접근

- 답
    - 주어진 돈으로 반반씩 나눠가질 수 있는 지의 여부
- 주어지는 정보
    - 시행 횟수 3번
    - 동전 종류 100(N)
    - 금액의 총합 최대 100,000
- 정보 정리
    - 완전 탐색 : 불가능
        - 돈의 모든 조합 구하기 = 100!
    - 남는 예상 후보
        - 그리디 : 불가능
            - 가능한 동전의 모든 조합을 만들어야 답을 구할 수 있음
        - DP : 가능
            - 만들 수 있는 조합을 배열에 저장하면 가능성 있을 듯함
- DP
    - 주어진 각 동전을 방문 표시한다
boolean[] visited = new boolean[100001];
for(int i = 0; i<coinCnt; i++)
{
    int coin = list[i].value;
    int cnt = list[i].cnt;
    
    for(int j = 1; j<=cnt; j++)
    {
        int CurCoin = coin * j;
        visited[CurCoin] = true;
    }

    total += coin * cnt;
}


돈의 총합이 홀수이면 반 쪼개기 불가능(돈은 모두 자연수라는 조건이 있음)
if(total % 2 == 1)
{
    System.out.println(0);
    return;
}
자기 자신의 동전을 제외한 모든 돈 조합에 자신 동전의 조합을 더함
int half = total/2;
for(int i = 0; i<coinCnt; i++)
{
    int coin = list[i].value;
    int cnt = list[i].cnt;
    
    boolean[] visited2 = new boolean[coin*cnt+1];
    for(int j = 1; j<=cnt; j++)
    {
        visited2[coin*j] = true;
    } 

    for(int curMoney = half; curMoney>=0; curMoney--)
    {
        if(visited[curMoney] == true)
        {
            if(curMoney <= coin*cnt)
            {
                if(visited2[curMoney] == true)
                {
                    continue;
                }
            }
            for(int j = 1; j<=cnt; j++)
            {
                int cur = curMoney + (coin*j);
                if(cur > half)
                {
                    break;
                }

                visited[cur] = true;
            }
        }
    }
}
