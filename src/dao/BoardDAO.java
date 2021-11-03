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
	
	
	// 3. 게시물 수정 메소드
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
	
	
	// 4. 게시물 전체조회 메소드
	public ObservableList<Board> boardList(){ // board에 있는거 통째로 빼와서 arraylist에 담기
		// 선언
		ObservableList<Board> obserList = FXCollections.observableArrayList(); 
		try {

		
			// 조건없이 다 가져오기
			String sql = "select * from board order by b_no desc"; // order by 기준 desc : 기준을 내림차순 정렬(asc : 오름차순)
		
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
	
	

	// 6. 조회수 증가 메소드
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
	
	// 7. 댓글 등록 메소드
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
	
	
	// 8. 댓글 출력 메소드
	public ObservableList<Reply> replyList(int r_b_no) {
		ObservableList<Reply> obList = FXCollections.observableArrayList();
		
		try {

			String sql = "select * from reply where r_b_no = ? order by r_b_no desc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r_b_no);
			
			rs = pstmt.executeQuery();
			while(rs.next()) { // 다음 레코드가 없을 때까지
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
