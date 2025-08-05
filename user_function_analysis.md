# 로그인 및 사용자 정보 수정 기능 분석

`UserController`의 로그인 및 사용자 정보 수정 기능에 대한 개선 방안과 현재 코드의 문제점을 분석합니다.

## 1. 로그인 및 로그아웃 (`loginUno` 개선)

현재 로그인 로직은 실제 데이터베이스 정보와 연동되지 않고, View에서 임의의 값(`1` 또는 `2`)을 `loginUno`에 할당하고 있습니다. 이로 인해 어떤 사용자가 로그인했는지 식별할 수 없습니다.

### 문제점
- `UserController.login()`과 `UserDao.login()` 메소드가 비어있어 실제 인증을 수행하지 않습니다.
- `UserView.login()`에서 `loginUno`에 실제 `uno`가 아닌 하드코딩된 값을 할당합니다.

### 개선 방안

**1. `UserDao.login(String uid, String upwd)` 수정**

아이디와 비밀번호를 받아 데이터베이스와 대조하고, 일치하는 사용자의 `uno`를 반환하도록 수정해야 합니다. 로그인 실패 시 `0`을 반환합니다.

```java
// UserDao.java
public int login(String uid, String upwd) {
    try {
        String sql = "select uno from user where uid = ? and upwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, uid);
        ps.setString(2, upwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("uno"); // 로그인 성공 시 uno 반환
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return 0; // 로그인 실패 시 0 반환
}
```

**2. `UserController.login(String uid, String upwd)` 수정**

DAO의 `login` 결과를 `loginUno`에 저장하고, 성공 여부를 View에 알려주도록 수정합니다.

```java
// UserController.java
public boolean login(String uid, String upwd) {
    if (uid == null || upwd == null) return false;

    int resultUno = userDao.login(uid, upwd);
    if (resultUno > 0) {
        loginUno = resultUno; // static 변수에 로그인 성공한 uno 저장
        return true;
    }
    return false;
}
```

**3. `UserView.login()` 및 `logout()` 수정**

Controller의 `login` 결과를 바탕으로 화면을 전환하고, `logout` 시 `loginUno`를 초기화합니다.

```java
// UserView.java
public void login(){
    System.out.print("아이디 : ");
    String uid = scan.next();
    System.out.print("비밀번호 : ");
    String upwd = scan.next();

    boolean result = userController.login(uid, upwd);

    if(result){
        System.out.println("[안내] 로그인 성공");
        if(uid.equals("admin")){
            adminMain();
        } else {
            main();
        }
    } else {
        System.out.println("[경고] 로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}

public void logout(){
    userController.logout(); // Controller를 통해 로그아웃 처리
    System.out.println("[안내] 로그아웃 되었습니다.");
}

// UserController.java
public void logout() {
    loginUno = 0; // 로그아웃 시 0으로 초기화
}
```

---

## 2. 사용자 정보 수정 (`userUpdate` 및 `checkPwd`)

`userUpdate` 관련 기능은 현재 **매우 위험한 상태**이며, 로직이 잘못 구현되어 의도대로 동작하지 않습니다.

### 문제점 1: `checkPwd`의 잘못된 로직

`UserDao.checkPwd`는 현재 로그인된 사용자의 비밀번호를 확인하는 것이 아니라, **입력된 비밀번호를 가진 사용자가 DB에 있는지** 확인합니다. 이는 보안상 매우 취약합니다.

**잘못된 코드 (`UserDao.java`):**
```java
public boolean checkPwd(String upwd){
    // 이 쿼리는 단순히 upwd를 가진 사용자가 있는지 여부만 확인한다.
    String sql = "select * from user where upwd = ?"; 
    // ...
}
```

### 문제점 2: `userUpdate`의 치명적 오류 (WHERE 절 부재)

`UserDao.userUpdate`의 `UPDATE` 쿼리에는 `WHERE` 절이 없습니다. 이 코드가 실행되면 **모든 사용자의 정보가 한 번에 변경**됩니다.

**매우 위험한 코드 (`UserDao.java`):**
```java
public boolean userUpdate(UserDto userDto){
    // WHERE 절이 없어서 모든 레코드를 업데이트하는 심각한 오류 발생
    String sql = "update user set upwd = ? , uphone = ? , uname = ?;";
    // ...
}
```

### 문제점 3: Controller와 View의 로직 오류

- `UserView`는 `userController.userUpdate`를 호출할 때 **새 비밀번호**를 인자로 넘깁니다.
- `UserController`는 이 **새 비밀번호**를 `checkPwd`로 확인한 뒤, `userUpdate`를 호출합니다. 
- 즉, "현재 비밀번호"를 확인하는 로직이 전혀 없고, "새 비밀번호"가 DB에 이미 존재하는지 확인하는 이상한 로직으로 동작하고 있습니다.

### 개선 방안

**1. `UserDao.checkPwd` 수정**

반드시 현재 로그인한 사용자(`loginUno`)의 비밀번호가 맞는지 확인해야 합니다.

```java
// UserDao.java
public boolean checkPwd(String upwd) { // Controller에서 loginUno를 같이 넘겨줘도 좋음
    try {
        String sql = "select uno from user where uno = ? and upwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, controller.UserController.loginUno); // 현재 로그인된 사용자 번호
        ps.setString(2, upwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true; // 현재 로그인된 사용자의 비밀번호가 맞음
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return false;
}
```

**2. `UserDao.userUpdate` 수정**

`WHERE` 절을 추가하여 **반드시 현재 로그인된 사용자만** 수정하도록 변경해야 합니다.

```java
// UserDao.java
public boolean userUpdate(String newUpwd, String newUphone, String newUname) { // DTO 대신 직접 받거나, DTO 필드를 명확히 사용
    try {
        String sql = "update user set upwd = ?, uphone = ?, uname = ? where uno = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newUpwd);
        ps.setString(2, newUphone);
        ps.setString(3, newUname);
        ps.setInt(4, controller.UserController.loginUno); // WHERE 절에 현재 사용자 uno 추가

        int count = ps.executeUpdate();
        if (count == 1) {
            return true;
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return false;
}
```

**3. `UserController` 및 `UserView` 로직 수정**

View에서 "현재 비밀번호"와 "새 정보"를 모두 입력받아 Controller로 전달하고, Controller는 이를 분리하여 DAO 메소드를 순서대로 호출해야 합니다.

```java
// UserView.java
public void userUpdate(){
    System.out.print("현재 비밀번호 확인: ");
    String currentPwd = scan.next();
    
    System.out.print("새 비밀번호: ");
    String newPwd = scan.next();
    System.out.print("새 연락처: ");
    String newPhone = scan.next();
    System.out.print("새 사용자명: ");
    String newName = scan.next();

    boolean result = userController.userUpdate(currentPwd, newPwd, newPhone, newName);
    if (result) {
        System.out.println("[안내] 정보 수정 완료");
    } else {
        System.out.println("[경고] 정보 수정 실패. 현재 비밀번호를 확인해주세요.");
    }
}

// UserController.java
public boolean userUpdate(String currentUpwd, String newUpwd, String newUphone, String newUname) {
    // 1. 현재 비밀번호가 맞는지 먼저 확인
    if (!userDao.checkPwd(currentUpwd)) {
        return false; // 비밀번호 불일치
    }

    // 2. 비밀번호가 맞으면 정보 업데이트 요청
    return userDao.userUpdate(newUpwd, newUphone, newUname);
}
```
