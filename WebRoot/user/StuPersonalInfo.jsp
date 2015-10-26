<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.aitiny.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人信息</title>
        <style type="text/css"> 
             table{ border-collapse: collapse; text-align: center; width: 100%; }
            th{ width: 150px;}
            tr,td{  border: 1px solid silver; line-height:20px; }
            th,tr, td{ border-left: none; border-right: none; padding-bottom:5px;}
            th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
            #profile_act{ margin-left:5%;}
            #profile_act > li { height: 20px;}
            #profile_act > li > a { padding-left: 20px; }
            #silder{float:left;}
            #info{float: left; margin-left: 5%; width: 750px;}
 #displayPagination{ margin-top:20px;}
            #displayPagination > div { display:inline-block; padding:10px; margin-top:10px; }
        </style>
                <script type="text/javascript" src="<%=request.getContextPath()%>/js/showpages.js"></script>
        <script type="text/javascript">
           function checkStu()
           {
               if (document.getElementById("nick").value=="") {
                   document.getElementById("msg").innerHTML="昵称不能为空！";
                    return false;
                }
                else{return true;}
                
           }
        </script>
    </head>
    <body>
    <c:if test="${error!=0}">
                		
        <div style="margin-left:2%;">
            <div id="silder">
                <c:if test="${requestScope.user.photoPath!=null }">
                <p>
                    <img style="width:200px; height:200px; " src="<%=request.getContextPath()%>/upload/${requestScope.user.photoPath}" />
                </p>
                </c:if>
                <c:if test="${requestScope.user.photoPath==null }">
                 <p>
                    <img style="width:200px; height:200px; " src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" />
                </p>
                </c:if>
                <br/>
            
                <ul id="profile_act">
                    <li><strong>昵称：</strong>${requestScope.user.nickName }</li>
                    <li><strong>积分：</strong>${requestScope.user.vantages }</li>
                    <li><strong>粉丝：</strong>${requestScope.user.fanscount }</li>
                    <li><strong>邮箱：</strong>${requestScope.user.email }</li>
                 </ul>
                
                <ul id="profile_act">
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/pmto.gif') no-repeat; " >发短消息</a></li>
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/addbuddy.gif') no-repeat; " >加为好友</a></li>
                    <li><a href="#" style=" background: url('<%=request.getContextPath()%>/images/fastreply.gif') no-repeat; ">搜索帖子</a></li>
                     </ul>
            </div>
            </div>
          </c:if>  
            
            <div id="info">
            <c:if test="${error==0}">
                		<h1>没有该用户！</h1>
                		</c:if>
            <c:if test="${error!=0}">
                <strong>${requestScope.user.nickName }的文章：</strong><br>
                

                <table >
                	<tr style="background-color:#E7EFEF;">
                		<th style="width:10px;" ></th>
                		<th style="text-align:lefts;width: 45px" >帖子标题</th>
                		<th>简介</th>
                		<th style="width: 45px">点击/收藏</th>
                		<th style="width: 125px">发表时间</th>
                	</tr>
                	<c:if test="${fn:length(requestScope.posts)==0}">
                		<tr><td>没有帖子！</td></tr>
                		</c:if>
                	<c:forEach items="${requestScope.posts }" var="post">
	                	<tr>
	                		<td></td>
	                		<td style="width: 80px"><a href="postDetail.do?pid=${post.id }">${post.name }</a></td>
	                		<td>${post.summary }</td>
	                		<td style="width: 45px">${post.viewcount } / ${post.likes }</td>
	                		<td style="width: 125px">${post.publishTime }</td>
	                	</tr>
                	</c:forEach>
                	
                </table>
               </c:if>
            </div>
            <center>
	        <div id="displayPagination">    
	            <script type="text/javascript">
	                var pg = new showPages('pg');
	                var total = ${requestScope.count};
	                var pageSize = ${requestScope.lineSize};
	                if (total%pageSize==0) {
	                    pg.pageCount= total/pageSize;
	                }else{
	                	 pg.pageCount = total / pageSize + 1 ;  // 定义总页数(必要)
	                }
	               
	                //pg.argName = 'p';  // 定义参数名(可选,默认为page)
	     
	                pg.printHtml(2);
	                pg.printHtml(4);
	            </script>
	        </div>
	    </center>
            </body>
        </html>
