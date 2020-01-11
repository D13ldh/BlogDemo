
package com.Haige.Myblog.mvc.impl;

import com.Haige.Myblog.mvc.ParseXml;
import com.Haige.Myblog.mvc.ifc.InfoInAndOut;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.dom4j.DocumentException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.lang.reflect.Method;

/**
 * @packageName: com.auko.demo.util
 * @className: InfoHandler
 * @Description:
 * @author: auko
 * @data 2019-11-05 11:20
 */
public class InfoHandler implements InfoInAndOut {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> infoIn = new HashMap();
    private String voId;
    private String voClass;
    private String resource;
    public InfoHandler() {
    }

    public InfoHandler(HttpServletRequest request, HttpServletResponse response, String voId, String voClass) throws Exception {
        this.request = request;
        this.response = response;
        this.voId = voId;
        this.voClass = voClass;
        parseRequest();
    }
//    public InfoHandler(HttpServletRequest request, HttpServletResponse response,String resource) {
//    	this.request = request;
//        this.response = response;
//        this.resource = resource;
//        InitParse();
//    }
//
//
//    private void InitParse() {
//		String RequestURI = request.getRequestURI();
//		System.out.println("RequestURI------"+RequestURI);
//		
//		
//	}

	private boolean isJSON(String str) {
        boolean result = false;
        try {
            if(str != null && !"".equals(str)){
                Object obj = JSON.parse(str);
                result = true;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private void parseRequest() throws Exception {
        ServletInputStream requestInputStream = request.getInputStream();
        String requestString = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(requestInputStream, "utf-8"));) {
            StringBuilder sb = new StringBuilder();
            String line;
            // 读取request body内容
            while (true) {

                if (((line = br.readLine()) == null)) {
                    break;
                }
                sb.append(line);
            }

            String queryString = request.getQueryString();
            System.out.println("queryString------"+queryString);
            if (queryString != null) {
                // 若有query string, 就补进去
                // 根据前面是否有数据来决定加不加 &
                sb.append(sb.length() == 0 ? queryString : "&"+queryString);
            }

            // 对url进行解码
            requestString = decodeUrl(sb.toString());
            System.out.println(requestString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断request body 是form data 还是 json 格式
        boolean isJson = isJSON(requestString);
        if (isJson) {
            infoIn = getJsonToMap(requestString);
        } else {
            infoIn = getRequestToMap(requestString);
        }
    }

    private String decodeUrl(String url) throws UnsupportedEncodingException {
        String prevURL = "";
        String decodeURL = url;
        while (!prevURL.equals(decodeURL)) {
            prevURL = decodeURL;
            decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
        }
        return decodeURL;
    }

    private HashMap getRequestToMap(String requestString) throws Exception {
        request.setCharacterEncoding("UTF-8");
        HashMap infoIn = new HashMap();

        for (String item : requestString.split("&")) {
            // = 号左边为fieldName
            String fieldName = item.split("=")[0];
            // 右边为值
            String value = item.split("=")[1];
            if (fieldName.endsWith("_" + voId)) {
                // 将需要的数据存入map
                System.out.println("fieldName : " + fieldName);
                System.out.println("value : " + value);
                infoIn.put(fieldName, value);
            }
        }
        return infoIn;
    }

    private HashMap getJsonToMap(String jsonString) {
        return JSON.parseObject(jsonString, HashMap.class);
    }


    @Override
    public Object get(Object obj) {
        return this.infoIn.get(obj);
    }

    @Override
    public void put(String key, Object value) {
        this.infoIn.put(key, value);
    }

    @Override
    public String getParameter(String fieldName) {
        return request.getParameter(fieldName);
    }

    @Override
    public Enumeration getParameterNames() {
        return request.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String fieldName) {
        return request.getParameterValues(fieldName);
    }

    @Override
    public String getQueryString() {
        return request.getQueryString();
    }

    @Override
    public Object getVO() throws Exception {
        return getVO(voId);
    }

    @Override
    public Object getVO(String id) throws Exception {
        Object objVo = null;
        System.out.println("D13voId----------"+voId);
        if (voId != null) {
            Class classVo = Class.forName((String) voClass);
            objVo = classVo.newInstance();
            String fieldName;
            String propName;
            System.out.println("D13infoIn----------"+infoIn);
            if (infoIn != null) {
                for (Map.Entry<String, Object> entry : infoIn.entrySet()) {

                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    // 获取页面中所有元素名
                    fieldName = entry.getKey();
                    if (fieldName.endsWith("_" + voId)) {
                        // 若为特定的数据, 则准备注入到vo中
                        String value = (String) entry.getValue();
                        propName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.lastIndexOf('_'));
                        System.out.println("ready to invoke: " + propName);
                        Method mtdVo = classVo.getMethod(propName, new Class[]{String.class});
                        try {
                            // 将对应的值注入到VO对应的set方法
                            mtdVo.invoke(objVo, value);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("配置文件必须设定valueObject");
        }
        return objVo;
    }

    @Override
    public List getVOs(String checkFieldName) throws Exception {
        return null;
    }

    @Override
    public List getVOs(String voId, String checkFieldName) throws Exception {
        return null;
    }

    @Override
    public HttpServletRequest getHttpServletRequest() throws Exception {
        return null;
    }

    @Override
    public HttpServletResponse getHttpServletResponse() throws Exception {
        return null;
    }

}

