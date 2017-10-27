package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.User;
import com.ydl.residentmap.model.vo.UserVo;
import com.ydl.residentmap.service.UserService;
import com.ydl.residentmap.util.CacheEntity;
import com.ydl.residentmap.util.CacheTimerHandler;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@SessionAttributes("loginUser")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "users")
public class UserController {
	private static Logger logger =Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;

	private CacheTimerHandler cacheTimer = new CacheTimerHandler();

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = { RequestMethod.POST })
	public ResponseResult add(@RequestBody User user) {
		logger.debug("添加用户：  账号：【"+user.getName()+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = "";
		String error = "";
		String error_description = ""; 
		try {
			userService.save(user);
			data = user.getId();
			desc = ResultMessage.SAVE_SUCCESS;			
		}
		catch (Exception ex){
			status = ResultCode.ERROR;
			desc = ResultMessage.SAVE_FAILURE;
			error_description = ex.getMessage();
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}



	/**
	 * WEB端退出登录
	 * 
	 * @param model
	 * @param session
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/web/logout", method = { RequestMethod.GET })
	public ResponseResult logout(Model model, HttpSession session) {
		logger.debug("退出登录");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = ""; 
		model.asMap().remove("loginUser");
		session.invalidate();
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * WEB端登录
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/web/login", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody User user,Model model,HttpSession session,HttpServletRequest request){
		logger.debug("WEB端用户登录：  账号：【"+user.getName()+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.LOGIN_SUCCESS;
		String error = "";
		String error_description = ""; 
		User u = null;
		try {
			//test;
			u = userService.login(user);
			session.setAttribute("loginUser",u);
			model.addAttribute("loginUser", u);
			data = u;
		}
		catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.LOGIN_FAILURE;
			error = "";
			error_description = e.getMessage(); 
			logger.debug("WEB端用户登录失败，  异常信息为：【"+error_description+"】");
		}

		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * APP登录接口
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/app/login", method = RequestMethod.POST)
	public ResponseResult loginForApp(@RequestBody User user) {
		logger.debug("APP端用户登录：  账号：【"+user.getName()+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.LOGIN_SUCCESS;
		String error = "";
		String error_description = ""; 
		User u = null;
		try {
			u = userService.login(user);
			data = u;
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.LOGIN_FAILURE;
			error = "";
			error_description = e.getMessage();
			logger.debug("APP端用户登录失败，  异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * WEB端删除用户
	 *
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public ResponseResult delete(@PathVariable("id") Long id) {
		logger.debug("WEB端删除用户：用户id：【"+id+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			userService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("WEB端删除用户异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	
	/**
	 * APP端修改密码
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/modifypassword", method = RequestMethod.PUT)
	public ResponseResult modifyPassword(@RequestBody UserVo user) {
		logger.debug("修改密码：用户账号为：【"+user.getName()+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.DELETE_SUCCESS;
		String error = "";
		String error_description = "";

		Boolean rs = userService.modifyPassword(user);
		if(!rs){
			status = ResultCode.ERROR;
			error_description = ResultMessage.MODIFY_PWD_FAIL;
		}else{
			desc = ResultMessage.MODIFY_PWD_SUCC;
		}


//		//校验验证码是否正确
//		// 缓存中验证码
//		String cacheVerifyCode;
//		CacheEntity ce = cacheTimer.getCache(user.getName());
//		if (ce == null) {
//			status = ResultCode.ERROR;
//			error_description = "验证码过期";
//		} else {
//			cacheVerifyCode = (String) ce.getCacheContext();
//
//			// 如果赎金来的验证码和缓存中的验证码一致，则验证成功
//			if (!user.getValidatecode().equals(cacheVerifyCode)) {
//				status = ResultCode.ERROR;
//				error_description = "验证码错误";
//			}else{
//				Boolean rs = userService.modifyPassword(user);
//				if(!rs){
//					status = ResultCode.ERROR;
//					error_description = ResultMessage.MODIFY_PWD_FAIL;
//				}else{
//					desc = ResultMessage.MODIFY_PWD_SUCC;
//				}
//			}
//		}
		
		return ResponseResult.create(status, data, desc, error, error_description);
		
	}


	/**
	 * 更新用户
	 *
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseResult update(@RequestBody User user) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = "";
		try {
			userService.update(user);
		} catch (Exception e) {
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = e.getMessage();
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * WEB端更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseResult updateForWeb(@RequestBody User user) {
		logger.debug("WEB端修改用户信息，用户账号为：【"+user.getName()+"】");
		return updateUser(user);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	private ResponseResult updateUser(User user) {
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try {
			userService.update(user);
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
		logger.debug("获取用户信息，用户ID为：【"+id+"】");
		String status = ResultCode.SUCCESS;
		Object data = new JSONObject();
		String desc = ResultMessage.UPDATE_SUCCESS;
		String error = "";
		String error_description = ""; 
		try {
			data = userService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.UPDATE_FAILURE;
			error_description = ResultMessage.UPDATE_FAILURE;
			logger.debug("获取用户信息异常，异常信息为：【"+error_description+"】");
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 获取所有用户
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getall", method = { RequestMethod.GET })
	public ResponseResult getAll() {
		logger.debug("获取所有社区干部");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		List<User> users = userService.getAll();
		if(users.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, users, desc, error, error_description);
	}

	/**
	 * 删除用户
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
	public ResponseResult deleteList(@PathVariable("ids") String ids) {
		logger.debug("批量删除社区用户：ids：【"+ids+"】");
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
			userService.deleteList(idList);
		} catch (Exception e) {
			e.printStackTrace();
			status = ResultCode.ERROR;
			desc = ResultMessage.DELETE_FAILURE;
			error = "";
			error_description = ResultMessage.DELETE_FAILURE;
			logger.debug("删除用户异常，异常信息为："+error_description);
		}
		return ResponseResult.create(status, data, desc, error, error_description);
	}

	/**
	 * 根据名称获取用户
	 *
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
	public ResponseResult getByName(@PathVariable(value = "name") String name) {
		logger.debug("获取带名称为：【"+name+"】的用户");
		String status = ResultCode.SUCCESS;
		String desc = ResultMessage.SEARCH_SUCCESS;
		String error = "";
		String error_description = "";
		List<User> users = userService.getUsersByName(name);
		if(users.size()==0){
			status=ResultCode.ERROR;
			desc=ResultMessage.SEARCH_FAILURE;
		}
		else{
			status=ResultCode.SUCCESS;
		}
		return ResponseResult.create(status, users, desc, error, error_description);
	}

}
