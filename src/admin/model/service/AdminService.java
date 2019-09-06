package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDAO;
import product.model.vo.Product;

public class AdminService {

	public ArrayList<Product> getAllProduct() {
		Connection conn = getConnection();
		ArrayList<Product> list = new AdminDAO().getAllProduct(conn);
		close(conn);
		return list;
	}

	public int insertProduct(Product p) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertProduct(conn, p);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Product> getSubCategory(int categoryNo) {
		Connection conn = getConnection();
		ArrayList<Product> list = new AdminDAO().getSubCategory(conn, categoryNo);
		close(conn);
		return list;
	}

	public int insertOption(String optionResult, int pId) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertOption(conn, optionResult, pId);
		close(conn);
		return result;
	}

	public int getNextPId() {
		Connection conn = getConnection();
		int result = new AdminDAO().getNextPId(conn);
		close(conn);
		return result;
	}

	public Product selectProduct(int pId) {
		Connection conn = getConnection();
		Product product = new AdminDAO().selectProduct(conn, pId);
		close(conn);
		return product;
	}

	public int updateProduct(Product p) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateProduct(conn, p);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateOption(String optionResult, int pId) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateOption(conn, optionResult, pId);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Product selectOption(int pId) {
		Connection conn = getConnection();
		Product product = new AdminDAO().selectOption(conn, pId);
		close(conn);
		return product;
	}

	public int deleteProduct(int pId) {
		Connection conn = getConnection();
		int result = new AdminDAO().deleteProduct(conn, pId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
