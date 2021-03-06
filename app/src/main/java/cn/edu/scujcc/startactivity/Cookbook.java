package cn.edu.scujcc.startactivity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ����ģ����
 * @author ASUS
 *
 */
public class Cookbook implements Serializable {
	
	private static final long serialVersionUID = 8113986652205866086L;

	private String id;
	private String title;
	private String cover; //���׷���
	private String maked; //���ױ���������
	private int cstar; //���ױ������� //��������
	private List<Material> materials;
	private List<Step> steps;
	private List<Comment> comments;
	
	public List<Material> getMaterial() {
		return materials;
	}
	public void setMaterial(List<Material> material) {
		this.materials = material;
	}
	public List<Step> getStep() {
		return steps;
	}
	public void setStep(List<Step> step) {
		this.steps = step;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getMaked() {
		return maked;
	}
	public void setMaked(String maked) {
		this.maked = maked;
	}
	public int getCstar() {
		return cstar;
	}
	public void setCstar(int cstar) {
		this.cstar = cstar;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + cstar;

		result = prime * result + ((maked == null) ? 0 : maked.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cookbook other = (Cookbook) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (cstar != other.cstar)
			return false;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maked == null) {
			if (other.maked != null)
				return false;
		} else if (!maked.equals(other.maked))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cookbook [id=" + id + ", title=" + title + ", cover=" + cover + ", maked=" + maked + ", cstar=" + cstar
				+ ", comments=" + comments + ", contents=" + ", material=" + materials + ", step=" + steps
				+ "]";
	}
	
	

}
