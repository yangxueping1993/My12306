<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
    <%
    //�趨���󷵻ص����ͺͱ���
    response.setContentType("text/xml;charset=GB2312");
    //�趨�����߲���Ҫ����
    response.setHeader("Cache-Control","no-cache");
    StringBuffer sb=new StringBuffer();
    sb.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
    sb.append("<books>");
    sb.append("<book>");
    sb.append("<title>");
    sb.append("Java���˼��");
    sb.append("</title>");
    sb.append("</book>");
    sb.append("<book>");
    sb.append("<title>");
    sb.append("���ݽṹ���㷨");
    sb.append("</title>");
    sb.append("</book>");
    sb.append("</books>");
    //�����write��print������ͬ��println��������ʱ���һ���س�
    //���ַ���������
  /*   response.getWriter().write(sb.toString());
    response.getWriter().print(sb.toString()); */
    response.getWriter().println(sb.toString());
    %>
    <!--��Ҫʹ�����ַ�ʽ����xml���ݣ�ajax�޷�����  -->
<%--     <%=sb.toString() %> --%>
    <%-- <%=sb %> --%>
<%-- <%="<?xml version=\"1.0\" ?>" %> --%>