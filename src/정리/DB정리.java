package 정리;

public class DB정리 {
	// DB : 데이터베이스, 표(테이블) 만들기
	// 목적 : 데이터 저장, 관리
	// data : 값
	// base : 모임 - > 표
	
	// 서버 : 데이터를 갖고 있는 컴퓨터 / cafe24를 이용하면 서버를 할당받을 수 있음, 아마존(aws)도 있음
	// 진행은 local로 할거임
	// DB(데이터테이블) <---------- DBMS(DB관리시스템) <------------------- DBA(DB관리자)
	//												* Mysql
	// MySQL Installer for Windows(470MB) -> MYSQL 서버와  관련 소프트웨어 제공
	// server 8.0.27 x64
	// workbench 8.0.27
	// connector/j 8.0.27
	// Connector/J(Platform independent)(4.8MB)
	
	// ip : pc 주소(= 집주소)
	// port : 네트워크에서 서버로 들어오는 문 
	// 포트 번호 확인(cmd : netstat -ano) (3307)
	// - DB로 들어가는 문(***port, pw 잘 기억)
	
	// 워크벤치 실행 -> 서버 접속
	
	// SQL : DML(조작어)
	// insert : 삽입
	//																			(값, 값, 값)
	// 특정필드 : insert into 테이블명(필드명1, 필드명2, 필드명3) values (?, ?, ?);
	// 모든필드 : insert into 테이블명 values (?, ?, ?);
	
	// 검색(select)
	// 특정필드 : select 필드명 from 테이블명 where 조건
	// 모든필드 : select * from 테이블명 where 조건
	
	// 삽입(insert)
	// insert
	
	// 삭제(delete)
	
	// 수정(update)
	// update 테이블면 set 변경필드 
	
	// 조건(비교연산자, 키워드, 함수)
	// and : 이면서, 면서, 이고, 그리고
	//		- 조건1 and 조건2
	// or : 이거나, 거나, 또는, 하나라도
	//		- 조건1 or 조건2
	
	// select만 executeQuery() 나머지는 executeUpdate()
	
	// next는 레코드를 빼오는것(한 줄)
	// get은 필드를 빼오는 것(현재 레코드의 필드)
	
	// SQL : 데이터베이스 관리 언어
	// DDL 정의어
	// 1. Create : 생성
	// 
	
	// 2. Drop : 삭제
	// 3. Alter : 수정
	
	
	// 테이블뷰
	// ObserverList<> : javafx를 사용하는 컬렉션 프레임워크
		// 1. 테이블에 리스트 세팅 : Tableview에 리스트를 넣을 때 setItems(ObservableList)
		// 2. 테이블 필드에 리스트 내 객체 세팅
		// 2-1. 테이블 내 열 가져오기
		// 2-2. 해당열에 객체 값 넣기
	
	// 테이블뷰 이름.getSelection
	
	
	
	// 1.primary key : 기본 키 / 식별용 필드(중복값x null) : 주민번호, 사번, 학번
	// 2. foreign key : 외래 키(다른 테이블에서 기본키와 연결된 키)
	// 3. auto_increment: 자동번호 주입
	// 4. not null : null값 제외(null일경우 오류 발생)
	// 5. 
	
	
	
}
