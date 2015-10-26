<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.aitiny.com/c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>论坛</title>
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Mainbbs.css" />
  
       	<script type="text/javascript" src="js/jquery1.42.min.js"></script>
	<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/showpages.js"></script>
        <style type="text/css">
            table{ border-collapse: collapse; text-align: center; width: 98%; }
            th{ width: 150px;}
            tr,td{  border: 1px solid silver; line-height:20px; }
            th,tr, td{ border-left: none; border-right: none; padding-bottom:5px;}
            th:hover,td:hover,tr:hover{ background-color: #F0F0F0;}
            #banner{  background: url('<%=request.getContextPath()%>/images/banner.jpg') no-repeat; width: 100%; height:180px; position:relative; left: 15px; top: -5px;  margin-bottom:0;}
            #displayPagination{ margin-top:20px;}
            #displayPagination > div { display:inline-block; padding:10px; margin-top:10px; }
            #nav{ margin:30px;}
             #warp{ width:980px; margin:0 auto; }
           
        </style>
        <script type="text/javascript">
        	function selectChanged(bid){
        		if(bid!=0){
        			window.location="<%=basePath%>"+"blog.do?bid="+bid;
        		}else{
        			window.location="<%=basePath%>"+"blog.do";
        		}
        		
        		
        	}
        </script>
    </head>
 <body>
    <div id="warp">
        <div id="nav">
        <div style="float: left;width: 200px" >
           <strong> 当前版块：</strong>
            <select onchange="selectChanged(this.value)">
        		<option value="0">==所有模块==</option>
        		<c:forEach items="${requestScope.boards }" var="board">
        			<option value="${board.id }"  
        				<c:if test="${requestScope.bid==board.id }">selected</c:if>
        				>
        				${board.name }
        			</option>
        		</c:forEach>
        	</select>
            </div>
             <div style="margin-left: 10">
            	<a href="blog.do?order=publishTime&type=1&bid=${requestScope.bid}" style="margin-left: 10">按时间降序</a>
            	<a href="blog.do?order=publishTime&type=0&bid=${requestScope.bid}" style="margin-left: 10">按时间升序</a>
            	<a href="blog.do?order=viewcount&type=1&bid=${requestScope.bid}"  style="margin-left: 10">按点击量降序</a>
            	<a href="blog.do?order=viewcount&type=0&bid=${requestScope.bid}" style="margin-left: 10">按点击量升序</a>
            	<a href="blog.do?order=likes&type=1&bid=${requestScope.bid}"  style="margin-left: 10">按收藏量降序</a>
            	<a href="blog.do?order=likes&type=0&bid=${requestScope.bid}" style="margin-left: 10">按收藏量升序</a>       
           </div>
        </div>
        <div>
        	
        </div>
    <center>
        <div>
            <!-- 帖子列表 -->
            <table>
                <tr style="background-color:#E7EFEF;">
                    <th style="width:10px;"></th>
                    <th style="text-align:left;">帖子标题</th>
                    <th>作者</th>
                    <th>点击 / 收藏</th>
                    <th>发布时间</th>
                </tr> 
                <c:if test="${fn:length(requestScope.posts)==0}">
                		<tr><td>没有帖子！</td></tr>
                </c:if>
                <c:forEach items="${requestScope.posts}"  var="post">
                    <tr>
                        <td><img src="<%=request.getContextPath()%>/images/folder_new.gif" style="display: inline-block; margin:4px 5px 0px 0px;" /></td>
                        <td style="text-align: left;" ><a href="postDetail.do?pid=${post.id}">${post.name}</a></td>
                        <td><a href="userinfo.do?uid=${post.uid }">${post.author}</a></td>
                        <td>${post.viewcount} / ${post.likes}</td>
                        
                        <td>
                            <!-- <s:property value="publishTime" /> -->  
                            ${ post.publishTime}
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
</div>
</body>
</html>
