package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.PartyOrgVo;
import com.ydl.residentmap.service.KeyPersonService;
import com.ydl.residentmap.service.PartyOrgService;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "party_orgs")
public class PartyOrgController {
    private static Logger logger =Logger.getLogger(PartyOrgController.class);
    @Resource
    private PartyOrgService partyOrgService;

    /**
     * 增加党组织
     * @param partyOrg
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody PartyOrg partyOrg) {
        logger.debug("添加党组织：  名称：【" + partyOrg.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            partyOrgService.save(partyOrg);
            data = partyOrg.getId();
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
     * 删除党组织
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除党组织：ids：【"+ids+"】");
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
            partyOrgService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除党组织异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新党组织
     *
     * @param partyOrg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody PartyOrg partyOrg) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            partyOrgService.update(partyOrg);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有党组织
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有党组织");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        //List<KeyPersonVo> keyPersons = keyPersonService.getAllKeyPersonVos();
        List<PartyOrgVo> partyOrgs = partyOrgService.getAllPartyOrgVos();
        if(partyOrgs.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyOrgs, desc, error, error_description);
    }

    /**
     * 根据名称获取党组织
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的党组织");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<PartyOrgVo> partyOrgs = partyOrgService.getPartyOrgVosByName(name);
        if(partyOrgs.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyOrgs, desc, error, error_description);
    }

    /**
     * 根据条件获取党组织
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult getByCondition(@PathVariable(value = "condition") String condition) {
        logger.debug("根据条件获取党组织");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<PartyOrgVo> partyOrgs = partyOrgService.getPartyOrgVosByCondition(map);
        if(partyOrgs.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, partyOrgs, desc, error, error_description);
    }

    /**
     * 根据条件导出党组织
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportexcelbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult exportExcelByCondition(@PathVariable(value = "condition") String condition,HttpServletResponse response) throws Exception {
        logger.debug("根据条件导出党组织");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<PartyOrgVo> partyOrgVos=partyOrgService.getPartyOrgVosByCondition(map);
        //有记录
        if(partyOrgVos.size()>0) {
            HSSFWorkbook workbook= partyOrgService.exportExcel(partyOrgVos);
            try{
                response = CommonUtil.setExcelResponse(response,"社区内党组织管理");
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
     * 根据党组织id获取党组织
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取党组织，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = partyOrgService.getPartyOrgVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取党组织信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

}
