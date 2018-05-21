package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.service.DelegateCommitteeService;
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
@RequestMapping(value = "delegate_committees")
public class DelegateCommitteeController {
    private static Logger logger =Logger.getLogger(DelegateCommitteeController.class);
    @Resource
    private DelegateCommitteeService delegateCommitteeService;

    /**
     * 增加两代表一委员
     * @param delegateCommittee
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody DelegateCommittee delegateCommittee) {
        logger.debug("添加两代表一委员：  名称：【" + delegateCommittee.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            delegateCommitteeService.save(delegateCommittee);
            data = delegateCommittee.getId();
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
     * 删除两代表一委员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除两代表一委员：ids：【"+ids+"】");
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
            delegateCommitteeService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除两代表一委员异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新两代表一委员
     *
     * @param delegateCommittee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody DelegateCommittee delegateCommittee) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            delegateCommitteeService.update(delegateCommittee);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description =e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有两代表一委员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有两代表一委员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<DelegateCommitteeVo> delegateCommittees = delegateCommitteeService.getAllDelegateCommitteeVos();
        if(delegateCommittees.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, delegateCommittees, desc, error, error_description);
    }

    /**
     * 根据名称获取两代表一委员
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的两代表一委员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<DelegateCommitteeVo> delegateCommittees = delegateCommitteeService.getDelegateCommitteeVosByName(name);
        if(delegateCommittees.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, delegateCommittees, desc, error, error_description);
    }

    /**
     * 根据条件查询两代表一委员
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult getByCondition(@PathVariable(value = "condition") String condition) {
        logger.debug("根据条件查询两代表一委员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<DelegateCommitteeVo> delegateCommittees = delegateCommitteeService.getDelegateCommitteeVosByCondition(map);
        if(delegateCommittees.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, delegateCommittees, desc, error, error_description);
    }

    /**
     * 根据条件导出两代表一委员
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportexcelbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult exportExcelByCondition(@PathVariable(value = "condition") String condition,HttpServletResponse response) throws Exception {
        logger.debug("根据条件导出两代表一委员");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<DelegateCommitteeVo> delegateCommitteeVos=delegateCommitteeService.getDelegateCommitteeVosByCondition(map);
        //有记录
        if(delegateCommitteeVos.size()>0) {
            HSSFWorkbook workbook= delegateCommitteeService.exportExcel(delegateCommitteeVos);
            try{
                response = CommonUtil.setExcelResponse(response,"两代表一委员管理");
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
     * 根据id获取两代表一委员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取两代表一委员，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = delegateCommitteeService.getDelegateCommitteeVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取两代表一委员信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }
}
