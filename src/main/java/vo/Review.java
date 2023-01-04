package vo;

public class Review {
	private int orderCode;
	private String reviewMemo;
	private String createdate;
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getReviewMemo() {
		return reviewMemo;
	}
	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
