package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.Street;
import com.ydl.residentmap.service.StreetService;
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
@RequestMapping(value = "streets")
public class StreetController {
    private static Logger logger =Logger.getLogger(StreetController.class);
    @Resource
    private StreetService streetService;

    /**
     * 增加街道
     * @param street
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody Street street) {
        logger.debug("添加街道：  名称：【" + street.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            streetService.save(street);
            data = street.getId();
            desc = ResultMessage.SAVE_SUCCESS;
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            error_description = ResultMessage.SAVE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除街道
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除街道：ids：【"+ids+"】");
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
            streetService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = e.getMessage();
            logger.debug("删除街道异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 根据id获取街道
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取街道，街道ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = streetService.getStreetById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = e.getMessage();
            logger.debug("获取街道信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }


    /**
     * 更新街道
     *
     * @param street
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody Street street) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            streetService.update(street);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 分页获取街道
     *
     * @param pageinfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbypage/{pageinfo}", method = { RequestMethod.GET })
    public ResponseResult getByPage(@PathVariable(value = "pageinfo") String pageinfo) {
        logger.debug("分页获取街道");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";

        String [] pageArr = pageinfo.split("_");
        int offset=Integer.parseInt(pageArr[0]);
        int size=Integer.parseInt(pageArr[1]);
        Pager<Street> streets  = streetService.getStreetsByPage(offset,size);
        if(streets.getTotal()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, streets, desc, error, error_description);
    }

    /**
     * 获取所有街道
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有街道");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<Street> streets = streetService.getAll();
        if(streets.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, streets, desc, error, error_description);
    }

    /**
     * 根据街道名称获取街道
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名字为：【"+name+"】的街道");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<Street> streets = streetService.getStreetsByName(name);
        if(streets.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, streets, desc, error, error_description);
    }

    /**
     * 根据条件获取街道
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult getByCondition(@PathVariable(value = "condition") String condition) {
        logger.debug("根据条件获取街道");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<Street> streets = streetService.getStreetsByCondition(map);
        if(streets.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, streets, desc, error, error_description);
    }

    /**
     * 根据条件导出街道
     *
     * @param condition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportexcelbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult exportExcelByCondition(@PathVariable(value = "condition") String condition,HttpServletResponse response) throws Exception {
        logger.debug("根据条件导出街道");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<Street> streets=streetService.getStreetsByCondition(map);
        //有记录
        if(streets.size()>0) {
            HSSFWorkbook workbook= streetService.exportExcel(streets);
            try{
                response = CommonUtil.setExcelResponse(response,"街道管理");
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

}
