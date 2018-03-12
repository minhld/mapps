package com.usu.mapps.objects;

import org.simpleframework.xml.Default;

import java.util.ArrayList;

@Default
public class BookList {
	
	private ArrayList<BookItem> bookItems=new ArrayList<BookItem>();

	public ArrayList<BookItem> getBookItems() {
		return bookItems;
	}
	public void setBookItems(ArrayList<BookItem> bookItems){
		this.bookItems = bookItems;
	}
	
	/*
	public void setBookItems(Map<Long,BookItem> mapSet){
		bookItems=new ArrayList<BookItem>();
		for (BookItem book:mapSet.values()){
			this.bookItems.add(book);
		}
	}
	*/
}
