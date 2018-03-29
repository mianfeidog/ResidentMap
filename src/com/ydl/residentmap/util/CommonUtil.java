package com.ydl.residentmap.util;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    /**
     * 获取查询条件map
     * @param condition
     * @return
     */
    public static HashMap<String,String> getCondtionMap(String condition)
    {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] keyVals = condition.split("\\|");
        if(keyVals.length>0)
        {
            for(int i=0;i<keyVals.length;i++)
            {
                String[] keyVal = keyVals[i].split(":");
                if(keyVal.length==2)
                {
                    String key = keyVal[0];
                    String val = keyVal[1];
                    if(map.containsKey(key)==false)
                    {
                        map.put(key,val);
                    }
                }
            }
        }
        return map;
    }
}
