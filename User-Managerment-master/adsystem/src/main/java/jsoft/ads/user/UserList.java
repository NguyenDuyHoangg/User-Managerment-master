package jsoft.ads.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.*;

import jsoft.*;
import jsoft.library.ORDER;
import jsoft.objects.*;
import jsoft.library.*;

/**
 * Servlet implementation class view
 */
@WebServlet("/user/list")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// tìm thông tin đăng nhập
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user == null) {
			response.sendRedirect("/adv/user/login");

		} else {
			view(request, response, user);
		}

	}

	protected void view(HttpServletRequest request, HttpServletResponse response, UserObject user)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Xác định tập ký tự làm việc
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType(CONTENT_TYPE);

		// tạo đối tượng xuất nội dung
		PrintWriter out = response.getWriter();

		// tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		UserControl uc = new UserControl(cp);
		if (cp == null) {
			getServletContext().setAttribute("CPool", cp);
		}

		String trash = request.getParameter("trash"); // Tham số xác định xem thùng rác
		boolean isTrash = (trash != null) ? true : false;
		
		//Tìm từ khóa nếu có
		String key = request.getParameter("keyword");
		String safeKey = (key!=null && !key.equalsIgnoreCase("")) ? key.trim() : "";
		
		UserObject similar = new UserObject();
		similar.setUser_permission(user.getUser_permission());
		similar.setUser_name(Utilities_Text.encode(safeKey));
		
		int p = Utilities.getPageParam(request);

		Quartet<UserObject, Integer, Byte, Boolean> infos = new Quartet<>(similar, p, (byte) 2, isTrash);

		Pair<USER_SORT, ORDER> so = new Pair<>(USER_SORT.NAME, ORDER.ASC);
		Pair<String, String> view = uc.viewUsers(infos, so);

		uc.releaseConnection();

		RequestDispatcher header = request.getRequestDispatcher("/header?pos=urlist");

		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách người sử dụng</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Người sử dụng</li>");
		if(!isTrash) {
			out.append("<li class=\"breadcrumb-item active\">Danh sách</li>");
		}else {
			out.append("<li class=\"breadcrumb-item active\">Thùng rác</li>");
		}
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-2\">");

		if (!isTrash) {
			out.append(
					"<button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#addUser\">");
			out.append("<i class=\"bi bi-person-add\"></i> Thêm mới");
			out.append("</button>");
			out.append("");
			out.append("<!-- Modal -->");
			out.append(
					"<div class=\"modal fade\" id=\"addUser\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
			out.append("<div class=\"modal-dialog modal-lg\">");

			out.append("<form method=\"post\" action=\"/adv/user/list\" class=\"needs-validation\" novalidate>");
			out.append("<div class=\"modal-content\">");
			out.append("<div class=\"modal-header text-bg-primary\">");
			out.append(
					"<h1 class=\"modal-title fs-5\" id=\"staticBackdropLabel\"><i class=\"bi bi-person-add\"></i>Thêm mới người sử dụng</h1>");
			out.append(
					"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
			out.append("</div>");
			out.append("<div class=\"modal-body\">");
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-md-6 mb-3\">");
			out.append("<label for=\"username\" class=\"form-label\">Tên đăng nhập</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"username\" required name=\"txtName\">");
			out.append("<div class=\"invalid-feedback\">Thiếu tên đăng nhập cho tài khoản.</div>");
			out.append("</div>");
			out.append("<div class=\"mb-3 col-md-6\">");
			out.append("<label for=\"fullname\" class=\"form-label\">Tên đầy đủ</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"fullname\" name =\"txtFullname\">");
			out.append("</div>");
			out.append("</div>");
			out.append("");
			out.append("");
			out.append("<div class=\"row\">");
			out.append("<div class=\"col-md-6 mb-3\">");
			out.append("<label for=\"userpass\" class=\"form-label\">Mật khẩu</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass\" required name=\"txtPass1\">");
			out.append("<div class=\"invalid-feedback\">Thiếu mật khẩu cho tài khoản.</div>");
			out.append("</div>");
			out.append("<div class=\"mb-3 col-md-6\">");
			out.append("<label for=\"userpass2\" class=\"form-label\">Nhập lại</label>");
			out.append("<input type=\"password\" class=\"form-control\" id=\"userpass2\" required name=\"txtPass2\">");
			out.append("<div class=\"invalid-feedback\">Thiếu xác nhận lại mật khẩu cho tài khoản.</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("");
			out.append("<div class=\"row\">");
			out.append("<div class=\"mb-3 col-md-6\">");
			out.append("<label for=\"email\" class=\"form-label\">Hộp thư</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"email\" required name=\"txtEmail\">");
			out.append("<div class=\"invalid-feedback\">Thiếu hộp thư cho tài khoản.</div>");
			out.append("</div>");
			out.append("<div class=\"col-md-6 mb-3\">");
			out.append("<label for=\"phone\" class=\"form-label\">Điện thoại</label>");
			out.append("<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"txtPhone\">");
			out.append("</div>	");
			out.append("</div>");
			out.append("");
			out.append("<div class=\"row\">");
			out.append("<div class=\"mb-3 col-md-6\">");
			out.append("<label for=\"permis\" class=\"form-label\">Quyền thực thi</label>");
			out.append("<select id=\"permis\" class=\"form-select\" name=\"slcPermis\" required >");
			out.append("<option value=\"\">-----</option>");
			out.append("<option value=\"1\">Thành viên</option>");
			out.append("<option value=\"2\">Tác giả</option>");
			out.append("<option value=\"3\">Quản lý</option>");
			out.append("<option value=\"4\">Quản trị</option>");
			out.append("<option value=\"5\">Quản trị cấp cao</option>");
			out.append("</select>");
			out.append("<div class=\"invalid-feedback\">Chọn quyền cho tài khoản.</div>");
			out.append("</div>");
			out.append("</div>");
			out.append("");
			out.append("");
			out.append("");
			out.append("</div>");
			out.append("<div class=\"modal-footer text-bt-light\">");
			out.append(
					"<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-person-add\"></i>Thêm mới</button>");
			out.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>");
			out.append("</div>");
			out.append("</div>");
			out.append("</form>");
			out.append("</div>");// modal-dialog modal-lg
			out.append("</div>");// modal
		}

		out.append(view.getValue0());
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		
		//Biểu đồ
		out.append(view.getValue1());

		out.append("</div>");// col-lg-12
		out.append("</div>");// row
		out.append("</section>");

		out.append("</main><!-- End #main -->");

		RequestDispatcher footer = request.getRequestDispatcher("/footer");
		if (footer != null) {
			footer.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Thiết lập tập ký tự cần lấy
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("txtName");
		String pass1 = request.getParameter("txtPass1");
		String pass2 = request.getParameter("txtPass2");
		String email = request.getParameter("txtEmail");
		byte permis = Utilities.getByteParam(request, "slcPermis");

		if (name != null && !name.equalsIgnoreCase("") && Utilities_Text.checkPass(pass1, pass2) && email != null
				&& !email.equalsIgnoreCase("") && permis > 0) {

			// Lấy thêm thông tin khác
			String fullname = request.getParameter("txtFullname");
			String phone = request.getParameter("txtPhone");
			String date = Utilities_Date.getDate();
			// Tìm thông tin đăng nhập
			UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

			// Tạo đối tượng lưu trữ thông tin
			UserObject nUser = new UserObject();
			nUser.setUser_name(name);
			nUser.setUser_pass(pass1);
			nUser.setUser_email(email);
			nUser.setUser_fullname(Utilities_Text.encode(fullname));
			nUser.setUser_created_date(date);
			nUser.setUser_parent_id(user.getUser_id());
			nUser.setUser_permission(permis);
			nUser.setUser_homephone(phone);

			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			UserControl uc = new UserControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			// Thêm mới
			boolean result = uc.addUser(nUser);

			// Trả về kết nối
			uc.releaseConnection();

			if (result) {
				response.sendRedirect("/adv/user/list");
			} else {
				response.sendRedirect("/adv/user/list?err=notok");
			}

		} else {
			response.sendRedirect("/adv/user/list?err=param");
		}

	}

}
