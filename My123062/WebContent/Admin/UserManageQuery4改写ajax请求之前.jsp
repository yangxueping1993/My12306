<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312" 
    %>
<%@ include file="../common/comm.jsp" %>
<%-- <%
	UserService us=UserService.getInstance();
	List<User> list=new ArrayList<User>();
	list=us.queryUser(null);
	 pageContext.setAttribute("userKK", list);
%> --%>
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
		 $("#checkAll").click(function(){
				if(this.checked){
					/* $("[name='checkbox']").attr("checked","true"); */
					 $(":checkbox[name='checkbox']").each(function(){
						/*  if(this.disabled){
							return true;
							返回 'false' 将停止循环 (就像在普通的循环中使用 'break')。
							返回 'true' 跳至下一个循环(就像在普通的循环中使用'continue')。
							return false 可用于及早停止循环,相当于break。
						 } else {
							 this.checked=true;
						 } */
						if(!this.disabled){
							 this.checked=true;
						 }
					});
				}else{
					/* $("[name='checkbox']").removeAttr("checked"); */
					 $(":checkbox[name='checkbox']").each(function(){
						this.checked=false; 
					}); 
				}
		});
	});
	function doSelOne(obj){
	 	var allFlag=true;
	 	$(":checkbox[name='checkbox']").each(function(){
	 	    if(!(this.checked||this.disabled)){
	 	    	allFlag=false;
	 	    	return false; 
	 	    }
	 	});
	 	$("#checkAll").get(0).checked=allFlag;
	 }
	
	function deleteMore(){
		var form=$("#form1").get(0);
		form.action="QueryServlet?action=delete";
		form.method="post";
		form.submit();
	}
	
	 function queryAll(type){
			if(type=="query"){
				//点击查询按钮后的查询
				$("#curPage").val("1");
			}else{
				//点击分页连接后的查询
			}
			console.log("查询");
			var form=$("#form1").get(0);
			form.action="QueryServlet?action=query";
			form.method="post";
			form.submit();
	   }
	
		function prePage(){
			var curPage=$("#curPage").val();
			if(curPage==1){
				alert("目前已经是第一页！")
			}else{
				$("#curPage").val(parseInt(curPage)-1);
				queryAll();
			}
		}
		function nextPage(){
			var curPage=$("#curPage").val();
			var pageCnt=$("#pageCnt").val();
			if(curPage==pageCnt){
				alert("目前已经是最后一页！")
			}else{
				$("#curPage").val(parseInt(curPage)+1);
				queryAll();
			}
		}
		function gotoPage(index){
			var curPage=$("#curPage").val();
			if(index==curPage){
				alert("目前已经是"+index+"页！")
			}else{
				$("#curPage").val(index);
				//提交
				queryAll();
			}
		}
		function exportExcel(type){
			if(type=="client"){
				tableToExcel("tableExcel");
				
			}else if(type=="back"){
				var form=document.form1;
				form.action="QueryServlet?action=excel";
				form.method="post";
				form.submit();
			}
		}
		var tableToExcel = (function() {
		    var uri = 'data:application/vnd.ms -excel;base64,',
		            template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
		            base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
		            format = function(s, c) {
		                return s.replace(/{(\w+)}/g,
		                        function(m, p) { return c[p]; }) }
		    return function(table, name) {
		        if (!table.nodeType) table = document.getElementById(table)
		        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
		        window.location.href = uri + base64(format(template, ctx))
		    }
		})()
</script>
</head>
<body class="write_bg">

	<form name="form1" method="post" action="" id="form1">
