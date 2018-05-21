package com.ydl.residentmap.constants;

import com.ydl.residentmap.model.DataDictionary;

import java.util.HashMap;

public class CommonConst {
    //表单动作
    public static final String ACTION_ADD="add";    //新增
    public static final String ACTION_EDIT="edit";  //修改

    public static final HashMap<Integer,String> CONST_DATADICTIONAY_TYPE_NAME=new HashMap(){{
        put(DataDictionaryCode.DATA_TYPE_KEY_PERSON,"重点人员类型");
        put(DataDictionaryCode.DATA_TYPE_GENDER,"性别");
        put(DataDictionaryCode.DATA_TYPE_COMMUNITY,"社区类型");
        put(DataDictionaryCode.DATA_TYPE_GRID_ROLE,"网格角色");
        put(DataDictionaryCode.DATA_TYPE_MINORITY,"民族");
        put(DataDictionaryCode.DATA_TYPE_EDUCATION,"文化程度");
        put(DataDictionaryCode.DATA_TYPE_POST,"岗位");
        put(DataDictionaryCode.DATA_TYPE_ORG_SYSTEM,"组织建制");
        put(DataDictionaryCode.DATA_TYPE_ORG_ATTRIBUTE,"党组织属性");
        put(DataDictionaryCode.DATA_TYPE_POSITION,"职务");
        put(DataDictionaryCode.DATA_TYPE_PARTY,"党派");
        put(DataDictionaryCode.DATA_TYPE_ASSIST_RESIDENT,"帮扶人员类型");
    }};
}
