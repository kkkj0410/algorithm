KnapSack 알고리즘

⇒ 물건을 중복으로 가져갈 수 없고, 한정된 무게만 가져갈 수 있을때,

최대의 값어치를 구하라.

ex) 1(4,3)

- 선택지 : 1번
- 무게 : 4
- 값어치 : 3

⇒ 초기값 설정 : DP[1][j]

- j ≥ 4이면, DP[1][j] = 3 (물건 개수는 중복으로 가져갈 수 없음)

DP[N][weight]

- N=주어진  선택지 개수, weight = 현재 가능 무게

DP[i][j] = MAX ( DP[i-1][j], DP[i-1][j-A]

- A = 현재 주어진 선택지 거리

⇒ DP의 값은 

- DP[i-1][j] : 마주한 현재 선택지를 제외한 최대 값어치
- DP[i-1][j-A] : 현재 선택지를 포함한 최대 값어치

둘 중 하나
