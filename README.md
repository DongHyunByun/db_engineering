# db_engineering

1. **기간 : 2023.06 ~ 2023.06** 

3. **개요**
    1. 로컬에서 SQL연결하여 실습진행
  
2. **기술스택**
    1. 개발언어 : JAVA
    2. IDE : Intellij

4. **파일설명(src/main/java/org/de/jdbc)**
    1. /callablestatement : procedure과 function의 활용. 자주 사용해야 하는 여러 동작의 세트
        1. /function : SQL함수 활용 실습 (function : 그 자체로 호출 가능, 복잡한 수학연산 자동화, 문자열 변환 등에 주로 사용)
        2. /procedurecall : procedure 실습 (procedure : 여러 SQL문으로 수행되어야 하는 데이터 집계.
    2. /crudexample : 연결 및 select 실습
    3. /mapper
        1. Product : product의 컬럼값들을 JAVA 객체로 받아 맴버함수에 할당하는 클래스.
        2. ResultSetMapper : product객체를 return하거나 print하는 함수를 갖고있는 클래스
    4. /preparedstatment : query에서 특정 값들을 '?'로 prepare해 놓고, 함수를 통해 입력 파라미터로 ?값들만 채워서 쿼리를 빠르게 실행하는 것
    5. /resultset
        1. methods : 쿼리의 결과값을 받아서 커서를 이동하며(포인터와 비슷함) 하나씩 확인.
        2. pojomapping : SQL에서 가져온 데이터들을 mapper클래스들을 이용하여 JAVA 클래스 속 맴버변수 객체에 넣는 코드
    6. /statement
        1. batch : SQL쿼리들을 여러개 실행한 후 저장 해 놓고, 나중에 한번에 수행하는 것. executeupdate 함수만을 사용할 수 있다.(select등은 사용할 수 없다)
        2. execute : select등 조회값을 확인하는 함수(결과값이 있으면 True를 리턴하며 getResultSet을 이용해 출려값을 얻고 , 결과값이 없거나 executeUpdate인 경우 False를 리턴하고 getUpdateCount로 수행된 갯수를 얻는다)
        3. executeupdate : update, insert, delete 등 결과를 받아오지 않는 명령을 실행하는 함수. (변경사항에 적용된 row수를 리턴)


