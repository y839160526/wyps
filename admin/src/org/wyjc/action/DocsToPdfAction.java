package org.wyjc.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.wyjc.util.JsonUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户管理
 * 
 * @author chenying
 * 
 */
public class DocsToPdfAction extends ActionSupport {
	// 封装上传文件域的属性
	private File image;
	// 封装上传文件类型的属性
	private String imageContentType;
	// 封装上传文件名的属性
	private String imageFileName;
	// 接受依赖注入的属性
	private String savePath;
	private String resultStr;

	public String uploadDocsFile() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		String realpath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/docstopdf");
		if (image != null) {
			File savefile = new File(new File(realpath), imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(image, savefile);
				savePath = realpath + "/" + imageFileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		image=null;
		return "success";

	}

	public String uploadDocsFileToPdf() {
		try {
			Process proc = Runtime.getRuntime().exec("java -jar d://docs-to-pdf-converter-1.8.jar -input " + savePath);
			System.out.println(proc.waitFor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		File file = new File(savePath);
		String getFilename = file.getName();
		String name = getFilename .substring(0,getFilename .lastIndexOf("."));
		map.put("downloadurl", "docstopdf/"+name+".pdf");
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

}
