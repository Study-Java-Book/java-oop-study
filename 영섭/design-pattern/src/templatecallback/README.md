# 템플릿 콜백 패턴

전략 패턴의 변형으로, 고정된 작업 흐름을 가진 코드에서 일부분만 자주 바뀌는 경우, 바뀌는 부분을 콜백으로 분리하여 재사용성을 높이는 방식

## 핵심 구성요소

- **템플릿:** 공통 로직을 담당하며, 콜백을 호출하는 역할
- **콜백:** 템플릿 안에서 실행될 목적으로 전달되는 오브젝트. 변하는 로직을 담고 있으며, 보통 익명 클래스나 람다식으로 구현

## 활용 예시 (JdbcTemplate)

```java
public <T> T execute(StatementCallback<T> action) {
    Connection con = null;
    Statement stmt = null;
    try {
        // 1. 커넥션 획득 (공통 로직)
        con = DataSourceUtils.getConnection(dataSource);
        
        // 2. Statement 생성 (공통 로직)
        stmt = con.createStatement();
        
        // 3. 콜백 실행 (변하는 로직)
        T result = action.doInStatement(stmt);
        
        return result;
    } catch (SQLException ex) {
        // 4. 예외 변환 (공통 로직)
        throw translateException("StatementCallback", sql, ex);
    } finally {
        // 5. 리소스 정리 (공통 로직)
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }
}
```

