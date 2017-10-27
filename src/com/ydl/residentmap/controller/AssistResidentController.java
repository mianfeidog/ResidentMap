package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import com.ydl.residentmap.service.AssistResidentService;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "assist_residents")
public class AssistResidentController {
    private static Logger logger =Logger.getLogger(AssistResidentController.class);
    @Resource
    private AssistResidentService assistResidentService;

    /**
     * 增加帮扶人员
     * @param assistResident
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody AssistResident assistResident) {
        logger.debug("添加帮扶人员：  名称：【" + assistResident.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            assistResidentService.save(assistResident);
            data = assistResident.getId();
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
     * 删除帮扶人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除帮扶人员：ids：【"+ids+"】");
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
            assistResidentService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除帮扶人员异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新帮扶人员
     *
     * @param assistResident
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody AssistResident assistResident) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            assistResidentService.update(assistResident);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有帮扶人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有帮扶人员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<AssistResidentVo> assistResidents = assistResidentService.getAllAssistResidentVos();
        if(assistResidents.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, assistResidents, desc, error, error_description);
    }

    /**
     * 根据名称获取帮扶人员
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的帮扶人员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<AssistResidentVo> assistResidents = assistResidentService.getAssistResidentVosByName(name);
        if(assistResidents.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, assistResidents, desc, error, error_description);
    }

    /**
     * 根据类型获取帮扶人员
     *
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbytypes/{types}", method = { RequestMethod.GET })
    public ResponseResult getByTypes(@PathVariable(value = "types") String types) {
        logger.debug("获取类型为：【"+types+"】的帮扶人员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";

        String [] typeArr = types.split("_");
        List<String> typeList = new ArrayList<String>();
        for(int i=0;i<typeArr.length;i++){
            String typeStr = typeArr[i].trim();
            if(!"".equals(typeStr)){
                String id = typeArr[i];
                typeList.add(id);
            }
        }
        List<AssistResidentVo> assistResidents = assistResidentService.getAssistResidentVosByTypes(typeList);
        if(assistResidents.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, assistResidents, desc, error, error_description);
    }

    /**
     * 根据id获取帮扶人员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取帮扶人员，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = assistResidentService.getAssistResidentVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取帮扶人员信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }


}
