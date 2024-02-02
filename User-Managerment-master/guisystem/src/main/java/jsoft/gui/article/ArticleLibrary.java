package jsoft.gui.article;

import jsoft.library.Utilities;
import jsoft.library.Utilities_Text;
import jsoft.objects.*;
import java.util.*;
import org.javatuples.*;

public class ArticleLibrary {
	
	public static ArrayList<String> viewHomePage(ArrayList<ArrayList<ArticleOject>> allDatas){
		ArrayList<String> viewAll = new ArrayList<>();
		
		//Grid
		ArrayList<ArticleOject> newest_items = allDatas.get(0);
		ArrayList<ArticleOject> trend_items = allDatas.get(1);
		viewAll.add(ArticleLibrary.viewGrid(newest_items, trend_items));
		
		//ProjectImage
		ArrayList<ArticleOject> images_items = allDatas.get(2);
		viewAll.add(ArticleLibrary.viewSections(images_items, SECTION.PROJECT_IMAGE));
		
		//Service
		ArrayList<ArticleOject> service_items = allDatas.get(3);
		viewAll.add(ArticleLibrary.viewSections(service_items, SECTION.SERVICE));
		
		return viewAll;
	}
	
	
	

	public static String viewGrid(ArrayList<ArticleOject> newest_items, ArrayList<ArticleOject> trend_items)
	{
		StringBuilder tmp = new StringBuilder();
		
		ArticleOject item = null;
		if(newest_items.size()>0) {
			item = newest_items.get(0);
		}
		
		tmp.append("<div class=\"col-lg-4\">");
		tmp.append("<div class=\"post-entry-1 lg\">");
		if(item!=null) {
			tmp.append("<a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\"><img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h2><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h2>");
			tmp.append("<p class=\"mb-4 d-block\">"+item.getArticle_summary()+"</p>");
			
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"/home/img/person-1.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
		}
		tmp.append("</div>");
		tmp.append("</div>");
		
		//3 cột
		tmp.append("<div class=\"col-lg-8\">");
		tmp.append("<div class=\"row g-5\">");
		
		for(int i=1; i<newest_items.size();i++) {
			if(i%2==1) {
				tmp.append("<div class=\"col-lg-4 border-start custom-border\">");
			}
			
			item = newest_items.get(i);
			
			tmp.append("<div class=\"post-entry-1\">");
			tmp.append("<a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\"><img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h2><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h2>");
			tmp.append("</div>");
			
			if(i%2==0 || i==newest_items.size()-1) {
				tmp.append("</div>");
			}
			
		}
		
		
		
		tmp.append("<!-- Trending Section -->");
		tmp.append("<div class=\"col-lg-4\">");
		
		tmp.append("<div class=\"trending\">");
		tmp.append("<h3>Trending</h3>");
		tmp.append("<ul class=\"trending-post\">");
		trend_items.forEach(ti -> {
			tmp.append("<li>");
			tmp.append("<a href=\"/home/tin-tuc/?id="+ti.getArticle_id()+"\">");
			tmp.append("<span class=\"number\">"+(trend_items.indexOf(ti)+1)+"</span>");
			tmp.append("<h3>"+ti.getArticle_title()+"</h3>");
			tmp.append("<span class=\"author\">"+ti.getCategory_name()+"</span>");
			tmp.append("</a>");
			tmp.append("</li>");
		});
		
		tmp.append("</ul>");
		tmp.append("</div>");
		
		tmp.append("</div> <!-- End Trending Section -->");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp.toString();
	}
	
	
	public static String viewSections(ArrayList<ArticleOject> items, SECTION sec) {
		StringBuilder tmp = new StringBuilder();
		
		ArticleOject item, item1 = null, item2 = null, item3 = null, item4 = null;
		for(int i=0; i<items.size() && i<4; i++) {
			switch(i) {
			case 0: item1 = items.get(0); break;
			case 1: item2 = items.get(1); break;
			case 2: item3 = items.get(2); break;
			case 3: item4 = items.get(3); break;
			}
		}
		
		if(sec==SECTION.PROJECT_IMAGE) {
			tmp.append("<div class=\"col-md-9\">");
		}else
		{
			tmp.append("<div class=\"col-md-9 order-md-2\">");
		}	
		
		tmp.append("<div class=\"d-lg-flex post-entry-2\">");
		if(item1!=null) {
			tmp.append("<a href=\"/home/tin-tuc/?id="+item1.getArticle_id()+"\" class=\"me-4 thumbnail mb-4 mb-lg-0 d-inline-block\">");
			tmp.append("<img src=\""+item1.getArticle_image()+"\" alt=\"\" class=\"img-fluid\">");
			tmp.append("</a>");
			tmp.append("<div>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item1.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item1.getArticle_created_date()+"</span></div>");
			tmp.append("<h3><a href=\"/home/tin-tuc/?id="+item1.getArticle_id()+"\">"+item1.getArticle_title()+"</a></h3>");
			tmp.append("<p>"+item1.getArticle_summary()+"</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"/home/img/person-2.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item1.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
		}
		
		tmp.append("</div>");//post-entry-2

		tmp.append("<div class=\"row\">");
		tmp.append("<div class=\"col-lg-4\">");
		tmp.append("<div class=\"post-entry-1 border-bottom\">");
		
		if(item2!=null) {
			tmp.append("<a href=\"/home/tin-tuc/?id="+item2.getArticle_id()+"\"><img src=\""+item2.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item2.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item2.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"#\">"+item2.getArticle_title()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+item2.getArticle_author_name()+"</span>");
			tmp.append("<p class=\"mb-4 d-block\">"+item2.getArticle_summary()+"</p>");
		}
		tmp.append("</div>");

		tmp.append("<div class=\"post-entry-1\">");
		if(item3!=null) {
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item3.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item3.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+item3.getArticle_id()+"\">"+item3.getArticle_title()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+item3.getArticle_author_name()+"</span>");
			tmp.append("</div>");
		}

		tmp.append("</div>"); // col-lg-4
		
		tmp.append("<div class=\"col-lg-8\">");
		tmp.append("<div class=\"post-entry-1\">");
		if(item4!=null) {
			tmp.append("<a href=\"/home/tin-tuc/?id="+item4.getArticle_id()+"\"><img src=\""+item4.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item4.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item4.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+item4.getArticle_id()+"\">"+item4.getArticle_title()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+item4.getArticle_author_name()+"</span>");
			tmp.append("<p class=\"mb-4 d-block\">"+item4.getArticle_summary()+"</p>");
		}
		
		tmp.append("</div>");
		
		tmp.append("</div>"); // col-lg-8
		tmp.append("</div>");
		tmp.append("</div>");

		if(sec==SECTION.PROJECT_IMAGE) {
			tmp.append("<div class=\"col-md-3\">");
		}else {
			tmp.append("<div class=\"col-md-3 order-md-2\">");
		}
		for(int i=4;i<items.size(); i++) {
			item = items.get(i);
			tmp.append("<div class=\"post-entry-1 border-bottom\">");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
			tmp.append("</div>");
		}
		tmp.append("</div>"); // col-md-3
		
		return tmp.toString();
	}
	
	
	private static String viewSectionPaging(
			ArrayList<ArticleOject> items, 
			ArrayList<Pair<CategoryObject, Integer>> cates_totals, 
			ArticleOject similar, 
			Triplet<Integer, Short, Byte> pagings) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"col-md-9\" data-aos=\"fade-up\">");
		tmp.append(ArticleLibrary.viewCateOptions(cates_totals, similar));
		
