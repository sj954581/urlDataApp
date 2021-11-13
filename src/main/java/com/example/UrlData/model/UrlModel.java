package com.example.UrlData.model;

public class UrlModel {
	
	public UrlModel() {}
	
	public UrlModel(String key,String url,int count) {
		this.key = key;
		this.url = url;
		this.count = count;
	}
	
	public String key;
	
	public String url;
	
	public int count;
	
	
	public String getKey() {
		return key;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getCount() {
		return count;
	}
	
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ key = " + getKey() + " count = " + getCount() + "url = " + getUrl() + " ]";
	}
}