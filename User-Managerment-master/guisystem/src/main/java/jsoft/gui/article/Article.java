package jsoft.gui.article;

import org.javatuples.*;
import java.sql.*;
import java.util.*;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Article extends ShareControl {
	
	public ResultSet getArticle(int id);
	
	public ArrayList<ResultSet> getArticles(Quartet<ArticleOject, Short, Byte, VIEW> infos);

}
