package org.wyjc.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.wyjc.entity.PageConf;
import org.wyjc.entity.PageConfData;
import org.wyjc.entity.UserDTO;
import org.wyjc.gap.CssSelector;
import org.wyjc.gap.Gap;
import org.wyjc.gap.PageNode;
import org.wyjc.service.PageConfDataService;
import org.wyjc.service.PageConfService;
import org.wyjc.util.JsonUtil;
import org.wyjc.util.UserUtil;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 
 * @author chenying
 * 
 */
public class GapAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private PageConfService pageConfService;
	@Resource
	private PageConfDataService pageConfDataService;
	private String content;
	private String resultStr;
	private String selectionText;
	private String confId;
	private int  screenWidth;
	private int  screenHeight;
	private String fieldsSelector;
	private String listSelector;
	private String nextSelector;
	private String targeturl;
	

	public String getNextSelector() {
		return nextSelector;
	}

	public void setNextSelector(String nextSelector) {
		this.nextSelector = nextSelector;
	}

	public String getListSelector() {
		return listSelector;
	}

	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}

	public String getConfId() {
		return confId;
	}

	public void setConfId(String confId) {
		this.confId = confId;
	}

	public String getSelectionText() {
		return selectionText;
	}

	public void setSelectionText(String selectionText) {
		this.selectionText = selectionText;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PageConfService getPageConfService() {
		return pageConfService;
	}

	public void setPageConfService(PageConfService pageConfService) {
		this.pageConfService = pageConfService;
	}

	public PageConfDataService getPageConfDataService() {
		return pageConfDataService;
	}

	public void setPageConfDataService(PageConfDataService pageConfDataService) {
		this.pageConfDataService = pageConfDataService;
	}

	public String getHtmlList() {
		Gap gap = new Gap();
		// List<CssSelector> list=gap.parseContent(content);
		Map<String, List<CssSelector>> m = gap.elements(content);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Map map = new HashMap();
		map.put("map", m);
		map.put("nextPage", gap.getPage().getNextPage());
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public String saveConf() {
		Map map = new HashMap();
		JSONObject obj = new JSONObject().fromObject(content);// 将json字符串转换为json对象
		PageConf conf = (PageConf) JSONObject.toBean(obj, PageConf.class);// 将建json对象转换为Person对象
		UserDTO userDto = UserUtil.getUserInfo();
		if(userDto==null){
			map.put("status", "fail");
			this.resultStr = JsonUtil.returnDefaultAjax(map);
			return "json";
		}
		conf.setUserId(userDto.getId());
		try {
			if(conf.getId()>0){
				pageConfService.updatePageConfById(conf);
				map.put("confId", conf.getId());
			}else{
				long confId = pageConfService.insertPageConf(conf);
				map.put("confId", confId);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public String getContentList() {
		Gap gap = new Gap();
		List<CssSelector> list = gap.parseContent(content);
		// Map<String,List<CssSelector>> m=gap.elements(content);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Map map = new HashMap();
		map.put("list", list);
		// map.put("nextPage", gap.getPage().getNextPage());
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String analysiscontent() {
		List<PageNode> list=new ArrayList<PageNode>();
		Gap gap = new Gap();
		Map map = new HashMap();
		JSONArray array = JSONArray.fromObject(content);
		for(int i=0;i<array.size();i++){
			PageNode node=new PageNode();
			node.setHeigth(array.getJSONObject(i).getInt("height"));
			node.setWidth(array.getJSONObject(i).getInt("width"));
			node.setY(array.getJSONObject(i).getInt("Y"));
			node.setX(array.getJSONObject(i).getInt("X"));
			node.setFontsize(array.getJSONObject(i).getInt("fontsize"));
			node.setSelector(array.getJSONObject(i).getString("selector"));
			node.setText(array.getJSONObject(i).getString("text"));
			list.add(node);
		}
		gap.analysiscontent(screenWidth, screenHeight, list);
		// map.put("nextPage", gap.getPage().getNextPage());
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String pageConfDetail() {
		return "pageConfDetail";
	}

	public String getConfbyId() {
		Map map = new HashMap();
		PageConf pageConf = pageConfService.getPageConfById(content);
		map.put("pageConf", pageConf);
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String savePageConfData() {
		Map map = new HashMap();
		PageConfData pageConfData=new PageConfData();
		pageConfData.setData(content);
		pageConfData.setPageConfId(Long.parseLong(confId));
		try {
			pageConfDataService.insertPageConfData(pageConfData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String parseFieldsFromContent() {
		Map map = new HashMap();
		Gap gap = new Gap();
		String fielsVal=gap.parseFieldsContent(content, fieldsSelector);
		PageConfData pageConfData=new PageConfData();
		pageConfData.setData(fielsVal);
		pageConfData.setPageConfId(Long.parseLong(confId));
		try {
			pageConfDataService.insertPageConfData(pageConfData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String parseUrlsFromContent() throws MalformedURLException {
		URL url=new URL(targeturl);
		String baseUri=url.getProtocol()+"://"+url.getHost();
		Map map = new HashMap();
		Gap gap = new Gap();
		List<String> urls=gap.parseUrlsContent(content, listSelector);
		for(String u:urls){
			u=fullurl(u,baseUri);
		}
		map.put("urls", urls);
		String nexturl=gap.parseNexturlContent(content, nextSelector);
		map.put("nexturl", fullurl(nexturl,baseUri));
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}
	public String fullurl(String url,String baseUri){
		if(url.startsWith("/")){
			url=baseUri+url;
		}
		return url;
	}
	
	public String getFieldsSelector() {
		return fieldsSelector;
	}

	public void setFieldsSelector(String fieldsSelector) {
		this.fieldsSelector = fieldsSelector;
	}

	public String getTargeturl() {
		return targeturl;
	}

	public void setTargeturl(String targeturl) {
		this.targeturl = targeturl;
	}
	
}
