<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.aitiny.com/c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>郑州轻工业学院论坛</title>
        <script type="text/javascript">
            window.onload=function(){
                var tabS = document.getElementById("tabL");
                var tabA = document.getElementById("tabA");
                var stuL = document.getElementById("stuLoginF");
                var adminL = document.getElementById("adminLoginF");          
                
                tabS.onclick = function(){
                    if (adminL.style.display =="block") {
                        adminL.style.display ="none";
                        reloadcode();
                    } 
                 if (tabA.style.backgroundColor!="") {
                tabA.style.backgroundColor="";
                }
                    tabS.style.backgroundColor="#2FB4D6";
                    stuL.style.display="block";
                }
                
                
                tabA.onclick = function(){
                    if (stuL.style.display=="block") {
                    stuL.style.display="none";
                    reloadcode2();
                }   
                if ( tabS.style.backgroundColor!="") {
                  tabS.style.backgroundColor="";
                 }       
                    tabA.style.backgroundColor  ="#2FB4D6";
                    adminL.style.display ="block";
                }
            }
            function reloadcode()
        	{
        		var verify=document.getElementById('vail');
        		verify.src=verify.src+'?'+Math.random();
        	}
            function reloadcode2()
        	{
        		var verify=document.getElementById('vail2');
        		verify.src=verify.src+'?'+Math.random();
        	}
        </script>
        <style type="text/css">
            #tabs{ height: 20px; width: 300px; margin: 0 auto; }
            #tabContent{ width: 400px; height: 200px;  border: 1px solid #CACACA;   margin-top: 10px;  border-radius:15px 15px 0px 0px; box-shadow:15px 2px 4px #000;   }
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
       <h3>Sorry,您还未登录，请登录:</h3>
        <div id="tabs">
          <ul>
              <li id="tabL" style=" background-color:#2FB4D6;" margin-right: 5px;" ><b>学生登录</b></li>
              <li id="tabA"><b>管理员登录</b></li>
           </ul>
        </div> 
       <div id="tabContent">
        <div id="stuLoginF" style="display: block;" >
            <form action="userLogin.do" method="post">
                 	<table>
                 		<tr>
                 			<td>邮箱：</td>
                 			<td> <input type="text" name="email" class="outerBorder"/></td>
                 		</tr>
                 	<tr>
                 			<td>密码：</td>
                 			<td><input type="password" name="password" class="outerBorder"/></td>
                 		</tr>

                 		<tr>
                 			<td><img id="vail" src="vail.v" onclick="this.src=this.src+'?'+Math.random();" width="80" height="25"></img>
                 			 <a style="WORD-WRAP:break-word;COLOR:blue;WORD-BREAK:break-all;TOP:5px;CURSOR:pointer;LEFT:210px" onclick="reloadcode()">换一个</a></td>
                 			<td><input type="text" name="vali" class="outerBorder"/></td>
                 		</tr>
                 		<tr>
                 			<td><input type="submit"  value="" class="loginBtn"/></td>
                 			<td><input type="reset" value="重置" class=""/></td>
                 		</tr>
                 	</table>
            </form>
        </div>
           <div id="adminLoginF" style="display: none;" >
            <form action="adminLogin.do" method="post">
                 	<table>
                 		<tr>
                 			<td>邮箱：</td>
                 			<td> <input type="text" name="email" class="outerBorder"/></td>
                 		</tr>
                 	<tr>
                 			<td>密码：</td>
                 			<td><input type="password" name="password" class="outerBorder"/></td>
                 		</tr>
                 		<tr>
                 			<td><img id="vail2" src="vail.v" onclick="this.src=this.src+'?'+Math.random();" width="80" height="25"></img>
                 			 <a style="WORD-WRAP:break-word;COLOR:blue;WORD-BREAK:break-all;TOP:5px;CURSOR:pointer;LEFT:210px" onclick="reloadcode2()">换一个</a></td>
                 			<td><input type="text" name="vali" class="outerBorder"/></td>
                 		</tr>
                 	
                 		<tr>
                 			<td><input type="submit"  value="" class="loginBtn"/></td>
                 			<td><input type="reset" value="重置" class=""/></td>
                 		</tr>
                 	</table>
            </form>
        </div>

        <span style="color:red"><c:if test="${param.error==0}">邮箱未验证！</c:if></span>
       <span style="color:red"> <c:if test="${param.error==5}">用户被锁定！</c:if></span>
       <span style="color:red"> <c:if test="${param.error==6 || param.error==7}">用户名或密码错误！</c:if></span>
        <span style="color:red"> <c:if test="${param.error==10}">验证码错误！</c:if></span>
       </div>
   </center>
    </body>
</html>
