<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://www.aitiny.com/c" %> %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发表帖子</title>
        <script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>      
    <script type="text/javascript">
    function reloadcode()
	{
		var verify=document.getElementById('vali');
		verify.src=verify.src+'?'+Math.random();
	}
  //HTML转义
    function HTMLEncode(html) 
    { 
    	var temp = document.createElement ("div"); 
    	(temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html); 
    	var output = temp.innerHTML; 
    	temp = null; 
    	return output; 
    } 

    //HTML反转义
    function HTMLDecode(text) 
    { 
    	var temp = document.createElement("div"); 
    	temp.innerHTML = text; 
    	var output = temp.innerText || temp.textContent; 
    	temp = null; 
    	return output; 
    } 
    </script>
    </head>
    <body>
    
    <center>
      
        <c:if test="${ sessionScope.user==null}">
        	请<a href="../bbc/login.jsp" style="color: blue">登录</a>以后再发表文章!
        </c:if>
        <c:if test="${ sessionScope.user!=null}">
        <form action="${ requestScope.postUrl}" method="post">
        <div style="float: left;margin-left: 127px">
        <strong>选择模块：</strong>
        <select name="bid">
        		<c:forEach items="${requestScope.boards }" var="board">
        			<option value="${board.id }"  
        				<c:if test="${requestScope.bid==board.id }">selected</c:if>
        				>
        				${board.name }
        			</option>
        		</c:forEach>
        	</select>
        </div>
        <table>
            <tr>
                <td><b>帖子标题：</b></td><td> <input type="text" name="name" size="50" value="${requestScope.post.name }"/></td>
            </tr>
            <tr>
                <td valign="top"><b>帖子内容：</b></td><td> 
                <textarea id="content" name="content" >
                ${requestScope.post.content }
                </textarea>  </td>
            </tr>
            <tr>
                <td><b>简介内容：</b></td><td> <input type="text" name="summary" size="50" placeholder="默认截取文章前50字" value="${requestScope.post.summary }"/></td>
            </tr>
            <tr>
                <td><b>标签：</b></td><td> <input type="text" name="tag" size="50"  placeholder="填写标签易于搜索" value="${requestScope.post.tag }"/></td>
            
            </tr>
            <tr>
            <td><b>验证码：</b></td>
                <td>
                	<img id="vali" src="vail.v" onclick="this.src=this.src+'?'+Math.random();" width="80" height="25"></img>
                 	<a style="WORD-WRAP:break-word;COLOR:blue;WORD-BREAK:break-all;TOP:5px;CURSOR:pointer;LEFT:210px" onclick="reloadcode()">换一个</a>
                 	<input type="text" name="vali"/><span>${valiError }</span>		 
                </td>
               </tr>
               <tr>
                <td>
                <input type="hidden" name="pid"  value="${requestScope.post.id }" />
                <input type="submit"  value="发表" />
                </td>
            </tr>
        </table>
        </form>
        </c:if>
        
        <script type="text/javascript">
            //<![CDATA[
            // Replace the <textarea id="editor1"> with a CKEditor instance using default configuration.
            CKEDITOR.replace( 'content',
            {
            filebrowserImageUploadUrl  :'uploadImg.action',
            filebrowserImageBrowseUrl  :'showImage.jsp?type=image',
            toolbar :'Full',
            width:'700',
            filebrowserWindowWidth  : 700,
            filebrowserWindowHeight : 500
           
            });
            
            //]]>
        </script>  
       </center>
    </body>
</html>
