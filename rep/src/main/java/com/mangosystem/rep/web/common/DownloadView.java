package com.mangosystem.rep.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	public void Download(){
		setContentType("application/download; UTF-8");
	}


	@Override
	protected void renderMergedOutputModel( Map<String, Object> model, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		FileInputStream fis = null;

		try {
			if (model!=null) {
				String fileRealName = (String)model.get("realname");

				File file = (File)model.get("downloadFile");
				response.setContentLength((int)file.length());
				System.out.println("FileName : " + fileRealName);
				fileRealName = URLEncoder.encode(fileRealName, "UTF-8");
				response.setContentType("application/octet-stream;charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileRealName + "\";");
				response.setHeader("Content-Transfer-Encoding", "binary");

				OutputStream out = response.getOutputStream();

				fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, out);
				out.flush();
			} else {
				response.setHeader("Cache-Control","no-cache");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DownloadView File --> File Not Found!!! ");
		} finally {
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){}
			}
		}
	}

}
