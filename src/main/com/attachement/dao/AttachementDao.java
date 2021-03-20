package com.lhz.tourproduct.attachement.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhz.tourproduct.attachement.entity.Attachements;

public interface AttachementDao {
	/**上传的附件(对附件数据的插入)*/
	int insertAttachement(Attachements attachement);
	
	/**查询附件Attachements数据*/
	List<Attachements> findAttachements(@Param("indexPage")int indexPage,
										@Param("pageSize")int pageSize,
										@Param("title")String title);
	
	/**查询Attachements总行数,方目的为了算出总页数*/
	int rowCount(@Param("title")String title);
	
	/**通过文件摘要fileDisgest查询数据库是否有同样的文件摘要
	 * 如果有一样的就提示不能在上传,文件已经存在了*/
	int countDisgest(String fileDisgest);
	
	/**通过下载id查询Attachements里的filePath*/
	Attachements findByIdFilePath(int id);
	
	/**通过多个id删除Attachements*/
	int findByIdDeleteAttachements(@Param("ids")String[] ids);
	
	/**通过id查找Attachements数据*/
	Attachements findByIdAttachementsObject(int id);
	
	/**查找Attachements数据中的fileName和id*/
	List<Attachements> findAttachementsFileName();
	
	/**通过id修改Attachement中的数据title*/
	
	boolean findByIdUpdateAttachements(@Param("id")int id,@Param("title")String title);
	
}
