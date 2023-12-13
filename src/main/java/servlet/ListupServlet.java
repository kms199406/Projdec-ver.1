package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serv.PHON;

/**
 * Servlet implementation class ListupServlet
 */
@WebServlet("/ListupServlet")
public class ListupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String select = "select ID,CATEGORY,PRODUCT_NAME,DESCRIPTION,PICTURL_URL,PRICE,BUN from phones where id=?";
		Connection con=null; PreparedStatement pstmt=null;
		ResultSet rs = null;
		PHON c = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();//select 실행
			if(rs.next()) {
				c = new PHON();
				c.setId(rs.getInt(1));
				c.setCategory(rs.getString(2));
				c.setProduct_name(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setPicturl_url(rs.getString(5));
				c.setPrice(rs.getString(6));
				c.setBun(rs.getString(7));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("교과목 변경용 검색 중 문제 발생!");
		}finally {
			try {
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		//Forward
		request.setAttribute("PHON", c);
		RequestDispatcher rd = request.getRequestDispatcher(
				"listupdate.jsp");
		rd.forward(request, response);
	}

}
