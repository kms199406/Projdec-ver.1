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
import javax.servlet.http.HttpSession;

import serv.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/noticeDetail.do")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("NO");//글번호
		String select="select no,title,content,to_char(entry_date,'YY/MM/DD') "
				+ " from hrdnotice_tbl where no=?";
		Notice notice = null;//조회결과를 저장할 변수 선언
		Connection con=null; PreparedStatement pstmt=null; ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, Integer.parseInt(no));
			rs = pstmt.executeQuery();//select 실행
			if(rs.next()) {
				notice = new Notice();
				notice.setNo(rs.getInt(1)); notice.setTitle(rs.getString(2));
				notice.setContent(rs.getString(3));
				notice.setEntry_date(rs.getString(4));
			}
		}catch(Exception e) {
			System.out.println("공지사항 상세 조회 중 문제발생!");
		}finally {
			try {
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e) {}
		}
		request.setAttribute("NOTICE", notice);
		HttpSession session = request.getSession();
		String admin = (String)session.getAttribute("ADMIN");
		if(admin != null) {//관리자인 경우
			RequestDispatcher rd = 
					request.getRequestDispatcher("noticeDetailAdmin.jsp");
			rd.forward(request, response);
		}else {//관리자가 아닌 경우
			RequestDispatcher rd = 
					request.getRequestDispatcher("noticeDetail.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



