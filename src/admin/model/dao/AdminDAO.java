package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import member.model.dao.MemberDAO;
import product.model.vo.Product;

public class AdminDAO {
	private Properties prop = new Properties();

	public AdminDAO() {
		String fileName = MemberDAO.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getAllProduct(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = null;

		String query = prop.getProperty("getAllProduct");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<>();

			while (rset.next()) {
				Product p = new Product(rset.getInt("pid"), rset.getInt("price"), rset.getInt("stock"),
						rset.getInt("sellcount"), rset.getString("pname"), rset.getString("cname"),
						rset.getString("sub_cname"), rset.getString("bname"), rset.getString("useoption"),
						rset.getString("product_op"), rset.getDate("regdate"), rset.getDate("modify_date"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int insertProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertProduct");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p.getpId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getCategoryNo());
			pstmt.setInt(4, p.getSubCategoryNo());
			pstmt.setInt(5, p.getBrandNo());
			pstmt.setInt(6, p.getPrice());
			pstmt.setInt(7, p.getStock());
			pstmt.setString(8, p.getUseOption());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> getSubCategory(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = null;

		String query = prop.getProperty("getSubCategory");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);

			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Product p = new Product(rset.getInt("sub_cid"), rset.getString("sub_cname"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertOption(Connection conn, String optionResult, int pId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertOption");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			pstmt.setString(2, optionResult);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getNextPId(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int pId = 0;

		String query = prop.getProperty("getNextPId");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				pId = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return pId;
	}

	public Product selectProduct(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;

		String query = prop.getProperty("selectProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				product = new Product(rset.getInt("pid"), rset.getInt("price"), rset.getInt("stock"),
						rset.getInt("sellcount"), rset.getString("pname"), rset.getString("cname"),
						rset.getString("sub_cname"), rset.getString("bname"), rset.getString("useoption"),
						rset.getString("product_op"), rset.getDate("regdate"), rset.getDate("modify_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}

	public int updateProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getpName());
			pstmt.setInt(2, p.getCategoryNo());
			pstmt.setInt(3, p.getSubCategoryNo());
			pstmt.setInt(4, p.getBrandNo());
			pstmt.setInt(5, p.getPrice());
			pstmt.setInt(6, p.getStock());
			pstmt.setString(7, p.getUseOption());
			pstmt.setInt(8, p.getpId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateOption(Connection conn, String optionResult, int pId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateOption");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, optionResult);
			pstmt.setInt(2, pId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Product selectOption(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;

		String query = prop.getProperty("selectProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				product = new Product(rset.getString("product_op"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}

	public int deleteProduct(Connection conn, int pId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteProduct");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
