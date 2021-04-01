package com.lhz.tourproduct.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhz.tourproduct.project.commons.ControllerException;
import com.lhz.tourproduct.project.commons.JsonResult;


@Controller
public class IndexController extends ControllerException {
	@RequestMapping("/indexDemo")
	public String indexDemo(){
		return "index";
	}
	
	//����session������(�������)
	//web.xml������session-config�ļ�,
	//ǰ��Ҫ��session�а�����(��register���Ʋ���)
	@RequestMapping("/toHearbeat")
	@ResponseBody
	public JsonResult<String> doHearbeat(){
		String str="ok";
		return new JsonResult<String>(str);
	}
}
