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
<title>�û���Ϣ�޸�</title>
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
				<td width="786" height="30" align="left" class="text_blod_title">�޸ĸ�����Ϣ</td>
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
							<td width="772" height="25" align="left" class="text_cray">ע������
								<span class="text_red">*</span> ������Ϊ������
							</td>
						</tr>
						<tr>
							<td height="20" colspan="2"></td>
						</tr>
					</table>
					<table width="700" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="15" colspan="4" align="left" class="text_title">��ϸ��Ϣ</td>
						</tr>
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red1"></td>
							<td width="100" height="40" align="left" class="text_cray1">��¼����</td>
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
										<td height="7" align="center" class="text_cray">�ϴ���Ƭ</td>
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
							<td width="100" height="40" align="left" class="text_cray1">��ʵ������</td>
							<td align="left" class="text_cray1">
							<input name="realName" onblur="processRequest(true);"
								type="text" disabled="true" class="text_cray" id="textfield2"
								value="${sessionScope.userSession.realName}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td width="100" height="30" align="left" class="text_cray1">�Ա�</td>
							<td align="left" class="text_cray1">
							<input type="radio" name="sex"
							 value="M"  checked='${userSession.sex=="M"?"checked":""}' /> 
							<span class="text_cray"><label>��</label> 
							<input type="radio" name="sex"
							value="F"  checked='${userSession.sex=="F"?"checked":""}'  /> <label>Ů</label>
							</span> <label></label> <span><label></label> </span></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">ʡ�ݣ�</td>
							<td align="left" class="text_cray1">
									<select name="province" class="text_cray" id="province">
									<option value="ʡ��" selected="selected">ʡ��</option>
									</select>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">���У�</td>
							<td align="left" class="text_cray1">
								<select name="city" class="text_cray" id="city">
								<option value="����" selected="selected">����</option>
								</select>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">֤�����ͣ�</td>
							<td align="left" class="text_cray1">
								<select	class="text_cray" name="idTypeCode"	id="cardType">
													<option value="1" ${userSession.certType.id=="1"?"selected":""}><span>�������֤</span></option>
													<option value="2" ${userSession.certType.id=="2"?"selected":""}><span>�۰�ͨ��֤</span></option>
													<option value="3" ${userSession.certType.id=="3"?"selected":""}><span>̨��ͨ��֤</span></option>
													<option value="4" ${userSession.certType.id=="4"?"selected":""}><span>����</span></option>
								</select>
								</td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">֤�����룺</td>
							<td align="left" class="text_cray1"><input name="cert"
								type="text" disabled="disabled" class="text_cray"
								id="textfield6" value="${sessionScope.userSession.cert}" readonly="true" /></td>
						</tr>
						<tr>
							<td width="20" align="center" class="text_red">*</td>
							<td width="100" height="40" align="left" class="text_cray1">�������ڣ�</td>
							<td colspan="2" align="left" class="text_cray1"><input
								name="birthday" type="date" disabled="disabled"
								class="text_cray" id="textfield7" value="${sessionScope.userSession.birthday}"
								readonly="true" /></td>
						</tr>
						<tr>
							<td width="20" height="35"></td>
							<td width="100" height="40" align="left" class="text_cray1">�ÿ����ͣ�</td>
							<td height="35" colspan="2" align="left" class="text_cray1">
								<select class="text_cray" id="passengerType" name="passenger_type">
										<option value="1" ${userSession.userType.id=="1"?"selected":""}>����</option>
										<option value="2" ${userSession.userType.id=="2"?"selected":""}>��ͯ</option>
										<option value="3" ${userSession.userType.id=="3"?"selected":""}>ѧ��</option>
										<option value="4" ${userSession.userType.id=="4"?"selected":""}>�м����ˡ��˲����񾯲�</option>
								</select></td>
						</tr>
						<tr>
							<td height="10" colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td width="100" height="80" align="left" class="text_cray1">��ע��</td>
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
				copyright@12306��Ʊ��</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
	</form>
	<script type="text/JavaScript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">
