package com.Haige.Myblog.mvc;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @packageName: com.auko.demo.util
 * @className: ParseXml
 * @Description:
 * @author: auko
 * @data 2019-11-05 14:28
 */
public class ParseXml {
    private Document document;
    private String pagePath;
    private String action;
    private String method;
    private String actionClass;
    private String prefix;
    private String suffix;
    private String innerPrefix;
    private String forwardJspPath;
    private String voId;
    private String voClass;
    private String initAction;
    private String initActionClass;
    private String initmethod;
    private String initPath;
    public String getInitPath() {
		return initPath;
	}

	public void setInitPath(String initPath) {
		this.initPath = initPath;
	}

	public String getInitmethod() {
		return initmethod;
	}

	public void setInitmethod(String initmethod) {
		this.initmethod = initmethod;
	}

	private String resource;

    public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getInitAction() {
		return initAction;
	}

	public void setInitAction(String initAction) {
		this.initAction = initAction;
	}

	public String getInitActionClass() {
		return initActionClass;
	}

	public void setInitActionClass(String initActionClass) {
		this.initActionClass = initActionClass;
	}

	public void read(String fileName) {
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(new File(fileName));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Element getRootElement(Document doc){
        return doc.getRootElement();
    }

    public void parse(){
        // 准备获取前缀与后缀
        List listBeanAttribute = document.selectNodes("/demo-config/bean/@id");
        List listBeanElement = document.selectNodes("/demo-config/bean");
        Iterator iteratorBeanPathAttribute = listBeanAttribute.iterator();
        Iterator itertorBeanElement = listBeanElement.iterator();

        while(iteratorBeanPathAttribute.hasNext() &&itertorBeanElement.hasNext()){
            Attribute attribute = (Attribute) iteratorBeanPathAttribute.next();
            Element element = (Element) itertorBeanElement.next();
            if(attribute.getValue().equals("viewResolver")){
                // 假如有id为viewResolver的bean节点
                Iterator iteratorPrefix = element.elementIterator("prefix");
                Iterator iteratorSuffix = element.elementIterator("suffix");
                Iterator iteratorInnerPrefix = element.elementIterator("innerPrefix");

                Element elementPrefix = (Element)iteratorPrefix.next();
                Element elementSuffix = (Element)iteratorSuffix.next();
                Element elementInnerPrefix = (Element)iteratorInnerPrefix.next();

                // 获取前缀与后缀
                prefix = elementPrefix.getText();
                suffix = elementSuffix.getText();
                innerPrefix = elementInnerPrefix.getText();
            }
        }

        // 准备获取page相关信息
        List listPathAttribute = document.selectNodes("/demo-config/page/@path");
        List listPageElement = document.selectNodes("/demo-config/page");
        Iterator iteratorListPathAttribute = listPathAttribute.iterator();
        Iterator iteratorPageElement = listPageElement.iterator();

        while(iteratorListPathAttribute.hasNext() && iteratorPageElement.hasNext()){
            Attribute attribute = (Attribute) iteratorListPathAttribute.next();
            Element element = (Element) iteratorPageElement.next();

            Iterator iteratorValueObject = element.elementIterator("valueObject");
            Iterator iteratorAction = element.elementIterator("action");
            Iterator iteratorInit = element.elementIterator("init");
            System.out.println("xml path: "+prefix + attribute.getValue() + suffix);
            System.out.println("in path: "+prefix + pagePath);
            System.out.println("in Initpath: "+prefix + initPath);
            if((prefix + attribute.getValue() + suffix).equals(prefix + pagePath)||(prefix + attribute.getValue() + suffix).equals(prefix + initPath)){

               int  i = 0;
               //获取Init属性值
               while(iteratorInit.hasNext()) {
                   Element elementInit = (Element)iteratorInit.next();
                   String InitPath = elementInit.attributeValue("Initpath")+".jsp";
                   if(InitPath.equals(initPath)) {
                   initActionClass = elementInit.attributeValue("class");
                   initmethod = elementInit.attributeValue("method");
                   resource = elementInit.attributeValue("resource");
                   }
               }
                // 获取valueObject 属性值
                while(iteratorValueObject.hasNext()){
//                    int m = i++;
                    Element elementValueObject = (Element)iteratorValueObject.next();
                    voId = elementValueObject.attributeValue("id");
                    voClass = elementValueObject.attributeValue("class");
                }

                while(iteratorAction.hasNext()){
                    Element elementAction = (Element)iteratorAction.next();
                    // 目标路径, 即addMember.do 的 addMember
                    String actionPath = elementAction.attributeValue("actionPath");
                    if(actionPath.equals(action)){
                        // 如果action 与页面上的相同, 获取相应的方法
                        actionClass = elementAction.attributeValue("class");
                        method = elementAction.attributeValue("method");

                        if(elementAction.attributeValue("forward") != null){
                            forwardJspPath = innerPrefix + elementAction.attributeValue("forward");
                        }
                        if(elementAction.attributeValue("infoOut") != null){
                            forwardJspPath = innerPrefix + elementAction.attributeValue("forward");
                        }
                        
                        break;
                    }
                    
                   
                }
            }
        }
    }
    
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String PagePath) {
        this.pagePath = PagePath;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getVoId() {
        return voId;
    }

    public void setVoId(String voId) {
        this.voId = voId;
    }

    public String getVoClass() {
        return voClass;
    }

    public void setVoClass(String voClass) {
        this.voClass = voClass;
    }

    public String getForwardJspPath() {
        return forwardJspPath;
    }

    public void setForwardJspPath(String forwardJspPath) {
        this.forwardJspPath = forwardJspPath;
    }
}

