package com.laywerspringboot.edition.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.laywerspringboot.edition.exception.JsonException;

import java.io.IOException;
import java.util.Collection;


/**
 * @Author:小七
 * @createTime:2020-10-22-15-18
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 转换集合类工具
     * @param collectionClass 集合的类型
     * @param elementClass 集合中的参数类型
     * @return
     */
    private static CollectionType getCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass){
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
        return collectionType;
    }



    /**
     * 转json
     * @param arg
     * @return
     */
    public static String toJson(Object arg){
        try {
            String json = mapper.writeValueAsString(arg);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new JsonException();
        }
    }

    /**
     * 解析json
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static  <T> T parseJson(String  json, Class <T> valueType){
        try {
            T t = mapper.readValue(json, valueType);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonException();
        }
    }


}
