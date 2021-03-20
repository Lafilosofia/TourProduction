package com.lhz.tourproduct.attachement.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhz.tourproduct.attachement.entity.Attachements;

public interface AttachementDao {
	/**�ϴ��ĸ���(�Ը������ݵĲ���)*/
	int insertAttachement(Attachements attachement);
	
	/**��ѯ����Attachements����*/
	List<Attachements> findAttachements(@Param("indexPage")int indexPage,
										@Param("pageSize")int pageSize,
										@Param("title")String title);
	
	/**��ѯAttachements������,��Ŀ��Ϊ�������ҳ��*/
	int rowCount(@Param("title")String title);
	
	/**ͨ���ļ�ժҪfileDisgest��ѯ���ݿ��Ƿ���ͬ�����ļ�ժҪ
	 * �����һ���ľ���ʾ�������ϴ�,�ļ��Ѿ�������*/
	int countDisgest(String fileDisgest);
	
	/**ͨ������id��ѯAttachements���filePath*/
	Attachements findByIdFilePath(int id);
	
	/**ͨ�����idɾ��Attachements*/
	int findByIdDeleteAttachements(@Param("ids")String[] ids);
	
	/**ͨ��id����Attachements����*/
	Attachements findByIdAttachementsObject(int id);
	
	/**����Attachements�����е�fileName��id*/
	List<Attachements> findAttachementsFileName();
	
	/**ͨ��id�޸�Attachement�е�����title*/
	
	boolean findByIdUpdateAttachements(@Param("id")int id,@Param("title")String title);
	
}
