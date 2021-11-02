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
	
	// 현클래스를 객체로 반환해주는 메소드
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
	
	// 메소드
	// 1. 게시물 등록 메소드
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
	
	
	// 2. 게시물 삭제 메소드
	
	
	
	// 3. 게시물 수정 메소드
	
	
	
	// 4. 게시물 전체조회 메소드
	public ObservableList<Board> boardList(){ // board에 있는거 통째로 빼와서 arraylist에 담기
		// 선언
		ObservableList<Board> obserList = FXCollections.observableArrayList(); 
		try {

		
			// 조건없이 다 가져오기
			String sql = "select * from board";
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			// 검색된 쿼리 레코드의 객체화(하나씩)
			while(rs.next()) {
				// 쿼리결과내 레코드가 없을 때까지 반복하면서 객체화하기
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				obserList.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return obserList;
	}
	
	
	// 5. 게시물 개별조회 메소드
	
	
	
}
