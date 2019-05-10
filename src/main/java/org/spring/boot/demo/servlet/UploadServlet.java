package org.spring.boot.demo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/uploadFile")
public class UploadServlet extends HttpServlet{

	private static final long serialVersionUID = -3846881939658249206L;
	
	private static final int sizeThreshold = 50*1024*1024;
	
	private Logger logger = LoggerFactory.getLogger(UploadServlet.class);
	
	@RequestMapping("/file")
	@ResponseBody
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 定义上传文件目录  
		String uploadDir = this.getServletContext().getRealPath("/temp");
		DiskFileItemFactory disk = new DiskFileItemFactory();
		 // 设置内存区块大小4KB  
		disk.setSizeThreshold(sizeThreshold);
		// 设置暂存容器，当上传文件大于设置的内存块大小时，用暂存容器做中转  
		disk.setRepository(new File(this.getServletContext().getRealPath("/upload")));
		ServletFileUpload fileUpload = new ServletFileUpload(disk);
		//设置上传文件的大小
		fileUpload.setFileSizeMax(1024*1024*10);
		List<FileItem> listItem = null;
		try {
			listItem = fileUpload.parseRequest((RequestContext) request);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("request请求转换list出错--->", e);
		}
		Iterator<FileItem> fileItemIterator = listItem.iterator();  
		FileItem fileItem = null;
		while(fileItemIterator.hasNext()){
			fileItem = fileItemIterator.next();  
			// 普通文件框上传 
			if (fileItem.isFormField()) {  
                String filedName = fileItem.getFieldName();  
                String filedValue = fileItem.getString("GBK");// 编码格式  
                System.out.println(filedName);// 文件框名称  
                System.out.println(filedValue);// 文件的值  
            } else {  
            	String filedName = fileItem.getFieldName();// 文件上传框的名称
            	// 获取文件上传的文件名  
                String OriginalFileName = takeOutFileName(fileItem.getName());  
                System.out.println("原始文件名："+OriginalFileName);  
                if(!"".equals(OriginalFileName)){
                	// 根据上传的文件名重新命名  
                    String newFileName = getNewFileName(OriginalFileName);  
                    System.out.println("重新名："+newFileName);  
                    File writeToFile = new File(uploadDir + File.separator  
                            + newFileName);  
                    try {
						fileItem.write(writeToFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
                }
            }
		}
		
		
		
		
	}
	
	private String takeOutFileName(String filePath) {  
        String fileName = filePath;  
        if (null != filePath && !"".equals(filePath)) {  
            int port = filePath.lastIndexOf("\\");  
            if(port != -1){  
                fileName = filePath.substring(port+1);  
            }  
        }  
        return fileName;  
    }  
	
	private String getNewFileName(String originalFileName) {  
        StringBuffer newFileName = new StringBuffer();  
        if (null != originalFileName && !"".equals(originalFileName)) {  
            int port = originalFileName.lastIndexOf(".");  
            String type = "";  
            String fileName = "";  
            if (port != -1) {  
                type = originalFileName.substring(port + 1);  
                fileName = originalFileName.substring(0, port);  
            } else {  
                fileName = originalFileName;  
            }  
            StringBuffer suffix = new StringBuffer("_");  
            suffix.append(Calendar.getInstance().getTimeInMillis());  
            suffix.append("_");  
            suffix.append(new Random().nextInt(100));  
            newFileName.append(fileName);  
            newFileName.append(suffix);  
            newFileName.append(".");  
            newFileName.append(type);  
        }  
        return newFileName.toString();  
    }  

}
