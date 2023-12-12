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

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		//중복검사용 쿼리
		String select="select user_id from phonesusers_tbl where user_id=?";
		String user_id=null;//조회결과를 저장하기 위한 변수 선언
		Connection con=null; PreparedStatement pstmt=null; ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();//select 실행
			if(rs.next()) {
				user_id = rs.getString(1);//조회결과를 저장
			}
		}catch(Exception e) {
			System.out.println("계정 중복 검사 중 문제발생!");
		}finally {
			try {
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		//중복검사용 계정과 중복검사 결과를 전달한다.
		request.setAttribute("ID", id);//계정을 저장
		request.setAttribute("DUP", user_id);//중복검사의 결과
		RequestDispatcher rd = request.getRequestDispatcher("idCheckResult.jsp");
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