		items.forEach(item -> {
			tmp.append("<div class=\"d-md-flex post-entry-2 half\">");
			tmp.append("<a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\" class=\"me-4 thumbnail\">");
			tmp.append("<img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\">");
			tmp.append("</a>");
			tmp.append("<div>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h3><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h3>");
			tmp.append("<p>"+item.getArticle_summary()+"</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"assets/img/person-2.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
		});
		
		tmp.append(ArticleLibrary.viewPaging(ArticleLibrary.createURL(similar), pagings));
		tmp.append("</div>"); // col-md-9
		
		return tmp.toString();
	}
	
	private static String createURL(ArticleOject similar) {
		StringBuilder tmp = new StringBuilder("/home/tin-tuc/?");
		if(similar!=null) {
			short sid = similar.getArticle_section_id();
			if(sid>0) {
				tmp.append("s=").append(sid);
			}
			
			short cid = similar.getArticle_category_id();
			if(cid>0) {
				if(sid>0) {
					tmp.append("&");
				}
				tmp.append("c=").append(cid);
			}
		}
		
		
		return tmp.toString();
	}
	
	private static StringBuilder viewCateOptions(
			ArrayList<Pair<CategoryObject, Integer>> cates_totals,
			ArticleOject similar)
	{
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"row mb-4\">");
		tmp.append("<div class=\"col-md-6\">");
		tmp.append("<form>");
		tmp.append("<div class=\"input-group\">");
		tmp.append("<span class=\"input-group-text fw-bolder fst-5\">Thể loại: </span>");
		tmp.append("<select class=\"form-select\" name=\"c\" id=\"slcCates\" onchange=\"refreshCate(this.form)\" >");
		tmp.append("<option value=0 >---</option>");
		cates_totals.forEach(ct -> {
			if(ct.getValue0().getCategory_id()==similar.getArticle_category_id()) {
				tmp.append("<option value=\""+ct.getValue0().getCategory_id()+"\" selected >");
			}else {
				tmp.append("<option value=\""+ct.getValue0().getCategory_id()+"\">");
			}
			tmp.append(ct.getValue0().getCategory_name());
			tmp.append("</option>");
		});
		tmp.append("</select>");
		tmp.append("</div>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<script language=\"javascript\" >");
		tmp.append("function refreshCate(fn){");
		tmp.append("fn.method='get'; ");
		tmp.append("fn.action='/home/tin-tuc/'; ");
		tmp.append("fn.submit(); ");
		tmp.append("}");
		tmp.append("</script>");
		
