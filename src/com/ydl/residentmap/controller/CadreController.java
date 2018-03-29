package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.CadreVo;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "cadres")
public class CadreController {
    private static Logger logger =Logger.getLogger(CadreController.class);
    @Resource
    private CadreService cadreService;

    /**
     * 增加社区干部
     * @param cadre
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody Cadre cadre) {
        logger.debug("添加社区干部：  名称：【" + cadre.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            cadreService.save(cadre);
            data = cadre.getId();
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
     * 删除社区干部
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除社区干部：ids：【"+ids+"】");
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
            cadreService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除社区干部异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新社区干部
     *
     * @param cadre
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody Cadre cadre) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            cadreService.update(cadre);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有社区干部
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
        List<CadreVo> cadres = cadreService.getAllCadreVos();
        if(cadres.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, cadres, desc, error, error_description);
    }

    /**
     * 根据名称获取社区干部
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的社区干部");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<CadreVo> cadres = cadreService.getCadreVosByName(name);
        if(cadres.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, cadres, desc, error, error_description);
    }

    /**
     * 根据条件查询社区干部
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbycondition/{condition}", method = { RequestMethod.GET })
    public ResponseResult getByCondition(@PathVariable(value = "condition") String condition) {
        logger.debug("根据条件查询社区干部");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        HashMap<String,String> map = CommonUtil.getCondtionMap(condition);
        List<CadreVo> cadres = cadreService.getCadreVosByCondition(map);
        if(cadres.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, cadres, desc, error, error_description);
    }

    /**
     * 根据id获取社区干部
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取社区干部，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = cadreService.getCadreVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取社区干部信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }
}
