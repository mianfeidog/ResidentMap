package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.AidRecord;
import com.ydl.residentmap.model.AidStep;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.AidStepService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 帮扶措施
 * Created by 小强 on 2017/7/20.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "aid_measures")
public class AidStepController {
    private static Logger logger =Logger.getLogger(AidStepController.class);
    @Autowired
    AidStepService aidStepService;
    /**
     * 添加帮扶措施
     *
     * @param obj
     * @return
     */
    @ResponseBody
    @RequestMapping(method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody AidStep aidStep) {
        String status = ResultCode.SUCCESS;
        Object data =  new JSONObject();
        String desc = ResultMessage.SAVE_SUCCESS;
        String error = "";
        String error_description = "";
        try
        {
            //保存记录
            Boolean rs = aidStepService.save(aidStep);
            if(!rs){
                status = ResultCode.ERROR;
                desc = ResultMessage.SAVE_FAILURE;
                error_description = ResultMessage.SAVE_FAILURE;
            }
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            desc = ResultMessage.SAVE_FAILURE;
            error_description = ResultMessage.SAVE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 查询所有的帮扶措施
     *
     * @return
     */
    @ResponseBody
    @RequestMapping( method = { RequestMethod.GET })
    public ResponseResult get() {
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        Object data = aidStepService.getAll();
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除帮扶措施
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    public ResponseResult delete(@PathVariable("id") Long id) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.DELETE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            aidStepService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error_description = ResultMessage.DELETE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }


    /**
     * 更新帮扶措施
     *
     * @param obj
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody AidStep aidStep) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            //更新帮扶措施
            Boolean rs = aidStepService.update(aidStep);
            if(!rs){
                status = ResultCode.ERROR;
                desc = ResultMessage.UPDATE_FAILURE;
                error_description = ResultMessage.UPDATE_FAILURE;
            }

        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 根据id获取帮扶措施
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            data = aidStepService.get(id);
            desc = ResultMessage.SEARCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.SEARCH_FAILURE;
            logger.debug("根据id获取帮扶记录，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }
}
