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
        <title>帖子</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>  
        <script type="text/javascript">
            window.onload = function(){
            
                var replyBtn = document.getElementById("replyBtn");
                var replyDiv = document.getElementById("replyDiv");
                replyBtn.onclick =function(){
                 
                    if (replyDiv.style.display=="none") {
                        replyDiv.style.display="block";
                    }
                    else if (replyDiv.style.display=="block") {
                        replyDiv.style.display="none";
                    }
                }
             
            }
            
        </script>
        <style type="text/css">
            table{ border-collapse: collapse; width: 95%;}
            th, tr,td{ border: 1px solid silver; background-color: #E3F2E1;}
            th{ height: 32px;}
            #nav{ margin:10px 0 10px 30px; font-size: 15px; }
            .left_td{width:15%; text-align: left; padding-left:30px;  }
            .right_td{width:70%; text-align: left;  padding-left: 30px; vertical-align: text-top; background-color: white;}
            #replyDiv{display: block; margin-left: 50px;  }
            #myImg{ width: 128px; height:128px; }
            #pmain li{ margin: 8px ;}
            #fastReply{ margin-top: 30px;}
        </style>
    </head>
    <body>
        <div id="nav">
        <div style="float: left">
          当前位置：&nbsp;  <a href="index.action">首页 </a>&nbsp; &nbsp;&nbsp;>> ${requestScope.boardName } &nbsp; >>&nbsp;  ${requestScope.post.name }
      	</div>
      	 <div style="float: right ;margin-right: 25px">
        	<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=1" width="300" height="19" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
     		</div>
       </div>
        <!-- 帖子主题 -->
        
        

    <center>
        <div id="pmain">
        <table>
            <th>作者</th><th>正文</th>
            <tr>
                <td class="left_td" valign="top">
                    <br/>
                    <c:if test="${requestScope.user.photoPath!=null}">
                        <img id="myImg" src="<%=request.getContextPath()%>/upload/${requestScope.user.photoPath}"  />
                    </c:if>
                    <c:if test="${requestScope.user.photoPath==null}">
                        <img src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" />
                    </c:if>
                    <br/>
                    <ul>
                        <li>  <h5>
                            <b>昵称：${requestScope.user.nickName}</b>
                        </h5>
                        </li>
                        
                        <li><h5>
                            <b>访问量：${requestScope.post.viewcount}</b>
                        </h5>
                        </li>
                        <li>
                        <h5>
                            <b>收藏量：${requestScope.post.likes} </b>
                        </h5>
                        </li>
             
                </td>
                <td class="right_td">
                    <h5>发帖时间： ${requestScope.post.publishTime} </h5>
                 <h5>   内容： </h5> ${requestScope.post.content}
                    
               </td>
            </tr>
            
            
            
            
            
            
            
            
            
            
            <!-- 回帖列表,循环tr，你懂得。。 -->  
            <s:iterator value="replies" id="row" status="st">
                <tr>
                    <td class="left_td" align="top" valign="top">
                        <br/>
                        <s:if test="#row.getStudent().photoPath!=null">
                            <img id="myImg" src="<%=request.getContextPath()%>/upload/<s:property value="#row.student.photoPath"  />" />
                        </s:if>
                        <s:else>
                            <img src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" />
                        </s:else>
                        <br/>
                        <h5>
                            <b>昵称：<s:property value="#row.getStudent().nickName" /></b>
                        </h5>
                        <h5>
                            <b>专业：<s:property value="#row.getStudent().major" /></b>
                        </h5>
                        <h5>
                            <b>班级：<s:property value="#row.getStudent().className" /></b>
                        </h5>
                    </td>
                    <td class="right_td">
                        <h5>回复时间： <s:date format="yyyy-MM-dd hh:mm:ss" name="#row.getPublishTime()" /></h5>
                        <s:property value="#row.getContent()" escape="false"/>
                    </td>
                </tr>
            </s:iterator> 
        </table>
       <div id="fastReply">
            <s:form action="reply!stuReply.action?pid=%{post.id} ">
                <tr>
                    <td style="width:19.5%;"  valign="middle" align="center">
                        <s:if test="#session.admin==null">
                          <img id="myImg" src="<%=request.getContextPath()%>/upload/<s:property value="%{student.photoPath}"  />" />
                          </s:if>
                        <h2>回复：</h2></td><td> <textarea id="context" name="reply.content" ></textarea>  </td>
                </tr>
                <tr>
                    <td></td><td><input type="submit"   value="" style=" border: none; cursor: pointer; width: 74px; height: 31px; background: url('<%=request.getContextPath()%>/images/btn_reply.png') no-repeat;" /></td>
                </tr>
            </s:form>
            <script type="text/javascript">
                //<![CDATA[
                // Replace the <textarea id="editor1"> with a CKEditor instance using default configuration.
                CKEDITOR.replace( 'context',
                {
                    filebrowserImageUploadUrl  :'uploadImg.action',
                    filebrowserImageBrowseUrl  :'showImage.jsp?type=image',
                    toolbar :'Full',
                    width:'100%',
                    height:'50%',
                    filebrowserWindowWidth  : 700,
                    filebrowserWindowHeight : 500
                });
                //]]>
            </script>  
          </div>
    </center>
</div>


</body>
</html>

