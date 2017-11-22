<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"
     import="com.neuedu.util.Constants,com.neuedu.domain.*" 
    %>
    <%
    User user=(User)session.getAttribute(Constants.userKey);
    %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户信息修改</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">

</head>

<body class="write_bg">
<iframe width="0" height="0" name="targetFrame" ></iframe>
	<form name="form1"  id="form1"  method="post" action=""  enctype="multipart/form-data" >
<input type="hidden" id="userID"  name="userID" value="${userSession.id }">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"><br> <br></td>
			</tr>
		</table>
		<table width="835" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="20" colspan="2" align="center"></td>
			</tr>
			<tr>
				<td width="64" height="11" class="text_blod_title"></td>
				<td width="786" height="30" align="left" class="text_blod_title">修改个人信息</td>
			</tr>
			<tr>
				<td height="15" colspan="2"><img src="../images/line1.jpg"
					width="835" height="6"></td>
			</tr>
			<tr>
				<td colspan="2"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="64"></td>
							<td width="772" height="25" align="left" class="text_cray">注：标有
								<span class="text_red">*</span> 处，均为必填项
							</td>
						</tr>
						<tr>
							<td height="20" colspan="2"></td>
						</tr>
					</table>
					<table width="700" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="15" colspan="4" align="left" class="text_title">详细信息</td>
						</tr>
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red1"></td>
							<td width="100" height="40" align="left" class="text_cray1">登录名：</td>
							<td width="350" align="left" class="text_cray1"><input
								name="loginName" type="text" disabled="true" class="text_cray"
								id="textfield22" value="${sessionScope.userSession.userName}" readonly="readonly" /></td>
							<td width="230" colspan="-1" rowspan="7" align="center"
								background="../images/bg_point_write.gif" class="text_cray1">
								<img src='${empty sessionScope.userSession.imagePath?"../images/cat.jpg":"../images/photo/"}${empty sessionScope.userSession.imagePath?"":sessionScope.userSession.imagePath}' width="200">
								<table width="90%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td height="15"></td>
									</tr>
									<tr>
										<td height="7" align="center" class="text_cray">上传照片</td>
									</tr>
									<tr>
										<td height="8"></td>
									</tr>
									<tr>
										<td align="center">
										<input name="uploadFile" type="file" 	class="text_cray" size="20" /></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td width="100" height="40" align="left" class="text_cray1">真实姓名：</td>
							<td align="left" class="text_cray1">
							<input name="realName" onblur="processRequest(true);"
								type="text" disabled="true" class="text_cray" id="textfield2"
								value="${sessionScope.userSession.realName}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td width="100" height="30" align="left" class="text_cray1">性别：</td>
							<td align="left" class="text_cray1">
							<input type="radio" name="sex"
							 value="M"  checked='${userSession.sex=="M"?"checked":""}' /> 
							<span class="text_cray"><label>男</label> 
							<input type="radio" name="sex"
							value="F"  checked='${userSession.sex=="F"?"checked":""}'  /> <label>女</label>
							</span> <label></label> <span><label></label> </span></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">省份：</td>
							<td align="left" class="text_cray1">
									<select name="province" class="text_cray" id="province">
									<option value="省份" selected="selected">省份</option>
									</select>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">城市：</td>
							<td align="left" class="text_cray1">
								<select name="city" class="text_cray" id="city">
								<option value="城市" selected="selected">市县</option>
								</select>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">证件类型：</td>
							<td align="left" class="text_cray1">
								<select	class="text_cray" name="idTypeCode"	id="cardType">
													<option value="1" ${userSession.certType.id=="1"?"selected":""}><span>二代身份证</span></option>
													<option value="2" ${userSession.certType.id=="2"?"selected":""}><span>港澳通行证</span></option>
													<option value="3" ${userSession.certType.id=="3"?"selected":""}><span>台湾通行证</span></option>
													<option value="4" ${userSession.certType.id=="4"?"selected":""}><span>护照</span></option>
								</select>
								</td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">证件号码：</td>
							<td align="left" class="text_cray1"><input name="cert"
								type="text" disabled="disabled" class="text_cray"
								id="textfield6" value="${sessionScope.userSession.cert}" readonly="true" /></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">出生日期：</td>
							<td colspan="2" align="left" class="text_cray1"><input
								name="birthday" type="date" disabled="disabled"
								class="text_cray" id="textfield7" value="${sessionScope.userSession.birthday}"
								readonly="true" /></td>
						</tr>
						<tr>
							<td width="20" height="35"></td>
							<td width="100" height="40" align="left" class="text_cray1">旅客类型：</td>
							<td height="35" colspan="2" align="left" class="text_cray1">
								<select class="text_cray" id="passengerType" name="passenger_type">
										<option value="1" ${userSession.userType.id=="1"?"selected":""}>成人</option>
										<option value="2" ${userSession.userType.id=="2"?"selected":""}>儿童</option>
										<option value="3" ${userSession.userType.id=="3"?"selected":""}>学生</option>
										<option value="4" ${userSession.userType.id=="4"?"selected":""}>残疾军人、伤残人民警察</option>
								</select></td>
						</tr>
						<tr>
							<td height="10" colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td width="100" height="80" align="left" class="text_cray1">备注：</td>
							<td height="80" colspan="2" align="left" class="text_cray1">
							<textarea	name="content" rows="8" class="text_cray" style="width: 100%">${sessionScope.userSession.content}</textarea>
							</td>
						</tr>
					</table>
					<br>
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="700" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="164"></td>
							<td width="99" height="30" align="center">
							<input name="button" type="button" class="buttj" id="button" value=""></td>
							<td width="98"></td>
							<td width="97" align="center">
							<input name="button2" type="button" class="butcz" id="button2" value="" onclick="resetPage();"> </td> 
							<td width="92"></td>
						</tr>
					</table>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td height="20"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td height="2" background="../images/bottom_point.gif"></td>
			</tr>
			<tr>
				<td height="25" align="center"background="../images/bottom_ny_bg.gif" class="text_cray">
				copyright@12306购票网</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
	</form>
	<script type="text/JavaScript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">
