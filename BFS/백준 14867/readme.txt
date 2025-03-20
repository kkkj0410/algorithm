1. BFS로 모든 경우의 수 탐색
2. 이미 탐색한 경우의 수는 Map에 등록
    (배열은 공간 복잡도 초과)

※Map은 따로 오버라이딩 필요
@Override //map.get(), map.put()(원소 출력/삽입)함수 사용 시, equlas함수가 사용됨
public boolean equals(Object obj) { 
    if (obj == null) return false; //map.get()값이 없으면 NULL임. 그리고 그 값은 false
    Solution solution = (Solution) obj;
    return cur_a == solution.cur_a && cur_b == solution.cur_b; //map의 key값을 비교
}

@Override //map에서는 값이 Hash로 기록되는 듯함. 따라서, 객체를 map에 저장 시, hash로 변환
public int hashCode() {
    return Objects.hash(cur_a, cur_b);
}
