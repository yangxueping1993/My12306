<%@ page language="java" contentType="text/html; charset=GB2312"
import="com.neuedu.util.Constants,com.neuedu.domain.User"
    pageEncoding="GB2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û������޸�</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
 <script src="../js/jquery-1.11.3.js" type="text/javascript" ></script>
<script type="text/javascript">
function doSave() {
	if(validateForm()){
		var form=$("#form1").get(0);
		form.action="../doLogin?action=modifyPwd";
		form.method="post";
		form.target="content";
		form.submit();
	}
}
function validateForm() {
	var flag=true;
	//����֮ǰ����֤��Ϣ��Ϊ������֤�ṩλ��
	$(".tempTipSpan").remove();
	var oldPwd=$("#oldPwd");
	if(oldPwd.val()==""){
		oldPwd.after(concatTipStrHtml("������ԭ��������!"));
		flag=false;
	}
	var pwd=$("#pwd");
	if(pwd.val()==""){
		pwd.after(concatTipStrHtml("�������µ�����!"));
		flag=false;
	}
	var pwd2=$("#pwd2");
	if(pwd2.val()==""){
		pwd2.after(concatTipStrHtml("��ȷ��������!"));
		flag=false;
	}else if(pwd.val()!=pwd2.val()){
		pwd2.after(concatTipStrHtml("������������벻һ��!"));
		flag=false;
	}
	return flag;
}
function concatTipStrHtml(tip) {
	var tipStrHtml="<span class='tempTipSpan'><font color='red'>"+tip+"</font></span>";
	return tipStrHtml;
}
</script>
</head>

<body class="write_bg">
<iframe name="content" width='0' height='0'></iframe>
	<form name="form1"  id="form1" method="post" action="#">
<input type="hidden" name="userID"  value="<%=((User)session.getAttribute(Constants.userKey)).getId()%>"/>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"></td>
			</tr>
		</table>
		<table width="835" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="20" colspan="2"></td>
			</tr>
			<tr>
				<td width="64" height="12"></td>
				<td width="744" height="30" align="left" class="text_blod_title">�����޸�</td>
			</tr>
			<tr>
				<td height="15" colspan="2"><img src="../images/line1.jpg"
					width="835" height="6"></td>
			</tr>
			<tr>
				<td colspan="2"><table width="700" border="0" align="center"
						cellspacing="0">
						<tr>
							<td width="20"></td>
							<td width="100" height="40" class="text_cray1">�û�����</td>
							<td align="left" class="text_cray1"><input name="userId333"
								type="text" disabled="true" class="text_cray" id="textfield4"
								size="30"  value="<%=((User)session.getAttribute(Constants.userKey)).getUserName()%>"/></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" class="text_cray1">ԭ���룺</td>
							<td align="left" class="text_cray1"><input name="oldPwd"
								type="text" class="text_cray" id="oldPwd" size="30" /></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" class="text_cray1">�����룺</td>
							<td align="left" class="text_cray1"><input name="pwd"
								type="text" class="text_cray" id="pwd" size="30" /></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" class="text_cray1">ȷ�������룺</td>
							<td align="left" class="text_cray1"><input name="pwd2"
								type="text" class="text_cray" id="pwd2" size="30" /></td>
						</tr>
					</table> <br></td>
			</tr>
		</table>
		<table width="700" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="124" height="30"></td>
				<td width="78" align="left"><input name="button" type="button" onclick="doSave();"
					class="buttj" id="button" value="" ></td>
				<td width="39" align="center"></td>
				<td align="left"><input name="button2" type="reset"
					class="butqx" id="button2" value=""></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td height="275"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td height="2" background="../images/bottom_point.gif"></td>
			</tr>
			<tr>
				<td height="25" align="center"
					background="../images/bottom_ny_bg.gif" class="text_cray">copyright@12306
					��Ʊ��</td>
			</tr>
		</table>
	</form>
</body>
</html>
    