package jsoft.ads.section;

import jsoft.*;
import jsoft.ads.section.*;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.util.*;
import org.javatuples.*;

public class SectionControl {

	private SectionModel sm;
	
	public SectionControl(ConnectionPool cp) {
		this.sm = new SectionModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.sm.getCP();
	}

	public void releaseConnection() {
		this.sm.releaseConnection();
	}
	
	//*********************************************8
	
	public boolean addSection(SectionObject item) {
		return this.sm.addSection(item);
	}
	
	public boolean editSection(SectionObject item) {
		return this.sm.editSection(item);
	}
	
	public boolean delSection(SectionObject item) {
		return this.sm.delSection(item);
	}
	//************************************************************************************
	
	public SectionObject getSectionObject(short id, UserObject userLogined) {
		return this.sm.getSectionObject(id, userLogined);
	}
	//************************************************************************************
	
	public ArrayList<String> viewSections(Quartet<SectionObject,Integer,Byte,SECTION_SORT> infos){
		Pair<ArrayList<SectionObject>,Short> datas = this.sm.getSectionObject(infos);
		ArrayList<String> views = new ArrayList<>();
		
		views.add(SectionLibrary.viewSection(datas.getValue0()));
		
		
		return views;
	}
	
	public static void main(String[] args) {
		ConnectionPool cp= new ConnectionPoolImpl();
		SectionControl sc = new SectionControl(cp);
		
		Quartet<SectionObject, Integer, Byte,SECTION_SORT> infos2 = new Quartet<>(null, 0, (byte)20,SECTION_SORT.NAME);
		
		ArrayList<String> views2= sc.viewSections(infos2);
		
		sc.releaseConnection();
		
		System.out.print(views2);
	}
	
}
