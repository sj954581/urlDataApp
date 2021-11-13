package com.example.UrlData.controller;

import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

import com.example.UrlData.model.UrlModel;

@RestController
public class UrlController {
	
	HashMap<String,UrlModel> hm = new HashMap<String,UrlModel>(); // for Storing all Data into Object type
	ArrayList<JSONObject> list = new ArrayList<JSONObject>(); // for pagination only.
	
	@GetMapping("/storeurl")
	public String storeurl(@RequestParam String url) {
		UrlModel um = new UrlModel();
		um.setUrl(url);
		um.setCount(0);
		um.setKey(new java.util.UUID(7, 3).toString());
		
		hm.put(url, um);
		
		return "URL Saved : " + url;
	}
	
	@GetMapping("/get")
	public String get(@RequestParam String url) {
		UrlModel um = hm.get(url);
		um.setCount(um.getCount() + 1);
		hm.put(url, um);
		
		return "Counter Added for URL Key : " + um.getKey();
	}
	
	@GetMapping("/count")
	public String count(@RequestParam String url) {
		UrlModel um = hm.get(url);
		return "URL Count = " + um.getCount();
	}
	
	@GetMapping("/list")
	public List<JSONObject> list(@RequestParam int page, @RequestParam int size) {
		
		list.clear();// clearing the list evrytime is nessesory
		
		// put All UrlModel in list 
		Iterator itr = hm.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry entry =  (Map.Entry) itr.next();
			
//			Bellow Logic is for taking existing UrlObject and 
//			Creating new From it WITH Removing "KEY" because we Don't want to SHOW "KEY".
			
			UrlModel temp = (UrlModel) entry.getValue();
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("url", temp.getUrl());
			jsonObj.put("count",temp.getCount());
			
			// adding object to list for pagination
			list.add(jsonObj);
		}
		
		// Pagingation starts here...
		PagedListHolder ph = new PagedListHolder(list); // List is Passed here for pagination.
		ph.setPage(page); // set Current Page
		ph.setPageSize(size); // set page size
		List<JSONObject> finalList = ph.getPageList(); // getting filtered result list
		
		return finalList;
	}
	
	// Bellow API is just added to see does Pagination actually works or not on large data ex. 100 records.
	@GetMapping("/checkPagination")
	public List<JSONObject> checkPagination(@RequestParam int page, @RequestParam int size) {
		
		list.clear();// clearing the list evrytime is nessesory
		
		// Adding 100 URl's in the list just for pagination check on large Data
		for(int i=1;i<=100;i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("url", "url_" + String.valueOf(i) + ".com");
			jsonObj.put("count",0);
			list.add(jsonObj);
		}
		
		// Pagingation starts here...
		PagedListHolder ph = new PagedListHolder(list); // List is Passed here for pagination.
		ph.setPage(page); // set Current Page
		ph.setPageSize(size); // set page size
		List<JSONObject> finalList = ph.getPageList(); // getting filtered result list
		
		return finalList;
	}
}