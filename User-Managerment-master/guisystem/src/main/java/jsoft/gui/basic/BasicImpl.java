package jsoft.gui.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;

public class BasicImpl implements Basic {

	// bộ quản lý kết nối của riêng Basic
	private ConnectionPool cp;

	// kết nối để basic làm việc với csdl protected: chỉ con cháu sử dụng

	protected Connection con; // một đối tượng được lưu trữ trong connectionPool

	// đối tượng làm việc với Basic
	private String objectName;

	public BasicImpl(ConnectionPool cp, String objectName) {
		// Xác định đối tượng làm việc với basic
		this.objectName = objectName;

		// Xác định bộ quản lý kết nối
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();// nếu chưa có thì tạo ra dùng
		} else {
			this.cp = cp;
		}
		// hỏi xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection(this.objectName);

			// kiểm tra trạng thái thực thi của kêt nối
			if (this.con.getAutoCommit()) {// getAutoCommit: tự động thực thi
				this.con.setAutoCommit(false);// hủy thực thi tự động
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// synchronized cài đặt đồng bộ để theo trình tự làm : gọi trước thì thực thi
	// trước
	private synchronized boolean exe(PreparedStatement pre) {// PreparedStatement câu lệnh thực thi
		if (pre != null) {
			try {
				int results = pre.executeUpdate();// thực thi cập nhập
				if (results == 0) {// ai đăng nhập vào mà không tồn tại
					this.con.rollback();
					return false;
				}

				// Xác nhận thực thi sau cùng
				this.con.commit();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// trở lại trạng thái an toàn của kết nối
				try {
					this.con.rollback();// bảng gương của cơ sở dữ liệu
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				pre = null;
			}
		}

		return false;
	}


	@Override
	public synchronized ResultSet get(String sql, int id) {
		// TODO Auto-generated method stub

		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			if (id > 0) {
				pre.setInt(1, id);
			}
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			sql = null;
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, pass);
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			sql = null;
		}
		return null;
	}


	// chia sẻ kết nối cho nhau
	@Override
	public ConnectionPool getCP() {
		// TODO Auto-generated method stub
		return this.cp;
	}

	// trả lại kết nối
	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		try {
			this.cp.releaseConnectionPool(this.con, this.objectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ResultSet> getsMR(String multiSelect) {
		// TODO Auto-generated method stub
		ArrayList<ResultSet> res = new ArrayList<>();
		try {
			PreparedStatement pre = this.con.prepareStatement(multiSelect);
			boolean result = pre.execute();

			do {
				if (result) {
					res.add(pre.getResultSet());
				}
				// di chuyển sang ResultSet tiếp theo
				result = pre.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);// dữ lại bản ghi không được đóng
			} while (result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
