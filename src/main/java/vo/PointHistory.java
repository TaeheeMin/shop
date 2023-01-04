package vo;

public class PointHistory {//DB 이력테이블
	private int orderCode;
	private String pointKind;
	private int point;
	private String createdate;
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getPointKind() {
		return pointKind;
	}
	public void setPointKind(String pointKind) {
		this.pointKind = pointKind;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
