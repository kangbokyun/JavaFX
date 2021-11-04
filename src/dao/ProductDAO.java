package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static ProductDAO dao = new ProductDAO();
	
	public static ProductDAO getProductDAO() {
		return dao;
	}
	
	public ProductDAO() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimeZone=UTC", "root", "1234");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean register(Product product) {
		try {

			String sql = "insert into product(p_name, p_img, p_contents, p_category, p_price, p_activation, m_no) values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, product.getP_name());
			pstmt.setString(2, product.getP_img());
			pstmt.setString(3, product.getP_contents());
			pstmt.setString(4, product.getP_category());
			pstmt.setInt(5, product.getP_price());
			pstmt.setInt(6, product.getP_activation());
			pstmt.setInt(7, product.getM_no());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public int bnoCheck(String id) {
		try {
			
			String sql = "select m_no from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public ObservableList<Product> productList(){
		ObservableList<Product> products = FXCollections.observableArrayList();
		try {
			String sql = "select * from product order by p_no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	// ȸ����ȣ�� �ش��ϴ� ���̵� ã��
	public String midCheck(int m_no) {
		try {
			
			String sql = "select m_id from member where m_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, m_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean delete(int p_no) {
		try {
			
			String sql = "delete from product where p_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, p_no);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean update(Product product) {
		try {
			
			String sql = "update product set p_name = ?, p_img = ?, p_contents = ?, p_category = ?, p_price = ? where p_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, product.getP_name());
			pstmt.setString(2, product.getP_img());
			pstmt.setString(3, product.getP_contents());
			pstmt.setString(4, product.getP_category());
			pstmt.setInt(5, product.getP_price());
			pstmt.setInt(6, product.getP_no());
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}