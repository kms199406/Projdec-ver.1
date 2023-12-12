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
 * Servlet implementation class UserEntryServlet
 */
@WebServlet("/userEntry.do")
public class UserEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEntryServlet() {
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
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("ID");//계정
		String pwd = request.getParameter("PWD");//암호
		String name = request.getParameter("NAME");//이름
		String addr = request.getParameter("ADDR");//주소
		String phone = request.getParameter("PHONE");//연락처
		String email = request.getParameter("EMAIL");//이메일
		String gender = request.getParameter("GENDER");//성별
		String job = request.getParameter("JOB");//직업
		String insert = "insert into phonesusers_tbl values(?,?,?,?,?,?,?,?)";
		Connection con=null; PreparedStatement pstmt=null; int result = -1;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, id); pstmt.setString(2, pwd);
			pstmt.setString(3, name); pstmt.setString(4, addr);
			pstmt.setString(5, email); pstmt.setString(6, phone);
			pstmt.setString(7, gender); pstmt.setString(8, job);
			result = pstmt.executeUpdate();//insert 실행
		}catch(Exception e) {
			System.out.println("가입자 등록 중 문제발생!");
		}finally {
			try {
				pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		response.sendRedirect("userEntryResult.jsp?R="+result);
	}

}















