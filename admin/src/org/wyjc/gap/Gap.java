package org.wyjc.gap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Gap {
	private Page page = new Page();

	public List<CssSelector> analysiscontent(int screenWidth, int screenHeight, List<PageNode> list) {
		return page.analysiscontent(screenWidth, screenHeight, list);
	}

	public Map<String, List<CssSelector>> elements(String content) {
		Document doc = Jsoup.parse(content);
		List<Element> es = doc.select("a");
		List<CssSelector> list = new ArrayList<CssSelector>();
		for (Element e : es) {
			if (e.hasText()) {
				System.out.println(e.text());
				System.out.println(e.cssSelector());
				CssSelector cs = new CssSelector();
				cs.setSelector(e.cssSelector());
				cs.setText(e.text().trim());
				cs.setSize(e.cssSelector().split(">").length);
				cs.setHref(e.attr("href"));
				list.add(cs);
			}
		}
		page.setList(list);
		page.handlePage();
		return page.getIsizeMap();
	}

	public List<CssSelector> parseContent(String content, String selectionText) {
		Document doc = Jsoup.parse(content);
		List<Element> es = doc.getAllElements();
		List<CssSelector> list = new ArrayList<CssSelector>();
		for (Element e : es) {
			if (e.hasText()) {
				CssSelector cs = new CssSelector();
				cs.setSelector(e.cssSelector());
				cs.setText(e.text());
				list.add(cs);
			}
		}
		page.setList(list);
		return page.parseContent(selectionText);
	}

	public String parseFieldsContent(String content, String fieldsStr) {
		String result = "";
		String[] fields = fieldsStr.split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
		Document doc = Jsoup.parse(content);
		for (String field : fields) {
			Elements elements = doc.select(field);
			if (elements.size() > 0) {
				result = result + elements.get(0).text() + "{{{{{}}}}}";
			} else {
				result = result + "{{{{{}}}}}";
			}

		}
		result = result.substring(0, result.lastIndexOf("{{{{{}}}}}"));
		return result;
	}

	public List<String> parseUrlsContent(String content, String listSelector) {
		List<String> urls = new ArrayList<String>();
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select(listSelector);
		for (Element e : elements) {
			if (e.hasAttr("href")) {
				urls.add(e.attr("href"));
			}
		}
		return urls;
	}

	public String parseNexturlContent(String content, String nextSelector) {
		String url = "";
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("a");
		for (Element e : elements) {
			if(e.text()!=null){
				if (e.text().equals(nextSelector.split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}")[1])) {
					url = e.attr("href");
				}
			}
			
		}
		//Elements elements  = doc.select(nextSelector.split("{{{{{}}}}}")[0]);
		/*doc = Jsoup.parse(doc.select(nextSelector.split("{{{{{}}}}}")[0]).html());
		*/
		return url;
	}

	public List<CssSelector> parseContent(String content) {
		Document doc = Jsoup.parse(content);
		List<Element> es = doc.getAllElements();
		List<CssSelector> clist = new ArrayList<CssSelector>();
		for (Element e : es) {
			if (e.hasText()) {
				CssSelector cs = new CssSelector();
				cs.setSelector(e.cssSelector());
				System.out.println(e.text());
				cs.setText(e.text());
				clist.add(cs);
			}
		}
		/*
		 * page.setClist(clist);
		 * 
		 * es = doc.select("a"); List<CssSelector> list = new
		 * ArrayList<CssSelector>(); for (Element e : es) { if (e.hasText()) {
		 * //System.out.println(e.text());
		 * //System.out.println(e.cssSelector()); CssSelector cs = new
		 * CssSelector(); cs.setSelector(e.cssSelector());
		 * cs.setText(e.text().trim());
		 * cs.setSize(e.cssSelector().split(">").length);
		 * cs.setHref(e.attr("href")); clist.add(cs); } }
		 */
		page.setList(clist);
		// page.handlePage();

		return page.getList();
	}

	public static void main(String[] args) throws IOException {
		Gap g = new Gap();
		Document doc = Jsoup.connect("http://newhouse.sh.fang.com/house/s/c9y-b810/").userAgent("Mozilla").timeout(8000)
				.get();
		// List<Element> es = doc.select("a");
		List<Element> es = doc.getAllElements();
		List<CssSelector> list = new ArrayList<CssSelector>();
		for (Element e : es) {
			if (e.hasText()) {
				System.out.println(e.text());
				System.out.println(e.cssSelector());
				CssSelector cs = new CssSelector();
				cs.setSelector(e.cssSelector());
				cs.setText(e.text());
				// cs.setSize(e.cssSelector().split(">").length);
				// cs.setHref(e.attr("href"));
				list.add(cs);
			}
		}
		g.getPage().setList(list);
		// g.getPage().handlePage();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
