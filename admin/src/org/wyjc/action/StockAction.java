package org.wyjc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wyjc.entity.Commend;
import org.wyjc.util.JsonUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 鐢ㄦ埛绠＄悊
 * 
 * @author chenying
 * 
 */
public class StockAction extends ActionSupport {
	private String resultStr;

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String commendList() {
		List<Commend> commendList = new ArrayList<Commend>();
		Commend commend = new Commend();
		commend.setId(1);
		commend.setTitle("惠天热电");
		commend.setDescription("沈阳惠天热电股份有限公司前身为原沈阳热力股份有限公司，发起人为沈阳市热力供暖公司，国家大型二级企业，成立于1980年5月22");
		commendList.add(commend);
		commend = new Commend();
		commend.setId(1);
		commend.setTitle("惠天热电");
		commend.setDescription("沈阳惠天热电股份有限公司前身为原沈阳热力股份有限公司，发起人为沈阳市热力供暖公司，国家大型二级企业，成立于1980年5月22");
		commendList.add(commend);
		commend = new Commend();
		commend.setId(1);
		commend.setTitle("惠天热电");
		commend.setDescription("沈阳惠天热电股份有限公司前身为原沈阳热力股份有限公司，发起人为沈阳市热力供暖公司，国家大型二级企业，成立于1980年5月22");
		commendList.add(commend);
		commend = new Commend();
		commend.setId(1);
		commend.setTitle("惠天热电");
		commend.setDescription("沈阳惠天热电股份有限公司前身为原沈阳热力股份有限公司，发起人为沈阳市热力供暖公司，国家大型二级企业，成立于1980年5月22");
		commendList.add(commend);
		Map m = new HashMap();
		m.put("list", commendList);
		this.resultStr = JsonUtil.returnDefaultAjax(m);
		return "json";
	}
}
