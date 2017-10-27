package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;
import com.ydl.residentmap.service.CommitteeMemberService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "committee_members")
public class CommitteeMemberController {
    private static Logger logger =Logger.getLogger(CommitteeMemberController.class);
    @Resource
    private CommitteeMemberService committeeMemberService;

    /**
     * 增加社区大党委成员
     * @param committeeMember
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody CommitteeMember committeeMember) {
        logger.debug("添加社区大党委成员：  名称：【" + committeeMember.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            committeeMemberService.save(committeeMember);
            data = committeeMember.getId();
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
     * 删除社区大党委成员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除社区大党委成员：ids：【"+ids+"】");
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
            committeeMemberService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除社区大党委成员异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新社区大党委成员
     *
     * @param committeeMember
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody CommitteeMember committeeMember) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            committeeMemberService.update(committeeMember);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.SAVE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有社区大党委成员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有社区大党委成员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<CommitteeMemberVo> committeeMembers = committeeMemberService.getAllCommitteeMemberVos();
        if(committeeMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, committeeMembers, desc, error, error_description);
    }

    /**
     * 根据名称获取社区大党委成员
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的社区大党委成员");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<CommitteeMemberVo> committeeMembers = committeeMemberService.getCommitteeMemberVosByName(name);
        if(committeeMembers.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, committeeMembers, desc, error, error_description);
    }

    /**
     * 根据id获取社区大党委成员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取社区大党委成员，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = committeeMemberService.getCommitteeMemberVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.SEARCH_FAILURE;
            logger.debug("获取社区大党委成员信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }
}
