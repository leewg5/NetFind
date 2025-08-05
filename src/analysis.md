# `userPrint` 함수 호출 문제 분석

`UserView.java`의 `main` 메소드 내 "조회 구매 페이지"에서 "판매자 상세 페이지" 옵션(4번)을 선택했을 때, `userPrint()` 함수가 의도대로 동작하지 않는 문제가 있습니다.

## 문제 원인

현재 코드의 실행 흐름은 다음과 같습니다.

1.  사용자가 "조회 구매 페이지" 메뉴에서 **4. 판매자 상세 페이지**를 선택합니다.
2.  `userPrint()` 함수가 즉시 호출됩니다.
3.  `userPrint()` 함수는 사용자에게 "추가할 제품 번호 >"라며 `pno` 입력을 요구합니다.

**핵심 문제:** 사용자는 어떤 제품(`pno`)이 존재하는지 목록을 보지 못한 상태에서 특정 제품 번호를 입력해야 합니다. 이는 올바른 상호작용 흐름이 아닙니다.

### 관련 코드 (`main` 메소드)

```java
// ...
} else if (select == 2) {
    System.out.println("============================ 조회 구매 페이지 ============================");
    // ...
    int selectCart = scan.nextInt();
    if (selectCart == 4) {
        userPrint(); // <- 문제 발생 지점
        // productPrint(); // 전체 제품 조회가 주석 처리되어 있음
    }
// ...
```

### `userPrint()` 함수 코드

```java
public void userPrint(){
    // 1. 입력받기
    System.out.println("============= 제품등록 페이지 ============="); // 페이지 이름도 부적절함
    System.out.println("┌──────────────────┐");
    System.out.println("│  추가할 제품 번호 > "); // <- 사용자는 어떤 번호를 입력해야 할지 알 수 없음
    System.out.println("└──────────────────┘");
    int pno = scan.nextInt();
    // 2. 컨트롤러에 전달 후 리턴값 저장
    ArrayList<UserDto> result = userController.userPrint(pno);
    // ...
}
```

## 해결 방안

사용자가 판매자 정보를 보기 위해서는 다음의 논리적 흐름을 따라야 합니다.

1.  **전체 제품 목록을 먼저 보여줍니다.** (`productPrint()` 호출)
2.  사용자는 목록에서 원하는 제품의 번호(`pno`)를 확인합니다.
3.  프로그램은 사용자에게 조회할 제품의 `pno`를 입력받습니다.
4.  입력받은 `pno`를 `userPrint()` 함수에 인자로 전달하여 해당 제품을 등록한 판매자의 정보를 출력합니다.

### 수정 제안

`userPrint` 함수가 자체적으로 입력을 받지 않고, 받은 `pno` 값을 처리하도록 변경하는 것이 더 효율적입니다.

**1. `main` 메소드 수정**

```java
// ...
} else if (selectCart == 4) {
    // 1. 먼저 전체 제품 목록을 출력
    productPrint(); 
    
    // 2. 사용자에게 조회할 제품 번호를 직접 입력받음
    System.out.println("┌──────────────────────────┐");
    System.out.println("│  상세 정보를 볼 제품 번호 > ");
    System.out.println("└──────────────────────────┘");
    int pno = scan.nextInt();

    // 3. 입력받은 번호를 userPrint 메소드에 전달
    userPrint(pno); 
    
} 
// ...
```

**2. `userPrint` 메소드 수정 (입력받는 로직 제거)**

기존 `userPrint()` 메소드의 이름과 역할을 명확히 하기 위해 `userPrintByPno(int pno)` 와 같이 변경하고, 내부에서 입력을 받는 코드를 제거합니다.

```java
// 1-4) 상세사용자조회 (pno를 받아서 처리)
public void userPrint(int pno){ // 매개변수로 pno를 받음
    // 1. 컨트롤러에 pno 전달 후 리턴값 저장
    ArrayList<UserDto> result = userController.userPrint(pno);
    
    // 2. 화면 구현
    System.out.println("============= 판매자 상세 정보 =============");
    System.out.println("사업자명 | 사업자번호    | 사업장주소지");
    for (UserDto dto : result){
        System.out.println(dto.getUbname() + "|" + dto.getUbnumber() + "|" + dto.getUblocation());
        System.out.println("☎Tel | " + dto.getUphone());
    }
}
```

이렇게 코드를 수정하면 사용자가 제품 목록을 보고 자연스럽게 판매자 정보를 조회하는 흐름이 완성됩니다.