<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
    <%
    //设定请求返回的类型和编码
    response.setContentType("text/xml;charset=GB2312");
    //设定接受者不需要缓存
    response.setHeader("Cache-Control","no-cache");
    StringBuffer sb=new StringBuffer();
    sb.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
    sb.append("<books>");
    sb.append("<book>");
    sb.append("<title>");
    sb.append("Java编程思想");
    sb.append("</title>");
    sb.append("</book>");
    sb.append("<book>");
    sb.append("<title>");
    sb.append("数据结构与算法");
    sb.append("</title>");
    sb.append("</book>");
    sb.append("</books>");
    //这里的write和print作用相同，println返回数据时会多一个回车
    //三种方法都可以
  /*   response.getWriter().write(sb.toString());
    response.getWriter().print(sb.toString()); */
    response.getWriter().println(sb.toString());
    %>
    <!--不要使用这种方式返回xml数据，ajax无法解析  -->
<%--     <%=sb.toString() %> --%>
    <%-- <%=sb %> --%>
<%-- <%="<?xml version=\"1.0\" ?>" %> --%>