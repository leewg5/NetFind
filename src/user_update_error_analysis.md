# 사용자 정보 수정 기능 오류 분석 (`SQLSyntaxErrorException`)

`userUpdate` 메소드 실행 시 `java.sql.SQLSyntaxErrorException`이 발생하는 원인과 해결 방안을 분석합니다.

## 1. 오류의 근본 원인

오류의 직접적인 원인은 **`UserDao.java`의 `checkPwd(String upwd)` 메소드 내부에 있는 SQL 쿼리문의 문법 오류**입니다.

`userUpdate` 기능은 가장 먼저 현재 비밀번호를 확인하기 위해 `checkPwd`를 호출하는데, 이 첫 단계에서 SQL 문법 오류로 인해 예외가 발생하고 전체 기능이 중단됩니다.

### 문제의 SQL 코드 (`UserDao.java`)

```java
public boolean checkPwd(String upwd){
    try {
        // 'where uno = ?' 와 'upwd = ?' 사이에 논리 연산자 'AND'가 누락됨
        String sql = "select * from user where uno = ? upwd = ?"; 
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, UserController.loginUno);
        ps.setString(2 , upwd);
        ResultSet rs = ps.executeQuery(); // 이 줄에서 예외 발생
        if (rs.next()){
            return true;
        }
    } catch (Exception e){
        System.out.println(e); // 오류 로그 출력
    } 
    return false;
} 
```

`WHERE` 절에서 `uno` 조건과 `upwd` 조건을 동시에 만족하는 레코드를 찾으려면 두 조건 사이에 `AND` 키워드가 반드시 있어야 합니다.

## 2. 해결 방안

`checkPwd` 메소드의 SQL 쿼리문에 `AND`를 추가하여 문법을 수정해야 합니다.

### 수정된 코드 (`UserDao.java`)

```java
public boolean checkPwd(String upwd){
    try {
        // 'AND' 키워드를 추가하여 올바른 SQL 문법으로 수정
        String sql = "select * from user where uno = ? AND upwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, UserController.loginUno);
        ps.setString(2, upwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return true;
        }
    } catch (Exception e){
        System.out.println(e);
    } 
    return false;
}
```

## 3. 추가 확인 사항

`checkPwd` 문제가 해결되면 `userUpdate` 기능은 정상적으로 동작할 것으로 보입니다. 이전에 분석해 드린 `user_function_analysis.md`의 내용처럼 `UserDao.userUpdate` 메소드에 `WHERE uno = ?` 조건절이 올바르게 추가되어 있어, 모든 사용자의 정보가 한 번에 바뀌는 위험한 상황은 발생하지 않을 것입니다.

**요약:**
- **오류 원인:** `UserDao.checkPwd`의 SQL 문법 오류 (AND 누락)
- **해결:** 해당 SQL에 `AND` 키워드 추가
