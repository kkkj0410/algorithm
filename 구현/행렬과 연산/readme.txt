https://school.programmers.co.kr/learn/courses/30/lessons/118670

1.실패한 방법(fail.java)

시간 복잡도 : O(N)~O(N*4)
N = 행 or 열
ShiftRow : 각 행의 순서를 하나하나 바꿨음 O(N)
Rotate : 테두리의 순서를 하나하나 바꿨음 O(N*4)

2. 성공한 방법(answer.java)

⇒deque에 행렬을 저장하고, 값을 순회 시킴
시간 복잡도 : O(N)
(deque에 값 저장하는 것은 O(N), deque로 알고리즘 실행하는 데는 O(1))
