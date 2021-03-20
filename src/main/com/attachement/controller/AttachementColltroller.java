package com.lhz.tourproduct.attachement.colltroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lhz.tourproduct.attachement.entity.Attachements;
import com.lhz.tourproduct.project.commons.ControllerException;
import com.lhz.tourproduct.project.commons.JsonResult;
import com.sun.xml.internal.ws.util.ReadAllStream;

import ryan.AttachementService;

@Controller
@RequestMapping("/attachement")
public class AttachementColltroller extends ControllerException {
	@Resource
	private AttachementService attachementService;
	
	@RequestMapping("/attaUI.do")
	public String attachement(){
		return "attachement/attachements";
	}
	
	@RequestMapping("/editUI")
	public String attachementEdit(){
		return "attachement/attachements_edit";
	}
	
	@RequestMapping("/doFindAttachement")
	@ResponseBody
	public JsonResult<Map<String,Object>> doFindAttachement(Integer currentPage,String title){
		Map<String,Object> map=attachementService.findAttachements(currentPage,title);
		return new JsonResult<Map<String,Object>>(map);
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	/**�ϴ��ļ��������Ʋ���� MultipartFile��ø����ļ���
	 * MultipartFile ʹ�õļ�����jquery.form.js��ܣ� �Լ�
		springmvc��ܡ���Ҫʵ���첽�ļ��ϴ���ͬʱ��װ����*/
	public JsonResult<Integer> doAttachementService(String title,
												Integer athType,
												Integer belongId,
												MultipartFile mFile,
										HttpServletRequest request){
		//��ȡ���·��(��ʵ·��)
		String serverPath=request.getServletContext().getRealPath("/");
		System.out.println("serverPath:"+serverPath);
		Integer n=attachementService.insertAttachementService(title, athType, belongId, mFile,serverPath);
		return new JsonResult<Integer>(n);
	}
	
	/**�ļ�����*/
	@RequestMapping("/toDownLoad")
	@ResponseBody
	public byte[] doDownLoad(Integer id,HttpServletResponse response) throws IOException{
		//�����ļ�����Ӧ��Ϣͷ(�����ļ��̶�д��)
		File file=attachementService.findByIdFilePath(id);
		response.setContentType("appliction/octet-stream");
		response.setHeader("Content-disposition","attachment;filename="+file.getName());
		//System.out.println(file.getName());
		//���ݶ�����ʵ·������һ��Path����
		//System.out.println("file.getPath()="+file.getPath());
		Path path=Paths.get(file.getPath());
		return Files.readAllBytes(path);
	}
	
	@RequestMapping("/toDeleteAttachements")
	@ResponseBody
	public JsonResult<Integer> doDeleteAttachements(String selects){
		int rows=attachementService.findByIdDeleteAttachements(selects);
		return new JsonResult<Integer>(rows);
	}
	
	@RequestMapping("/toFindAttachementsObject")
	@ResponseBody
	public JsonResult<Map<String,Object>> doFindAttachementsObject(Integer id){
		Map<String,Object> map=attachementService.findByIdAttachementsObject(id);
		return new JsonResult<Map<String,Object>>(map);
	}
	
	@RequestMapping("/toUpdateAttachements")
	@ResponseBody
	public JsonResult<Boolean> doUpdateAttachements(Integer id,String title){
		boolean b=attachementService.findByIdUpdateAttachements(id, title);
		return new JsonResult<Boolean>(b);
	}
	
}

