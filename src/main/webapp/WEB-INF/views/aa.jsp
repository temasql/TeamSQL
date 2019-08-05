<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<span style="font-family: 굴림;">    </span><head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
sun.boot.class.path : <%= System.getProperty("sun.boot.class.path") %><hr>
sun.boot.lihrary.path : <%= System.getProperty("sun.boot.lihrary.path") %><hr>
java.lihrary.path : <%= System.getProperty("java.lihrary.path") %><hr>
java.class.path : <%= System.getProperty("java.class.path") %><hr>
getClassLoader() : <%= this.getClass().getClassLoader() %><hr>
DocumentBuilderFactory : <%= javax.xml.parsers.DocumentBuilderFactory.newInstance() %><hr>
/javax/xml.parsers/DocumentBuilderFactory.class : <%= this.getClass().getResource("/javax/xml.parsers/DocumentBuilderFactory.class") %><hr>
SAXParserFactory : <%= javax.xml.parsers.SAXParserFactory.newInstance() %><hr>
/javax/xml/parsers/SAXParserFactory.class : <%= this.getClass().getResource("/javax/xml/parsers/SAXParserFactory.class") %><hr>
TransformerFactory : <%= javax.xml.transform.TransformerFactory.newInstance() %><hr>
/javax/xml/transform/TransformerFactory.class : <%= this.getClass().getResource("/javax/xml/transform/TransformerFactory.class") %><hr>
<hr>
Servlet : <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><hr>
JSP : <%= javax.servlet.jsp.JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><hr>
ServerInfo : <%= application.getServerInfo() %><hr>
RealPath : <%= application.getRealPath("/") %><hr><hr>
ContextPath : <%= request.getContextPath()%><hr>
<hr>
totalMemory : <%= Runtime.getRuntime().totalMemory() %><hr>
maxMemory : <%= Runtime.getRuntime().maxMemory() %><hr>
freeMemory : <%= Runtime.getRuntime().freeMemory() %><hr>
<hr>
    </body>
</html>

