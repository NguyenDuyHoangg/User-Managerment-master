package jsoft.ads.category;

import jsoft.*;
import jsoft.ads.category.*;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.util.*;
import org.javatuples.*;

public class CategoryControl {

	private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}

	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	//*********************************************8
	
	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}
	
	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}
	
	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}
	//************************************************************************************
	
	public CategoryObject getCategoryOject(short id, UserObject userLogined) {
		return this.cm.getCategoryObject(id, userLogined);
	}
	//************************************************************************************
	
	public ArrayList<String> viewCategories(Quartet<CategoryObject,Integer,Byte,CATEGORY_SORT> infos){
		Pair<ArrayList<CategoryObject>,Short> datas = this.cm.getCategoryObject(infos);
		ArrayList<String> views = new ArrayList<>();
		
		views.add(CategoryLibrary.viewCategory(datas.getValue0()));
		
		
		return views;
	}
	
	public static void main(String[] args) {
		ConnectionPool cp= new ConnectionPoolImpl();
		CategoryControl sc = new CategoryControl(cp);
		
		Quartet<CategoryObject, Integer, Byte,CATEGORY_SORT> infos2 = new Quartet<>(null, 0, (byte)10,CATEGORY_SORT.NAME);
		
		ArrayList<String> views2= sc.viewCategories(infos2);
		
		sc.releaseConnection();
		
		System.out.print(views2);
	}
	
}
