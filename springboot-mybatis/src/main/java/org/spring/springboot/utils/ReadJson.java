package org.spring.springboot.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {

    public static String LoadFile2String ()throws IOException, ParseException {
        JSONParser parser = new JSONParser();
       Object o = parser.parse(new FileReader(new File("/Users/Alexander/ideaspace/springboot-learning-example/springboot-mybatis/2017-11-08-12-00.json")));
        JSONObject jsonObject = (JSONObject) o;
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object o = parser.parse(new FileReader(new File("/Users/Alexander/ideaspace/springboot-learning-example/springboot-mybatis/2017-11-08-12-00.json")));
        JSONObject jsonObject = (JSONObject) o;
        System.out.println(jsonObject.toJSONString());
    }
}
