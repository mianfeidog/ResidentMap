package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.User;
import com.ydl.residentmap.model.vo.ResidentVo;
import com.ydl.residentmap.service.ResidentService;
import com.ydl.residentmap.util.ImageUtils;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "residents")
public class ResidentController {
	private static Logger logger =Logger.getLogger(ResidentController.class);
	@Resource
	private ResidentService residentService;

//	/**
//	 * 添加用户
//	 *
//	 * @param resident
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/add",method = { RequestMethod.POST })
//	public ResponseResult add(@RequestBody Resident resident,@RequestBody(required=true) HashMap<String,Object> map) {
//		logger.debug("添加居民：  姓名：【"+resident.getName()+"】");
//		String status = ResultCode.SUCCESS;
//		Object data = new JSONObject();
//		String desc = "";
//		String error = "";
//		String error_description = "";
//		try
//		{
////			if(!"".equals(photoStr)){
////				String photo= ImageUtils.decodeBase64ToImg("E:\\Tomcat8.5\\webapps\\ResidentMap\\images\\residents\\header",photoStr);
////				resident.setPhoto(photo);
////			}
//
//			String address = resident.getAddress().trim();
//			//地址不为空，获取经纬度
//			if(!"".equals(address)){
//				desc = ResultMessage.SAVE_SUCCESS;
//				Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//				if(lngLat!=null){
//					String lng = lngLat.get("lng");
//					String lat = lngLat.get("lat");
//					resident.setLng(lng);
//					resident.setLat(lat);
//				}
//				else{
//					resident.setLng(null);
//					resident.setLat(null);
//					desc += " 根据地址["+address+"]未查询到居民经纬度，经纬度设置为空。";
//				}
//			}
//			residentService.save(resident);
//
//			data = resident.getId();
//
//		}
//		catch (Exception ex){
//			status = ResultCode.ERROR;
//			error_description = ResultMessage.SAVE_FAILURE;
//		}
//		return ResponseResult.create(status, data, desc, error, error_description);
//	}