<input type="hidden" name="curPage" id="curPage"  value='${empty curPage?"1":curPage }'>
<input type="hidden" name="recordCnt" id="recordCnt" value='${empty recordCnt?"0":recordCnt }'>
<input type="hidden" name="pageCnt"  id="pageCnt" value='${empty pageCnt?"0":pageCnt}'>
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
									<input name="realName"  value="${pageUserParam.userName }" type="text" class="text_cray" style="width: 80px">
							</label></td>
							<td width="6%" align="center" class="text_cray1">性别</td>
							<td width="6%" align="left" class="text_cray1"><label>
									<select name="sexCode" class="text_cray">
										<option value="">请选择</option>
										<option value="M" ${pageUserParam.sex=="M"?"selected":"" }>男</option>
										<option value="F" ${pageUserParam.sex=="F"?"selected":"" }>女</option>
								</select>
							</label></td>
							<td width="9%" align="center" class="text_cray1">证件类型</td>
							<td width="13%" align="left" class="text_cray1"><label>
									<select class="text_cray" name="idTypeCode"	id="cardType">
										<option value="0" >请选择</option>
										<option value="1" ${pageUserParam.certType.id=="1"?"selected":"" }>二代身份证</option>
										<option value="2" ${pageUserParam.certType.id=="2"?"selected":"" }>港澳通行证</option>
										<option value="3" ${pageUserParam.certType.id=="3"?"selected":"" }>台湾通行证</option>
										<option value="4" ${pageUserParam.certType.id=="4"?"selected":"" }>护照</option>
								</select>
							</label></td>
							<td width="8%" align="center" class="text_cray1">证件号码</td>
							<td width="13%" align="left" class="text_cray1"><label>
							<input name="idCode" type="text" class="text_cray"  value="${pageUserParam.cert}" style="width: 100px">
							</label></td>
							<td width="8%" align="center" class="text_cray1">旅客类型</td>
							<td width="13%" align="left" class="text_blod"><label>
									<select class="text_cray" id="passengerType"
									name="passenger_type" style="width: 100px">
										<option value="0">请选择</option>
										<option value="1" ${pageUserParam.userType.id=="1"?"selected":"" }>成人</option>
										<option value="2" ${pageUserParam.userType.id=="2"?"selected":"" }>儿童</option>
										<option value="3" ${pageUserParam.userType.id=="3"?"selected":"" }>学生</option>
										<option value="4" ${pageUserParam.userType.id=="4"?"selected":"" }>残疾军人、伤残人民警察</option>
								</select>
							</label></td>
							<td width="8%" align="center" valign="middle"
								class="text_craybold"><label> 
								<input name="Submit" 	type="button" class="butcx"  id="query" value="" onclick="queryAll('query');">
							</label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td height="20" colspan="11" align="center">&nbsp;</td>
						</tr>
					</table>
					<table id="tableExcel" width="553" border="1" align="center" cellpadding="0"
						cellspacing="1" bordercolor="#dadada" bgcolor="#FFFFFF">
						<tr align="center">
						<td width="44" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1" >选择</td>
							<td width="44" height="25" valign="middle" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1" >序号</td>
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
							<td height="15" colspan="8" bordercolor="#FFFFFF"
								bgcolor="#FFFFFF" class="text_cray1"><img
								src="../images/line1.jpg" width="790" height="6"></td>
						</tr>
						<!-- begin="1" end="5" step="1" -->
						<c:forEach items="${userList}" var="userK" varStatus="status"  >
						<tr align="center" bgcolor='${status.count%2==0?"#FFFFFF":"pink" }' >
								<td bordercolor="#FFFFFF" class="text_cray1">${status.index+1}  
								<input  type="checkbox" name="checkbox"  onclick="doSelOne(this);" value='${userK.id}'   ${userK.id==userSession.id?"disabled":""} ></td>
								<td width="98" bordercolor="#FFFFFF"  bgcolor="#FF69B4" class="text_cray1">${status.count}  </td>
								<td width="98" bordercolor="#FFFFFF"  bgcolor="#FF69B4" class="text_cray1">${userK.realName}</td>
								<td width="80" bordercolor="#FFFFFF" class="text_cray1">${userK.sex=="M"?"男":"女"}</td>
								<td width="132" bordercolor="#FFFFFF" bgcolor="	#FFB6C1"  class="text_cray1"> ${userK.certType.content} </td>
								<td width="247" bordercolor="#FFFFFF" class="text_cray1">${userK.cert}</td>
								<td width="82" bordercolor="#FFFFFF"  bgcolor="#FF69B4" class="text_cray1"> ${userK.userType.content} </td>
								<td width="89" bordercolor="#FFFFFF" class="text_cray1">
								<a href='QueryServlet?action=${userK.id }'  target="_blank" class="text_red">编辑${userK.id}</a>
								 <%-- <c:choose> 
									 <c:when test="${userK.id!=userSession.id }"> 查看 </c:when>
									 <c:otherwise>
									 <a href="UserManageInfo_Amind_Edit.html" class="text_red">编辑</a>
									 </c:otherwise>
								 </c:choose> <c:if test="${userK.id==userSession.id }">当前</c:if> --%>
								 </td>
							</tr>
						</c:forEach>
	
					</table> <br>
					<table width="773" border="0" align="center" cellpadding="0" cellspacing="0">
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
									<input name="Submit22" type="button" class="butsc"  id="delete" value="" onclick="deleteMore();"> 
							</a></td>
							<td width="91" align="right" class="text_cray1"><label>
									<input name="Submit3" type="button" class="butdc" value="" onclick="exportExcel('client');">
									<input name="Submit4" type="button" class="butdc" value="" onclick="exportExcel('back');">
							</label></td>
						</tr>
					</table> <br>
					<table width="773" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr align="center" style="width: 60%">
							<td width="335" align="center" class="text_cray">&nbsp;</td>
							<td width="284" align="center" class="text_cray">
	<c:if test="${empty pageCnt==false }">
		 <a href="javascript:void(0);" onclick="prePage();">&lt;&lt;</a>
		 <c:forEach var="i" begin="1" end="${pageCnt }">
			<a href="javascript:void(0);" onclick="gotoPage(${i});">${i}</a>
		</c:forEach>
		<a href="javascript:void(0);" onclick="nextPage();">&gt;&gt;</a>	
	</c:if></td>
							<td width="154" align="right" class="text_cray1"
								style="width: 20%"><label class="text_cray"> 每页显示 
								<select  name="pageSize">
										<option value="5" ${pageSize==5?"selected":"" }>5</option>
										<option value="3" ${pageSize==3?"selected":"" }>3</option>
										<option value="1" ${pageSize==1?"selected":"" }>1</option>
										<option value="2" ${pageSize==2?"selected":"" }>2</option>
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
    