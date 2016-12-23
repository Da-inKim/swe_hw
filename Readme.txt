★ GitHub url: https://github.com/Da-inKim/swe_hw

★ 디비는 MySQL 을 사용하였습니다.

★ MySQL 연결 시, 교수님의 root계정 비밀번호로 코드를 수정하여 사용해주세요
   LoginView.java 파일을 제외한 모든 파일의 String pass = "01047670231"; 부분을 교수님의 비밀번호로 수정부탁드립니다.

★ testcase를 돌리실 땐, 꼭 다시 account table의 stored_id를 swuser로, stored_pw를 user0000으로 수정하신 후 돌려주세요!!!

★ Test source file: TestCase.java
★ Program source files: 그 외 10개의 .java 파일들
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

★ 디비 설정은 다음과 같이 해주세요 ★

CREATE TABLE account
   (stored_id VARCHAR(20) NOT NULL,
   stored_pw VARCHAR(20), 
   PRIMARY KEY (id)); 

☆ account table생성 후, stored_id에 swuser, stored_pw에 user0000으로 row를 추가하여 초기화한 후 시작해주세요.


CREATE TABLE phonebook
   (phone_key INT(11) NOT NULL AUTO INCREMENT,
   stored_id VARCHAR(20) NOT NULL,
   name VARCHAR(20),
   phone_number VARCHAR(20),
   PRIMARY KEY (stored_id, phone_key),
   FOREIGN KEY(stored_id) 
   REFERENCES account ON DELETE CASCASE,
   REFERENCES account ON UPDATE CASCASE);


CREATE TABLE schedule
   (schedule_key INT(11) NOT NULL AUTO INCREMENT,
   store_id VARCHAR(20) NOT NULL,
   date DATE,
   description VARCHAR(45),
   PRIMARY KEY (store_id, schedule_key),
   FOREIGN KEY(store_id) 
   REFERENCES account ON DELETE CASCASE
   REFERENCES account ON UPDATE CASCASE);

☆ 외래키 설정시 schedule table에서는 stored_id 가 아닌 store_id 임을 확인해주세요!!

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

* java파일 실행(아무거나 실행하셔도 User_Account3.java파일이 먼저 실행됩니다.) *


ID 값으로 swuser를 입력하고, PW 값으로 user0000 값을 입력한 후, 로그인 버튼을 누른다.

로그인 된 상태에서 Change ID 버튼, Change PW 버튼, 스케줄 기능 버튼, 폰북 기능 버튼 중 원하는 기능의 버튼을 누른다.
-----------------------------------------------------------------------------------------------------------------------

1. Change ID 버튼을 눌렀을 때, 왼쪽의 현재 ID값을 확인하고, 오른쪽에 변경하고자 하는 ID 값을 입력한다.

1-1. 입력값이 15자보다 많으면 변경되지 않는다.(현재의 아이디가 유지된다.)
-----------------------------------------------------------------------------------------------------------------------

2. Change PW 버튼을 눌렀을 때, 왼쪽의 현재 PW값을 확인하고, 오른쪽에 변경하고자 하는 PW 값을 입력한다.

2-1. 입력값이 15자보다 많으면 변경되지 않는다.(현재의 비밀번호가 유지된다.)

-----------------------------------------------------------------------------------------------------------------------

3. 스케줄 기능 버튼을 누르면, 현재 등록된 스케줄들의 목록과 아래에는 추가 버튼, 삭제 버튼, 이전으로 버튼이 보인다.

3-1-1. 추가 버튼을 누른 후, 스케줄 날짜와 내용을 입력하고 추가 버튼을 누른다. 
3-1-1-1. date값은 꼭 161223과 같이 6자의 년월일 형식으로 입력해야한다. 그렇지 않으면 다시 입력하라는 경고메세지가 뜬다.
         description값은 꼭 최소 4자, 최대 30자로 입력해야한다. 그렇지 않으면 다시 입력하라는 경고메세지가 뜬다.

3-1-2. 추가 버튼을 누른 후, 스케줄의 추가를 원하지 않으면 취소버튼을 누른다.


3-2-1. 삭제 버튼을 누른 후, 삭제할 스케줄의 key 번호를 입력하고 삭제 버튼을 누른다.
3-2-1-1. 존재하지 않는 스케줄의 key번호를 입력하면, 경고메세지가 뜨며 삭제되지 않는다.

3-2-2.  삭제 버튼을 누른 후, 스케줄의 삭제를 원하지 않으면 취소 버튼을 누른다.

3-3. 이전으로 버튼을 누르면, 다시 Change ID 버튼, Change PW 버튼, 스케줄 기능 버튼, 폰북 기능 버튼이 있는 창이 나타난다.
-----------------------------------------------------------------------------------------------------------------------

4. 폰북 기능 버튼을 누르면, 현재 등록된 상태의 연락처들과 추가 버튼, 삭제 버튼, 이전으로 버튼이 보인다.

4-1-1. 추가 버튼을 누른 후, 연락처 주인의 이름과 연락처를 입력하고 추가 버튼을 누른다.
4-1-1-1. name값은 꼭 최소 2자, 최대 7자로 입력해야한다. 그렇지 않으면 다시 입력하라는 경고메세지가 뜬다.

4-1-2. 추가 버튼을 누른 후, 연락처의 추가를 원하지 않으면 취소버튼을 누른다.

4-2-1. 삭제 버튼을 누른 후, 삭제 할 연락처의 key 번호를 입력하고 삭제 버튼을 누른다.
4-2-1-1. 존재하지 않는 연락처의 key번호를 입력하면, 경고메세지가 뜨며 삭제되지 않는다.

4-2-2. 삭제 버튼을 누른 후, 연락처의 삭제를 원하지 않으면 취소 버튼을 누른다.

4-3. 이전으로 버튼을 누르면, 다시 Change ID 버튼, Change PW 버튼, 스케줄 기능 버튼, 폰북 기능 버튼이 있는 창이 나타난다.

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
