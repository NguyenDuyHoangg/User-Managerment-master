package jsoft.ads.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.ORDER;
import jsoft.objects.ArticleOject;
import jsoft.objects.CategoryObject;
import jsoft.objects.SectionObject;

public class ArticleImpl extends BasicImpl implements Article{

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addArticle(ArticleOject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(); 
		sql.append("INSERT INTO tblarticle( ");
		sql.append("article_title, article_summary, article_content, article_created_date, ");
		sql.append("article_last_modified, article_image, article_category_id, article_section_id, ");
		sql.append("article_visited, article_author_name, article_enable, article_url_link, ");
		sql.append("article_tag, article_title_en, article_summary_en, article_content_en, ");
		sql.append("article_tag_en, article_fee, article_isfee, article_delete, ");
		sql.append("article_deleted_date, article_restored_date, article_modified_author_name, article_author_permission, ");
		sql.append("article_source, article_language, article_focus, article_type, article_forhome");
		sql.append(") ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		// biên dịch 
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_created_date());
			pre.setString(5, item.getArticle_last_modified());
			pre.setString(6, item.getArticle_image());
			pre.setShort(7, item.getArticle_category_id());
			pre.setShort(8, item.getArticle_section_id());
			pre.setShort(9, item.getArticle_visited());
			pre.setString(10, item.getArticle_author_name());
			pre.setBoolean(11, item.isArticle_enable());
			pre.setString(12, item.getArticle_url_link());
			pre.setString(13, item.getArticle_tag());
			pre.setString(14, item.getArticle_title_en());
			pre.setString(15, item.getArticle_summary_en());
			pre.setString(16, item.getArticle_content_en());
			pre.setString(17, item.getArticle_tag_en());
			pre.setInt(18, item.getArticle_fee());
			pre.setBoolean(19, item.isArticle_isfee());
			pre.setBoolean(20, item.isArticle_delete());
			pre.setString(21, item.getArticle_deleted_date());
			pre.setString(22, item.getArticle_restored_date());
			pre.setString(23, item.getArticle_modified_author_name());
			pre.setByte(24, item.getArticle_author_permission());
			pre.setString(25, item.getArticle_source());
			pre.setByte(26, item.getArticle_language());
			pre.setBoolean(27, item.isArticle_focus());
			pre.setByte(28, item.getArticle_type());
			pre.setBoolean(29, item.isArticle_forhome());
			
			return this.add(pre); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		return false;
	}

	@Override
	public boolean editArticle(ArticleOject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(); 
		sql.append("UPDATE tblarticle SET ");
		sql.append("article_title=?, article_summary=?, article_content=?, article_last_modified=?, ");
		sql.append("article_image=?, article_category_id=?, article_visited=?, article_author_name=?, ");
		sql.append("article_enable=?, article_url_link=?, article_tag=?, article_title_en=?, article_summary_en=?, ");
		sql.append("article_content_en=?, article_tag_en=?, article_fee=?, article_isfee=?, ");
		sql.append("article_deleted_date=?, article_restored_date=?, article_modified_author_name=?, article_author_permission=?, ");
		sql.append("article_source=?, article_language=?, article_focus=?, article_type=?, ");
		sql.append("article_forhome=? ");
		sql.append(" WHERE article_id=?");
		
		//biên dịch 
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getArticle_title());
			pre.setString(2, item.getArticle_summary());
			pre.setString(3, item.getArticle_content());
			pre.setString(4, item.getArticle_last_modified());
			pre.setString(5, item.getArticle_image());
			pre.setShort(6, item.getArticle_category_id());
			pre.setShort(7, item.getArticle_visited());
			pre.setString(8, item.getArticle_author_name());
			pre.setBoolean(9, item.isArticle_enable());
			pre.setString(10, item.getArticle_url_link());
			pre.setString(11, item.getArticle_tag());
			pre.setString(12, item.getArticle_title_en());
			pre.setString(13, item.getArticle_summary_en());
			pre.setString(14, item.getArticle_content_en());
			pre.setString(15, item.getArticle_tag_en());
			pre.setInt(16, item.getArticle_fee());
			pre.setBoolean(17, item.isArticle_isfee());
			pre.setString(18, item.getArticle_deleted_date());
			pre.setString(19, item.getArticle_restored_date());
			pre.setString(20, item.getArticle_modified_author_name());
			pre.setByte(21, item.getArticle_author_permission());
			pre.setString(22, item.getArticle_source());
			pre.setByte(23, item.getArticle_language());
			pre.setBoolean(24, item.isArticle_focus());
			pre.setByte(25, item.getArticle_type());
			pre.setBoolean(26, item.isArticle_forhome());
			pre.setInt(27, item.getArticle_id());
			
			return this.edit(pre); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		
		return false;
	}

	private boolean isEmpty(ArticleOject item) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT article_id FROM tblcategory WHERE article_category_id=? ");

		ArrayList<ResultSet> res = this.getsMR(sql.toString());
		for (ResultSet rs : res) {
			if (rs != null) {
				try {
					if (rs.next()) {
						flag = false;
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	@Override
	public boolean delArticle(ArticleOject item) {
		// TODO Auto-generated method stub
		if(!this.isEmpty(item)) {
			return false ; 
		}
		StringBuilder sql = new StringBuilder(); 
		sql.append(" DELETE FROM tblarticle WHERE (article_id=?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getArticle_id());
			return this.del(pre); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		return false;
	}

	@Override
	public ResultSet getArticle(short id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblarticle");
		sql.append("LEFT JOIN tblcategory on article_category_id = category_id ");
		sql.append("LEFT JOIN tblsection on category_section_id = section_id ");
		sql.append(" WHERE article_id=?"); 
		
		return get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getArticles(Triplet<ArticleOject, Integer, Byte> infos, Pair<ARTICLE_SORT, ORDER> ao) {
		// TODO Auto-generated method stub
		// đối tượng lưu trữ thông tin lọc kết quả
		ArticleOject similer = infos.getValue0();//similer: tương tự 

		// vị trí bắt đầu lấy bản ghi
		int at = infos.getValue1();

		// số bản ghi được lấy trong một lần
		byte totalperpage = infos.getValue2();

		StringBuilder sql= new StringBuilder();
		//tổng số bản ghi 
		sql.append("SELECT COUNT(article_id) AS totalparpage FROM tblarticle ;");
		
		// câu lệnh truy vấn 
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory on article_category_id = category_id ");
		sql.append("LEFT JOIN tblsection on category_section_id = section_id ");
		sql.append("");
		switch(ao.getValue0()) {
		case TITLE:
			sql.append("ORDER BY article_title ").append(ao.getValue1().name());
			break;
		case AUTHOR_NAME:
			sql.append("ORDER BY article_author_name ").append(ao.getValue1().name());
			break;
		case VISITED:
			sql.append("ORDER BY article_visited ").append(ao.getValue1().name());
			break;
		case SECTION:
			sql.append("ORDER BY section_name ").append(ao.getValue1().name());
			break;
		case CATEGORY:
			sql.append("ORDER BY category_name ").append(ao.getValue1().name());
			break;
		default:
			sql.append("ORDER BY article_id DESC ");
		}
		sql.append("LIMIT " + at + ", " + totalperpage + "; ");
		

		return this.getsMR(sql.toString());
	}

}
