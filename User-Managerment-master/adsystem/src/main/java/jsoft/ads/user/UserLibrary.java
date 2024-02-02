package jsoft.ads.user;

import java.util.*;

import org.javatuples.*;

import jsoft.library.Utilities_Text;
import jsoft.objects.*;

public class UserLibrary {
	public static Pair<String, String> viewUser(
			Pair<ArrayList<UserObject>, Short> datas,
			Quartet<UserObject, Integer, Byte, Boolean> infos) {
		
		ArrayList<UserObject> items = datas.getValue0();
		int total = datas.getValue1();
		
		UserObject similar = infos.getValue0();
		int page = infos.getValue1();
		byte totalperpage = infos.getValue2();
		
		StringBuilder tmp = new StringBuilder();

		
		tmp.append("<table class=\"table table-striped table-bordered table-sm mt-2 mb-3\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\" class=\"text-center\">STT</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Ngày tạo</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Tài khoản</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Họ tên</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Hộp thư</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Lần đăng nhập</th>");
		tmp.append("<th scope=\"col\" colspan= \"3\" class=\"text-center\">Thực hiện</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">#</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");

		tmp.append("<tbody>");
		
		items.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle text-center\">" + (items.indexOf(item) + 1) + "</th>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getUser_created_date() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_name() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_fullname() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_email() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getUser_logined() + "</td>");
			tmp.append("<td><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&t=over\" class=\"btn btn-outline-primary btn-sm \" title=\"Xem chi tiết\" ><i class=\"bi bi-eye-fill\"></i></a></td>");
			tmp.append("<td><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&t=edit\" class=\"btn btn-warning btn-sm \" title=\"Sửa\" ><i class=\"bi bi-pencil-square\"></i></a></td>");
			tmp.append("<td><button class=\"btn btn-danger btn-sm \" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\" title=\"Xóa\" ><i class=\"bi bi-trash3\"></i></button></td>");
			tmp.append(UserLibrary.getDelModal(item, false));
			
			tmp.append("<th scope=\"row\" class=\"align-middle text-center\">" + item.getUser_id() + "</th>");
			tmp.append("</tr>");
		});

		tmp.append("</tbody>");
		tmp.append("</table>");
		
		String key = similar.getUser_name();
		String url = "/adv/user/list?";
		if(key!=null && !key.equalsIgnoreCase("")) {
			url += "keyword="+key+"&";
		}

		tmp.append(UserLibrary.viewPaging(url, total, page, totalperpage));
		