	/**
	 * 添加用户
	 *
	 * @param resident
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add",method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody(required=true) HashMap<String,Object> map) {
		logger.debug("添加居民：  姓名：【"+map.get("name")+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = "";

		Resident resident = new Resident();
		try
		{
			String photoStr = map.get("photoStr").toString();
			if(!"".equals(photoStr)){
				//String photo = ImageUtils.convertByteToImage(photoStr,"images\\residents\\header","jpg");
				String photo= ImageUtils.decodeBase64ToImg("images\\residents\\header",photoStr);
				resident.setPhoto(photo);
			}

			resident.setType(Integer.parseInt(map.get("type").toString()));
			resident.setName(map.get("name").toString());
			String birthdayStr = map.get("birthday").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthdayStr);
			resident.setBirthday(date);
			resident.setFamilyCnt(Integer.parseInt(map.get("familyCnt").toString()));
			resident.setTelephone(map.get("telephone").toString());
			BigDecimal yearIncome = new BigDecimal(map.get("yearIncome").toString());
			resident.setYearIncome(yearIncome);
			resident.setAddress(map.get("address").toString());
			resident.setReceivePolicyStandard(map.get("receivePolicyStandard").toString());
			resident.setLowIncome(Boolean.valueOf(map.get("lowIncome").toString()) );
			resident.setDeformity(Boolean.valueOf(map.get("deformity").toString()));

			String address = resident.getAddress().trim();
			//地址不为空，获取经纬度
			if(!"".equals(address)){
				desc = ResultMessage.SAVE_SUCCESS;
				Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
				if(lngLat!=null){
					String lng = lngLat.get("lng");
					String lat = lngLat.get("lat");
					resident.setLng(lng);
					resident.setLat(lat);
				}
				else{
					resident.setLng(null);
					resident.setLat(null);
					desc += " 根据地址["+address+"]未查询到居民经纬度，经纬度设置为空。";
				}
			}
			residentService.save(resident);

			data = resident.getId();

		}
		catch (Exception ex){
			ex.printStackTrace();
			status = ResultCode.ERROR;
			error_description = ResultMessage.SAVE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

//	/**
//	 * 添加用户
//	 *
//	 * @param resident
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/add",method = { RequestMethod.POST })
//	public ResponseResult add(@RequestBody Resident resident,@RequestParam(value="file",required=false) MultipartFile file,
//							  HttpServletRequest request) {
//		logger.debug("添加居民：  姓名：【"+resident.getName()+"】");
//		String status = ResultCode.SUCCESS;
//		Object data = new JSONObject();
//		String desc = "";
//		String error = "";
//		String error_description = "";
//		try
//		{
//			residentService.save(resident);
//
//			//获得物理路径webapp所在路径
//			String pathRoot = request.getSession().getServletContext().getRealPath("");
//			String path="";
//			if(file!=null){
//				//生成uuid作为文件名称
//				String uuid = UUID.randomUUID().toString().replaceAll("-","");
//				//获得文件类型（可以判断如果不是图片，禁止上传）
//				String contentType=file.getContentType();
//				//获得文件后缀名称
//				String imageName=contentType.substring(contentType.indexOf("/")+1);
//				path="/images/"+uuid+"."+imageName;
//				file.transferTo(new File(pathRoot+path));
//			}
//			resident.setPhoto(path);
//
//			data = resident.getId();
//			desc = ResultMessage.SAVE_SUCCESS;
//		}
//		catch (Exception ex){
//			status = ResultCode.ERROR;
//			error_description = ResultMessage.SAVE_FAILURE;
//		}
//		return ResponseResult.create(status, data, desc, error, error_description);
//	}

	/**
	 * 根据用户类型获取用户
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getall", method = { RequestMethod.GET })
	public ResponseResult getAll() {
		logger.debug("获取所有居民");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = ""; 
		List<ResidentVo> residents = residentService.getAll();
		return ResponseResult.create(status, residents, desc, error, error_description);
	}

	/**
	 * 根据用户类型获取用户
	 *
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbytype/{type}", method = { RequestMethod.GET })
	public ResponseResult getByType(@PathVariable(value = "type") int type) {
		logger.debug("获取类型为：【"+type+"】的用户");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		List<ResidentVo> residents = residentService.getResidentsByType(type);
		ResponseResult res = ResponseResult.create(status, residents, desc, error, error_description);
		return res;
	}

	/**
	 * 根据用户类型获取用户
	 *
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbytypes/{types}", method = { RequestMethod.GET })
	public ResponseResult getByTypes(@PathVariable(value = "types") String types) {
		logger.debug("获取类型为：【"+types+"】的用户");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		String [] typeArr = types.split("_");
		List<Resident> residentList = new ArrayList<Resident>();
		for(int i=0;i<typeArr.length;i++) {
			if(!"".equals(typeArr[i])){
				int type = Integer.parseInt(typeArr[i]);
				List<ResidentVo> residents = residentService.getResidentsByType(type);
				residentList.addAll(residents);
			}
		}
		return ResponseResult.create(status, residentList, desc, error, error_description);
	}

	/**
	 * 根据用户类型获取用户
	 *
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
	public ResponseResult getUsers(@PathVariable(value = "name") String name) {
		logger.debug("获取带名字为：【"+name+"】的用户");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		List<ResidentVo> residents = residentService.getResidentsByName(name);
		if(residents.size()==0){
			status=ResultCode.ERROR;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, residents, desc, error, error_description);
	}

	/**
	 * 根据用户类型获取用户
	 *
	 * @param pageinfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbypage/{pageinfo}", method = { RequestMethod.GET })
	public ResponseResult getByPage(@PathVariable(value = "pageinfo") String pageinfo) {
		logger.debug("获取用户分页");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";

		String [] pageArr = pageinfo.split("_");
		int offset=Integer.parseInt(pageArr[0]);
		int size=Integer.parseInt(pageArr[1]);
		Pager<Resident> residents = residentService.getResidents(offset,size);
		if(residents.getTotal()==0){
			status=ResultCode.ERROR;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, residents, desc, error, error_description);
	}

	/**
	 * 删除居民
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
	public ResponseResult deleteList(@PathVariable("ids") String ids) {
		logger.debug("删除居民：用户id：【"+ids+"】");
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
			residentService.deleteList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("删除居民异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 删除居民
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		logger.debug("删除居民：用户id：【"+id+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try {
			residentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("删除居民异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	/**更新居民信息
	 * @param resident
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody(required=true) HashMap<String,Object> map) {
		logger.debug("修改居民信息，名：【"+map.get("name")+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = "";
		Resident resident = new Resident();

		try {
			String photoStr = map.get("photoStr").toString();
			if(!"".equals(photoStr)){
				//String photo = ImageUtils.convertByteToImage(photoStr,"images\\residents\\header","jpg");
				String photo= ImageUtils.decodeBase64ToImg("images\\residents\\header",photoStr);
				resident.setPhoto(photo);
			}

			resident.setType(Integer.parseInt(map.get("type").toString()));
			resident.setName(map.get("name").toString());
			String birthdayStr = map.get("birthday").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthdayStr);
			resident.setBirthday(date);
			resident.setFamilyCnt(Integer.parseInt(map.get("familyCnt").toString()));
			resident.setTelephone(map.get("telephone").toString());
			BigDecimal yearIncome = new BigDecimal(map.get("yearIncome").toString());
			resident.setYearIncome(yearIncome);
			resident.setAddress(map.get("address").toString());
			resident.setReceivePolicyStandard(map.get("receivePolicyStandard").toString());
			resident.setLowIncome(Boolean.valueOf(map.get("lowIncome").toString()) );
			resident.setDeformity(Boolean.valueOf(map.get("deformity").toString()));



			String address = resident.getAddress().trim();
			//地址不为空，获取经纬度
			if(!"".equals(address)){
				Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
				String lng = lngLat.get("lng");
				String lat = lngLat.get("lat");
				resident.setLng(lng);
				resident.setLat(lat);
			}

			residentService.update(resident);
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}


	/**
	 * WEB端和APP端共用获取用户信息
	 *
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
	public ResponseResult getById(@PathVariable("id") Long id) {
		logger.debug("获取居民信息，居民ID为：【"+id+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			data = residentService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.SEARCH_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
			logger.debug("获取居民信息异常，异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}
	
}
