package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Board;
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
	
	
	
	// 3. �Խù� ���� �޼ҵ�
	
	
	
	// 4. �Խù� ��ü��ȸ �޼ҵ�
	public ObservableList<Board> boardList(){ // board�� �ִ°� ��°�� ���ͼ� arraylist�� ���
		// ����
		ObservableList<Board> obserList = FXCollections.observableArrayList(); 
		try {

		
			// ���Ǿ��� �� ��������
			String sql = "select * from board";
		
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
	
	
	
}
