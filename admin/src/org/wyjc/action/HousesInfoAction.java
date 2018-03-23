package org.wyjc.action;

import java.util.List;

import javax.annotation.Resource;

import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.entity.HousesInfoDTO;
import org.wyjc.entity.UserDTO;
import org.wyjc.service.AreaService;
import org.wyjc.service.HousesInfoService;
import org.wyjc.util.JsonUtil;
import org.wyjc.util.Pager;
import org.wyjc.util.UserUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 业主管理
 * 
 * @author chenying
 * 
 */
public class HousesInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private HousesInfoService housesInfoService;
	@Resource
	private AreaService areaService;
	private List<AreaDTO> areaList;
	private List<HousesInfoDTO> housesInfoList;
	private String[] ids;
	private String id;
	private Pager pager = new Pager();
	private int pageNum = 1;
	private String resultStr;
	private HousesInfoDTO housesInfo;

	public List<AreaDTO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaDTO> areaList) {
		this.areaList = areaList;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public String erShouHousesInfoList() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		pager.setPageNum(pageNum);
		pager.setCurrentPage((pageNum - 1) * pager.getNumPerPage());
		pager = housesInfoService.listByPager(pager);
		return "erShouHousesInfoList";
	}

	public String create() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		UserDTO user = UserUtil.getUserInfo();
		areaList = areaService.getAreasByCityId(user.getCity());

		return "createHouseInfo";
	}

	public String save() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		housesInfo.setType("1");
		housesInfoService.insert(housesInfo);
		this.resultStr = JsonUtil.returnAjax("200", "添加二手房成功",
				"erShouHousesInfoList", "closeCurrent",
				"housesInfoAction!erShouHousesInfoList.action");
		return "json";
	}

	public String delete() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		for (int i = 0; i < ids.length; i++) {
			HousesInfoDTO housesInfo = new HousesInfoDTO();
			housesInfo.setId(Integer.parseInt(ids[i]));
			housesInfoService.delete(housesInfo);
		}
		this.resultStr = JsonUtil.returnAjax("200", "删除二手房成功",
				"erShouHousesInfoList", "closeCurrent",
				"housesInfoAction!erShouHousesInfoList.action");
		return "json";
	}

	public String deleteHousesInfo() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		HousesInfoDTO housesInfo = new HousesInfoDTO();
		housesInfo.setId(Integer.parseInt(id));
		housesInfoService.delete(housesInfo);

		this.resultStr = JsonUtil.returnAjax("200", "删除二手房成功",
				"erShouHousesInfoList", "forward",
				"housesInfoAction!erShouHousesInfoList.action");
		return "json";
	}

	public String edit() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		UserDTO user = UserUtil.getUserInfo();
		areaList = areaService.getAreasByCityId(user.getCity());
		// ================是否登录(权限判断)结束===================
		housesInfo = housesInfoService.get(Integer.parseInt(id));
		return "createHouseInfo";
	}
	public String update() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		//housesInfoService.
		this.resultStr = JsonUtil.returnAjax("200", "修改业主成功", "customerList",
				"closeCurrent", "customerAction!list.action");
		return "json";
	}
	public HousesInfoService getHousesInfoService() {
		return housesInfoService;
	}

	public void setHousesInfoService(HousesInfoService housesInfoService) {
		this.housesInfoService = housesInfoService;
	}

	public List<HousesInfoDTO> getHousesInfoList() {
		return housesInfoList;
	}

	public void setHousesInfoList(List<HousesInfoDTO> housesInfoList) {
		this.housesInfoList = housesInfoList;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public HousesInfoDTO getHousesInfo() {
		return housesInfo;
	}

	public void setHousesInfo(HousesInfoDTO housesInfo) {
		this.housesInfo = housesInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
