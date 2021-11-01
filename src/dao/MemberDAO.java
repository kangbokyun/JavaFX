package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;

public class MemberDAO {
		// JDBC 주요 인터페이스, 클래스
		// 1. Connection : DB 연결 인터페이스(DriverManager 클래스)
		
		// 필드
		private Connection conn; // 연결 인터페이스
		private PreparedStatement pstmt; // SQL 연결 인터페이스 선언
		private ResultSet rs; // 검색 결과 확인

//----------------------------------------------------------------------------------------------------------------------------------------------------------		
		
		// 생성자에서 실행(연결) 후 객체에 담는 역할
		private static MemberDAO memDAO = new MemberDAO();
		
		// 실행하는 생성자
		public MemberDAO() {
			try {
				
				// 실행(연결)하는 부분
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//----------------------------------------------------------------------------------------------------------------------------------------------------------		
		
		// 메소드
		public static MemberDAO getMemberDAO() {
			return memDAO;
		}
		
		// 기능메소드
		// 회원가입
		public boolean signup(Member member) { // 인수로 멤버 객체를 받아 db에 저장하는 메소드
			try {
			
				// 1. sql 작성 (DB 조작어 -> DML)
				String sql = "insert into member(m_id, m_pw, m_name, m_email, m_point) values (?, ?, ?, ?, ?)";
				
				// 2. SQL -> DBconnection
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL(조작) -> ?에 데이터 넣기
				pstmt.setString(1, member.getM_id()); // (첫번째 물음표에, id 넣기)
				pstmt.setString(2, member.getM_pw());
				pstmt.setString(3, member.getM_name());
				pstmt.setString(4, member.getM_email());
				pstmt.setInt(5, member.getM_point());
				
				// 4. SQL 실행(업데이트)
				pstmt.executeUpdate();
				
				return true; // DB 저장 성공시 true 반환
				
			} catch (Exception e) {
				return false; // DB 저장 실패시 false 반환
			}
		}
		
		// 로그인
		public boolean login(String id, String pw) {
			try {
				
				// 1. SQL의 작성
				String sql = "select * from member where m_id = ? and m_pw = ?";
				
				// 2. SQL -> DB연결
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL 설정 : ?에 들어온 인수를 메소드에 대입( login(String id) -> pre.setString(1, id))
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				// 5. SQL 결과 = 4. SQL 실행(쿼리 -> 검색결과)
				ResultSet rs = pstmt.executeQuery();
				
				// 6. 결과 확인
				if(rs.next()) { // 쿼리 결과에 다음 내용이 있으면 true 없으면 false)
					return true;
				} else {
					return false;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		// 아이디찾기
		public String findid(String name, String email) {
			try {

				// SQL 작성
				String sql = "select m_id from member where m_name=? and m_email=?";
				
				// SQL -> DB
				pstmt = conn.prepareStatement(sql);
				
				// SQL 설정
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				
				// SQL 실행
				rs = pstmt.executeQuery();
				
				// SQL 결과
				if(rs.next()) {
					return rs.getString(1);
				} else {
					return null;
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// 비밀번호 찾기
		public String findpw(String id, String email) {
			try {
				
				String sql = "select m_pw from member where m_id=? and m_email";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, email);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return rs.getString(1);
				} else {
					return null;
				}
				
			} catch (Exception e) {
				return null;
			}
		}
		// 회원 조회 메소드(회원 아이디를 인수로 받아 회원정보 반환)
		public Member getmember(String loginId) {
			try {
		
				// 1. SQL 작성
				String sql = "select * from member where m_id = ?";
				
				// 2. SQL
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL 설정
				pstmt.setString(1, loginId);
				
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				
				// 5. SQL 결과
				if(rs.next()) {
					Member member = new Member(rs.getString(2), "", rs.getString(4), rs.getString(5), rs.getInt(6));
					return member;
				} else {
					return null;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		
		// 회원 수정
		
		
		// 회원 탈퇴
		public boolean delete(String loginId) {
			try {
		
				// 삭제 : delete from 테이블명 where 조건
				// 다 삭제 : delete from 테이블명
				String sql = "delete from member where m_id = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.executeUpdate();
				
				return true; // 성공시
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
			
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------		
// DB 연동 테스트		
//		try {
//
//			// 1. 해당 mysql 드라이브 확인
//			Class.forName("com.mysql.cj.jdbc.Driver"); // 주소는 회사마다 다르고 외울필욘 없다. 복사해서 쓰면 된다고 함
//			System.out.println("드라이브 가져오기 성공");
//			
//			// 2. DB 연동
//			// "jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC" <- 고정
//			// "root" <- DB 이름
//			// "1234" <- 설정한 비밀번호
//			// Connection conn = DriverManager.getConnection() <- DB와 연동
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
//			System.out.println("DB와 연동 성공");
//			
//		} catch (Exception e) {
//			System.out.println("연동 실패");
//		}
}
