## RequestParamStudy
사용자가 입력 폼(entry_form.html)에서 데이터를 입력한 뒤, 컨트롤러를 거쳐 확인 화면(confirm_form.html)으로 파라미터를 전달하는 과정을 공부

### 핵심
1. **RequestParam 바인딩**:  
   초기에 `@RequestParam`과 개별 필드를 이용한 파라미터 바인딩을 사용
2. **Form 객체 활용**:  
   1번 이후로 `Form` 클래스를 활용해 폼 입력값을 한 번에 객체 형태로 받고, 해당 객체를 이용해 뷰로 전달해보기
3. **Thymeleaf와 바인딩**:  
   Thymeleaf의 `th:object`, `th:field` 등을 이용하여 폼과 객체를 양방향으로 바인딩하고, 이를 통해 RequestParam 처리
4. **데이터 변환(DateTimeFormat)**:  
   날짜 타입을 파라미터로 받을 때 `@DateTimeFormat`를 사용하여 적절히 변환
   
### 결론
 RequestParam과 관련된 다양한 방법(개별 파라미터 바인딩, 객체 바인딩, 날짜 타입 처리 등)을 시도해 볼 수 있음
