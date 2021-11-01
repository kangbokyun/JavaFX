package 정리;

public class DB {
	// database
	// - 데이터집합 -> 테이블관리
	
	// 용어 : 표 = table / 세로 = field / 가로 = record
	
	// SQL : 데이터베이스 관리 언어
	// - DDL(정의어)
	// - DML(조작어)
	
	// 1. 삽입 : insert into 테이블명(필드1, 필드2) values (값1, 값2) 
	// - 모든필드 삽입 시 "필드명" 생략
	// 2. 검색 : select 필드명 from 테이블명 where 조건
	// - 모든필드 검색 : select * from 테이블명 where 조건(*=와일드카드)
	// 3. 삭제 : delete
	// 4. 수정 : update
	
	// 키워드
	// 1. where : 조건
	// 2. and : 이면서, 면서, 이고, 모두, 그리고
	// 3. or : 이거나, 거나, 하나라도
	
	// CRUD(Create, Read, Update, Delete)
	
	
	// MVC(Model, View, Controller)
	// 1. Model : 데이터
	// 2. View : 프론트엔드(씬빌더 / Fxml), html
	// 3. Controller : java파일(클래스)
	
	// View <---> Controller <--DTO--> Model <--DAO--> DB
	// DTO : 데이터 이동 객체
	// DAO : 데이터 기능조작 객체
	// 삽입 : view -> con -> model -> db
	// 조회 : db -> model -> con -> view
	
	
	// DTO
	
	
	// DAO
	
	
}
