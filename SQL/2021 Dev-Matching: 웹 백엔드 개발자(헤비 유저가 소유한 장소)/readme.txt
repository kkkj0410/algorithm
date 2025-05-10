## 문법

- SELECT
    - 출력할 열 선택
- FROM
    - 테이블 선택
- GROUP BY
    - 같은 값의 행끼리 그룹화
    - 그룹화된 행 중에서 출력되는 값은 순서상 제일 먼저 위치해있는 행 값임
    
    ```sql
    SELECT *
    FROM PLACES
    GROUP BY HOST_ID
    
    <Before>
    HOST_ID
    100
    200
    100
    -------
    <After>
    HOST_ID
    100
    200
    ```
    
- WHERE, HAVING
    - 조건문
    - WHERE
        - GROUP BY 이전 테이블에 대한 조건문 지정
        
        ```sql
        # HOST_ID = 5507453인 데이터 개수 출력
        
        SELECT count(*)
        FROM PLACES
        WHERE HOST_ID = 5507453
        ```
        
    - HAVING
        - GROUP BY 이후 테이블에 대한 조건문 지정
        
        ```sql
        # HOST_ID = 760849인 데이터 개수 출력
        
        SELECT count(*)
        FROM PLACES
        GROUP BY HOST_ID
        HAVING HOST_ID = 760849
        ```
        

- count(*),  count(열 이름)
    - 각 그룹의 행 개수 세기 + NULL이 아닌 행 개수 세기
    - count(*)
        - count 대상을 전체 행으로 지정
    - count(열 이름)
        - count 대상을 특정 열로 지정
