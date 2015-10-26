<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://www.aitiny.com/c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>我的帖子</title>
        <style type="text/css">
           table{ border-collapse: collapse; text-align: center; width: 98%; }
            th{ width: 150px;}
            tr,td{  border: 1px solid silver; }
            th,tr, td{ border-left: none; border-right: none; padding-bottom:5px;}
            th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
        	 #displayPagination{ margin-top:20px;}
            #displayPagination > div { display:inline-block; padding:10px; margin-top:10px; }
        </style>
        <script type="text/javascript" src="js/showpages.js"></script>
    </head>
    <body>
        <h3 style="padding-left:20px;"><s:property value="#session.student.nickName" />您好，欢迎查看您发布过的帖子：</h3>
    <center>
       
         <!-- 帖子列表 -->
             <table>
                 <tr style="background-color:#E7EFEF;">
                     <th style="width:10px;"></th><th style="text-align:left;">帖子标题</th>
                     <th>点击/收藏</th>
                     <th>操作</th>
                     <th>发布时间</th>
                 </tr> 
                 <c:if test="${sessionScope.user==null }">
                 	<tr>
                 	<td colspan="4">
       					请<a href="../bbc/login.jsp" style="color: blue">登录</a>以后再发表文章!
     		  
     		 		 </td>
                 	</tr>
                 </c:if>
                 ${requestScope.info}
                 <c:if test="${requestScope.info==0}">删除错误</c:if>
                 <c:if test="${requestScope.info==1}">删除成功${requestScope.id}</c:if>
                 <c:forEach items="${requestScope.posts}" var="post">
                <tr>
                    <td><img src="<%=request.getContextPath() %>/images/folder_new.gif" style="display: inline-block; margin:4px 5px 0px 0px;" /></td>
                    <td style="text-align: left;" ><a href="postDetail.do?pid=${post.id}">${post.name}</a></td>
                    <td>${post.viewcount}/${post.likes}</td>
                    <td><a href="updatePre.do?id=${post.id }">编辑</a> / <a href="delete.do?id=${post.id }" onclick="return window.confirm('确定要删除？')">删除</a></td>
                     <td>
                      ${post.publishTime }
                     </td>
                </tr>
                </c:forEach>
            </table>     
     </center>  
     <!-- 生成分页 -->
    <div id="fenye">
    <div style="padding-left:30px; margin-top: 20px;">
        <img src="<%=request.getContextPath()%>/images/pn_post.png" style="cursor: pointer;" onclick="javascript:location.href='<%=request.getContextPath()%>/postPre.do?bid=${requestScope.bid }' " />
    </div>
	    <center>
	        <div id="displayPagination">    
	            <script type="text/javascript">
	                var pg = new showPages('pg');
	                var total = ${requestScope.total};
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
    </div>  
    </body>
</html>
