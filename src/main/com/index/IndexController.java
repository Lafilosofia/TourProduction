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
	
	//保持session的新鲜(心跳检测)
	//web.xml中配置session-config文件,
	//前提要在session中绑定数据(在register控制层中)
	@RequestMapping("/toHearbeat")
	@ResponseBody
	public JsonResult<String> doHearbeat(){
		String str="ok";
		return new JsonResult<String>(str);
	}
}