		return new Pair<>(tmp.toString(), UserLibrary.viewLoginChart(items));
	}
	
	public static Pair<String, String> viewTrashUser(ArrayList<UserObject> items) {
		StringBuilder tmp = new StringBuilder();

		
		tmp.append("<table class=\"table table-striped table-bordered table-sm mt-2\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th scope=\"col\" class=\"text-center\">STT</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Ngày xóa</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Tài khoản</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Họ tên</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Hộp thư</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">Người xóa</th>");
		tmp.append("<th scope=\"col\" colspan= \"2\" class=\"text-center\">Thực hiện</th>");
		tmp.append("<th scope=\"col\" class=\"text-center\">#</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");

		tmp.append("<tbody>");
		
		items.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<th scope=\"row\" class=\"align-middle text-center\">" + (items.indexOf(item) + 1) + "</th>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_last_modified() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_name() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_fullname() + "</td>");
			tmp.append("<td class=\"align-middle\">" + item.getUser_email() + "</td>");
			tmp.append("<td class=\"align-middle text-center\">" + item.getUser_trash_id() + "</td>");

			tmp.append("<td><a href=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=res\" class=\"btn btn-warning btn-sm \" title=\"Phục hồi\" ><i class=\"bi bi-reply\"></i></a></td>");
			tmp.append("<td><button class=\"btn btn-danger btn-sm \" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\" title=\"Xóa\" ><i class=\"bi bi-trash3\"></i></button></td>");
			tmp.append(UserLibrary.getDelModal(item, true));
			
			tmp.append("<th scope=\"row\" class=\"align-middle text-center\">" + item.getUser_id() + "</th>");
			tmp.append("</tr>");
		});

		tmp.append("</tbody>");
		tmp.append("</table>");
		

		return new Pair<>(tmp.toString(), UserLibrary.viewLoginChart(items));
	}
	
	private static StringBuilder getDelModal(UserObject item, boolean isAbsolute) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"delLabel"+item.getUser_id()+"\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header text-bg-danger\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"delLabel"+item.getUser_id()+"\"><i class=\"bi bi-exclamation-triangle\"></i> Xóa người sử dụng</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body text-center\">");
		tmp.append("<h3 class=\"text-danger\">Bạn có chắc chắn muốn xóa?</h3>");
		tmp.append("<p class=\"my-3\">"+item.getUser_fullname()+" ("+item.getUser_name()+")</p>");
		
		if(isAbsolute) {
			tmp.append("<h4 class=\"text-danger\">Người sử dụng sẽ bị xóa khỏi hệ thống, không thể phục hồi lại.</h4>");
		}
		
		
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer text-bg-light\">");
		if(isAbsolute) {
			tmp.append("<form method=\"post\" action=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=delabs\"> ");
		}else {
			tmp.append("<form method=\"post\" action=\"/adv/user/profiles?id="+item.getUser_id()+"&dr=del\"> ");
		}
		tmp.append("<button type=\"submit\" class=\"btn btn-danger\"><i class=\"bi bi-exclamation-triangle\"></i> Xóa</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"bi bi-x-lg\"></i> Hủy</button>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	private static String viewLoginChart(ArrayList<UserObject> items) {
		
		StringBuilder values = new StringBuilder();
		StringBuilder names = new StringBuilder();
		items.forEach(item -> {
			values.append(item.getUser_logined());
			names.append("'"+item.getUser_name()+" ("+Utilities_Text.decode(item.getUser_fullname())+")'");
			
			if(items.indexOf(item)<(items.size()-1)) {
				values.append(",");
				names.append(",");
			}
			
		});
		
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ đăng nhập</h5>");
		tmp.append("<div id=\"barChart\"></div>");
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("name: 'Số lần đăng nhập',");
		tmp.append("data: ["+values+"]");
		tmp.append("}],");
		tmp.append("chart: {type: 'bar', height: 500, fontFamily: 'Tahoma, sans-serif'},");
		tmp.append("plotOptions: {bar: {borderRadius: 4, horizontal: true,}},");
		tmp.append("dataLabels: {enabled: false},");
		tmp.append("");
		tmp.append("xaxis: {");
		tmp.append("categories: ["+names+"],");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '15px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 600,");
		tmp.append("cssClass: 'apexcharts-xaxis-label',");
		tmp.append("},");
		tmp.append("}");
		tmp.append("},");
		tmp.append("");
		tmp.append("yaxis: {");
		tmp.append("show: true,");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("align: 'right',");
		tmp.append("minWidth: 0,");
		tmp.append("maxWidth: 350,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '14px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 400,");
		tmp.append("cssClass: 'apexcharts-yaxis-label',");
		tmp.append("},");
		tmp.append("},");
		tmp.append("}");
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("</div>");
		tmp.append("</div>");	
		
		return tmp.toString();
	}
	
	private static StringBuilder viewPaging(String url, int total, int page, byte totalperpage) {
		StringBuilder tmp = new StringBuilder();
		
		//Tính số trang
		short countPages = (short) (total/totalperpage) ;
		if(total%totalperpage!=0) {
			countPages++;
		}
		
		//Xác định sự hợp lệ của trang hiện tại
		if(page<=0 || page > countPages) {
			page=1;
		}
		
		
		tmp.append("<nav aria-label=\"...\">");
		tmp.append("<ul class=\"pagination justify-content-center\">");
		
		short pre = (short)( ((page - 1) > 0) ? (page-1) : 1 ); 
		String dis="";
		if(page==1) dis= "disabled";
		tmp.append("<li class=\"page-item\"><a class=\"page-link "+dis+"\" href=\""+url+"p="+pre+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>");
		//tmp.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"/?p=1\">1</a></li>");
		

		StringBuilder left = new StringBuilder();
		for(int i=page-1; i>0;i--) {
			if((page-i)>2) {
				break;
			}
			left.insert(0, "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"p="+i+"\">"+i+"</a></li>");
		}
		if(page>=4) {
			left.insert(0,"<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>");
		}
		
		tmp.append(left);
		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+page+"</a></li>");
		
		StringBuilder right = new StringBuilder();
		for(int i=page+1; i<=countPages;i++) {
			if((i-page)>2) {
				break;
			}
			right.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"p="+i+"\">"+i+"</a></li>");
		}
		if((countPages-page)>4) {
			right.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>");
		}
		
		tmp.append(right);
		
		short next = (short)( ((page + 1) <= countPages) ? (page+1) : countPages ); 
		dis = "";
		if(page==countPages) dis="disabled";
		tmp.append("<li class=\"page-item\"><a class=\"page-link "+dis+"\" href=\""+url+"p="+next+"\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
		tmp.append("</ul>");
		tmp.append("</nav>");

		
		return tmp;
	}

