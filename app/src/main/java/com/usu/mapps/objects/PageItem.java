package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

public class PageItem {
	public static final int PAGETYPE_FIXED_LAYOUT=1;
	public static final int PAGETYPE_REFLOWABLE=2;
	public static final int PAGETYPE_MIXED_LAYOUT=3;
	
	private String idref;
	private String contentCfi;
	private int pageIndex;
	private String pageIndexString;
	private String pageDemoText;
	private int type;
	
	public PageItem(){ }
	
	public PageItem(int pageIndex, String pageDemoText){
		this.idref = Constant.EMPTY;
		this.contentCfi = Constant.EMPTY;
		this.pageIndex = pageIndex;
		this.pageIndexString = "page-"+this.pageIndex;
		this.pageDemoText = pageDemoText;
		this.type = PageItem.PAGETYPE_FIXED_LAYOUT;
	}
	
	public PageItem(int pageIndex, String pageDemoText, int pageType){
		this(pageIndex, pageDemoText);
		this.type=pageType;
	}
	
	public String getIdref() {
		return idref;
	}
	public void setIdref(String idref) {
		this.idref = idref;
	}
	
	public String getContentCfi() {
		return contentCfi;
	}
	public void setContentCfi(String contentCfi) {
		this.contentCfi = contentCfi;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getPageIndexString() {
		return pageIndexString;
	}
	public void setPageIndexString(String pageIndexString) {
		this.pageIndexString = pageIndexString;
	}
	
	public String getPageDemoText() {
		return pageDemoText;
	}
	public void setPageDemoText(String pageDemoText) {
		this.pageDemoText = pageDemoText;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
