<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>普通用户</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>
<script type="text/JavaScript" src="../js/common.js"></script>
<body class="topbody">
	<form action="" method="get">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="1411" colspan="2"
					background="../images/ny_top_img_bg.gif"><img
					src="../images/ny_top_img.gif" width="650" height="108"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="75" height="25" bgcolor="#deedf8"></td>
				<td width="1122" align="left" valign="top" bgcolor="#deedf8"><span
					class="text_cray1">当前位置: 所有用户管理</span></td>
				<td width="140" valign="top" bgcolor="#deedf8" class="text_cray1">欢迎您，${userSession.userName }</td>
				<td width="64" align="left" valign="top" bgcolor="#deedf8">
				<a href="../Login.jsp" target="_blank" class="cray" onclick="logOut()">退出</a></td>
			</tr>
		</table>

	</form>
<script type="text/javascript">
 function logOut(){
	 window.top.location.href="../Login.jsp?logout=true";
 }
</script>
</body>
</html>
    