//ע������������ʱ��

function resetPage(){
	//ʵ��ˢ��ҳ��
	//����1
	//window.location.reload();
	//����2
	//window.location.href=window.location.href;
	//����3
	/* $("#button2").click(function(){
		document.form1.reset();
	});  */
	//����4
	//��ť����Ϊreset
	 $("#button2").attr("type","reset"); 
}

$(function(){
	$(":input").removeAttr("readonly");
	$(":input").removeAttr("disabled");
	//���ÿ��ϴ��ļ�
	$("#form1").attr("enctype","multipart/form-data");
});

//1.��ȡ�û�ѡ�е�ʡ��
var userPro='${userSession.city.province.province}';
//1.�����û�ѡ��ʡ�ݣ���������������
var opArray=province.options;
for(var i=0;i<opArray.length;i++){
	if(opArray[i].value==userPro){
		opArray[i].selected="selected";
	}
}
//3.�������ú��ʡ�ݣ�������������ȡ��Ӧ����
showChild(province, city, cityArr);
//4.ѡ���û���������
var userCity='${userSession.city.city}';
var opArray=city.options;
for(var i=0;i<opArray.length;i++){
	if(opArray[i].value==userCity){
		opArray[i].selected="selected";
	}
}
</script>
<script type="text/javascript">
//ע��ʱ����֤��¼���Ƿ��Ѿ�����
 function processRequest(async){
	 $("span[name=tempTipSpan]").remove();
	var flag=false;
	var str="����ĸ�����ֻ�_����ɣ����Ȳ�����6λ��������30λ";
	var tipStr="<span style='color: red' name='tempTipSpan'>"+str+"</span>";
	var loginNameObj=$("input[name=loginName]");
	var loginNameVal=loginNameObj.val();
	if(loginNameVal=='${userSession.userName}'){
		//����û�������session�е��û�������ʾ�û���û���޸ģ������κ���֤
		return true;
	}
	if(!(/^\w{6,30}$/.test(loginNameVal))){
		console.log("����");
		loginNameObj.after(tipStr);
		flag=false;
	}else{
		$.ajax({
			//���·��
			url:"ModifyServlet?action=check",
			type:"post",
			async:async,
			data:{loginName:loginNameVal,userID:$("#userID").val()},
			success:function(data){
				if(data=="exists"){
					str="��¼���Ѵ��ڣ�";
					tipStr="<span style='color: red' name='tempTipSpan'>"+str+"</span>"; 
					$("input[name=loginName]").after(tipStr);
				}else{
					flag=true;
				}
			},
			error:function(resp,status,xhr){
				alert("ҳ���Ajax�������쳣�����Ժ�����");
			}
		});
		return flag;
	}
}
$(function(){
	//��֤������������
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
			//�ύ��
			/**
			1������Ҫ�ϴ��ļ�ʱ��ajax������data:$("#form1").serialize(),���ݱ����ݣ�
			2����Ҫ�ϴ��ļ�ʱ��ajax����������
			data:new FormData($("#form1")[0]),
			cache:false,
			processData:false,
			contentType:false,
			*/
			//ajax�ļ��ϴ���ʼ
			<%--  $.ajax({
				//����·��
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
						alert("����ʧ�ܣ����Ժ�����");
					}
				},
				error:function(resp,status,xhr){
					alert("��Ϣ���ձ���ʧ�ܣ����Ժ�����");
				}
			});  --%>
			//ajax�ļ��ϴ�end
			
			//��ͨģʽ�ϴ��ļ�begin
			var form=document.form1;
			form.action="<%=request.getContextPath()%>/User/ModifyServlet?action=update";
			form.method="post";
			form.target="targetFrame";
			form.submit();
			//��ͨģʽ�ϴ��ļ�end
		}
	});
});
</script>	
</body>
</html>
    