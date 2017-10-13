package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import com.ydl.residentmap.model.vo.KeyPersonVo;
import com.ydl.residentmap.model.vo.MergeResidentVo;
import com.ydl.residentmap.service.AssistResidentService;
import com.ydl.residentmap.service.KeyPersonService;
import com.ydl.residentmap.service.MergeResidentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MergeResidentServiceImpl implements MergeResidentService {

    @Resource
    private KeyPersonService keyPersonService;

    @Resource
    private AssistResidentService assistResidentService;

    /**
     * 根据重点人员列表获取居民信息
     * @param keyPersonVoList
     * @return
     */
    public List<MergeResidentVo> getFromKeyPersonVos(List<KeyPersonVo> keyPersonVoList){
        List<MergeResidentVo> mergeResidentVoList = new ArrayList<MergeResidentVo>();
        for(int i=0;i<keyPersonVoList.size();i++) {
            KeyPersonVo keyPersonVo = keyPersonVoList.get(i);
            MergeResidentVo mergeResidentVo=new MergeResidentVo(keyPersonVo);
            mergeResidentVo.setDataType(DataDictionaryCode.DATA_TYPE_KEY_PERSON);
            mergeResidentVoList.add(mergeResidentVo);
        }
        return mergeResidentVoList;
    }

    /**
     * 根据帮扶人员列表获取居民信息
     * @param assistResidentVoList
     * @return
     */
    public List<MergeResidentVo> getFromAssistResidentVos(List<AssistResidentVo> assistResidentVoList){
        List<MergeResidentVo> mergeResidentVoList = new ArrayList<MergeResidentVo>();
        for(int j=0;j<assistResidentVoList.size();j++) {
            AssistResidentVo assistResidentVo = assistResidentVoList.get(j);
            MergeResidentVo mergeResidentVo = new MergeResidentVo(assistResidentVo);
            mergeResidentVo.setDataType(DataDictionaryCode.DATA_TYPE_ASSIST_RESIDENT);
            mergeResidentVoList.add(mergeResidentVo);
        }
        return mergeResidentVoList;
    }

    /**
     * @param dataType1List     重点人员类型列表
     * @param dataType12List    帮扶人员类型列表
     * @param name
     * @return
     */
    @Override
    public List<MergeResidentVo> queryMergeResidentVo(List<String> dataType1List, List<String> dataType12List, String name) {
        List<MergeResidentVo> mergeResidentVoList = new ArrayList<MergeResidentVo>();
        //名字为空，居民类型列表不为空，根据居民类型查找
        if("".equals(name) && (dataType1List.size()>0 || dataType12List.size()>0)) {
            List<KeyPersonVo> keyPersonVoList = new ArrayList<KeyPersonVo>();
            if(dataType1List.size()>0){
                keyPersonVoList=keyPersonService.getKeyPersonVosByTypes(dataType1List);
            }
            List<AssistResidentVo> assistResidentVoList = new ArrayList<AssistResidentVo>();
            if(dataType12List.size()>0){
                assistResidentVoList = assistResidentService.getAssistResidentVosByTypes(dataType12List);
            }
            List<MergeResidentVo> mergeResidentVoList1 = this.getFromKeyPersonVos(keyPersonVoList);
            List<MergeResidentVo> mergeResidentVoList12 = this.getFromAssistResidentVos(assistResidentVoList);
            mergeResidentVoList.addAll(mergeResidentVoList1);
            mergeResidentVoList.addAll(mergeResidentVoList12);
        }
        //姓名不为空，居民类型列表为空，根据居民姓名查找
        else if(!"".equals(name) && (dataType1List.size()==0 && dataType12List.size()==0)){
            List<KeyPersonVo> keyPersonVoList=keyPersonService.getKeyPersonVosByName(name);
            List<AssistResidentVo> assistResidentVoList = assistResidentService.getAssistResidentVosByName(name);
            List<MergeResidentVo> mergeResidentVoList1 = this.getFromKeyPersonVos(keyPersonVoList);
            List<MergeResidentVo> mergeResidentVoList12 = this.getFromAssistResidentVos(assistResidentVoList);
            mergeResidentVoList.addAll(mergeResidentVoList1);
            mergeResidentVoList.addAll(mergeResidentVoList12);
        }
        //姓名不为空，居民类型不为空，根据居民类型与姓名联合查找
        else if(!"".equals(name) && (dataType1List.size()>0 || dataType12List.size()>0))
        {
            List<KeyPersonVo> keyPersonVoList = new ArrayList<KeyPersonVo>();
            if(dataType1List.size()>0){
                keyPersonVoList=keyPersonService.getKeyPersonVosByTypesName(dataType1List,name);
            }
            List<AssistResidentVo> assistResidentVoList = new ArrayList<AssistResidentVo>();
            if(dataType12List.size()>0){
                assistResidentVoList = assistResidentService.getAssistResidentVosByTypesName(dataType12List,name);
            }
            List<MergeResidentVo> mergeResidentVoList1 = this.getFromKeyPersonVos(keyPersonVoList);
            List<MergeResidentVo> mergeResidentVoList12 = this.getFromAssistResidentVos(assistResidentVoList);
            mergeResidentVoList.addAll(mergeResidentVoList1);
            mergeResidentVoList.addAll(mergeResidentVoList12);
        }
        return mergeResidentVoList;
    }
}
