package jsoft.gui.article;

import jsoft.*;
import jsoft.objects.*;
import java.util.*;
import org.javatuples.*;

public class ArticleControl {
	public ArticleModel am;
	public ArticleControl(ConnectionPool cp, Quartet<ArticleOject, Short, Byte, VIEW> infos)
	{
		this.am = new ArticleModel(cp, infos);
	}
	
	public ConnectionPool getCP()
	{
		return this.am.getCP();
	}
	
	public void releaseConnection()
	{
		this.am.releaseConnection();
	}
	
	//--------------------------------------------------------------------------
//	public ArticleOject getArticleObject(int id)
//	{
//		return this.am.getArticleObject(id);
//	}
	
	public ArrayList<String> viewHomePage() {
		
		ArrayList<ArrayList<ArticleOject>> allDatas = this.am.getAllArticleObjects();
		
		return ArticleLibrary.viewHomePage(allDatas);
	}
	
	public String viewInnerPage(ArticleOject similar) {
		
		ArrayList<ArrayList<ArticleOject>> allDatas = this.am.getAllArticleObjects();
		ArrayList<Pair<CategoryObject, Integer>> cates_totals = this.am.getCates_Totals();
		HashMap<String, Integer> tags = this.am.getTags_Counts();	
		
		if(similar.getArticle_id()>0) {
			Optional<ArticleOject> item = this.am.getArticleObject();
			return ArticleLibrary.viewDetailPage(item, allDatas, cates_totals, tags, similar);
		}else {
			Triplet<Integer, Short, Byte> pagings = this.am.getPagingInfos();
			return ArticleLibrary.viewInnerPage(allDatas, cates_totals, tags, similar, pagings);
		}
	}
	
	
}
