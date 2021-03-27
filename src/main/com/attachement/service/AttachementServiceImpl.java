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
	
	/**�ļ��ϴ�*/
	public int insertAttachementService(String title, Integer athType, Integer belongId, MultipartFile mFile,String serverPath) {
		byte[] fileByte;
		String fileDisgest;
		String fileName;
		File file;
		try {
			//ͨ��MultipartFile��ȡ�ļ����ݵ��ֽ�
			fileByte = mFile.getBytes();
			//���ļ������ֽڽ���md5�γ��ļ�ժҪ
			fileDisgest=DigestUtils.md5Hex(fileByte);
			 int countDisgest=amd.countDisgest(fileDisgest);
			 System.out.println("countDisgest="+countDisgest);
		    if(countDisgest>0){
		    	throw new RuntimeException("�ļ��Ѵ���");
		    }
			//ͨ��MultipartFile��ȡ�ļ�����ʵ����
		    fileName=mFile.getOriginalFilename();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		    String dateStr=sdf.format(new Date());
		   //FilenameUtils.getExtension(fileName)��ȡ�ļ��ĺ�׺��(�ļ�����չ��)
		    String newFileName=UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(fileName);
		    String filePath=serverPath+"/uploads/"+dateStr+"/"+newFileName;
		    file=new File(filePath);
		    File parentPath=file.getParentFile();
		    //parentPath:D:\tts9\tts9\apache-tomcat-7.0.67\
		    //wtpwebapps\ttmsDemo\
		    //uploads\2017\11\24
		    System.out.println("parentPath:"+parentPath);
		    //���parentPath�ļ������ھʹ���һ��
		    if(!parentPath.exists()){
		    	parentPath.mkdirs();
		    }
		   
		    //ִ���ϴ��ļ�
		    mFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("�ϴ�ʧ��");
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
	
	/**��ѯ����Attachements����*/
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
	
	//�ļ�����
	/**ͨ��id����Attachements���filePath*/
	public File findByIdFilePath(Integer id) {
		if(id==null){
			throw new RuntimeException("id����Ϊ��");
		}
		Attachements attachements=amd.findByIdFilePath(id);
		if(attachements==null){
			throw new RuntimeException("û�ж�Ӧ������");
		}
		File file=new File(attachements.getFilePath());
		//�ж�file�����ݿ����Ƿ����
		if(!file.exists()){
			throw new RuntimeException("�ļ��Ѿ�������");
		}
		
		return file;
	}

	/**ͨ�����idɾ��Attachements*/
	public int findByIdDeleteAttachements(String selects) {
		if(selects==null || selects.length()==0){
			throw new RuntimeException("id����Ϊ��");
		}
		String[] ids=selects.split(",");
		int rows=amd.findByIdDeleteAttachements(ids);
		return rows;
	}

	/**ͨ��id��ѯAttachements����*/
	public Map<String, Object> findByIdAttachementsObject(Integer id) {
		if(id==null){
			throw new RuntimeException("id����Ϊ��!");
		}
		Attachements attachementObject=amd.findByIdAttachementsObject(id);
		List<Attachements> list=amd.findAttachementsFileName();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("aObject",attachementObject );
		return map;
	}

	/**ͨ��id�޸�Attachements�е�title*/
	public boolean findByIdUpdateAttachements(Integer id, String title) {
		if(id==null){
			throw new RuntimeException("id����Ϊ��");
		}
		if(title==null || title.trim().isEmpty()){
			throw new RuntimeException("���ⲻ��Ϊ��");
		}
		boolean b=amd.findByIdUpdateAttachements(id, title);
		return b;
	}



}
