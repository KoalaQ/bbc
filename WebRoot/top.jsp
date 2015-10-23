<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://www.aitiny.com/c" %>

 

        <div id="wrap">
            <div id="Top"> 
                <div id="logo">
                    <img src="<%=request.getContextPath()%>/images/media.jpg" alt="Aitiny BBS"  border="0"  />
                    <label style="font-size:30px; font-weight: bold;">Aitiny 论坛</label>
                    <div id="logRegist">
                   
                    <c:choose>
                    	<c:when test="${sessionScope.user!=null}">
                    		<span id="po"><img style=" float: right;width:50px; height:50px;" src="<%=request.getContextPath()%>/upload/${sessionScope.user.photoPath}" /></span>
                            <h4><a href="student!personalStuInfo.action">${sessionScope.user.nickName}</a>欢迎你！</h4>
                    	</c:when>
                    	<c:when test="${sessionScope.admin!=null}">
                    	<span id="po"><img style="float: right;width:50px; height:50px;" src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" /></span>
                    		 <h4>管理员:<a href="#">${sessionScope.admin.nickName}</a>，欢迎您！</h4>
                    	</c:when>
                    	<c:otherwise>
                    	<span id="po"><img style="float: right;width:50px; height:50px;" src="<%=request.getContextPath()%>/images/bbsPhoto.jpg" /></span>
                    		<h4>您 好,请<a href="<%=request.getContextPath()%>/login.jsp" style="color:orange;">登 录</a></h4>
                    	</c:otherwise>
                    </c:choose>

                    </div>

                </div>
            </div>
            <div id="s_head">
                <div id="menu">
                    <ul id="menu_left">
                        <li id="m_01"><a id="a_01" href="<%=request.getContextPath()%>/index.do">首&nbsp;页</a></li>
                        <li class="menu_ge"></li>
                        <li id="m_03"><a href="post!viewPostsByUser.action">我的帖子</a></li>
                        <c:if test="${sessionScope.user!=null}">
                            <li class="menu_ge"></li>
                        <li id="m_04"><a href="student!personalStuInfo.action">个人资料</a></li>
                        <li class="menu_ge"></li>
                         <li id="m_07"><a href="<%=request.getContextPath()%>/PersonalInfo/StuPswModify.jsp">修改密码</a></li>
                        </c:if>
                        <c:if test="${sessionScope.admin!=null}">
                            <li class="menu_ge"></li>
                        <li id="m_04"><a href="admin!personalAdminInfo.action">个人资料</a></li>
                        </c:if>   
                        <li class="menu_ge"></li>
                        <%if (request.getSession().getAttribute("admin") != null) {%> 
                        <li id="m_07"><a href="post">帖子管理</a></li>
                        <li class="menu_ge"></li>
                        <li id="m_07"><a href="board!loadRootBoards.action">版块管理</a></li>
                          <li class="menu_ge"></li>
                          <li id="m_07"><a href="#">系统维护</a></li>
                          <li class="menu_ge"></li>
                        <%}%>
                      
                        <li id="m_08"><a href="login!exit.action">退出</a></li>
                    </ul>
                </div>
            </div>
        </div>
