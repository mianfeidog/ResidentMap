package com.ydl.residentmap.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.model.AidStep;
import com.ydl.residentmap.model.vo.AidResidentVo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.AidResidentService;

/**
 * 帮扶对象
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "aid_residents")
public class AidResidentController {
	private static Logger logger =Logger.getLogger(AidResidentController.class);
	@Resource
	private AidResidentService aidResidentService;


	/**
	 * 帮扶对象保存
	 * @param aidStep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody AidResident aidResident) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("帮扶对象保存");
			//保存记录
			Boolean rs = aidResidentService.save(aidResident);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.SAVE_FAILURE;
				error_description = ResultMessage.SAVE_FAILURE;
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	/**
	 * 帮扶对象修改
	 * @param aidStep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = { RequestMethod.PUT })
	public ResponseResult update(@RequestBody AidResident aidResident) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("帮扶对象修改");
			//保存记录
			Boolean rs = aidResidentService.update(aidResident);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.UPDATE_FAILURE;
				error_description = ResultMessage.UPDATE_FAILURE;
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 帮扶对象删除
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id}",method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("帮扶对象删除");
			//保存记录
			Boolean rs = aidResidentService.delete(id);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.DELETE_FAILURE;
				error_description = ResultMessage.DELETE_FAILURE;
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error_description = ResultMessage.DELETE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}




	/**
	 * 根据贫困类型 和 姓名 （可能为空） 和 创建人id 查询帮扶对象的分布
	 * @param aidStep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/position",method = { RequestMethod.POST })
	public ResponseResult getPosition(@RequestBody AidResident aidResident) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("查询帮扶对象的分布");
			//保存记录
			data = aidResidentService.getPositions(aidResident);

		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.SEARCH_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}



	/**
	 * 根根据镇id  和 贫困类型  和 创建人id 查询 帮扶对象列表
	 * @param aidStep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/remarkinfos",method = { RequestMethod.POST })
	public ResponseResult getRemarkInfos(@RequestBody AidResidentVo aidResidentVo) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("查询帮扶对象的分页列表");
			data = aidResidentService.getRemarkInfos(aidResidentVo);

		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	/**
	 * 根据帮扶对象id 查询帮扶对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/remarkinfo/{id}",method = { RequestMethod.GET })
	public ResponseResult getRemarkInfo(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("根据帮扶对象id 查询帮扶对象");
			data = aidResidentService.getRemarkInfo(id);
		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.SEARCH_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}



	/**
	 * 查询帮扶对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/{id}",method = { RequestMethod.GET })
	public ResponseResult getDetail(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			logger.debug("查询帮扶对象详细信息");
			data = aidResidentService.getDetailInfo(id);
		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}











}
