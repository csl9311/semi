package payment.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import payment.model.dao.PaymentDAO;
import payment.model.vo.Payment;
import shop.model.vo.Cart;

public class PaymentService {
	public ArrayList<Payment> selectPayment(String userId) {
		System.out.println("서비스 들어옴?");
		Connection conn = getConnection();
		ArrayList<Payment> pList = new PaymentDAO().selectPayment(conn, userId);
		close(conn);
		return pList;
	}

	public int insertPurchase(String userId, Cart ca, Payment shipinfo) {
		Connection conn= getConnection();
		
		int result = new PaymentDAO().insertPurchase(conn, userId, ca, shipinfo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
}
