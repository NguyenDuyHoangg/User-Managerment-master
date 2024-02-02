package jsoft.gui.basic;

import java.sql.*;

import jsoft.*;
import jsoft.objects.*;

import java.util.*;

public interface Basic extends ShareControl {
	
	// Các chức năng lấy dữ liệu
	
	public ResultSet get(String sql, int id);

	public ResultSet get(String sql, String name, String pass);

	public ArrayList<ResultSet> getsMR(String multiSelect);

}
