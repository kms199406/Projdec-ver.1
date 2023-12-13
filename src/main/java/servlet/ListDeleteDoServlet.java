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
 * Servlet implementation class ListDeleteDoServlet
 */
@WebServlet("/listDeleteDo.do")
public class ListDeleteDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListDeleteDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");//과목코드 수신
		System.out.println("수신된 코드:"+id);
		String delete="delete from phones where id=?";
		int result=-1; Connection con=null; PreparedStatement pstmt=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, Integer.parseInt(id));
			result = pstmt.executeUpdate();//delete 실행
		}catch(Exception e) {
			System.out.println("제품 삭제 중 문제발생!");
		}finally {
			try {
				pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		//redirect
		response.sendRedirect("listDeleteResult.jsp?R="+result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
