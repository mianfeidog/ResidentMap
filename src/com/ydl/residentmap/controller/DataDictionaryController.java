package com.ydl.residentmap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.DataDictionary;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.DataDictionaryService;

/**
 * 数据字典
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "data_dictionaries")
public class DataDictionaryController {
	private static Logger logger =Logger.getLogger(DataDictionaryController.class);
	@Resource
	private DataDictionaryService dataDictionaryService;

	/**
	 * 根据类型获取数据字典
	 * 
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/type/{type}", method = { RequestMethod.GET })
	public ResponseResult getByType(@PathVariable(value = "type") String type) {
		logger.debug("获取类型为：【"+type+"】的数据字典");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = ""; 

		Integer t = Integer.valueOf(type);

		List<DataDictionary> dicData = dataDictionaryService.getByType(t);
		return ResponseResult.create(status, dicData, desc, error, error_description);
	}


	/**
	 * 根据用户类型获取用户
	 *
	 * @param types
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbytypes/{types}", method = { RequestMethod.GET })
	public ResponseResult getByTypes(@PathVariable(value = "types") String types) {
		logger.debug("获取类型为：【"+types+"】的数据字典");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		String [] typeArr = types.split("_");
		List<DataDictionary> dataDictionaryList = new ArrayList<DataDictionary>();
		for(int i=0;i<typeArr.length;i++) {
			if(!"".equals(typeArr[i])){
				int type = Integer.parseInt(typeArr[i]);
				List<DataDictionary> dataDictionaryList1 = dataDictionaryService.getByType(type);
				dataDictionaryList.addAll(dataDictionaryList1);
			}
		}
		return ResponseResult.create(status, dataDictionaryList, desc, error, error_description);
	}

	/**
	 * 添加数据字典
	 *
	 * @param dataDictionary
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody DataDictionary dataDictionary) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			Boolean rs = dataDictionaryService.save(dataDictionary);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.SAVE_FAILURE;
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
	 * 添加数据字典
	 *
	 * @param dataDictionary
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addbytype", method = { RequestMethod.POST })
	public ResponseResult addByType(@RequestBody DataDictionary dataDictionary) {
		String status = ResultCode.SUCCESS;
		Object data =  new JSONObject();
		String desc = ResultMessage.SAVE_SUCCESS;
		String error = "";
		String error_description = "";
		try
		{
			int dataType=dataDictionary.getDataType();
			int nextValue=dataDictionaryService.getNextValue(dataType);
			dataDictionary.setValue(nextValue);
			Boolean rs = dataDictionaryService.save(dataDictionary);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.SAVE_FAILURE;
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
	 * 获取所有的数据字典
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getall", method = { RequestMethod.GET })
	public ResponseResult getAll() {
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		Object data = dataDictionaryService.getAll();
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 批量删除
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
	public ResponseResult deleteList(@PathVariable("ids") String ids) {
		logger.debug("批量删除数据字典：ids：【"+ids+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			String [] idArr = ids.split("_");
			List<String> idList = new ArrayList<String>();
			for(int i=0;i<idArr.length;i++){
				String idStr = idArr[i].trim();
				if(!"".equals(idStr)){
					String id = idArr[i];
					idList.add(id);
				}
			}
			dataDictionaryService.deleteList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("删除数据字典异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			Boolean rs = dataDictionaryService.delete(id);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.DELETE_FAILURE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error_description = ResultMessage.DELETE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	/**
	 * 更新数据字典
	 *
	 * @param dataDictionary
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody DataDictionary dataDictionary) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			Boolean rs = dataDictionaryService.update(dataDictionary);
			if(!rs){
				status = ResultCode.ERROR;
				desc = ResultMessage.UPDATE_FAILURE;
			}
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

}
