## 문제 유형

- 완전탐색

## 문제 접근

- 답
    - 식이 주어졌을 때, 괄호 조합으로 만들 수 있는 최대값
- 주어지는 정보
    - 수식의 길이 최대 19 (N)
    - 식에서 쓰이는 숫자의 길이는 1자리
- 후보
    - 완전 탐색
        - 가능
        - 수식의 길이 N은 충분히 작기 때문에 완전 탐색 가능
- 풀이
    - 목적
        - 만들 수 있는 괄호 조합을 모두 구하여, 최대값을 선정한다.
    - 단계
        - 식은 모두 char 배열에 담는다
            - 식에서 주어지는 숫자의 길이는 1자리 수 이기 때문에 char 배열은 적합하다
        - DFS 탐색으로 모든 경우의 수를 탐색한다
            - 선택지
                - 해당 자리의 수를 total 값에 포함한다.
                - 해당 자리의 수와 그 다음 자리의 수를 괄호로 묶어서 total 값에 포함한다
        - 최대값을 선정한다.
