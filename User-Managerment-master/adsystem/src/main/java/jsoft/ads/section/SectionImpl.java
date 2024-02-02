package jsoft.ads.section;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import jsoft.ads.section.SECTION_SORT;
import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.BasicImpl;
import jsoft.library.ORDER;
import jsoft.objects.SectionObject;
import jsoft.objects.UserObject;

public class SectionImpl extends BasicImpl implements Section {

	public SectionImpl(ConnectionPool cp) {
		super(cp, "Section");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addSection(SectionObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();// dữ nguyên tham chiếu không cần phải làm chiếu nhiều lần
		sql.append("INSERT INTO tblsection( ");
		sql.append(" section_name, section_notes, section_created_date, section_manager_id,  ");
		sql.append("section_enable, section_delete, section_last_modified, section_created_author_id, ");
		sql.append(" section_name_en, section_language ");
		sql.append(") ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());
			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// trả về trạng thái an toàn
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editSection(SectionObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblsection SET ");
		sql.append("section_name=?, section_notes=?, section_manager_id=?, section_enable=?, ");
		sql.append(" section_last_modified=?, section_name_en=?, section_language=? ");
		sql.append("WHERE section_id=?");
		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setInt(3, item.getSection_manager_id());
			pre.setBoolean(4, item.isSection_enable());
			pre.setString(5, item.getSection_last_modified());
			pre.setString(6, item.getSection_name_en());
			pre.setByte(7, item.getSection_language());
			pre.setShort(8, item.getSection_id());

			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// trả về trạng thái an toàn
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delSection(SectionObject item) {
		if (!this.isEmpty(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(
				"DELETE FROM tblsection WHERE (section_id=?) AND ((section_created_author_id=?) OR (section_manager_id=?))");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());// sql.toString khi dungf String ko phai
																				// là Stringbuilder
			pre.setInt(1, item.getSection_id());
			pre.setInt(2, item.getSection_created_author_id());
			pre.setInt(3, item.getSection_manager_id());
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

	private boolean isEmpty(SectionObject item) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT category_id FROM tblcategory WHERE category_section_id=? ");

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
	public ResultSet getSection(short id, UserObject userLogined) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblsection AS s");
		sql.append("LEFT JOIN tbluser AS u ON s.section_manager_id = u.user_id");
		sql.append("WHERE (s.section_id=? AND (s.section_manager_id=").append(userLogined.getUser_id()).append(")");
		return this.get(sql.toString(), id);
	}

	@Override
	public ArrayList<ResultSet> getSections(Triplet<SectionObject, Integer, Byte> infos, Pair<SECTION_SORT, ORDER> so) {
		// TODO Auto-generated method stub
		// đối tượng lưu trữ thông tin lọc kết quả
		SectionObject similer = infos.getValue0();

		// vị trí bắt đầu lấy bản ghi
		int at = infos.getValue1();

		// số bản ghi được lấy trong một lần
		byte totalperpage = infos.getValue2();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(section_id) AS totalparpage FROM tblsection;");
		sql.append("SELECT * FROM tblsection ");
		sql.append("LEFT JOIN tbluser ON tblsection.section_manager_id = tbluser.user_id ");
		sql.append("");
		switch (so.getValue0()) {
		case NAME:
			sql.append("ORDER BY section_name ").append(so.getValue1().name());
			break;
		case MANAGER:
			sql.append("ORDER BY section_manager_id").append(so.getValue1().name());
			break;
		default:
			sql.append("ORDER BY section_id DESC ");
		}
		sql.append(" LIMIT " + at + ", " + totalperpage + "; ");

		// tổng số bản ghi

		return this.getsMR(sql.toString());
	}

	public static void main(String[] args) {
		// tạo bộ quản lý kết nối
		ConnectionPool cp = new ConnectionPoolImpl();

		// tạo đối tượng thực thi chức năng mức interface
		Section s = new SectionImpl(cp);

		SectionObject nSection = new SectionObject();
		nSection.setSection_name("ktht");

		boolean result = s.addSection(nSection);
		if (!result) {
			System.out.println("\n---------------------------------KHONG THANH CONG --------------------------------");
		}

		// lấy tập kết quả (danh sách người sử dụng)
		Triplet<SectionObject, Integer, Byte> infos = new Triplet<>(null, 0, (byte) 15);
		ArrayList<ResultSet> res = s.getSections(infos, new Pair<>(SECTION_SORT.NAME, ORDER.DESC));
		ResultSet rs = res.get(1);

		String row;
		if (rs != null) {
			try {
				while (rs.next()) {
					row = "ID: " + rs.getInt("section_id");
					row += "\tMANAGER_ID" + "\t" + rs.getInt("section_manager_id");
					row += "\tNAME: " + "\t" + rs.getString("section_name");

					System.out.println(row);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = res.get(0);
		if (rs != null) {
			try {
				if (rs.next()) {
					System.out.println("Tong so tai khoan trong CSDL la:" + rs.getShort("totalparpage"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<ResultSet> getSections(Quartet<SectionObject, Integer, Byte, SECTION_SORT> infos) {
		// TODO Auto-generated method stub
				SectionObject similar = infos.getValue0();
				
				int at = infos.getValue1();
				
				byte totalperpage = infos.getValue2();
				
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM tblsection s ");
				
				sql.append("LEFT JOIN tbluser u ON s.section_manager_id = u.user_id ");
				
				sql.append("");
				switch(infos.getValue3()) {
					case NAME: sql.append(" ORDER BY section_name ");break;
					case MANAGER:sql.append(" ORDER BY section_manager_id ");break;
					default: sql.append(" ORDER BY section_id DESC");
				}
				
				sql.append(" LIMIT ").append(at).append(", ").append(totalperpage).append(" ; ");
				sql.append(" SELECT COUNT(section_id) AS total FROM tblsection ;");
				
				return this.getsMR(sql.toString());
	}
	
}
