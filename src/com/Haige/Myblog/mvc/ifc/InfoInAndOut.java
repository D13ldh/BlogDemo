package com.Haige.Myblog.mvc.ifc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;


/**
 * @Author: auko on 2019-11-05 10:53
 * @Description: 实现存放页面中获取值的接口
 */
public interface InfoInAndOut extends Serializable {

    /**
     * 同map的get 方法
     * @param obj Object
     * @return Object
     */
    public Object get(Object obj);


    /**
     * 同map的put 方法
     * @param key Object
     * @param value Object
     */
    void put(String key, Object value);

    /**
     * 同ServletRequest 的 getParameter(String fieldName)
     * @param fieldName 元素名称
     * @return 返回元素名称的value
     */
    public String getParameter(String fieldName);

    /**
     * 同ServletRequest 的 getParameterNames()
     * @return 返回所有元素名称
     */
    public Enumeration getParameterNames();

    /**
     * 同ServletRequest 的 getParameterValues(String var)
     * @param fieldName 元素名称
     * @return 返回有相同元素名称的多个值, 以字符串数组的形式返回
     */
    public String[] getParameterValues(String fieldName);

    /**
     * 同HttpServlet 的 getQueryString()
     * @return 返回请求网址的 query string
     */
    public String getQueryString();

    public Object getVO() throws Exception;

    public Object getVO(String id) throws Exception;

    public List getVOs(String checkFieldName) throws Exception;

    public List getVOs(String voId, String checkFieldName) throws Exception;

    HttpServletRequest getHttpServletRequest() throws Exception;

    public HttpServletResponse getHttpServletResponse() throws Exception;

}

