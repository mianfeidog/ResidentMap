package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.KeyPersonVo;
import com.ydl.residentmap.service.KeyPersonService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "key_persons")
public class KeyPersonController {
	private static Logger logger =Logger.getLogger(KeyPersonController.class);
	@Resource
	private KeyPersonService keyPersonService;


	/**
	 * 获取所有重点人员
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getall", method = { RequestMethod.GET })
	public ResponseResult getAll() {
		logger.debug("获取所有重点人员");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = ""; 
		List<KeyPersonVo> keyPersons = keyPersonService.getAllKeyPersonVos();
		//List<KeyPerson> keyPersons = keyPersonService.getAllKeyPersons();
		if(keyPersons.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, keyPersons, desc, error, error_description);
	}

	/**
	 * 增加重点人员
	 * @param keyPerson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody KeyPerson keyPerson) {
		logger.debug("添加重点人员：  姓名：【" + keyPerson.getName() + "】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = "";
		try {
			String idCard = keyPerson.getIdCard();
			if(idCard!=null && !idCard.trim().equals(""))
			{
				List<KeyPerson> keyPersonList = keyPersonService.getKeyPersonsByIdCard(idCard, CommonConst.ACTION_ADD,keyPerson.getId());
				if(keyPersonList.size()>0)
				{
					status = ResultCode.ERROR_DUPLICATE_IDCADR;
					desc = ResultMessage.SAVE_FAILURE;
					error_description=ResultMessage.SAVE_FAILURE_DUPLICATE_IDCARD;
					return ResponseResult.create(status, data, desc, error, error_description);
				}
			}

			keyPersonService.save(keyPerson);
			data = keyPerson.getId();
			desc = ResultMessage.SAVE_SUCCESS;

		}
		catch (Exception ex){
			status = ResultCode.ERROR;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 删除重点人员
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
	public ResponseResult deleteList(@PathVariable("ids") String ids) {
		logger.debug("批量删除重点人员：ids：【"+ids+"】");
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
			keyPersonService.deleteList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("删除重点人员异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 更新重点人员
	 *
	 * @param keyPerson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody KeyPerson keyPerson) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			String idCard = keyPerson.getIdCard();
			if(idCard!=null && !idCard.trim().equals(""))
			{
				List<KeyPerson> keyPersonList = keyPersonService.getKeyPersonsByIdCard(idCard, CommonConst.ACTION_EDIT,keyPerson.getId());
				if(keyPersonList.size()>0)
				{
					status = ResultCode.ERROR_DUPLICATE_IDCADR;
					desc = ResultMessage.UPDATE_FAILURE;
					error_description=ResultMessage.UPDATE_FAILURE_DUPLICATE_IDCARD;
					return ResponseResult.create(status, data, desc, error, error_description);
				}
			}
			keyPersonService.update(keyPerson);
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
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
		logger.debug("获取类型为：【"+types+"】的重点人员");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		String [] typeArr = types.split("_");
		List<KeyPerson> keyPersonList = new ArrayList<KeyPerson>();
		for(int i=0;i<typeArr.length;i++) {
			if(!"".equals(typeArr[i])){
				int type = Integer.parseInt(typeArr[i]);
				List<KeyPersonVo> keyPersons = keyPersonService.getKeyPersonVosByType(type);
				//List<KeyPerson> keyPersons = keyPersonService.getKeyPersonsByType(type);
				keyPersonList.addAll(keyPersons);
			}
		}
		if(keyPersonList.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, keyPersonList, desc, error, error_description);
	}

	/**
	 * 根据重点人员姓名获取重点人员
	 *
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
	public ResponseResult getByName(@PathVariable(value = "name") String name) {
		logger.debug("获取带名字为：【"+name+"】的重点人员");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		List<KeyPersonVo> keyPersons = keyPersonService.getKeyPersonVosByName(name);
		//List<KeyPerson> keyPersons = keyPersonService.getKeyPersonsByName(name);
		if(keyPersons.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, keyPersons, desc, error, error_description);
	}

	/**
	 * 分页获取重点人员
	 *
	 * @param pageinfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbypage/{pageinfo}", method = { RequestMethod.GET })
	public ResponseResult getByPage(@PathVariable(value = "pageinfo") String pageinfo) {
		logger.debug("分页获取重点人员");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		String [] pageArr = pageinfo.split("_");
		int offset=Integer.parseInt(pageArr[0]);
		int size=Integer.parseInt(pageArr[1]);
		List<KeyPersonVo> keyPersons = keyPersonService.getKeyPersonVosByPage(offset,size);
		if(keyPersons.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, keyPersons, desc, error, error_description);
	}

	/**
	 * 根据重点人员id获取重点人员
	 *
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
	public ResponseResult getById(@PathVariable("id") Long id) {
		logger.debug("根据id获取重点人员，人员ID为：【"+id+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			data = keyPersonService.getKeyPersonVoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
			logger.debug("获取重点人员信息异常，异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	
}
