package com.usu.mapps.objects;

import org.simpleframework.xml.Default;

import java.util.ArrayList;
import java.util.List;

@Default
public class CategoryList{
	private List<Category> categoryItems=new ArrayList<Category>();

	public List<Category> getCategoryItems(){
		return categoryItems;
	}
	public void setCategoryItems(List<Category> categoryItems){
		this.categoryItems = categoryItems;
	}
	
	public CategoryList(){
		this.categoryItems=new ArrayList<Category>();
	}
	
	public CategoryList(List<Category> categoryItems){
		this.categoryItems=categoryItems;
	}
}
