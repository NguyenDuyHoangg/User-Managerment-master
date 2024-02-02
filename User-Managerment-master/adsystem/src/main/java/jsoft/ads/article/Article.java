package jsoft.ads.article;

import jsoft.ads.article.Article;
import jsoft.ads.article.ARTICLE_SORT;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.sql.*;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public interface Article {
	//Các chức năng cập nhật
	public boolean addArticle(ArticleOject item);
	public boolean editArticle(ArticleOject item);
	public boolean delArticle(ArticleOject item);
	
	//Các chức năng lấy dũư liệu
	public ResultSet getArticle(short id);
	//public ResultSet getArticle(ArticleOject similar, int at, byte total);
	public ArrayList<ResultSet> getArticles(Triplet<ArticleOject, Integer, Byte> infos, Pair<ARTICLE_SORT, ORDER> so);
}
