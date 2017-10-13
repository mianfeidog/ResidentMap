package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.AidPlan;
import com.ydl.residentmap.model.AidRecord;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.AidPlanService;
import com.ydl.residentmap.service.AidRecordService;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帮扶记录
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "aid_records")
public class AidRecordController {
	private static Logger logger =Logger.getLogger(AidRecordController.class);
	@Resource
	private AidRecordService aidRecordService;

	/**
	 * 添加帮扶记录
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody AidRecord aidRecord) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try
		{
			//获取上传的图片list
			//List<String> imgList = aidRecord.getImgList();

			//上传图片

			//保存记录
			Boolean rs = aidRecordService.save(aidRecord);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.SAVE_FAILURE;
				error_description = ResultMessage.SAVE_FAILURE;
			}
		}
		catch (Exception ex){
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 根据创建人查询帮扶记录
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
		Object data = aidRecordService.getAidRecordsByUserId(id);
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	
	/**
	 * 删除帮扶记录
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try {
			aidRecordService.delete(id);
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
	 * 更新帮扶记录
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody AidRecord aidRecord) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try {
			//重新上传图片

			//更新帮扶记录
			Boolean rs = aidRecordService.update(aidRecord);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.UPDATE_FAILURE;
				error_description = ResultMessage.UPDATE_FAILURE;
			}

		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 根据id获取帮扶记录
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
			data = aidRecordService.get(id);
			desc = ResultMessage.SEARCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.SEARCH_FAILURE;
			logger.debug("根据id获取帮扶记录，异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	
}
