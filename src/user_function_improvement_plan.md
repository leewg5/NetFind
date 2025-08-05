# 사용자 정보 수정 및 삭제 기능 개선 방안

`UserView.java`에서 `userUpdate()` (사용자 정보 수정)와 `userDelete()` (사용자 정보 삭제) 메소드를 실행할 때 발생하는 문제점을 분석하고, 이에 대한 해결책을 제시합니다.

## 1. 문제 현황

현재 사용자 정보 수정 및 삭제 기능은 정상적으로 동작하지 않습니다.
- **정보 수정:** 비밀번호를 올바르게 입력해도 항상 "비밀번호를 확인해주세요"라는 메시지가 출력되며 더 이상 진행되지 않습니다.
- **정보 삭제:** 정보 수정과 동일한 문제가 발생하며, 탈퇴 확인 절차의 로직도 불안정합니다.

## 2. 근본 원인: `UserView.java`의 로직 오류

문제의 핵심 원인은 `UserView.java` 내에서 컨트롤러로부터 받은 **비밀번호 확인 결과(boolean 타입)를 사용자가 입력한 비밀번호(String 타입)와 잘못된 방식으로 비교**하고 있기 때문입니다.

### 2-1. `userUpdate()` 메소드의 오류

- **문제 코드:**
  ```java
  // in UserView.java
  public void userUpdate(){
      System.out.print("비밀번호 확인 : ");
      String upwd = scan.next();
      boolean check = userController.checkPwd(upwd);
      
      // ▼▼▼ 문제 지점 ▼▼▼
      // String 타입인 upwd와 boolean 타입인 check를 비교하고 있어 항상 false가 됨
      if (upwd.equals(check)){ 
          System.out.println("[경고] 비밀번호를 확인해주세요.");
          return;
      }
      // ... (이하 로직 실행 불가)
  }
  ```
- **분석:** `userController.checkPwd(upwd)`는 비밀번호가 일치하면 `true`, 아니면 `false`를 반환합니다. 하지만 코드에서는 사용자가 입력한 `upwd` 문자열과 `check` 불리언 값을 `.equals()`로 비교하고 있습니다. 이 비교는 항상 `false`를 반환하므로, `if`문의 다음 로직은 절대 실행될 수 없습니다. **실제로는 비밀번호가 틀렸을 때(`check`가 `false`일 때) 경고를 띄워야 합니다.**

### 2-2. `userDelete()` 메소드의 오류

`userDelete()` 메소드는 두 가지 오류를 포함하고 있습니다.

1.  **잘못된 비밀번호 확인:** `userUpdate()`와 동일한 문제입니다.
2.  **잘못된 탈퇴 동의 확인:** 문자열 비교 시 `!=` 연산자를 사용하여 참조 주소를 비교하고 있습니다. Java에서 문자열의 내용을 비교하려면 `.equals()` 또는 `.equalsIgnoreCase()`를 사용해야 합니다.

- **문제 코드:**
  ```java
  // in UserView.java
  public void userDelete(){
      System.out.print("비밀번호 확인 : ");
      String upwd = scan.next();
      boolean check = userController.checkPwd(upwd);

      // ▼▼▼ 문제 지점 1 ▼▼▼
      if (!upwd.equals(check)){ // upwd(String)와 check(boolean)를 비교
          System.out.println("[경고] 비밀번호를 확인해주세요.");
          return;
      }

      System.out.println("정말 삭제하시겠습니까? [Y/N]");
      String deleteCheck = scan.next();

      // ▼▼▼ 문제 지점 2 ▼▼▼
      if (deleteCheck != "Y"){ // 문자열 비교는 '!=' 연산자를 사용하면 안 됨
          System.out.println("[안내] 돌아와주셔서 감사합니다.");
          mypageIndex();
          return;
      }
      // ...
  }
  ```

## 3. 해결 방안

`UserView.java`의 `userUpdate()`와 `userDelete()` 메소드 내 로직을 다음과 같이 수정해야 합니다.

### 3-1. `userUpdate()` 수정 제안

비밀번호 확인 결과가 `false`일 경우, 즉 비밀번호가 틀렸을 경우에만 경고 메시지를 출력하고 함수를 종료하도록 변경합니다.

- **수정 코드:**
  ```java
  // in UserView.java
  public void userUpdate(){
      // 1. 입력받기
      System.out.println("수정할 정보를 입력해주세요.");
      System.out.print("비밀번호 확인 : ");
      String upwd = scan.next();
      
      // * 유효성 검사
      boolean check = userController.checkPwd(upwd);
      if (!check){ // check가 false이면 (비밀번호가 틀리면)
          System.out.println("[경고] 비밀번호가 일치하지 않습니다.");
          return; // 메소드 종료
      }
      
      // 검사 통과 후
      System.out.print("새 비밀번호 : ");
      String upwdNew = scan.next();
      System.out.print("연락처 : ");
      String uphone = scan.next();
      System.out.print("사용자명 : ");
      String uname = scan.next();
      
      // 2. 컨트롤러에 전달 후 리턴값 저장
      boolean result = userController.userUpdate(upwdNew , uphone , uname);
      
      // 3. 리턴값 출력
      if (result){
          System.out.println("[안내] 정보 수정 완료");
      } else {
          System.out.println("[경고] 정보 수정 실패 (DB 오류)");
      }
  }
  ```

### 3-2. `userDelete()` 수정 제안

비밀번호 확인 로직을 수정하고, 탈퇴 동의 여부를 확인할 때 대소문자를 구분하지 않는 `.equalsIgnoreCase()` 메소드를 사용하도록 변경합니다.

- **수정 코드:**
  ```java
  // in UserView.java
  public void userDelete(){
      // 1. 입력받기
      System.out.print("비밀번호 확인 : ");
      String upwd = scan.next();
      
      // * 유효성 검사
      boolean check = userController.checkPwd(upwd);
      if (!check){ // check가 false이면 (비밀번호가 틀리면)
          System.out.println("[경고] 비밀번호가 일치하지 않습니다.");
          return; // 메소드 종료
      }
      
      System.out.println("정말 삭제하시겠습니까? [Y/N]");
      String deleteCheck = scan.next();
      
      // * 유효성 검사 (대소문자 구분 없이 "Y"가 아니면)
      if (!deleteCheck.equalsIgnoreCase("Y")){
          System.out.println("[안내] 탈퇴가 취소되었습니다.");
          return; // mypageIndex() 호출 대신 현재 메소드 종료
      }
      
      // 2. 컨트롤러에 전달 후 리턴값 저장
      boolean result = userController.userDelete(loginUno);
      
      // 3. 리턴값 출력
      if (result){
          System.out.println("[안내] 회원 탈퇴가 완료되었습니다. 안녕히 가십시오.");
          logout(); // 로그아웃 처리 후 초기화면으로 이동
      } else {
          System.out.println("[경고] 회원 탈퇴 실패 (DB 오류)");
      }
  }
  ```

## 4. 결론

`UserController`와 `UserDao`의 핵심 로직(DB 업데이트 및 삭제)은 `loginUno`를 사용하여 특정 사용자를 대상으로 하므로 안전하게 구현되어 있습니다. 따라서 위와 같이 `UserView.java`의 사용자 입력 처리 및 유효성 검사 로직만 수정하면 사용자 정보 수정 및 삭제 기능이 의도대로 정상 동작할 것입니다.
