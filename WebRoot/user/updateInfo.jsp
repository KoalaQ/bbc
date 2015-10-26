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
        <title>个人信息修改</title>
        <style type="text/css"> 
            table{ border-collapse: collapse;}
            th,td{ height: 29px; }
            #profile_act{ margin-left:5%;}
            #profile_act > li { height: 20px;}
            #profile_act > li > a { padding-left: 20px; }
            #silder{float:left;}
            #info{float: left; margin-left: 5%; width: 500px;}
            #modifyInfo{ float: left; padding-left: 3%; }
        </style>
        <script type="text/javascript">
        function vailUser(value){

        	//alert(type+","+value);
    		if(window.XMLHttpRequest){//非IE支持
    			req=new XMLHttpRequest();
    		}else{//IE支持
    			req=new ActiveXObject("Microsoft.XMLHTTP");
    		}
    		
    			req.onreadystatechange=nickNamePress;
    			var nickName=value;
        		var nickNameMsg=document.getElementById("nickNameMsg");
            	if(nickName==null || nickName.trim()==""){
            		nickNameMsg.innerHTML="<a style='color:red'>*不能为空</a>";
            		nickNameMsg.focus();
            		return false;
            	}
    		//发送到服务器验证
    		if(value.trim()!="${sessionScope.user.nickName}"){
    			
    			req.open("GET", "<%=basePath%>userRegisterPre.do?type="+1+"&value="+value, true);	
    			req.send("");
    		}
    		
    	
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
  							//alert("false");
  						nickName.focus();
  						nickNameMsg.innerHTML="<a  style='color: red'>"+root[0].childNodes[1].childNodes[0].nodeValue+"</a>";
  					}
  						
  					}
    				return true;
    			}
    		
    	} 
          //更改图片的js
           function PreviewImage(imgFile)
           {
           var filextension=imgFile.value.substring(imgFile.value.lastIndexOf("."),imgFile.value.length);
           filextension=filextension.toLowerCase();
           if ((filextension!='.jpg')&&(filextension!='.gif')&&(filextension!='.jpeg')&&(filextension!='.png')&&(filextension!='.bmp'))
           {
           alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
           imgFile.focus();
           }
           else
           {
           var path;
           if(document.all)//IE
           {
           imgFile.select();
           path = document.selection.createRange().text;
           document.getElementById("imgPreview").innerHTML="";
           document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
           }
           else//FF
           {
           path=window.URL.createObjectURL(imgFile.files[0]);// FF 7.0以上
           //path = imgFile.files[0].getAsDataURL();// FF 3.0
           document.getElementById("imgPreview").innerHTML = "<img id='img1' width='120px' height='100px' src='"+path+"'/>";
           //document.getElementById("img1").src = path;
           }
           }
           }
        </script>
    </head>
    <body>
     <c:if test="${sessionScope.user==null }">           	
       	<span style="width:100%; height:20px; line-height:20px; text-align:center; float:left;">
      	  	请<a href="../login.jsp" style="color: blue">登录</a>以后再发表文章!
       	</span>
    </c:if>
    <c:if test="${sessionScope.user!=null }">
        <div style="margin-left:2%;">
            <div id="silder">
                <c:if test="${sessionScope.user.photoPath!=null }">
                <p>
                    <img style="width:200px; height:200px; " src="<%=request.getContextPath()%>/upload/${sessionScope.user.photoPath}" />
                </p>
                </c:if>
                <c:if test="${sessionScope.user.photoPath==null }">
                 <p>
                    <img style="width:200px; height:200px; " src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" />
                </p>
                </c:if>
                <br/>
                <ul id="profile_act">
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/pmto.gif') no-repeat; " >发短消息</a></li>
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/addbuddy.gif') no-repeat; " >加为好友</a></li>
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/fastreply.gif') no-repeat; ">搜索帖子</a></li>
                    <li><a href="# " style=" background: url('<%=request.getContextPath()%>/images/home.gif') no-repeat ; ">个人空间</a></li>
                </ul>
            </div>
            <div id="info">
            <form action="/bbc/userUpdate.do" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <th>昵称:</th>
                        <td><input type="text" id="nickName"  name="nickName" value="${sessionScope.user.nickName }" onblur=" vailUser(this.value) "/><span id="nickNameMsg"></span></td>
                    </tr>
                    <tr>
                        <th>头像：</th>
                        <td style="word-break:all">
                        	<div id="imgPreview" style='width:120px; height:100px;'>
									<c:if test="${sessionScope.user.photoPath!=null }">
               							 <p>
                  							  <img style="width:120px; height:100px; " src="<%=request.getContextPath()%>/upload/${sessionScope.user.photoPath}" />
               							 </p>
               						</c:if>
                					<c:if test="${sessionScope.user.photoPath==null }">
                 						<p>
                    						<img style="width:120px; height:100px; " src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" />
                						</p>
                					</c:if>
							</div>
							<br/>
                          <input type="file" name="photo" onchange="PreviewImage(this)"  />
                        
                        </td>
                    </tr>
                   <tr>
                        <th>主题：</th>
                        <td style="word-break:all"><input type="text" value="主题：${sessionScope.user.theme }" name="theme" disabled="disabled"/> </td>
                    </tr>
                   <tr>
                      
                        <td ><input type="submit"  /> </td>
                    </tr>
                   
                </table>
               </form>
            </div>
            
            <div id="modifyInfo">
            </div>      
           </div>
            </c:if>
                <div style="clear: both;"></div>
            </body>
        </html>

