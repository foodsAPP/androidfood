package cn.edu.scujcc.startactivity;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * 菜谱评论模型类
 * @author ASUS
 *
 */
public class Comment {
	private String author;
	private String content;
	private Date date;
	private int star = 0;//点赞量

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public int getStar() {
		return star;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStar(int star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"author='" + author + '\'' +
				", content='" + content + '\'' +
				", date=" + date +
				", star=" + star +
				'}';
	}
}