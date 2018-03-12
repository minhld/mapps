package com.usu.mapps.objects;

import com.usu.mapps.utils.Constant;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * this class is derived from NsoftXpub project
 * to contain a basic information of each category.
 * 
 * @author minhld
 *
 */
@Default
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Element(required = false)
	private int id;
	@Element(required = false)
	private String category_name;
	@Element(required = false)
	private String category_name_kr;
	@Element(required = false)
	private String category_name_vn;
	@Element(required = false)
	private String category_name_jp;
	@Element(required = false)
	private String category_name_cn;
	@Element(required = false)
	private int category_parent_id;
	@Element(required = false)
	private String category_image;
	@Element(required = false)
	private String category_desc;
	
	public Category(){
		this.id = Constant.ZERO;
		this.category_name = Constant.EMPTY;
		this.category_name_kr = Constant.EMPTY;
		this.category_name_vn = Constant.EMPTY;
		this.category_name_jp = Constant.EMPTY;
		this.category_name_cn = Constant.EMPTY;
		this.category_parent_id = Constant.ZERO;
		this.category_image = Constant.EMPTY;
		this.category_desc = Constant.EMPTY;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public String getCategory_name_kr() {
		return category_name_kr;
	}
	public void setCategory_name_kr(String category_name_kr) {
		this.category_name_kr = category_name_kr;
	}
	
	public String getCategory_name_vn() {
		return category_name_vn;
	}
	public void setCategory_name_vn(String category_name_vn) {
		this.category_name_vn = category_name_vn;
	}
	
	public String getCategory_name_jp() {
		return category_name_jp;
	}
	public void setCategory_name_jp(String category_name_jp) {
		this.category_name_jp = category_name_jp;
	}
	
	public String getCategory_name_cn() {
		return category_name_cn;
	}
	public void setCategory_name_cn(String category_name_cn) {
		this.category_name_cn = category_name_cn;
	}
	
	public int getCategory_parent_id() {
		return category_parent_id;
	}
	public void setCategory_parent_id(int category_parent_id) {
		this.category_parent_id = category_parent_id;
	}
	
	public String getCategory_image() {
		return category_image;
	}
	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}
	
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	
	
}
