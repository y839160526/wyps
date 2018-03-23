package org.wyjc.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.wyjc.entity.PageConf;
import org.wyjc.entity.PageConfDTO;
import org.wyjc.entity.PageConfData;
import org.wyjc.entity.Pager;
import org.wyjc.entity.UserDTO;
import org.wyjc.gap.ColModel;
import org.wyjc.gap.PageData;
import org.wyjc.service.PageConfDataService;
import org.wyjc.service.PageConfService;
import org.wyjc.util.JsonUtil;
import org.wyjc.util.UserUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * @author chenying
 * 
 */
public class PageConfAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private PageConfService pageConfService;
	@Resource
	private PageConfDataService pageConfDataService;
	private String resultStr;
	private String id;
	private PageConf pageConf;
	private PageConfDTO pageConfDto = new PageConfDTO();
	private List<PageConfData> pageConfDataList;
	private List<PageConf> pageConfList;
	private List<String[]> valueList = new ArrayList<String[]>();
	private Pager pager = new Pager();
	private String colTitle;
	private String colNames;
	private int page = 0;
	private int rows = 10;
	private String oper;

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<PageConf> getPageConfList() {
		return pageConfList;
	}

	public void setPageConfList(List<PageConf> pageConfList) {
		this.pageConfList = pageConfList;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PageConf getPageConf() {
		return pageConf;
	}

	public void setPageConf(PageConf pageConf) {
		this.pageConf = pageConf;
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

	public List<PageConfData> getPageConfDataList() {
		return pageConfDataList;
	}

	public void setPageConfDataList(List<PageConfData> pageConfDataList) {
		this.pageConfDataList = pageConfDataList;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public List<String[]> getValueList() {
		return valueList;
	}

	public void setValueList(List<String[]> valueList) {
		this.valueList = valueList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PageConfDTO getPageConfDto() {
		return pageConfDto;
	}

	public void setPageConfDto(PageConfDTO pageConfDto) {
		this.pageConfDto = pageConfDto;
	}

	public String getColTitle() {
		return colTitle;
	}

	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}

	public String index() {
		return "index";
	}

	public String dataManage() {
		if (!UserUtil.isLogin()) {
			return "login";
		}
		UserDTO userDto = UserUtil.getUserInfo();
		pageConfList = pageConfService.getPageConfListByUserId(userDto.getId());
		for (int i = 0; i < pageConfList.size(); i++) {
			pageConfList.get(i).setDataCount(
					pageConfDataService.getPageConfDataCountById(String.valueOf(pageConfList.get(i).getId())) + "条");
		}
		return "dataManage";
	}

	public String pageConf() {
		if (!UserUtil.isLogin()) {
			return "login";
		}
		long total = pageConfDataService.getPageConfDataCountById(id);
		pager.setTotalnum(total);
		pager.setPagenum((pager.getCurrentnum() - 1) * pager.getPagesize());
		pager.setConfid(Long.parseLong(id));
		pageConfDataList = pageConfDataService.getPageConfDataByPager(pager);
		for (int i = 0; i < pageConfDataList.size(); i++) {
			if (pageConfDataList.get(i).getData() != null) {
				String[] data = pageConfDataList.get(i).getData().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
				valueList.add(data);
			}

		}
		pageConf = pageConfService.getPageConfById(id);
		String[] fenyeArr = pageConf.getNextSelector().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
		if (fenyeArr.length > 1) {
			pageConfDto.setNextSelector(fenyeArr[1]);
			pageConfDto.setFenye(fenyeArr[0]);
		} else {
			pageConfDto.setNextSelector("");
			pageConfDto.setFenye(pageConf.getNextSelector());
		}
		if (pageConfDto.getFenye().equals("下一页")) {
			pageConfDto.setFenye("0");
		} else if (pageConfDto.getFenye().equals(">")) {
			pageConfDto.setFenye("1");
		} else if (pageConfDto.getFenye().equals(">>")) {
			pageConfDto.setFenye("2");
		}
		//
		List<ColModel> colList = new ArrayList<ColModel>();
		List<String> nameList = new ArrayList<String>();
		ColModel col = new ColModel();
		col.setIndex("编号");
		col.setName("id");
		colList.add(col);
		nameList.add("编号");
		String[] confNames = pageConf.getFieldsName().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
		for (int i = 0; i < confNames.length; i++) {
			ColModel col1 = new ColModel();
			col1.setIndex(confNames[i]);
			col1.setName(confNames[i]);
			colList.add(col1);
			nameList.add(confNames[i]);
		}

		colTitle = JsonUtil.arrayToJson(nameList);
		colNames = JsonUtil.arrayToJson(colList);
		pageConfDto.setFieldsName(pageConf.getFieldsName().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}"));
		pageConfDto.setFieldsSelector(pageConf.getFieldsSelector().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}"));
		return "pageConf";
	}

	public String getColNames() {
		return colNames;
	}

	public void setColNames(String colNames) {
		this.colNames = colNames;
	}

	public String pageData() {
		PageData data = new PageData();
		long total = pageConfDataService.getPageConfDataCountById(id);
		data.setRecords(total);
		data.setPage(page);
		data.setTotal((total + rows - 1) / rows);
		// pager.setTotalnum(data.);
		pager.setPagenum((page - 1) * rows);
		pager.setPagesize(rows);
		pager.setConfid(Long.parseLong(id));
		pageConfDataList = pageConfDataService.getPageConfDataByPager(pager);
		for (int i = 0; i < pageConfDataList.size(); i++) {
			if (pageConfDataList.get(i).getData() != null) {
				String[] data1 = pageConfDataList.get(i).getData().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
				String[] str1 = { String.valueOf(pageConfDataList.get(i).getId()) };
				int str1Length = str1.length;
				int str2length = data1.length;

				str1 = Arrays.copyOf(str1, str1Length + str2length);// 数组扩容
				System.arraycopy(data1, 0, str1, str1Length, str2length);

				valueList.add(str1);
			}

		}
		data.setRows(valueList);

		this.resultStr = JsonUtil.objToJson(data);
		return "json";
	}

	public String pageConfData() {
		PageData data = new PageData();
		/*
		 * long total = pageConfDataService.getPageConfDataCountById(id);
		 * data.setRecords(total); data.setPage(page); data.setTotal(total /
		 * rows);
		 */
		UserDTO userDto = UserUtil.getUserInfo();
		pageConfList = pageConfService.getPageConfListByUserId(userDto.getId());
		data.setRows(pageConfList);

		this.resultStr = JsonUtil.objToJson(data);
		return "json";
	}

	public String updateConf() {
		Map map = new HashMap();
		pageConf.setUpdatetime(new Date());
		pageConfService.updatePageConfById(pageConf);
		map.put("status", "success");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public String exportExcel() {
		if (!UserUtil.isLogin()) {
			return "login";
		}
		UserDTO userDto = UserUtil.getUserInfo();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/vnd.ms-excel");
		pageConf = pageConfService.getPageConfById(id);
		pageConfDataList = pageConfDataService.getPageConfDataByConfId(id);
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = java.net.URLEncoder.encode(pageConf.getTitle() + "_" + "总数" + pageConfDataList.size(),
					"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("数据");
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 10);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cellStyle.setFont(font);// 选择创建的字体格式

		HSSFRow row = sheet.createRow((int) 0);// 创建一行
		HSSFCell cell = null;
		String[] confNames = pageConf.getFieldsName().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
		for (int i = 0; i < confNames.length; i++) {
			cell = row.createCell((short) i);// 创建一列
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(confNames[i]);
			cell.setCellStyle(cellStyle);
			sheet.setColumnWidth((short) i, (short) 10000);
		}
		cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(HSSFCellStyle.VERTICAL_TOP); 
		for (int i = 0; i < pageConfDataList.size(); i++) {

			if (pageConfDataList.get(i).getData() != null) {
				HSSFRow crow = sheet.createRow((int) i + 1);// 创建一行
				String[] data = pageConfDataList.get(i).getData().split("\\{\\{\\{\\{\\{\\}\\}\\}\\}\\}");
				for (int j = 0; j < data.length; j++) {
					cell = crow.createCell((short) j);// 创建一列
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(data[j]));
					cell.setCellStyle(cellStyle);
				}
			}

		}
		try {
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String navGrid(){
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			pageConfDataService.deleteConfData(ids[i]);
		}
		return "json";
	}

}
