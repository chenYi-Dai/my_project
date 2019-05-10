package org.spring.boot.demo.servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.entity.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;


@Controller
public class fileName {


	private Logger logger = LoggerFactory.getLogger(UploadServlet.class);

	private static final int sizeThreshold = 50*1024*1024;
	/**
	 * 批量新增词条（上传txt文件）
	 * @param request
	 * @return
	 */
	/*	@ResponseBody
	@PostMapping(value = "/upload")
	public ResultVO uploadWord(HttpServletRequest request){
		//BaseResponse<WordItem> result = new BaseResponse<WordItem>();
		ResultVO result = new ResultVO();
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartHttpServletRequest.getFile("file");
		int rowNumber = 0;
		try {
			byte[] content = file.getBytes();

			String filePath = getFilePath(file);
			logger.info("---filePath-->"+filePath);
			FileOutputStream outputStream = new FileOutputStream(filePath);
			outputStream.write(content);
			outputStream.close();
			String encoding="UTF-8";
            File file2=new File(filePath);
            if(file2.isFile() && file2.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file2),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                    rowNumber++;
                }
                System.out.println(rowNumber);
                read.close();
		    }else{
		    	//log.info("找不到指定的文件");
		    }
            result.setCode(200);
            result.setMsg("导入完成，共导入"+rowNumber+"个词条！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
	        result.setMsg("导入失败，服务器错误！");
		}
		return result;
	}
	 */
	private String getFilePath(MultipartFile file) {
		String guid = java.util.UUID.randomUUID().toString().replaceAll("-", "");
		String fileName = file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String fileNameWithoutExt = fileName.replaceAll(extName, "");
		String fileNewName = fileNameWithoutExt + "-" + guid + extName;
		//		String filePath = fileUploadConfig.getSavePath() + fileNewName;
		String filePath = "D:/temmp/" + fileNewName;
		System.out.println("filePath" + filePath);
		return filePath;
	}


	@ResponseBody
	@PostMapping(value = "/upload")
	public void doPost(HttpServletRequest request,MultipartHttpServletRequest multiReq) throws IOException{
		//获取上传文件的路径
		ResultVO<Object> result = new ResultVO<>();
		MultipartFile file = multiReq.getFile("file");
		String fileNameUrl = multiReq.getFile("file").getOriginalFilename();
		logger.info("上传文件的路径==>"+fileNameUrl);
		int rowNumber = 0;
		try {

			byte[] content = file.getBytes();

			String filePath = getFilePath(file);
			logger.info("---filePath-->"+filePath);
			String sd = "D:\\temp\\file\\";
			File saveFilePath = new File(sd,fileNameUrl);
			/*if(!saveFilePath.exists()){
				boolean createNewFile = saveFilePath.createNewFile();
			}*/
			InputStream inputStream=file.getInputStream();
			FileOutputStream fileOutputStream=new FileOutputStream(saveFilePath);
			IOUtils.copy(inputStream,fileOutputStream);
			inputStream.close();fileOutputStream.close();
			/*BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFilePath));
			out.write(file.getBytes());
            out.flush();
            out.close();*/



		} catch (Exception e) {
			e.printStackTrace();
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




	@RequestMapping(value = "/upload2")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return "文件为空";
			}
			// 获取文件名
			String fileName = file.getOriginalFilename();
			logger.info("上传的文件名为：" + fileName);
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			logger.info("文件的后缀名为：" + suffixName);

			// 设置文件存储路径10 148000 30*100 2
			String filePath = "D://aim//";
			String path = filePath + fileName;

			File dest = new File(path);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();// 新建文件夹
			}
			file.transferTo(dest);// 文件写入
			String saveFilePath = path +"上传成功!";
			logger.info("--saveFilePath-->"+saveFilePath);
			return saveFilePath;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败";
	}
}
