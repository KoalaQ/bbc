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
        <title>论坛</title>
        <script type="text/javascript">
        function vailUser(type,value){
        	//alert(type+","+value);
    		if(window.XMLHttpRequest){//非IE支持
    			req=new XMLHttpRequest();
    		}else{//IE支持
    			req=new ActiveXObject("Microsoft.XMLHTTP");
    		}
    			req.onreadystatechange=emailPress;
    			var email=document.getElementById("email");
    			var str=email.value.trim();
            	var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            	if(!reg.test(str)){
            		document.getElementById("emailMsg").innerHTML="<a style='color:red'>*邮箱格式错误</a>";
            		email.focus();
            		return false;
            	}
    
    		
    		
    		//传回的数据到press函数中处理
    		
    		//发送到服务器验证
    		req.open("GET", "<%=basePath%>userRegisterPre.do?type="+type+"&value="+value, true);
    		req.send("");
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
  						emailMsg.innerHTML="<a  style='color: red'>用户不存在</a>";
  						document.getElementById("submit").disabled=true;
  						email.focus();	
  					}else{
  						//	alert("false");
  						
  						emailMsg.innerHTML="<a  style='color: blue'>用户存在</a>";
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
            function onValiChange(){
            	document.getElementById("submit").disabled=false;
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
      
        <div id="tabs">
          <ul>
              <li id="tabL" style=" background-color:#2FB4D6;" margin-right: 5px;" ><b>密码修改</b></li>
            <span style="color:red"> <c:if test="${param.error==0}">邮箱验证失败，请重新确认发送邮件!</c:if></span>   
              <span style="color:red"> <c:if test="${param.error==1}">发送失败邮件失败!</c:if></span>   
              <span style="color:red"> <c:if test="${param.error==2}">发送成功，请30分钟内登陆验证!</c:if></span>   
              <span style="color:red"> <c:if test="${param.error==3}">验证码错误!</c:if></span>   
           </ul>
        </div> 
       <div id="tabContent">
      
      
            <form action="/bbc/changePasswordSendEmail.do"  method="post" >
                 	<table>
                 		<tr>
                 			<td>邮箱：<span id="emailMsg"></span></td>
                 			<td> <input type="text" id="email" name="email" class="outerBorder" onblur="vailUser(0,this.value)"/></td>
                 		</tr>
                 		<tr>
                 			<td><img id="vail" src="/bbc/vail.v" onclick="this.src=this.src+'?'+Math.random();" width="80" height="25"></img>
                 			 <a style="WORD-WRAP:break-word;COLOR:blue;WORD-BREAK:break-all;TOP:5px;CURSOR:pointer;LEFT:210px" onclick="reloadcode()">换一个</a></td>
                 			<td><input type="text" id="vali" name="vali" class="outerBorder" onKeyUp="onValiChange()"/></td>
                 		</tr>
                 		
                 		<tr>
                 			<td><input type="submit" id="submit" value="发送"  disabled="true"/></td>
                 			<td>记起密码，<a href="login.jsp" style="color : blue">点击登陆</a></td>
                 		</tr>
                 	</table>
            </form>
        </div>          

       	 </div>
   </center>
    </body>
</html>
