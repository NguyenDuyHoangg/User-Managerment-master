package jsoft.ads.category;

import jsoft.ShareControl;
import jsoft.ads.category.*;
import jsoft.ads.section.SECTION_SORT;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.sql.*;
import java.util.ArrayList;

import org.javatuples.*;


public interface Category extends ShareControl {
	//Các chức năng cập nhật
	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	//Các chức năng lấy dữ liệu
	public ResultSet getCategory(short id, UserObject user);
	//public ResultSet getCategory(CategoryOject similar, int at, byte total);
	
//	public ArrayList<ResultSet> getCategories(Triplet<CategoryObject, Integer, Byte> infos, Pair<CATEGORY_SORT, ORDER> so);
	public ArrayList<ResultSet> getCategories(Quartet<CategoryObject,Integer,Byte,CATEGORY_SORT> infos);
}