		return tmp;
	}
	
	private static StringBuilder viewPaging(String url, Triplet<Integer, Short, Byte> pagings) {
		StringBuilder tmp = new StringBuilder();
		
		int total=pagings.getValue0(); 
		short page=pagings.getValue1(); 
		byte totalperpage=pagings.getValue2();
		
		short countPages = (short)(total/totalperpage);
		if(total%totalperpage > 0) {
			countPages++;
		}
		
		//Xác định sự hợp lệ của trang hiện tại
		if(page<=0 || page > countPages) {
			page=1;
		}
		
		
		
		tmp.append("<div class=\"text-start py-4\">");
		tmp.append("<div class=\"custom-pagination\">");
		short pre = (short)( ((page - 1) > 0) ? (page-1) : 1 ); 
		String dis="";
		if(page==1) dis= "disabled";
		tmp.append("<a class=\"page-link "+dis+"\" href=\""+url+"&p="+pre+"\"><span aria-hidden=\"true\">&laquo;</span></a>");

		StringBuilder left = new StringBuilder();
		for(int i=page-1; i>0;i--) {
			if((page-i)>2) {
				break;
			}
			left.insert(0, "<a class=\"page-link\" href=\""+url+"&p="+i+"\">"+i+"</a>");
		}
		if(page>=4) {
			left.insert(0,"<a class=\"page-link disabled\" href=\"#\">...</a>");
		}
		
		tmp.append(left);
		tmp.append("<a class=\"page-link\" href=\"#\">"+page+"</a>");
		
		StringBuilder right = new StringBuilder();
		for(int i=page+1; i<=countPages;i++) {
			if((i-page)>2) {
				break;
			}
			right.append("<a class=\"page-link\" href=\""+url+"&p="+i+"\">"+i+"</a>");
		}
		if((countPages-page)>4) {
			right.append("<a class=\"page-link disabled\" href=\"#\">...</a>");
		}
		
		tmp.append(right);
		
		short next = (short)( ((page + 1) <= countPages) ? (page+1) : countPages ); 
		dis = "";
		if(page==countPages) dis="disabled";
		tmp.append("<a class=\"page-link "+dis+"\" href=\""+url+"&p="+next+"\"><span aria-hidden=\"true\">&raquo;</span></a>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	
	private static String viewList(ArrayList<ArticleOject> list) {
		StringBuilder tmp = new StringBuilder();
		
		list.forEach(li -> {
			tmp.append("<div class=\"post-entry-1 border-bottom\">");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+li.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+li.getArticle_created_date()+"</span></div>");
			tmp.append("<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+li.getArticle_id()+"\">"+li.getArticle_title()+"</a></h2>");
			tmp.append("<span class=\"author mb-3 d-block\">"+li.getArticle_author_name()+"</span>");
			tmp.append("</div>");
		});
		
		return tmp.toString();
	}
	
	private static String viewTabList(
			ArrayList<ArticleOject> trending, 
			ArrayList<ArticleOject> latest) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"aside-block\">");
		
		tmp.append("<ul class=\"nav nav-pills custom-tab-nav mb-4\" id=\"pills-tab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link active\" id=\"pills-trending-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-trending\" type=\"button\" role=\"tab\" aria-controls=\"pills-trending\" aria-selected=\"true\">Xu hướng</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link\" id=\"pills-new-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-new\" type=\"button\" role=\"tab\" aria-controls=\"pills-new\" aria-selected=\"false\">Mới nhất</button>");
		tmp.append("</li>");
		
		tmp.append("</ul>");
		
		tmp.append("<div class=\"tab-content\" id=\"pills-tabContent\">");
		
		tmp.append("<div class=\"tab-pane fade show active\" id=\"pills-trending\" role=\"tabpanel\" aria-labelledby=\"pills-trending-tab\">");
		tmp.append(ArticleLibrary.viewList(trending));
		tmp.append("</div>");
		
		tmp.append("<div class=\"tab-pane fade\" id=\"pills-new\" role=\"tabpanel\" aria-labelledby=\"pills-new-tab\">");
		tmp.append(ArticleLibrary.viewList(latest));
		tmp.append("</div>");
		
		tmp.append("</div>");//pills-tabContent
		tmp.append("</div>");//aside-block
		
		return tmp.toString();
	}
	
	private static String viewRightSide(
			ArrayList<ArticleOject> trending, 
			ArrayList<ArticleOject> latest, 
			ArrayList<Pair<CategoryObject, Integer>> cates_totals,
			HashMap<String, Integer> tags, 
			ArticleOject similar) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div class=\"col-md-3\">");
		tmp.append(ArticleLibrary.viewTabList(trending, latest));
		
		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Thể loại</h3>");
		tmp.append("<ul class=\"aside-links list-unstyled\">");
		cates_totals.forEach(ct ->{
			if(ct.getValue0().getCategory_id()==similar.getArticle_category_id()) {
				tmp.append("<li class=\"fw-bolder\">");
			}else {
				tmp.append("<li>");
			}
			tmp.append("<a href=\"/home/tin-tuc/?c="+ct.getValue0().getCategory_id()+"\"><i class=\"bi bi-chevron-right\"></i>");
			tmp.append(ct.getValue0().getCategory_name());
			tmp.append(" ("+ct.getValue1()+")");
			tmp.append("</a></li>");
		});
		tmp.append("</ul>");
		tmp.append("</div>");
		
		//Tags
		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Từ khóa</h3>");
		tmp.append("<ul class=\"aside-tags list-unstyled\">");
		tags.forEach((tag, count) -> {
			tmp.append("<li><a href=\"#\">");
			tmp.append(tag);
			tmp.append(" ("+count+")");
			tmp.append("</a></li>");
		});
		tmp.append("</ul>");
		tmp.append("</div>");
		
		
		tmp.append("</div>");
		
		return tmp.toString();
	}
	
	public static String viewInnerPage(
			ArrayList<ArrayList<ArticleOject>> a_items, 
			ArrayList<Pair<CategoryObject, Integer>> cates_totals,
			HashMap<String, Integer> tags, 
			ArticleOject similar, 
			Triplet<Integer, Short, Byte> pagings){
		String viewAll = "";
		
		//Danh sách bài viết phân trang
		ArrayList<ArticleOject> paging_items = a_items.get(0);
		//Danh sách bài viết trending
		ArrayList<ArticleOject> trend_items = a_items.get(1);
		//Danh sách bài viết mới nhất
		ArrayList<ArticleOject> newest_items = a_items.get(2);
		
		viewAll += ArticleLibrary.viewSectionPaging(paging_items, cates_totals, similar, pagings);
		viewAll += ArticleLibrary.viewRightSide(trend_items, newest_items, cates_totals, tags, similar);
		
		return viewAll;
	}
	
	private static String viewDetail(ArticleOject item) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"col-md-9 post-content\" data-aos=\"fade-up\">");
		tmp.append("<div class=\"single-post\">");
		if(item!=null) {
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
			tmp.append("<h1 class=\"mb-5\">"+item.getArticle_title()+"</h1>");
			tmp.append("<p><span class=\"firstcharacter\">"+item.getArticle_summary().charAt(0)+"</span>"+item.getArticle_summary().substring(1)+"</p>");
			
			tmp.append("<figure class=\"my-5\">");
			tmp.append("<img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\">");
			tmp.append("<figcaption>"+item.getArticle_title()+"</figcaption>");
			tmp.append("</figure>");
			
			tmp.append("<p>"+Utilities_Text.decode(item.getArticle_content())+"</p>");
		}

		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp.toString();
	}
	
	public static String viewDetailPage(
			Optional<ArticleOject> item, 
			ArrayList<ArrayList<ArticleOject>> a_items, 
			ArrayList<Pair<CategoryObject, Integer>> cates_totals,
			HashMap<String, Integer> tags, 
			ArticleOject similar){
		String viewAll = "";
		
		//Danh sách bài viết trending
		ArrayList<ArticleOject> trend_items = a_items.get(1);
		//Danh sách bài viết mới nhất
		ArrayList<ArticleOject> newest_items = a_items.get(2);
		
		viewAll += ArticleLibrary.viewDetail(item.get());
		viewAll += ArticleLibrary.viewRightSide(trend_items, newest_items, cates_totals, tags, similar);
		
		return viewAll;
	}
	
	
}

enum SECTION{
	PROJECT_IMAGE,
	SERVICE
}
