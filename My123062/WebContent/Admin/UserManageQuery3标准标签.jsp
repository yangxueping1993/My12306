<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312" 
    %>
<%@ include file="../common/comm.jsp" %>
<%
	UserService us=UserService.getInstance();
	List<User> list=new ArrayList<User>();
	list=us.queryUser(null);
	 pageContext.setAttribute("userKK", list);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>所有用户管理</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
 <script src="../js/jquery-1.11.3.js" type="text/javascript" ></script>
<script language="javascript">
	function UserAdd(){
		window.navigate("UserInfo_Add.html");
	}
	$(function(){
		 console.log($("#checkAll").get(0).checked);
		 if($("#checkAll").get(0).checked==false){
			 console.log("你全选啦！");
		}
	
		$("#checkAll").click(function(){
			if(this.checked){
				//全选
				$("[name='checkbox']").attr("checked","true");
			}else{
				//反选
				$("[name='checkbox']").removeAttr("checked");
			}
		});
	  
		$("#query").click(function(){
			console.log("查询");
			/* var form=document.form1;
			form.action="QueryServlet?action=query";
			form.method="post";
			form.submit(); */
			var form=$("#form1").get(0);
			form.action="QueryServlet?action=query";
			form.method="post";
			form.submit();
		});
	});
</script>
</head>
<body class="write_bg">
	<form name="form1" method="post" action="" id="form1">

		<table width="1107" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"></td>
			</tr>
		</table>
		<table width="850" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="20" colspan="2"></td>
			</tr>
			<tr>
				<td width="13" height="30" align="left" valign="top"></td>
				<td width="822" align="left" valign="top" class="text_blod_title">用户管理</td>
			</tr>
			<tr>
				<td height="15" colspan="2" align="center"><img
					src="../images/line.jpg" width="850" height="6"></td>
			</tr>
			<tr>
				<td height="15" colspan="2"></td>
			</tr>
		</table>
		<table width="835" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="835" background="../images/wb_01 (3).jpg"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="5"></td>
							<td width="4%" height="25" align="left" class="text_cray1">姓名</td>
							<td width="11%" align="left" class="text_cray1"><label>
									<input name="realName" type="text" class="text_cray" style="width: 80px">
							</label></td>
							<td width="6%" align="center" class="text_cray1">性别</td>
							<td width="6%" align="left" class="text_cray1"><label>
									<select name="sexCode" class="text_cray">
										<option value="M">男</option>
										<option value="F">女</option>
								</select>
							</label></td>
							<td width="9%" align="center" class="text_cray1">证件类型</td>
							<td width="13%" align="left" class="text_cray1"><label>
									<select class="text_cray" name="idTypeCode"	id="cardType">
										<option value="1">二代身份证</option>
										<option value="2">港澳通行证</option>
										<option value="3">台湾通行证</option>
										<option value="4">护照</option>
								</select>
							</label></td>
							<td width="8%" align="center" class="text_cray1">证件号码</td>
							<td width="13%" align="left" class="text_cray1"><label>
									<input name="idCode" type="text" class="text_cray"
									style="width: 100px">
							</label></td>
							<td width="8%" align="center" class="text_cray1">旅客类型</td>
							<td width="13%" align="left" class="text_blod"><label>
									<select class="text_cray" id="passengerType"
									name="passenger_type" style="width: 100px">
										<option value="1">成人</option>
										<option value="2">儿童</option>
										<option value="3">学生</option>
										<option value="4">残疾军人、伤残人民警察</option>
								</select>
							</label></td>
							<td width="8%" align="center" valign="middle"
								class="text_craybold"><label> 
								<input name="Submit" 	type="button" class="butcx"  id="query" value="">
							</label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td height="20" colspan="11" align="center">&nbsp;</td>
						</tr>
					</table>
					<table width="553" border="1" align="center" cellpadding="0"
						cellspacing="1" bordercolor="#dadada" bgcolor="#FFFFFF">
						<tr align="center">
							<td width="44" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1" >选择</td>
							<td width="98" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">姓名</td>
							<td width="80" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">性别</td>
							<td width="132" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">证件类型</td>
							<td width="247" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">证件号码</td>
							<td width="82" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">旅客类型</td>
							<td width="89" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1">操作</td>
						</tr>
						<tr align="center">
							<td height="15" colspan="7" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1"><img
								src="../images/line1.jpg" width="790" height="6"></td>
						</tr>
						
						<c:forEach items="${userKK}" var="userK" varStatus="status"  begin="1" end="5" step="1">
						<tr align="center" bgcolor='${status.count%2==0?"#FFFFFF":"pink" }' >
								<td bordercolor="#FFFFFF" class="text_cray1">${status.index+1}  
								<input  type="checkbox" name="checkbox" value='${userK.id}'   ${userK.id==userSession.id?"disabled":""} ></td>
								<td width="98" bordercolor="#FFFFFF"  bgcolor="#FF69B4" class="text_cray1">${userK.userName}</td>
								<td width="80" bordercolor="#FFFFFF" class="text_cray1">${userK.sex=="M"?"男":"女"}</td>
								<td width="132" bordercolor="#FFFFFF" bgcolor="	#FFB6C1"  class="text_cray1"> ${userK.certType.content} </td>
								<td width="247" bordercolor="#FFFFFF" class="text_cray1">${userK.cert}</td>
								<td width="82" bordercolor="#FFFFFF"  bgcolor="#FF69B4" class="text_cray1"> ${userK.userType.content} </td>
								<td width="89" bordercolor="#FFFFFF" class="text_cray1">
								 <c:choose>
									 <c:when test="${userK.id!=userSession.id }">
									 查看
									 </c:when>
									 <c:otherwise>
									 <a href="UserManageInfo_Amind_Edit.html" class="text_red">编辑</a>
									 </c:otherwise>
								 </c:choose>
								 <c:if test="${userK.id==userSession.id }">当前</c:if>
								 </td>
							</tr>
						</c:forEach>
					
						
					</table> <br>
					<table width="773" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr align="center">
							<td width="102" align="left" class="text_cray1"><a href="#">
									<label></label> <label></label> 

<label>
<input type="checkbox" name="checkbox2" value="11"  id="checkAll"	>
<span class="text_blue">全选</span></label>
							</a></td>
							<td width="525" align="right" class="text_cray1"><a href="#">
									<!--<input type="button" name="Submit23" value="新增" onClick="UserAdd()"> -->
							</a></td>
							<td width="55" align="right" class="text_cray1"><a href="#">
									<input name="Submit22" type="button" class="butsc" value="">
							</a></td>
							<td width="91" align="right" class="text_cray1"><label>
									<input name="Submit3" type="submit" class="butdc" value="">
							</label></td>
						</tr>
					</table> <br>
					<table width="773" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr align="center" style="width: 60%">
							<td width="335" align="center" class="text_cray">&nbsp;</td>
							<td width="284" align="center" class="text_cray">>> 1 2 3 4
								5 6 7 8 &lt;&lt;</td>
							<td width="154" align="right" class="text_cray1"
								style="width: 20%"><label class="text_cray"> 每页显示 <select
									name="select2">
										<option>10</option>
										<option>20</option>
										<option>30</option>
								</select> 条信息
							</label></td>
						</tr>
					</table> <br></td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td height="2" background="../images/bottom_point.gif"></td>
			</tr>
			<tr>
				<td height="25" align="center"
					background="../images/bottom_ny_bg.gif" class="text_cray">copyright@12306
					购票网</td>
			</tr>
		</table>
	</form>
</body>
</html>
    