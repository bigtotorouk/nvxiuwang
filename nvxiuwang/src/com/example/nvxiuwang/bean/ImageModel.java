package com.example.nvxiuwang.bean;

import java.io.Serializable;

/**
 * 图片对象实体
 * @author bing
 *
 */
public class ImageModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6649445079512209349L;
	/**
	 * 
	 */
	private String id;
	private String text;
	private String thumb_pic;
	private String middle_pic;
	private String big_pic;
	private String original_pic;
	private String thumb_avatar;
	private String source;
	private String location;
	private String create_time;
	private String comments_count;
	private String click_count;
	private String like_num;
	private String hot;
	private String uid;
	private String user_name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getThumb_pic() {
		return thumb_pic;
	}
	public void setThumb_pic(String thumb_pic) {
		this.thumb_pic = thumb_pic;
	}
	public String getMiddle_pic() {
		return middle_pic;
	}
	public void setMiddle_pic(String middle_pic) {
		this.middle_pic = middle_pic;
	}
	public String getBig_pic() {
		return big_pic;
	}
	public void setBig_pic(String big_pic) {
		this.big_pic = big_pic;
	}
	public String getOriginal_pic() {
		return original_pic;
	}
	public void setOriginal_pic(String original_pic) {
		this.original_pic = original_pic;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getComments_count() {
		return comments_count;
	}
	public void setComments_count(String comments_count) {
		this.comments_count = comments_count;
	}
	public String getClick_count() {
		return click_count;
	}
	public void setClick_count(String click_count) {
		this.click_count = click_count;
	}
	public String getLike_num() {
		return like_num;
	}
	public void setLike_num(String like_num) {
		this.like_num = like_num;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getThumb_avatar() {
		return thumb_avatar;
	}
	public void setThumb_avatar(String thumb_avatar) {
		this.thumb_avatar = thumb_avatar;
	}
}
