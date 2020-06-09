package cn.edu.scujcc.startactivity;

import java.util.List;

public class Response<T> {
	public final static int STATUS_OK=1;
	public final static int STATUS_ERROR=-1;

	private int status;
	private String message;
	private T data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data =data;
	}

	public Response<T> ok() {
		Response<T> result=new Response<T>();
		result.setStatus(STATUS_OK);
		result.setMessage("操作成功");
		return result;
	}
	
	public Response<T> error() {
		Response<T> result=new Response<T>();
		result.setStatus(STATUS_ERROR);
		result.setMessage("操作失败");
		return result;
	}
}
