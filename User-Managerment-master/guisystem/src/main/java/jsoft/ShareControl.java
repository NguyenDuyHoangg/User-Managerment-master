package jsoft;

public interface ShareControl {
	// chia sẻ bộ quản lý kết nối giữa các thể hiện của basic
	public ConnectionPool getCP();

	// yêu cầu các đối tượng trả lại kết nối sau khi lấy xong dữ liệu
	public void releaseConnection();
}
