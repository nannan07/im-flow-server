package com.allmsi.flow.model;

/**
 * 总数+内容
 * 
 * @author sunnannan
 *
 */
public class ListBean {

	private int total;

	private Object list;

	public ListBean() {

	}

	public ListBean(int total, Object list) {
		this.total = total;
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}
}
