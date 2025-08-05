# AppStart 실행 오류: 데이터베이스 연결 실패로 인한 RuntimeException

## 문제 현상

`AppStart.main()`을 실행해도 애플리케이션이 즉시 종료되며, 특별한 오류 메시지 없이 아무런 반응이 없는 것처럼 보입니다.

## 근본 원인

애플리케이션 시작 과정에서 각 DAO 객체들이 싱글톤 패턴으로 생성될 때, 생성자에서 `connect()` 메소드를 호출하여 데이터베이스 연결을 시도합니다.

이때 **`ProductDao.java`**의 `connect()` 메소드는 데이터베이스 연결에 실패할 경우 `RuntimeException`을 발생시키도록 구현되어 있습니다. 이 예외는 프로그램의 어느 곳에서도 처리되지 않기 때문에(uncaught exception), JVM은 즉시 실행을 중단합니다. 이로 인해 `AppStart`의 `main` 메소드에 있는 `UserView.getInstance().index()` 코드가 실행될 기회조차 얻지 못하고 프로그램이 종료되는 것입니다.

### 문제의 코드 (`ProductDao.java`)

```java
// (*) 데이터베이스 연동
public void connect() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(db_url, db_user, db_password);
    } catch (Exception e) {
        // DB 연결 실패 시 RuntimeException을 발생시켜 프로그램을 강제 종료시킴
        throw new RuntimeException(e); 
    }
}
```

반면, `UserDao`, `NoteDao`, `SampleDao` 등 다른 DAO 클래스들은 연결 실패 시 예외를 콘솔에 출력만 하고 프로그램을 중단시키지는 않습니다. 이 방식이 더 안정적입니다.

## 해결 방안

`ProductDao.java`의 `connect()` 메소드에서 `RuntimeException`을 발생시키는 대신, 다른 DAO들처럼 예외 메시지를 콘솔에 출력하도록 수정해야 합니다. 이렇게 하면 데이터베이스 연결에 실패하더라도 프로그램이 비정상적으로 종료되는 것을 막을 수 있습니다.

### 수정 제안 코드 (`ProductDao.java`)

```java
// (*) 데이터베이스 연동
public void connect() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(db_url, db_user, db_password);
    } catch (Exception e) {
        // RuntimeException 대신, 오류 메시지를 출력하여 프로그램 강제 종료를 방지
        System.out.println("[경고] ProductDao 데이터베이스 연결 실패: " + e.getMessage());
    }
}
```

## 기대 효과

이 수정이 적용되면, 데이터베이스 서버가 꺼져 있거나 연결 정보가 잘못되었더라도 `AppStart`는 정상적으로 실행됩니다. 프로그램이 시작된 후 데이터베이스 관련 기능을 사용할 때 SQL 관련 예외가 발생하겠지만, 적어도 애플리케이션의 초기 실행 및 UI 확인은 가능해져 다른 부분의 개발 및 디버깅이 용이해집니다.
