package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serv.PHON;

/**
 * Servlet implementation class ItemListServlet
 */
@WebServlet("/PhonListServlet")
public class PhonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhonListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q="select * from phones";
		Connection con=null; Statement stmt=null; ResultSet rs=null;
		ArrayList<PHON> phon = new ArrayList<PHON>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");

				stmt = con.createStatement();
			rs = stmt.executeQuery(q);//select 실행
			while(rs.next()) {
				PHON item = new PHON();
				item.setId(rs.getInt(1));
				item.setCategory(rs.getString(2));
				item.setProduct_name(rs.getString(3));
				item.setBun(rs.getString(4));
				item.setPicturl_url(rs.getString(5));
				phon.add(item);
				/*
				 * item.setItem_id(rs.getInt(1));//상품번호
				 * item.setItem_name(rs.getString(2));//상품이름 item.setPrice(rs.getInt(3));//상품가격
				 * item.setDescription(rs.getString(4));//상품설명
				 * item.setPicture_rul(rs.getString(5));//상품이미지 이름 list.add(item);//DTO를
>>>>>>> 7f6a7def7250ca27fcbaa9e7b5713508c2d3306e
				 * ArrayList에 저장
				 */			}
		}catch(Exception e) {
			System.out.println("상품정보 조회 중 문제발생!");
		}finally {
			try {
				rs.close(); stmt.close(); con.close();
			}catch(Exception e) {}
		}
		request.setAttribute("LIST", phon);
		int totalCount = getTotalItemCount();
	
		int pageCount = totalCount / 5;
		System.out.print(pageCount);
		if(pageCount % 5 != 0) pageCount++;
		request.setAttribute("PAGES", pageCount);
		RequestDispatcher rd = request.getRequestDispatcher("iphone.jsp");
		rd.forward(request, response);
	}
	protected int getTotalItemCount() {
		String select="select count(*) from phones";
		int count = 0; 
		Connection con=null; Statement stmt=null; ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			stmt = con.createStatement();
			rs = stmt.executeQuery(select);//select 실행
			if(rs.next()) count = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("상품 목록 갯수 조회 중 문제발생!");
		}finally {
			try {
				rs.close(); stmt.close(); con.close();
			}catch(Exception e) {}
		}
		return count;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}








