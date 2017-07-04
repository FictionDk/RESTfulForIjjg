package org.fictio.shop.ijjg.pojo;

public class Page {
	private int pageStart;
	private int pageSize;
	private Object pageContent;
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Object getPageContent() {
		return pageContent;
	}
	public void setPageContent(Object pageContent) {
		this.pageContent = pageContent;
	}
	@Override
	public String toString() {
		return "Page [pageStart=" + pageStart + ", pageSize=" + pageSize + ", pageContent=" + pageContent + "]";
	}
	
}
