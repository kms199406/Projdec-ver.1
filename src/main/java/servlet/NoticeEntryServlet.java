package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeEntryServlet
 */
@WebServlet("/noticeEntry.do")
public class NoticeEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected int getNo() {
		String q="select max(no) from phonenotice_tbl";
		Integer result = null;//조회결과를 저장할 변수 선언
		Connection con=null; Statement stmt=null; ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			stmt = con.createStatement();
			rs = stmt.executeQuery(q);//select 실행
			if(rs.next()) {
				result = rs.getInt(1);//글번호
			}else {
				result = 0;
			}
		}catch(Exception e) {
			System.out.println("공지글 번호 검색 중 문제 발생!");
		}finally {
			try {
				rs.close(); stmt.close(); con.close();
			}catch(Exception e) {}
		}
		return result;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		int no = getNo() + 1;
		String insert = "insert into phonenotice_tbl values(?,?,?,sysdate)";
		int result = -1; Connection con=null; PreparedStatement pstmt=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, no); pstmt.setString(2, title);
			pstmt.setString(3, content);
			result = pstmt.executeUpdate();//insert 실행
		}catch(Exception e) {
			System.out.println("공지사항 등록 중 문제발생!");
		}finally {
			try {
				pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		response.sendRedirect("noticeEntryResult.jsp?R="+result);
	}

}







