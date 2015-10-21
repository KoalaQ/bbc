<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.aitiny.com/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>郑州轻工业学院论坛</title>
        <script type="text/javascript">
        function vailUser(type,value){
        	//alert(type+","+value);
    		if(window.XMLHttpRequest){//非IE支持
    			req=new XMLHttpRequest();
    		}else{//IE支持
    			req=new ActiveXObject("Microsoft.XMLHTTP");
    		}
    		if(type==0){
    			req.onreadystatechange=emailPress;
    			var email=document.getElementById("email");
    			var str=email.value.trim();
            	var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            	if(!reg.test(str)){
            		document.getElementById("emailMsg").innerHTML="<a style='color:red'>*邮箱格式错误</a>";
            		email.focus();
            		return false;
            	}
    			
    		}else if(type==1){
    			req.onreadystatechange=nickNamePress;
    			var nickName=document.getElementById("nickName");
        		var nickNameMsg=document.getElementById("nickNameMsg");
            	if(nickName==null || nickName.value.trim()==""){
            		nickNameMsg.innerHTML="<a style='color:red'>*不能为空</a>";
            		nickNameMsg.focus();
            		return false;
            	}
    		}
    		
    		
    		//传回的数据到press函数中处理
    		
    		//发送到服务器验证
    		req.open("GET", "<%=basePath%>userRegisterPre.do?type="+type+"&value="+value, true);
    		req.send("");
    	}
    	function nickNamePress(){
    		var nickName=document.getElementById("nickName");
    		var nickNameMsg=document.getElementById("nickNameMsg");
    		//readyState的值，0未初始化，1正在加载，2装载完毕，3交互中，4完成
    		if(req.readyState==4){
    			//处理200
    			if(req.status==200){
    				//alert(req.responseText);
    				//userName.focus();
    				root=req.responseXML.getElementsByTagName("*");
    				
  					if(root[0].childNodes[0].childNodes[0].nodeValue.trim()=="0"){
  						//alert("true");
  						nickNameMsg.innerHTML="<a  style='color: blue'>"+root[0].childNodes[1].childNodes[0].nodeValue+"</a>";
  						}else{
  						//	alert("false");
  						document.getElementById("submit").disabled=true;
  						nickName.focus();
  						nickNameMsg.innerHTML="<a  style='color: red'>"+root[0].childNodes[1].childNodes[0].nodeValue+"</a>";
  					}
  						
  					}
    				return true;
    			}
    		
    	} 
    	function emailPress(){
    		var email=document.getElementById("email");
    		var emailMsg=document.getElementById("emailMsg");
    		//readyState的值，0未初始化，1正在加载，2装载完毕，3交互中，4完成
    		if(req.readyState==4){
    			//处理200
    			if(req.status==200){
    				//alert(req.responseText);
    				//userName.focus();
    				root=req.responseXML.getElementsByTagName("*");
    				
  					if(root[0].childNodes[0].childNodes[0].nodeValue.trim()=="0"){
  						//alert("true");
  						emailMsg.innerHTML="<a  style='color: blue'>"+root[0].childNodes[1].childNodes[0].nodeValue+"</a>";
  						}else{
  						//	alert("false");
  						document.getElementById("submit").disabled=true;
  						email.focus();
  						emailMsg.innerHTML="<a  style='color: red'>"+root[0].childNodes[1].childNodes[0].nodeValue+"</a>";
  					}
  						
  					}
    				return true;
    			}
    		
    	} 
            function reloadcode()
        	{
        		var verify=document.getElementById('vail');
        		verify.src=verify.src+'?'+Math.random();
        	}
            function checkPassword()
        	{
            	
            	var passwrod=document.getElementById('password'); 
            	var password2=document.getElementById('password2'); 
            	var passwrodMsg=document.getElementById('passwordMsg');
            	var password2Msg=document.getElementById('password2Msg');  
            
            	if(passwrod.value==null || passwrod.value=="" || passwrod.value!=password2.value){
            		passwrod2Msg.innerHTML="<a style='color:red'>*密码不相同</a>";
            		document.getElementById("submit").disabled=true;
            	}else{
            		password2Msg.innerHTML="<a style='color:blue'>正确</a>"; 
            		
            	}
            	  
        	}
            function onValiChange(){
            	document.getElementById("submit").disabled=false;
            }
            function valiAll()
        	{
            	var email=document.getElementById('email');
            	var passwrod=document.getElementById('password'); 
            	var password2=document.getElementById('password2'); 
            	
            	var emailMsg=document.getElementById('emailMsg');
            	var password2Msg=document.getElementById('password2Msg');  
            	
            	if(email.value==null || email.value==""){
            		emailMsg.innerHTML="<a style='color:red'>*填写邮箱</a>";
            		return false;
            	}
            	
            	var str=email.value.trim();
            	var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            	if(!reg.test(str)){
            		emailMsg.innerHTML="<a style='color:red'>*邮箱格式错误</a>";
            		return false;
            	}
            	emailMsg.innerHTML="";

            	if(passwrod.value==null || passwrod.value=="" || passwrod.value!=password2.value){
            		password2Msg.innerHTML="<a style='color:red'>*密码不相同</a>";
            		return false;
            	}
            	password2Msg.innerHTML="";
            	return true;
        	}
        </script>
        <style type="text/css">
            #tabs{ height: 20px; width: 300px; margin: 0 auto; }
            #tabContent{ width: 400px; height: 300px;  border: 1px solid #CACACA;   margin-top: 10px;  border-radius:15px 15px 0px 0px; box-shadow:15px 2px 4px #000;   }
            ul{ float: left; list-style-type: none;  line-height: 25px; }
            li{ width: 120px; height: 30px; text-align: center; float: left; display: block;  cursor: pointer;  }
            #tabs{position:relative; left:-30px;}
            #tabs li { display:block;  border-radius:5px 35px 0px 0px;  }
            li:hover{  }
            .loginBtn{ border: none; width: 128px; height: 40px; background:url('<%=request.getContextPath() %>/images/btn-submit.png') no-repeat;}
            .loginBtn:hover{cursor: pointer;}
            .outerBorder{ width: 200px; height:25px; background-color: #F1FAFF; border: #C1C1C1 1px solid; }
            .tdLabel{ font-family:宋体; font-size: 14px;}
             td { padding-top: 15; }
             #login{margin-left:-30px;}
        </style>
    </head>
    <body>
       
    <center>
       <span style="color:red"> <c:if test="${param.error==0}">注册失败!</c:if></span>
       <span style="color:red"> <c:if test="${param.error==1 || param.error==7}">密码不对!</c:if></span>
        <span style="color:red"> <c:if test="${param.error==2}">注册成功，请在30分钟内登陆邮箱验证！<a href="valiEmail.jsp" style="color : blue">点击重新获取验证邮件</a></c:if></span>
             
        <div id="tabs">
          <ul>
              <li id="tabL" style=" background-color:#2FB4D6;" margin-right: 5px;" ><b>用户注册</b></li>
           
              	
              
           </ul>
        </div> 
       <div id="tabContent">
      
      
            <form action="userRegister.do" onsubmit="return valiAll()" method="post" >
                 	<table>
                 		<tr>
                 			<td>邮箱：<span id="emailMsg"></span></td>
                 			<td> <input type="text" id="email" name="email" class="outerBorder" onblur="vailUser(0,this.value)"/></td>
                 		</tr>
                 		<tr>
                 			<td>昵称：<span id="nickNameMsg"></td>
                 			<td><input type="text" id="nickName" name="nickName" class="outerBorder" onblur="vailUser(1,this.value)"/></td>
                 		</tr>
                 		<tr>
                 			<td>密码：<span id="passwordMsg"></td>
                 			<td><input type="password" id="password" name="password" class="outerBorder"/></td>
                 		</tr>
						<tr>
                 			<td>重复密码：<span id="password2Msg"></td>
                 			<td><input type="password" id="password2" name="password2" class="outerBorder" onblur="checkPassword()"/></td>
                 		</tr>
                 		
                 		<tr>
                 			<td><img id="vail" src="vail.v" onclick="this.src=this.src+'?'+Math.random();" width="80" height="25"></img>
                 			 <a style="WORD-WRAP:break-word;COLOR:blue;WORD-BREAK:break-all;TOP:5px;CURSOR:pointer;LEFT:210px" onclick="reloadcode()">换一个</a></td>
                 			<td><input type="text" id="vali" name="vali" class="outerBorder" onKeyUp="onValiChange()"/></td>
                 		</tr>
                 		<tr>
                 			<td><input type="submit" id="submit" value="注册"  disabled="true"/></td>
                 			<td>已有账号，<a href="login.jsp" style="color : blue">点击登陆</a></td>
                 		</tr>
                 	</table>
            </form>
        </div>          

       	 </div>
   </center>
    </body>
</html>
