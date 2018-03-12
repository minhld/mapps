package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.Element;

import java.io.Serializable;

@Default
public class BookItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Element(required = false)
	private String bookId;
	@Element(required = false)
	private long bookUniqueId;
	@Element(required = false)
	private String bookISBN;
	@Element(required = false)
	private String identifier;
	@Element(required = false)
	private String title;
	@Element(required = false)
	private String description;
	@Element(required = false)
	private int bookTypeId;
	@Element(required = false)
	private String bookTypeName;
	@Element(required = false)
	private int categoryId;
	@Element(required = false)
	private String categoryName;
	@Element(required = false)
	private String authorId;
	@Element(required = false)
	private String authorName;
	@Element(required = false)
	private String authorPortrait;
	@Element(required = false)
	private String modifyAuthorId;
	@Element(required = false)
	private String modifyAuthorName;
	@Element(required = false)
	private int languageId;
	@Element(required = false)
	private String languageName;
	@Element(required = false)
	private String folderId;
	@Element(required = false)
	private String folderName;
	@Element(required = false)
	private String createDate;
	@Element(required = false)
	private String modifyDate;
	@Element(required = false)
	private String publisher;
	@Element(required = false)
	private String writer;
	@Element(required = false)
	private String bookFolder;
	@Element(required = false)
	private String bookConfig;
	@Element(required = false)
	private String bookCover;
	@Element(required = false)
	private String bookCoverS;
	@Element(required = false)
	private String bookToc;
	@Element(required = false)
	private String bookUrl;
	@Element(required = false)
	private String copyrights;
	@Element(required = false)
	private String version;
	@Element(required = false)
	private int bookStatusId;
	@Element(required = false)
	private String bookStatusName;
	@Element(required = false)
	private int visitedCount;
	@Element(required = false)
	private int likeCount;
	@Element(required = false)
	private int commentCount;
	@Element(required = false)
	private float price;
	@Element(required = false)
	private int readCount;
	@Element(required = false)
	private String bookExtra;
	@Element(required = false)
	private int coverWidth;
	@Element(required = false)
	private int coverHeight;
	
	public BookItem(){
		this.bookId = Constant.EMPTY;
		this.bookUniqueId=0;
		this.bookISBN=Constant.EMPTY;
		this.identifier=Constant.EMPTY;
		this.title = Constant.EMPTY;
		this.description = Constant.EMPTY;
		this.bookTypeId = 0;
		this.bookTypeName = Constant.EMPTY;
		this.categoryId = 0;
		this.categoryName = Constant.EMPTY;
		this.authorId = Constant.EMPTY;
		this.authorName = Constant.EMPTY;
		this.authorPortrait = Constant.EMPTY;
		this.modifyAuthorId = Constant.EMPTY;
		this.modifyAuthorName = Constant.EMPTY;
		this.languageId = 0;
		this.languageName = Constant.EMPTY;
		this.folderId = Constant.EMPTY;
		this.folderName = Constant.EMPTY;
		this.createDate = Constant.EMPTY;
		this.modifyDate = Constant.EMPTY;
		this.publisher = Constant.EMPTY;
		this.writer = Constant.EMPTY;
		this.bookFolder = Constant.EMPTY;
		this.bookConfig = Constant.EMPTY;
		this.bookCover = Constant.EMPTY;
		this.bookCoverS = Constant.EMPTY;
		this.bookToc = Constant.EMPTY;
		this.bookUrl = Constant.EMPTY;
		this.copyrights = Constant.EMPTY;
		this.version = Constant.EMPTY;
		this.bookStatusId = -1;
		this.bookStatusName = Constant.EMPTY;
		this.visitedCount = 0;
		this.likeCount = 0;
		this.commentCount = 0;
		this.readCount = 0;
		this.price = 0;
		this.coverWidth = 0;
		this.coverHeight = 0;
	}
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public long getBookUniqueId() {
		return bookUniqueId;
	}
	public void setBookUniqueId(long bookUniqueId) {
		this.bookUniqueId = bookUniqueId;
	}
	
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(int bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getAuthorPortrait() {
		return authorPortrait;
	}
	public void setAuthorPortrait(String authorPortrait) {
		this.authorPortrait = authorPortrait;
	}
	
	public String getModifyAuthorId() {
		return modifyAuthorId;
	}
	public void setModifyAuthorId(String modifyAuthorId) {
		this.modifyAuthorId = modifyAuthorId;
	}
	
	public String getModifyAuthorName() {
		return modifyAuthorName;
	}
	public void setModifyAuthorName(String modifyAuthorName) {
		this.modifyAuthorName = modifyAuthorName;
	}
	
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
		
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getBookFolder() {
		return bookFolder;
	}
	public void setBookFolder(String bookFolder) {
		this.bookFolder = bookFolder;
	}
	
	public String getBookConfig() {
		return bookConfig;
	}
	public void setBookConfig(String bookConfig) {
		this.bookConfig = bookConfig;
	}
	
	public String getBookCover() {
		return bookCover;
	}
	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	
	public String getBookCoverS() {
		return bookCoverS;
	}
	public void setBookCoverS(String bookCoverS) {
		this.bookCoverS = bookCoverS;
	}

	public String getBookToc() {
		return bookToc;
	}
	public void setBookToc(String bookToc) {
		this.bookToc = bookToc;
	}
	
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
	
	public String getCopyrights() {
		return copyrights;
	}
	public void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public int getBookStatusId() {
		return bookStatusId;
	}
	public void setBookStatusId(int bookStatusId) {
		this.bookStatusId = bookStatusId;
	}
	
	public String getBookStatusName() {
		return bookStatusName;
	}
	public void setBookStatusName(String bookStatusName) {
		this.bookStatusName = bookStatusName;
	}
	
	public int getVisitedCount() {
		return visitedCount;
	}
	public void setVisitedCount(int visitedCount) {
		this.visitedCount = visitedCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getBookExtra() {
		return bookExtra;
	}
	public void setBookExtra(String bookExtra) {
		this.bookExtra = bookExtra;
		String[] extras=bookExtra.split(Constant.SEMICOLON);
		
		if (extras.length == 2){
			this.setCoverWidth(Integer.parseInt(extras[0]));
			this.setCoverHeight(Integer.parseInt(extras[1]));
		}
	}

	public int getCoverWidth() {
		return coverWidth;
	}
	public void setCoverWidth(int coverWidth) {
		this.coverWidth = coverWidth;
	}

	public int getCoverHeight() {
		return coverHeight;
	}
	public void setCoverHeight(int coverHeight) {
		this.coverHeight = coverHeight;
	}
	
}
