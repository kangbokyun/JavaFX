package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Member;

public class MemberDAO {
		// JDBC �ֿ� �������̽�, Ŭ����
		// 1. Connection : DB ���� �������̽�(DriverManager Ŭ����)
		
		// �ʵ�
		private Connection conn; // ���� �������̽�
		private PreparedStatement pstmt; // SQL ���� �������̽� ����
		private ResultSet rs; // �˻� ��� Ȯ��

//----------------------------------------------------------------------------------------------------------------------------------------------------------		
		
		// �����ڿ��� ����(����) �� ��ü�� ��� ����
		private static MemberDAO memDAO = new MemberDAO();
		
		// �����ϴ� ������
		public MemberDAO() {
			try {
				
				// ����(����)�ϴ� �κ�
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//----------------------------------------------------------------------------------------------------------------------------------------------------------		
		
		// �޼ҵ�
		public static MemberDAO getMemberDAO() {
			return memDAO;
		}
		
		// ��ɸ޼ҵ�
		// ȸ������
		public boolean signup(Member member) { // �μ��� ��� ��ü�� �޾� db�� �����ϴ� �޼ҵ�
			try {
			
				// 1. sql �ۼ� (DB ���۾� -> DML)
				String sql = "insert into member(m_id, m_pw, m_name, m_email, m_point) values (?, ?, ?, ?, ?)";
				
				// 2. SQL -> DBconnection
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL(����) -> ?�� ������ �ֱ�
				pstmt.setString(1, member.getM_id()); // (ù��° ����ǥ��, id �ֱ�)
				pstmt.setString(2, member.getM_pw());
				pstmt.setString(3, member.getM_name());
				pstmt.setString(4, member.getM_email());
				pstmt.setInt(5, member.getM_point());
				
				// 4. SQL ����(������Ʈ)
				pstmt.executeUpdate();
				
				return true; // DB ���� ������ true ��ȯ
				
			} catch (Exception e) {
				return false; // DB ���� ���н� false ��ȯ
			}
		}
		
		// �α���
		public boolean login(String id, String pw) {
			try {
				
				// 1. SQL�� �ۼ�
				String sql = "select * from member where m_id = ? and m_pw = ?";
				
				// 2. SQL -> DB����
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL ���� : ?�� ���� �μ��� �޼ҵ忡 ����( login(String id) -> pre.setString(1, id))
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				// 5. SQL ��� = 4. SQL ����(���� -> �˻����)
				ResultSet rs = pstmt.executeQuery();
				
				// 6. ��� Ȯ��
				if(rs.next()) { // ���� ����� ���� ������ ������ true ������ false)
					return true;
				} else {
					return false;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		// ���̵�ã��
		public String findid(String name, String email) {
			try {

				// SQL �ۼ�
				String sql = "select m_id from member where m_name=? and m_email=?";
				
				// SQL -> DB
				pstmt = conn.prepareStatement(sql);
				
				// SQL ����
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				
				// SQL ����
				rs = pstmt.executeQuery();
				
				// SQL ���
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
		
		// ��й�ȣ ã��
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
		// ȸ�� ��ȸ �޼ҵ�(ȸ�� ���̵� �μ��� �޾� ȸ������ ��ȯ)
		public Member getmember(String loginId) {
			try {
		
				// 1. SQL �ۼ�
				String sql = "select * from member where m_id = ?";
				
				// 2. SQL
				pstmt = conn.prepareStatement(sql);
				
				// 3. SQL ����
				pstmt.setString(1, loginId);
				
				// 4. SQL ����
				rs = pstmt.executeQuery();
				
				// 5. SQL ���
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
		
		
		// ȸ�� ����
		
		
		// ȸ�� Ż��
		public boolean delete(String loginId) {
			try {
		
				// ���� : delete from ���̺�� where ����
				// �� ���� : delete from ���̺��
				String sql = "delete from member where m_id = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				pstmt.executeUpdate();
				
				return true; // ������
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
			
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------		
// DB ���� �׽�Ʈ		
//		try {
//
//			// 1. �ش� mysql ����̺� Ȯ��
//			Class.forName("com.mysql.cj.jdbc.Driver"); // �ּҴ� ȸ�縶�� �ٸ��� �ܿ��ʿ� ����. �����ؼ� ���� �ȴٰ� ��
//			System.out.println("����̺� �������� ����");
//			
//			// 2. DB ����
//			// "jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC" <- ����
//			// "root" <- DB �̸�
//			// "1234" <- ������ ��й�ȣ
//			// Connection conn = DriverManager.getConnection() <- DB�� ����
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
//			System.out.println("DB�� ���� ����");
//			
//		} catch (Exception e) {
//			System.out.println("���� ����");
//		}
}
