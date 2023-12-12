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
 * Servlet implementation class ItemEntryServlet
 */
@WebServlet("/phoneEntry.do")
public class PhoneEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String code = request.getParameter("CODE");
		String category = request.getParameter("CATEGORY");
		String p_name = request.getParameter("P_NAME");
		String filename = request.getParameter("IMAGE");
		String desc = request.getParameter("DESC");
		String price = request.getParameter("PRICE");		
		String bun = request.getParameter("BUNRYU");		
		String q = "insert into phones values(?,?,?,?,?,?,?)";
		int result = -1; Connection con=null; PreparedStatement pstmt=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(q);
			pstmt.setInt(1, Integer.parseInt(code));
			pstmt.setString(2, category);
			pstmt.setString(3, p_name);
			pstmt.setString(4, filename);
			pstmt.setString(5, desc);
			pstmt.setString(6, price);
			pstmt.setString(7, bun);
			result = pstmt.executeUpdate();//insert실행
		}catch(Exception e) {
			System.out.println("상품 등록 중 문제 발생!");
		}finally {
			try {
				pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		response.sendRedirect("phoneEntryResult.jsp?R="+result);
	}

}










