package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

public class Reaction {
	public static enum ReactionType{
		lol,
		win,
		omg,
		cute,
		failed,
		trashy,
		wtf,
		share,
		download
	}
	
	private String bookId;
	private int lolCount;
	private int winCount;
	private int omgCount;
	private int cuteCount;
	private int failedCount;
	private int trashyCount;
	private int wtfCount;
	private int shareCount;
	private int downloadCount;
	
	public Reaction(){
		this.bookId = Constant.EMPTY;
		this.lolCount = Constant.ZERO;
		this.winCount = Constant.ZERO;
		this.omgCount = Constant.ZERO;
		this.cuteCount = Constant.ZERO;
		this.failedCount = Constant.ZERO;
		this.trashyCount = Constant.ZERO;
		this.wtfCount = Constant.ZERO;
		this.setShareCount(Constant.ZERO);
		this.setDownloadCount(Constant.ZERO);
	}
	
	public Reaction(String bookId){
		this.bookId = bookId;
		this.lolCount = Constant.ZERO;
		this.winCount = Constant.ZERO;
		this.omgCount = Constant.ZERO;
		this.cuteCount = Constant.ZERO;
		this.failedCount = Constant.ZERO;
		this.trashyCount = Constant.ZERO;
		this.wtfCount = Constant.ZERO;
		this.setShareCount(Constant.ZERO);
		this.setDownloadCount(Constant.ZERO);
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public int getLolCount() {
		return lolCount;
	}
	public void setLolCount(int lolCount) {
		this.lolCount = lolCount;
	}
	
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	
	public int getOmgCount() {
		return omgCount;
	}
	public void setOmgCount(int omgCount) {
		this.omgCount = omgCount;
	}
	
	public int getCuteCount() {
		return cuteCount;
	}
	public void setCuteCount(int cuteCount) {
		this.cuteCount = cuteCount;
	}
	
	public int getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	
	public int getTrashyCount() {
		return trashyCount;
	}
	public void setTrashyCount(int trashyCount) {
		this.trashyCount = trashyCount;
	}
	
	public int getWtfCount() {
		return wtfCount;
	}
	public void setWtfCount(int wtfCount) {
		this.wtfCount = wtfCount;
	}

	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
}
