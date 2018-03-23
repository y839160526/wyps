package org.wyjc.gap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Page {
	private Map<String, List<CssSelector>> sizeMap = new HashMap<String, List<CssSelector>>();
	private Map<String, List<CssSelector>> isizeMap = new HashMap<String, List<CssSelector>>();
	private Map<String, List<CssSelector>> selectMap = new HashMap<String, List<CssSelector>>();
	private List<CssSelector> list;
	private List<CssSelector> clist;
	private List<TargetKey> keyList = new ArrayList<TargetKey>();
	private NextPage nextPage = new NextPage();

	public List<CssSelector> getClist() {
		return clist;
	}

	public void setClist(List<CssSelector> clist) {
		this.clist = clist;
	}

	public List<CssSelector> getList() {
		return list;
	}

	public void setList(List<CssSelector> list) {
		this.list = list;
	}

	public Map<String, List<CssSelector>> getSizeMap() {
		return sizeMap;
	}

	public void setSizeMap(Map<String, List<CssSelector>> sizeMap) {
		this.sizeMap = sizeMap;
	}

	public Map<String, List<CssSelector>> getIsizeMap() {
		return isizeMap;
	}

	public void setIsizeMap(Map<String, List<CssSelector>> isizeMap) {
		this.isizeMap = isizeMap;
	}

	public void handlePage() {
		try {
			initSizeMap();
			initISizeMap();
			// sortisizeMap();
			analysisISizeMap();
			initRegex();
			getNextPage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		initSelectionMap("");

	}

	public List<CssSelector> analysiscontent(int screenWidth, int screenHeight, List<PageNode> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getY() > screenHeight * 0.2 || list.get(i).getY() < screenHeight * 0.9) {
				// if (list.get(i).getFontsize() >= 18) {
				System.out.println("++++++++++++++"+list.get(i).getSelector());
				System.out.println(list.get(i).getText());
				// }
			}

		}
		List<CssSelector> retList = new ArrayList<CssSelector>();
		return retList;
	}

	public List<CssSelector> parseContent(String selectionText) {
		List<CssSelector> cslist = new ArrayList<CssSelector>();
		for (CssSelector cs : list) {
			if (cs.getText().trim().equals(selectionText.trim())) {
				cslist.add(cs);
			}
		}
		return cslist;
	}

	public List<CssSelector> parseContent() {
		for (int i = 0; i < clist.size(); i++) {
			int k = clist.get(i).getSelector().length();
			for (int j = i; j < clist.size(); j++) {
				if (clist.get(i).getText().equals(clist.get(j).getText())) {
					if (clist.get(j).getSelector().length() > k) {
						k = clist.get(j).getSelector().length();
					}
				}
			}
			if (clist.get(i).getSelector().length() < k) {
				clist.get(i).setAdd(true);
			}
			for (int j = i; j < clist.size(); j++) {
				if (clist.get(i).getText().equals(clist.get(j).getText())) {
					if (clist.get(j).getSelector().length() < k) {
						clist.get(j).setAdd(true);
					}
				}
			}
			for (String key : isizeMap.keySet()) {
				List<CssSelector> csList = isizeMap.get(key);
				for (CssSelector cs : csList) {
					if(clist.get(i).getSelector().equals(cs.getSelector())){
						clist.get(i).setAdd(true);
					}
				}
			}
		}
		
		Iterator<CssSelector> cssIter = clist.iterator();
		while (cssIter.hasNext()) {
			CssSelector css = cssIter.next();
			if (css.isAdd()) {
				cssIter.remove();
			}
		}
		

		return clist;
	}

	public void initSelectionMap(String selectionText) {
		for (String key : isizeMap.keySet()) {
			System.out.println("==========================================================================");
			System.out.println(key);
			List<CssSelector> csList = isizeMap.get(key);
			for (CssSelector cs : csList) {
				System.out.println(cs.getText());
				System.out.println(cs.getSelector());
				System.out.println(cs.getHref());
				/*if (cs.getText() != null) {
					if (cs.getText().contains(selectionText)) {
						selectMap.put(key, csList);
						continue;
					}
				}*/
			}
		}
	}

	public NextPage getNextPage() {

		for (String key : isizeMap.keySet()) {
			List<CssSelector> csList = isizeMap.get(key);
			int numCount = 0;
			for (CssSelector cs : csList) {
				if (StringUtils.isNumeric(cs.getText())) {
					numCount++;
				}
			}
			if (numCount > 1) {
				for (CssSelector cs : csList) {
					if (!StringUtils.isNumeric(cs.getText())) {
						if (cs.getText().equals("下一页")) {
							nextPage.setNextPageType("下一页");
							nextPage.setNextStep(csList.get(0).getRegex());
							nextPage.setHasNextConf(true);
							break;
						} else if (cs.getText().equals(">>")) {
							nextPage.setNextPageType(">>");
							nextPage.setNextStep(csList.get(0).getRegex());
							nextPage.setHasNextConf(true);
							break;
						} else if (cs.getText().equals(">")) {
							nextPage.setNextPageType(">");
							nextPage.setNextStep(csList.get(0).getRegex());
							nextPage.setHasNextConf(true);
							break;
						}else if (cs.getText().equals("下一页 →")) {
							nextPage.setNextPageType("下一页 →");
							nextPage.setNextStep(csList.get(0).getRegex());
							nextPage.setHasNextConf(true);
							break;
						}else if (cs.getText().equals("下一页>")) {
							nextPage.setNextPageType("下一页>");
							nextPage.setNextStep(csList.get(0).getRegex());
							nextPage.setHasNextConf(true);
							break;
						}
					}
				}

			}

		}

		return nextPage;
	}

	public List<CssSelector> getHtmlList() {
		return isizeMap.get(keyList.get(0).getKey());
	}

	public void initRegex() {
		for (String size : isizeMap.keySet()) {
			List<CssSelector> cList = isizeMap.get(size);
			String cssSelector = cList.get(0).getSelector();
			String[] cssSelectorArr = cssSelector.split(">");
			int index = cList.get(0).getIndex();
			String node = cssSelectorArr[index];
			if (node.contains("nth-child")) {
				cssSelectorArr[index] = node.split(":")[0];
			}
			cssSelector = "";
			for (int i = 0; i < cssSelectorArr.length; i++) {
				cssSelector = cssSelector + cssSelectorArr[i] + ">";
			}
			cssSelector = cssSelector.substring(0, cssSelector.length() - 1);
			isizeMap.get(size).get(0).setRegex(cssSelector);
		}
	}

	public void sortisizeMap() {
		Collections.sort(keyList, new Comparator<TargetKey>() {
			public int compare(TargetKey k1, TargetKey k2) {
				if (k1.getLevel() < k2.getLevel()) {
					return 1;
				}
				return -1;
			}
		});
	}

	public void analysisISizeMap() {
		Iterator<String> iterator = isizeMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (removeList(key, isizeMap.get(key))) {
				iterator.remove();
				removeKey(key);
			}

		}
	}

	public void removeKey(String key) {
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).getKey().equals(key)) {
				keyList.remove(i);
			}
		}
	}

	public boolean removeList(String key, List<CssSelector> list) {
		boolean remove = false;
		if (list.size() <=1) {
			return true;
		}
		/*
		 * if (wordNumLessFour(list)) { return true; } if (wordNumSame(list)) {
		 * return true; }
		 */
		// if (selectorSizeLessThree(list)) {
		// return true;
		// }

		return remove;
	}

	public boolean wordNumLessFour(List<CssSelector> list) {
		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().length() > 4) {
				flag = false;
			}
		}
		return flag;
	}

	public boolean wordNumSame(List<CssSelector> list) {
		boolean flag = true;
		int num = list.get(0).getText().length();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().length() != num) {
				flag = false;
			}
		}
		return flag;
	}

	public boolean selectorSizeLessThree(List<CssSelector> list) {
		String[] selectorArr = list.get(0).getSelector().split(">");
		if (selectorArr.length <= 3 && selectorArr[0].trim().equals("html")) {
			return true;
		}
		return false;
	}

	public void initSizeMap() {
		for (CssSelector cs : list) {
			String size = String.valueOf(cs.getSize());
			if (sizeMap.get(size) == null) {
				List<CssSelector> cList = new ArrayList<CssSelector>();
				cList.add(cs);
				sizeMap.put(size, cList);
			} else {
				sizeMap.get(size).add(cs);
			}
		}
	}

	public void initISizeMap() {
		for (String size : sizeMap.keySet()) {
			List<CssSelector> cList = sizeMap.get(size);
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).isAdd()) {
					continue;
				}
				String key = size + System.currentTimeMillis() + Math.random();
				TargetKey targetKey = new TargetKey();
				int k = -1;
				List<CssSelector> csList = new ArrayList<CssSelector>();
				int wordAvgNum = 0;
				for (int j = i; j < cList.size(); j++) {
					int index = compareSelector(cList.get(i).getSelector(), cList.get(j).getSelector());
					if (index != -1) {
						if (k == -1 || k == index) {
							k = index;
							cList.get(j).setIndex(index);
							cList.get(j).setAdd(true);
							csList.add(cList.get(j));
							wordAvgNum = wordAvgNum + cList.get(j).getText().length();
						}

					}
				}
				if (wordAvgNum > 0) {
					wordAvgNum = wordAvgNum / csList.size();
					csList.get(0).setWordAvgNum(wordAvgNum);
				}
				targetKey.setKey(key);
				targetKey.setLevel(wordAvgNum);
				keyList.add(targetKey);
				isizeMap.put(key, csList);
			}
		}
	}

	public int compareSelector(String a, String b) {
		String[] aArr = a.split(">");
		String[] bArr = b.split(">");

		if (aArr.length != bArr.length) {
			return -1;
		}
		String lastNode = aArr[aArr.length - 1];
		if (lastNode.contains(".")) {
			lastNode = lastNode.substring(0, lastNode.indexOf("."));
			aArr[aArr.length - 1] = lastNode;
		}
		lastNode = bArr[bArr.length - 1];
		if (lastNode.contains(".")) {
			lastNode = lastNode.substring(0, lastNode.indexOf("."));
			bArr[bArr.length - 1] = lastNode;
		}
		int index = 0;
		int k = -1;
		for (int i = 0; i < aArr.length; i++) {
			if (!aArr[i].equals(bArr[i])) {
				index++;
				k = i;
			}
		}
		if (index == 1) {
			if (!aArr[k].contains("nth-child")) {
				k = -1;
			}
			return k;
		}
		return -1;
	}

	public List<TargetKey> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<TargetKey> keyList) {
		this.keyList = keyList;
	}

	public Map<String, List<CssSelector>> getSelectMap() {
		return selectMap;
	}

	public void setSelectMap(Map<String, List<CssSelector>> selectMap) {
		this.selectMap = selectMap;
	}

	public static void main(String[] args) {
		String lastNode = "a.next";
		if (lastNode.contains(".")) {
			lastNode = lastNode.substring(0, lastNode.indexOf("."));
		}
		System.out.println(lastNode);
	}

}