//	public static ArrayList<String> viewUsers(Pair<ArrayList<UserObject>, Integer> datas) {
//		// Bóc tách các dữ liệu để xử lý
//		ArrayList<UserObject> items = datas.getValue0();
//		int total = datas.getValue1();
//
//		// Cấu trúc trình bày danh sách và phân trang
//		ArrayList<String> tmp = new ArrayList<>();
//
//		// Danh sách
//		StringBuilder list = new StringBuilder();
//		list.append("<div class=\"card\">");
//		list.append("<div class=\"card-body\">");
//		list.append("<h5 class=\"card-title\">Table with stripped rows</h5>");
//		list.append("");
//		list.append("<table class=\"table table-striped\">");
//		list.append("<thead>");
//		list.append("<tr>");
//		list.append("<th scope=\"col\">STT</th>");
//		list.append("<th scope=\"col\">Tài khoản</th>");
//		list.append("<th scope=\"col\">Họ tên</th>");
//		list.append("<th scope=\"col\">Hộp thư</th>");
//		list.append("<th scope=\"col\">Điện thoại</th>");
//		list.append("<th scope=\"col\">Ngày đăng nhập</th>");
//		list.append("<th scope=\"col\" colspan= \"3\">Thực hiện</th>");
//		list.append("<th scope=\"col\">#</th>");
//		list.append("</tr>");
//		list.append("</thead>\n");
//
//		list.append("<tbody>");
//
//		items.forEach(item -> {
//			list.append("<tr>");
//			list.append("<th scope=\"row\">" + (items.indexOf(item) + 1) + "</th>");
//			list.append("<td>" + item.getUser_name() + "</td>");
//			list.append("<td>" + item.getUser_fullname() + "</td>");
//			list.append("<td>" + item.getUser_email() + "</td>");
//			list.append("<td>" + item.getUser_homephone() + "</td>");
//			list.append("<td>" + item.getUser_last_logined() + "</td>");
//			list.append("<td>Chi tiết</td>");
//			list.append("<td>Sửa</td>");
//			list.append("<td>Xóa</td>");
//			list.append("<th scope=\"row\">" + item.getUser_id() + "</th>");
//			list.append("</tr>\n");
//		});
//		list.append("</tbody>");
//		list.append("</table>");
//		list.append("</div>");
//		list.append("</div>");
//
//		// Phân trang
//
//		tmp.add(list.toString());
//		
//		return tmp;
//	}
}
