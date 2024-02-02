package jsoft.ads.user;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import jsoft.*;
import jsoft.objects.*;


/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/user/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Thường đc dùng để cung cấp giao diện (GUI/HTML)<br>
	 * Được gọi:,<br>
	 * 1) Thông qua URL (https://www.jsoft.com/tin-tuc/?id=123)<br>
	 * 2) Sự kiện của form: method = "get" <br>
	 * 
	 * @param request - lưu trữ dữ liệu và các yêu cầu xử lý từ trình khách gửi lên
	 * @param response - lưu trữ kết quả xử lý cần gửi về trình khách (client)
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xác định kiểu nội dung xuất về trình khách
		response.setContentType(CONTENT_TYPE);
		
		// Tạo đối tượng xuất nội dung
		PrintWriter out = response.getWriter();
		out.append("<!doctype html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.append("<title>Login V3</title>");
		out.append("<link href=\"/adv/css/bootstrap.min.css\" rel=\"stylesheet\" >");
		out.append("<link href=\"/adv/css/all.min.css\" rel=\"stylesheet\" >");
		out.append("<link href=\"/adv/css/login3.css\" rel=\"stylesheet\" >");
		
		out.append("<script language=\"javascript\" src=\"/adv/js/loginv3.js\"></script>");
		
		out.append("</head>");
		out.append("<body>");
		out.append("<div class=\"container-lg\">");
		out.append("<div class=\"row mt-5\">");
		out.append("<div class=\"col-lg-6 offset-lg-3 text-bg-light\">");
		
		out.append("<form class=\"loginview\">");
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-12 py-3 text-center text-uppercase fw-bold text-bg-primary\">");
		out.append("<i class=\"fa-solid fa-user\"></i> Đăng nhập");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-4 text-end\">");
		out.append("<label for=\"username\" class=\"form-label\"><i class=\"fa-solid fa-user\"></i> Tên đăng nhập</label>");
		out.append("</div>");
		out.append("<div class=\"col-md-6\">");
		out.append("<input type=\"text\" class=\"form-control\" id=\"username\" name=\"txtName\" onKeyup=\"checkValidLogin()\" aria-describedby=\"nameHelp\">");
		out.append("<div id=\"nameHelp\" class=\"form-text\">Bạn có thể nhập tên hộp thư cho tài khoản</div>");
		out.append("<div id=\"errName\"></div>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-4 text-end\">");
		out.append("<label for=\"userpass\" class=\"form-label\"><i class=\"fa-solid fa-key\"></i> Mật khẩu</label>");
		out.append("</div>");
		out.append("<div class=\"col-md-6\">");
		out.append("<input type=\"password\" class=\"form-control\" id=\"userpass\" name=\"txtPass\" onKeyup=\"checkValidLogin()\">");
		out.append("<div id=\"errPass\"></div>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-8 offset-md-4\">");
		out.append("<input type=\"checkbox\" class=\"form-check-input\" id=\"chkSave\">");
		out.append("<label class=\"form-check-label\" for=\"chkSave\">Lưu thông tin đăng nhập trên máy này?</label>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-12 text-center\">");
		out.append("<a href=\"#\" class=\"text-decoration-none\"><i class=\"fa-solid fa-lock-open\"></i> Quên mật khẩu</a> &nbsp;&nbsp;|&nbsp;&nbsp;");
		out.append("<a href=\"#\" class=\"text-decoration-none\"><i class=\"fa-solid fa-circle-question\"></i> Trợ giúp</a>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-12 text-center\">");
		out.append("<button type=\"button\" class=\"btn btn-primary\" onclick=\"login(this.form)\" ><i class=\"fa-solid fa-right-to-bracket\"></i> Đăng nhập</button>");
		out.append("<button type=\"button\" class=\"btn btn-secondary\"><i class=\"fa-solid fa-right-from-bracket\"></i> Thoát</button>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<div class=\"row mb-3\">");
		out.append("<div class=\"col-md-12 text-end\">");
		out.append("<a href=\"#\" class=\"text-decoration-none\"><i class=\"fa-solid fa-language\"></i> English</a>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("</form>");
		
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		
		out.append("<script src=\"/adv/js/bootstrap.bundle.min.js\" ></script>");
		out.append("</body>");
		out.append("</html>");
	}

	/**
	 * Thường được dùng để xử lý dữ liệu do doGet gửi cho<br>
	 * Được gọi thông qua sự kiệm form: method = "post"<br>
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Lấy thông tin đăng nhập 
		String name = request.getParameter("txtName");
		String pass = request.getParameter("txtPass");
		
		if(name!=null && pass !=null) {
			if(!name.equalsIgnoreCase("") && !pass.equalsIgnoreCase("")) {
				//Tham chiếu ngữ cảnh ứng dụng (là 1 không gian bộ nhớ tồn tại ở server, là duy nhất, lưu trữ vận hành cho website)
				ServletContext application = getServletConfig().getServletContext();
				
				//Tìm bộ quản lí kết nối trong ngữ cảnh
				ConnectionPool cp = (ConnectionPool) application.getAttribute("CPool");
				
				//Tạo đối tượng thực thi chức năng
				UserControl uc = new UserControl(cp);
				if(cp==null) {
					application.setAttribute("CPool", uc.getCP());
				}
				
				//Thực hiện đăng nhập
				UserObject user = uc.getUserObject(name, pass);
				
				//Trả về kết nối
				uc.releaseConnection();
				
				if(user!=null) {
					//Tham chiếu phiên làm việc mới ( là 1 không gian bộ nhớ đại diện cho phía client)
					HttpSession session = request.getSession(true);
					
					//Đưa thông tin đăng nhập vào phiên
					session.setAttribute("userLogined", user);
					
					//Chuyển về giao diện chính
					response.sendRedirect("/adv/view");
					
				}else {
					response.sendRedirect("/adv/user/login?err=notok");
				}
			}else {
				response.sendRedirect("/adv/user/login?err=value");
			}
			
		}else {
			response.sendRedirect("/adv/user/login?err=param");
		}
		
		
		
	}

}