//注意各个步骤加载时机

function resetPage(){
	//实现刷新页面
	//方法1
	//window.location.reload();
	//方法2
	//window.location.href=window.location.href;
	//方法3
	/* $("#button2").click(function(){
		document.form1.reset();
	});  */
	//方法4
	//按钮类型为reset
	 $("#button2").attr("type","reset"); 
}

$(function(){
	$(":input").removeAttr("readonly");
	$(":input").removeAttr("disabled");
	//设置可上传文件
	$("#form1").attr("enctype","multipart/form-data");
});

//1.获取用户选中的省份
var userPro='${userSession.city.province.province}';
//1.根据用户选中省份，进行下拉框设置
var opArray=province.options;
for(var i=0;i<opArray.length;i++){
	if(opArray[i].value==userPro){
		opArray[i].selected="selected";
	}
}
//3.根据设置后的省份，触发函数，获取对应城市
showChild(province, city, cityArr);
//4.选中用户所属城市
var userCity='${userSession.city.city}';
var opArray=city.options;
for(var i=0;i<opArray.length;i++){
	if(opArray[i].value==userCity){
		opArray[i].selected="selected";
	}
}
</script>
<script type="text/javascript">
//注册时，验证登录名是否已经存在
 function processRequest(async){
	 $("span[name=tempTipSpan]").remove();
	var flag=false;
	var str="由字母、数字或“_”组成，长度不少于6位，不多于30位";
	var tipStr="<span style='color: red' name='tempTipSpan'>"+str+"</span>";
	var loginNameObj=$("input[name=loginName]");
	var loginNameVal=loginNameObj.val();
	if(loginNameVal=='${userSession.userName}'){
		//如果用户名等于session中的用户名，表示用户名没有修改，则不做任何验证
		return true;
	}
	if(!(/^\w{6,30}$/.test(loginNameVal))){
		console.log("延伸");
		loginNameObj.after(tipStr);
		flag=false;
	}else{
		$.ajax({
			//相对路径
			url:"ModifyServlet?action=check",
			type:"post",
			async:async,
			data:{loginName:loginNameVal,userID:$("#userID").val()},
			success:function(data){
				if(data=="exists"){
					str="登录名已存在！";
					tipStr="<span style='color: red' name='tempTipSpan'>"+str+"</span>"; 
					$("input[name=loginName]").after(tipStr);
				}else{
					flag=true;
				}
			},
			error:function(resp,status,xhr){
				alert("页面的Ajax请求发生异常，请稍后重试");
			}
		});
		return flag;
	}
}
$(function(){
	//验证表单中其他内容
	function validateForm(){
		  obj=$("form:first")[0];
		  var flag=true;
 		return flag;
	}	
	$('#button').click(function(){
		var flag=false;
		flag=processRequest(false);
		if(flag){
			flag=validateForm();
		}
		if(flag){
			//提交表单
			/**
			1、不需要上传文件时，ajax请求用data:$("#form1").serialize(),传递表单数据，
			2、需要上传文件时，ajax请求需设置
			data:new FormData($("#form1")[0]),
			cache:false,
			processData:false,
			contentType:false,
			*/
			//ajax文件上传开始
			<%--  $.ajax({
				//绝对路径
				url:"<%=request.getContextPath()%>/User/ModifyServlet?action=update",
				//url:"ModifyServlet?action=update",
				type:"post",
				//contentType:"multipart/form-data",
				//data:$("#form1").serialize(),
				data:new FormData($("#form1")[0]),
				cache:false,
				processData:false,
				contentType:false,
				success:function(data){
					if(data=="success"){
						window.location.href="UserManageInfo.jsp";
					}else{
						alert("保存失败，请稍后重试");
					}
				},
				error:function(resp,status,xhr){
					alert("消息接收保存失败，请稍后重试");
				}
			});  --%>
			//ajax文件上传end
			
			//普通模式上传文件begin
			var form=document.form1;
			form.action="<%=request.getContextPath()%>/User/ModifyServlet?action=update";
			form.method="post";
			form.target="targetFrame";
			form.submit();
			//普通模式上传文件end
		}
	});
});
</script>	
</body>
</html>
    