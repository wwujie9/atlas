package com.base.atlas.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author wujie
 * @date 2020/08/23 22:57
 */
public class EmptyCheker {
    private EmptyCheker(){

    }

    public static boolean isEmpty(Object obj) {
        if(obj == null || "null".equals(obj.toString()) || "".equals(obj.toString())){
            return true;
        }

        if(obj instanceof String){
            return ((String) obj).trim().length() == 0;
        }
        if(obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }
        if(obj instanceof Map){
            return ((Map) obj).isEmpty();
        }

        return false;
    }

    public static boolean notEmpty(Object obj) {return !isEmpty(obj);}

    public static boolean isEmpty(Object[] array){
        if(array == null || array.length == 0){
            return true;
        }
        return false;
    }
}

