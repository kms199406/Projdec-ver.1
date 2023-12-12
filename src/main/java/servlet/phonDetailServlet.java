package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serv.PHON;

/**
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/phonDetail.do")
public class phonDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public phonDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");//상품번호 수신
		String select="select * from phones where id = ?";
		PHON item=null; Connection con=null; PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();//select 실행
			if(rs.next()) {
				item = new PHON();//DTO를 생성
				item.setId(rs.getInt(1));
				item.setCategory(rs.getString(2));
				item.setProduct_name(rs.getString(3));
				item.setDescription(rs.getString(4));
				item.setPicturl_url(rs.getString(5));
				/*
				 * item.setItem_id(rs.getInt(1));//상품번호
				 * item.setItem_name(rs.getString(2));//상품이름 item.setPrice(rs.getInt(3));//상품가격
				 * item.setDescription(rs.getString(4));//상품설명
				 * item.setPicture_rul(rs.getString(5));//이미지 이름
				 */			}
		}catch(Exception e) {
			System.out.println("상품 상세 조회 중 문제발생!");
		}finally {
			try {
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		request.setAttribute("ITEM", item);
		RequestDispatcher rd = request.getRequestDispatcher("phonDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}







