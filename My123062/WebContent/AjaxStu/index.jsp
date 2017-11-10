<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript">
function createXHR(){
	//����Ajax������ҪXHP���󣨿�������������ԣ�
	var req=false;
	if(window.XMLHttpRequest){
		//���IE7+��Firefox��Chrome��Opera��Safari�Ĵ���
		req=new XMLHttpRequest();
		console.log("��req��XHLHttpRequest����");
	}else if(window.ActiveXObject){
		//Ϊ�˼���IE5��IE6
		req=new ActiveXObject("Microsoft.XMLHTTP");
		console.log("��req��ActiveXObject����");
	}
	return req;
}
function doAjaxTxt(){
	var req=createXHR();
	if(req){
		/**
		��һ������������ʽ��get��post
		�ڶ���������url��Ҫ������Դ�ĵ�ַ
		�������������첽��true  ͬ����false
		*/
		req.open("POST","txt.jsp",true);
		req.onreadystatechange=parseData;
		//��������
		req.send();
		console.log("���Ѿ�ִ���ˡ�����")
	}else {
		console.log("�����������֧��AjaxӦ�ã�");
	}
	function parseData(){
		console.log("req.readyState"+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseText;
			console.log("������"+resp);
		}else if(req.readyState==4&&req.status!=200){
			console.log("req.readyState="+req.readyState+"=---req.status"+req.status+"---req.statusTest"+req.statusTest);
		}
	}
}

function doAjaxTxtParam(){
	var req=createXHR();
	if(req){
		/* get��ʽ��send�����ò�����ҳ���޷���ȡ */
		req.open("post","txt.jsp?name=��ķ&age=12",true);
		req.onreadystatechange=parseData;
		/**ͨ����req������������ͷContent-Type
		ʹ��send�����еĲ�������ͨform����ʽ�ύ����̨
		��URL�еĲ��������Ƿ�����Content-Type�޹�
		*/
		req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=GB2312");
		/**
		���������һ���Զ����X-Requested-With����ͷ
		������ʶ�������ͷ��Ajax�����Ա����ڱ����������
		������������ı��봦��
		*/
		req.setRequestHeader("X-Requested-With","XMLHttpRequest");
		//��������
		req.send("sex=��");
		console.log("���Ѿ�ִ���ˡ�����");
	}else {
		console.log("�����������֧��AjaxӦ�ã�");
	}
	function parseData(){
		console.log("req.readyState"+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseText;
			console.log("������"+resp);
		}else if(req.readyState==4&&req.status!=200){
			console.log("req.readyState="+req.readyState+"=---req.status"+req.status+"---req.statusTest"+req.statusTest);
		}
	}
}

function doAjaxXML(){
	var req=createXHR();
	if(req){
		req.open("post","xml.jsp",false);
		req.onreadystatechange=parseData;
		req.send();
		console.log("���Ѿ�ִ���ˡ�����")
	}else {
		console.log("�����������֧��AjaxӦ�ã�");
	}
	function parseData(){
		console.log("req.readyState"+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseXML;
			//��xml���תΪxml doc����
			var xmlDoc=resp.documentElement;
			//ͨ��xml�ĵ��ı�ǩ����ȡԪ��
			var titleArray=xmlDoc.getElementsByTagName("title");
			for(var i=0;i<titleArray.length;i++){
				//ѭ����ЩxmlԪ�أ���ȡ��Ӧ��ֵ��
				//ͨ��js��ɶ�ajax���ص�xml�Ľ���
				console.log("tagName="+titleArray[i].tagName);
				console.log("titleArray[i].firstChild="+titleArray[i].firstChild);
				console.log("title value="+titleArray[i].firstChild.nodeValue);
			}
			/* var resp=req.responseText; */
			console.log("������="+resp);
		}else if(req.readyState==4&&req.status!=200){
			console.log("req.readyState="+req.readyState+"=---req.status"+req.status+"---req.statusTest"+req.statusTest);
		}
	}
}	
function doAjaxJSON(){
	var req=createXHR();
	if(req){
		req.open("POST","json.jsp",true);
		req.onreadystatechange=parseData;
		req.send();
		console.log("���Ѿ�ִ���ˡ�����")
	}else {
		console.log("�����������֧��AjaxӦ�ã�");
	}
	function parseData(){
		console.log("req.readyState"+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseText;
			console.log("������"+resp.trim());
			//ͨ��JSON���js�࣬�Ѻ�̨���ص�json�ַ���ת��Ϊjson����
			//jsonƽ̨�޹أ�xmlҲ��ƽ̨�޹�
			var jsonArray=JSON.parse(resp.trim());
			for(var i=0;i<jsonArray.length;i++){
				var obj=jsonArray[i];
				console.log(obj.title);
				console.log(obj.author);
			}
		}else if(req.readyState==4&&req.status!=200){
			console.log("req.readyState="+req.readyState+"=---req.status"+req.status+"---req.statusTest"+req.statusTest);
		}
	}		
}
</script>
</head>
<body>
<!-- form������enctype���ԣ��Զ�Ĭ��Ϊapplication/x-www-form-urlencoded -->
<form action="" enctype="application/x-www-form-urlencoded"></form>
<!-- form���ϴ��ļ�����enctype="multipart/form-data" -->
<form action="" enctype="multipart/form-data"></form>
<button onclick="doAjaxTxt()">����Ajax���ı�����</button>
<button onclick="doAjaxTxtParam()">����Ajax���ı���������</button>
<button onclick="doAjaxXML()">����Ajax��XML����</button>
<button onclick="doAjaxJSON()">����Ajax��JSON����</button>
</body>
</html>