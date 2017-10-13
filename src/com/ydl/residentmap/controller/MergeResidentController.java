package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.MergeResidentVo;
import com.ydl.residentmap.model.vo.ResidentVo;
import com.ydl.residentmap.service.MergeResidentService;
import com.ydl.residentmap.service.ResidentService;
import com.ydl.residentmap.util.ImageUtils;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "merge_residents")
public class MergeResidentController {
	private static Logger logger =Logger.getLogger(MergeResidentController.class);
	@Resource
	private MergeResidentService mergeResidentService;

	/**
	 * 查询重点人员、帮扶人员信息
	 * params格式为datatype/value,datatype/value;name
	 *dateType 1 为重点人员 12 为帮扶人员
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getbycondition/{params}", method = RequestMethod.GET)
	public ResponseResult getbycondition(@PathVariable("params") String params) {
		logger.debug("获取重点人员与帮扶人员信息");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		if("".equals(params.trim())){
			desc = "查询条件为空，请先录入查询条件。";
			return ResponseResult.create(status, null, desc, error, error_description);
		}

		String[] paramArr=params.split("\\+");
		//居民姓名
		String name="";
		//字典类型与居民类型字符串
		String dataTypeValueStr="";
		//1_1,1_2,12_2+
		if(paramArr.length==1){

			//格式示例: 1_3,1_1,12_1,12_2
			dataTypeValueStr=paramArr[0].trim();
			name="";
		}
		else if(paramArr.length>=2) {
			//格式示例: 1_3,1_1,12_1,12_2
			dataTypeValueStr=paramArr[0].trim();
			name=paramArr[1].trim();
		}

		List<String> dataType1List = new ArrayList<String>();	//重点人员类型
		List<String> dataType12List = new ArrayList<String>();	//帮扶人员类型
		if(!"".equals(dataTypeValueStr))
		{
			String[] dataTypeValuesArr = dataTypeValueStr.split(",");
			for (int i=0;i<dataTypeValuesArr.length;i++)
			{
				//item格式 1_3
				String item = dataTypeValuesArr[i];
				String[] dataTypeValue=item.split("_");
				if(dataTypeValue.length>=2) {
					String dataType=dataTypeValue[0];
					String value=dataTypeValue[1];
					//重点人员
					if("1".equals(dataType)) {
						dataType1List.add(value);
					}
					//帮扶人员
					else if("12".equals(dataType)){
						dataType12List.add(value);
					}
				}
			}
		}
		List<MergeResidentVo> mergeResidentVoList = mergeResidentService.queryMergeResidentVo(dataType1List,dataType12List,name);
		if(mergeResidentVoList.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, mergeResidentVoList, desc, error, error_description);
	}
	
}
