package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Board;
import domain.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static BoardDAO boardDAO = new BoardDAO();
	
	// ��Ŭ������ ��ü�� ��ȯ���ִ� �޼ҵ�
	public static BoardDAO getboardDAO() {
		return boardDAO;
	}
	
	
	public BoardDAO() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// �޼ҵ�
	// 1. �Խù� ��� �޼ҵ�
	public boolean write(Board board) {
		try {
			String sql = "insert into board(b_title, b_contents, b_write) values (?, ?, ?)";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_cntents());
			pstmt.setString(3, board.getB_write());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	// 2. �Խù� ���� �޼ҵ�
	public boolean delete(int b_no) {
		try {
			String sql = "delete from board where b_no = ?";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b_no);
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	// 3. �Խù� ���� �޼ҵ�
	public boolean update(int b_no, String b_title, String b_contents) {
		try {
			String sql = "update board set b_title = ? b_contents = ? where b_no = ?";
		
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, b_no);
			pstmt.setString(2, b_title);
			pstmt.setString(3, b_contents);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	// 4. �Խù� ��ü��ȸ �޼ҵ�
	public ObservableList<Board> boardList(){ // board�� �ִ°� ��°�� ���ͼ� arraylist�� ���
		// ����
		ObservableList<Board> obserList = FXCollections.observableArrayList(); 
		try {

		
			// ���Ǿ��� �� ��������
			String sql = "select * from board order by b_no desc"; // order by ���� desc : ������ �������� ����(asc : ��������)
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			// �˻��� ���� ���ڵ��� ��üȭ(�ϳ���)
			while(rs.next()) {
				// ��������� ���ڵ尡 ���� ������ �ݺ��ϸ鼭 ��üȭ�ϱ�
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				obserList.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return obserList;
	}
	
	
	// 5. �Խù� ������ȸ �޼ҵ�
	
	

	// 6. ��ȸ�� ���� �޼ҵ�
	public boolean ViewUpdate(int view) {
		try {
		
			String sql = "update board set b_view = b_view + 1 where b_no = ?";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, view);
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	// 7. ��� ��� �޼ҵ�
	public boolean replyWrite(Reply reply) {
		try {
			
			String sql = "insert into reply(r_contents, r_writer, r_b_no) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getR_contents());
			pstmt.setString(2, reply.getR_write());
			pstmt.setInt(3, reply.getR_b_no());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	// 8. ��� ��� �޼ҵ�
	public ObservableList<Reply> replyList(int r_b_no) {
		ObservableList<Reply> obList = FXCollections.observableArrayList();
		
		try {

			String sql = "select * from reply where r_b_no = ? order by r_b_no desc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r_b_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) { // ���� ���ڵ尡 ���� ������
				Reply reply = new Reply(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				obList.add(reply);
			}
			return obList;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return obList;
		}
	}
}
