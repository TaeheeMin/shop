package vo;

public class QuestionComment {
	private int commentCode;
	private int question_code;
	private String comment_memo;
	private String createdate;
	public int getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(int commentCode) {
		this.commentCode = commentCode;
	}
	public int getQuestion_code() {
		return question_code;
	}
	public void setQuestion_code(int question_code) {
		this.question_code = question_code;
	}
	public String getComment_memo() {
		return comment_memo;
	}
	public void setComment_memo(String comment_memo) {
		this.comment_memo = comment_memo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
