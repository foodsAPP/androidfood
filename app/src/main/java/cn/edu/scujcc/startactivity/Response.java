package cn.edu.scujcc.startactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Response<T> implements List<Comment> {
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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(@Nullable Object o) {
		return false;
	}

	@NonNull
	@Override
	public Iterator<Comment> iterator() {
		return null;
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@NonNull
	@Override
	public <T> T[] toArray(@NonNull T[] ts) {
		return null;
	}

	@Override
	public boolean add(Comment comment) {
		return false;
	}

	@Override
	public boolean remove(@Nullable Object o) {
		return false;
	}

	@Override
	public boolean containsAll(@NonNull Collection<?> collection) {
		return false;
	}

	@Override
	public boolean addAll(@NonNull Collection<? extends Comment> collection) {
		return false;
	}

	@Override
	public boolean addAll(int i, @NonNull Collection<? extends Comment> collection) {
		return false;
	}

	@Override
	public boolean removeAll(@NonNull Collection<?> collection) {
		return false;
	}

	@Override
	public boolean retainAll(@NonNull Collection<?> collection) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public Comment get(int i) {
		return null;
	}

	@Override
	public Comment set(int i, Comment comment) {
		return null;
	}

	@Override
	public void add(int i, Comment comment) {

	}

	@Override
	public Comment remove(int i) {
		return null;
	}

	@Override
	public int indexOf(@Nullable Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(@Nullable Object o) {
		return 0;
	}

	@NonNull
	@Override
	public ListIterator<Comment> listIterator() {
		return null;
	}

	@NonNull
	@Override
	public ListIterator<Comment> listIterator(int i) {
		return null;
	}

	@NonNull
	@Override
	public List<Comment> subList(int i, int i1) {
		return null;
	}
}
