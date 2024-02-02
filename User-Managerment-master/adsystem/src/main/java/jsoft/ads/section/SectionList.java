package jsoft.ads.section;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Quartet;

import jsoft.ConnectionPool;
import jsoft.ads.user.UserControl;
import jsoft.library.Utilities;
import jsoft.library.Utilities_Date;
import jsoft.library.Utilities_Text;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

/**
 * Servlet implementation class SectionList
 */
@WebServlet("/section/list")
public class SectionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionList() {
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
		// Xác định tập ký tự làm việc
		request.setCharacterEncoding("UTF-8");

		response.setContentType(CONTENT_TYPE);

		// tạo đối tượng xuất nội dung
		PrintWriter out = response.getWriter();

		// tìm bộ quản lý kết nối
		ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
		SectionControl sc = new SectionControl(cp);

		if (cp == null) {
			getServletContext().setAttribute("CPool", cp);
		}
		
		Quartet<SectionObject,Integer,Byte,SECTION_SORT> infos = new Quartet<>(null, 1, (byte)20, SECTION_SORT.NAME);
		ArrayList<String> view = sc.viewSections(infos);
		
		sc.releaseConnection();
		RequestDispatcher header = request.getRequestDispatcher("/header?pos=sclist");

		if (header != null) {
			header.include(request, response);
		}

		out.append("<main id=\"main\" class=\"main\">");

		out.append("<div class=\"pagetitle d-flex\">");
		out.append("<h1>Danh sách danh mục</h1>");
		out.append("<nav class=\"ms-auto\">");
		out.append("<ol class=\"breadcrumb\">");
		out.append("<li class=\"breadcrumb-item\"><a href=\"/adv/view\"><i class=\"bi bi-house-fill\"></i></a></li>");
		out.append("<li class=\"breadcrumb-item\">Bài viết & Tin tức</li>");
		out.append("<li class=\"breadcrumb-item active\">Danh mục</li>");
		out.append("</ol>");
		out.append("</nav>");
		out.append("</div><!-- End Page Title -->");

		out.append("<section class=\"section\">");
		out.append("<div class=\"row\">");
		out.append("<div class=\"col-lg-12\">");
		out.append("<div class=\"card\">");
		out.append("<div class=\"card-body pt-2\">");

		// Thêm mới
		out.append(
				"<button type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#addSection\">");
		out.append("<i class=\"bi bi-folder-plus\"></i> Thêm mới");
		out.append("</button>");
//		out.append("");
//		out.append("<!-- Modal -->");
//		out.append(
//				"<div class=\"modal fade\" id=\"addSection\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
//		out.append("<div class=\"modal-dialog modal-lg\">");
//
//		out.append("<form method=\"post\" action=\"/adv/section/list\" class=\"needs-validation\" novalidate>");
//		out.append("<div class=\"modal-content\">");
//		out.append("<div class=\"modal-header text-bg-primary\">");
//		out.append(
//				"<h1 class=\"modal-title fs-5\" id=\"staticBackdropLabel\"><i class=\"bi bi-folder-plus\"></i>Thêm mới danh mục</h1>");
//		out.append(
//				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
//		out.append("</div>");
//		out.append("<div class=\"modal-body\">");
//		out.append("<div class=\"row\">");
//		out.append("<div class=\"col-md-6 mb-3\">");
//		out.append("<label for=\"sectionname\" class=\"form-label\">Section name</label>");
//		out.append("<input type=\"text\" class=\"form-control\" id=\"sectionname\" required name=\"txtSecName\">");
//		out.append("<div class=\"invalid-feedback\">Missing category name for article.</div>");
//		out.append("</div>");
//		out.append("</div>");
//		// Thêm mô tả cho danh mục
//		out.append("<div class=\"row align-items-center\">");
//		out.append("<div class=\"col-md-6 mb-3\">");
//		out.append("<label for=\"sectionnotes\" class=\"form-label\">Section notes</label>");
//		out.append("<textarea class=\"form-control\" name=\"txtSectionNotes\" id=\"txtSectionNotes\"></textarea>");
//		out.append("</div>");
//		out.append("</div>");

//		out.append("<script language=\"javascript\">");
//		out.append("ClassicEditor");
//		out.append(".create( document.querySelector( '#txtSectionNotes' ) )");
//		out.append(".catch( error => {");
//		out.append("console.error( error );");
//		out.append("} );");
//		out.append("</script>");

//		out.append("</div>");
//		out.append("<div class=\"modal-footer text-bt-light\">");
//		out.append(
//				"<button type=\"submit\" class=\"btn btn-primary\"><i class=\"bi bi-folder-plus\"></i>Thêm mới</button>");
//		out.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>");
//		out.append("</div>");
//		out.append("</div>");
//		out.append("</form>");
//		out.append("</div>");// modal-dialog modal-lg
//		out.append("</div>");// modal

		// Danh sách chuyên mục
		out.append(view.toString());
//		out.append("</div>");
		out.append("</div>");
		out.append("</div>");

		// Biểu đồ

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

		String name = request.getParameter("txtSecName");

		if (name != null && !name.equalsIgnoreCase("")) {

			String date = Utilities_Date.getDate();
			// Tìm thông tin đăng nhập
			UserObject user = (UserObject) request.getSession().getAttribute("userLogined");

			// Tạo đối tượng lưu trữ thông tin
			SectionObject nSection = new SectionObject();
			nSection.setSection_name(Utilities_Text.encode(name));
			nSection.setSection_created_date(date);
			nSection.setSection_created_author_id(user.getUser_id());

			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			SectionControl sc = new SectionControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", sc.getCP());
			}

			// Thêm mới
			boolean result = sc.addSection(nSection);

			// Trả về kết nối
			sc.releaseConnection();

			if (result) {
				response.sendRedirect("/adv/section/list");
			} else {
				response.sendRedirect("/adv/section/list?err=notok");
			}

		} else {
			response.sendRedirect("/adv/section/list?err=param");
		}
	}

}


