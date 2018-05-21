package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.PartyMemberVo;
import com.ydl.residentmap.service.PartyMemberService;
import com.ydl.residentmap.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "party_members")
public class PartyMemberController {
    private static Logger logger =Logger.getLogger(PartyMemberController.class);
    @Resource
    private PartyMemberService partyMemberService;

    /**
     * 增加党员
     * @param partyMember
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody PartyMember partyMember) {
        logger.debug("添加党员：  姓名：【" + partyMember.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {

            partyMemberService.save(partyMember);
            data = partyMember.getId();
            desc = ResultMessage.SAVE_SUCCESS;
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            desc = ResultMessage.SAVE_FAILURE;
            error_description =ex.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除党员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除党员：ids：【"+ids+"】");
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
            partyMemberService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除党员异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新党员
     *
     * @param partyMember
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody PartyMember partyMember) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            partyMemberService.update(partyMember);

        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.SAVE_FAILURE;
            error_description =e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有党员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有党员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<PartyMemberVo> partyMembers = partyMemberService.getAllPartyMemberVos();
        if(partyMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyMembers, desc, error, error_description);
    }

    /**
     * 根据查询条件获取党员
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition", method = { RequestMethod.POST })
    public ResponseResult getByCondition(@RequestBody Map<String, String> map) {
        logger.debug("根据查询条件获取党员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<PartyMemberVo> partyMembers = partyMemberService.getPartyMemberVosByCondition(map);
        if(partyMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyMembers, desc, error, error_description);
    }

    /**
     * 根据条件导出党员
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportexcelbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult exportExcelByCondition(@PathVariable(value = "condition") String condition,HttpServletResponse response) throws Exception {
        logger.debug("根据条件导出社区干部");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<PartyMemberVo> partyMemberVos=partyMemberService.getPartyMemberVosByCondition(map);
        //有记录
        if(partyMemberVos.size()>0) {
            HSSFWorkbook workbook= partyMemberService.exportExcel(partyMemberVos);
            try{
                response = CommonUtil.setExcelResponse(response,"党员信息管理");
                // 将文件输出到客户端浏览器
                ServletOutputStream out=response.getOutputStream();
                workbook.write(out);
                out.flush();
                out.close();


            }catch(Exception e){
                e.printStackTrace();
                status=ResultCode.ERROR;
            }
        }
        //无记录
        else {
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 根据姓名获取党员
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名字为：【"+name+"】的党员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<PartyMemberVo> partyMembers = partyMemberService.getPartyMemberVosByName(name);
        if(partyMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyMembers, desc, error, error_description);
    }

    /**
     * 根据条件获取党员
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult getByCondition(@PathVariable(value = "condition") String condition) {
        logger.debug("根据条件获取党员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<PartyMemberVo> partyMembers = partyMemberService.getPartyMemberVosByCondition(map);
        if(partyMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyMembers, desc, error, error_description);
    }

    /**
     * 根据id获取党员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取党员，人员ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = partyMemberService.getPartyMemberVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取党员信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }
}
