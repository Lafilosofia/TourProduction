package cn.ryan.ttms.attachement.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.ryan.ttms.attachement.dao.AttachementDao;
import cn.ryan.ttms.attachement.entity.Attachements;
import cn.ryan.ttms.common.web.PageObject;
import ryan.AttachementService;

@Service
public class AttachementServiceImpl implements AttachementService {
	@Resource 
	private AttachementDao amd;
	
	/**文件上传*/
	public int insertAttachementService(String title, Integer athType, Integer belongId, MultipartFile mFile,String serverPath) {
		byte[] fileByte;
		String fileDisgest;
		String fileName;
		File file;
		try {
			//通过MultipartFile获取文件内容的字节
			fileByte = mFile.getBytes();
			//给文件内容字节进行md5形成文件摘要
			fileDisgest=DigestUtils.md5Hex(fileByte);
			 int countDisgest=amd.countDisgest(fileDisgest);
			 System.out.println("countDisgest="+countDisgest);
		    if(countDisgest>0){
		    	throw new RuntimeException("文件已存在");
		    }
			//通过MultipartFile获取文件的真实名字
		    fileName=mFile.getOriginalFilename();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		    String dateStr=sdf.format(new Date());
		   //FilenameUtils.getExtension(fileName)获取文件的后缀名(文件的扩展名)
		    String newFileName=UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(fileName);
		    String filePath=serverPath+"/uploads/"+dateStr+"/"+newFileName;
		    file=new File(filePath);
		    File parentPath=file.getParentFile();
		    //parentPath:D:\tts9\tts9\apache-tomcat-7.0.67\
		    //wtpwebapps\ttmsDemo\
		    //uploads\2017\11\24
		    System.out.println("parentPath:"+parentPath);
		    //如果parentPath文件不存在就创建一个
		    if(!parentPath.exists()){
		    	parentPath.mkdirs();
		    }
		   
		    //执行上传文件
		    mFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("上传失败");
		}
		Attachements acm=new Attachements();
		acm.setTitle(title);
		acm.setAthType(athType);
		acm.setBelongId(belongId);
		acm.setFileDisgest(fileDisgest);
		acm.setFileName(fileName);
		acm.setFilePath(file.getPath());
		acm.setContentType(mFile.getContentType());
		acm.setCreatedUser("admin");
		acm.setCreatedTime(new Date(System.currentTimeMillis()));
		acm.setCreatedTime(new Date(System.currentTimeMillis()));
		acm.setModifiedUser("admin");
		int n=amd.insertAttachement(acm);
		System.out.println(n);
		System.out.println(acm);
		return n;
	}
	
	/**查询附件Attachements数据*/
	public Map<String,Object> findAttachements(Integer currentPage,String title) {
		PageObject pageObject=new PageObject();
		pageObject.setCurrentPage(currentPage);
		int countRows=amd.rowCount(title);
		pageObject.setCountRows(countRows);
		int indexPage=pageObject.getIndexPage();
		int pageSize=pageObject.getPageSize();
		List<Attachements> list=amd.findAttachements(indexPage,pageSize,title);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pageObject", pageObject);
		map.put("list", list);
		return map;
	}
	
	//文件下载
	/**通过id查找Attachements里的filePath*/
	public File findByIdFilePath(Integer id) {
		if(id==null){
			throw new RuntimeException("id不能为空");
		}
		Attachements attachements=amd.findByIdFilePath(id);
		if(attachements==null){
			throw new RuntimeException("没有对应的数据");
		}
		File file=new File(attachements.getFilePath());
		//判断file在数据库中是否存在
		if(!file.exists()){
			throw new RuntimeException("文件已经不存在");
		}
		
		return file;
	}

	/**通过多个id删除Attachements*/
	public int findByIdDeleteAttachements(String selects) {
		if(selects==null || selects.length()==0){
			throw new RuntimeException("id不能为空");
		}
		String[] ids=selects.split(",");
		int rows=amd.findByIdDeleteAttachements(ids);
		return rows;
	}

	/**通过id查询Attachements数据*/
	public Map<String, Object> findByIdAttachementsObject(Integer id) {
		if(id==null){
			throw new RuntimeException("id不能为空!");
		}
		Attachements attachementObject=amd.findByIdAttachementsObject(id);
		List<Attachements> list=amd.findAttachementsFileName();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("aObject",attachementObject );
		return map;
	}

	/**通过id修改Attachements中的title*/
	public boolean findByIdUpdateAttachements(Integer id, String title) {
		if(id==null){
			throw new RuntimeException("id不能为空");
		}
		if(title==null || title.trim().isEmpty()){
			throw new RuntimeException("标题不能为空");
		}
		boolean b=amd.findByIdUpdateAttachements(id, title);
		return b;
	}



}
