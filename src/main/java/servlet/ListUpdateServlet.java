package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListUpdateServlet
 */
@WebServlet("/listUpdateDo.do")
public class ListUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("ID");
		String Category = request.getParameter("CATEGORY");
		String P_name = request.getParameter("P_NAME");
		String desc = request.getParameter("DESC");
		String Picture = request.getParameter("PITURE");
		String Price = request.getParameter("PRICE");
		String Bun = request.getParameter("BUN");
		String update = "update phones set CATEGORY=?, PRODUCT_NAME=?, "
				+ "DESCRIPTION=?, PICTURL_URL=?, PRICE=?, BUN=? where ID=?";
		int result = -1; Connection con=null; PreparedStatement pstmt=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, Category);
			pstmt.setString(2, P_name);
			pstmt.setString(3, desc);
			pstmt.setString(4, Picture);
			pstmt.setString(5, Price);
			pstmt.setString(6, Bun);
			pstmt.setInt(7, Integer.parseInt(id));
			result = pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 중 문제발생!");
		}finally {
			try {
				pstmt.close(); con.close();
			} catch (Exception e2) {
				
			}
		}
		response.sendRedirect("listUpdateResult.jsp?R="+result);
	}
}
