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
	// 
	
	// 삭제(delete)
	
	// 조건(비교연산자, 키워드, 함수)
	// and : 이면서, 면서, 이고, 그리고
	//		- 조건1 and 조건2
	// or : 이거나, 거나, 또는, 하나라도
	//		- 조건1 or 조건2
}
