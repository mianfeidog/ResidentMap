package com.ydl.residentmap.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.AidPlan;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.AidPlanService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "aid_plans")
public class AidPlanController {
	private static Logger logger =Logger.getLogger(AidPlanController.class);
	@Resource
	private AidPlanService aidPlanService;

	/**
	 * 添加帮扶计划
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody AidPlan obj) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = "";
		String error = "";
		String error_description = ""; 
		try
		{
			aidPlanService.save(obj);
			data = obj.getId();
			desc = ResultMessage.SAVE_SUCCESS;			
		}
		catch (Exception ex){
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 根据创建人查询帮扶计划
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{id}", method = { RequestMethod.GET })
	public ResponseResult get(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = ""; 
		Object data = aidPlanService.getPlansByUserId(id);
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	
	/**
	 * 删除帮扶计划
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = ""; 
		try {
			aidPlanService.delete(id);
			desc = ResultMessage.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error_description = ResultMessage.DELETE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	

	/**
	 * 更新帮扶计划
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody AidPlan obj) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = ""; 
		try {
			aidPlanService.update(obj);
			desc = ResultMessage.UPDATE_SUCCESS;
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 根据id获取帮扶计划
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseResult getById(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = ""; 
		try {
			data = aidPlanService.get(id);
			desc = ResultMessage.SEARCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.SEARCH_FAILURE;
			logger.debug("获取用户信息异常，异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	
}
