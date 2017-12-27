<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="majun" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>shiro标签</title>
</head>
<br>
    <shiro:guest>
        欢迎游客访问,<a href="/login.jsp">点击登录</a></br>
    </shiro:guest>

    <shiro:user>
        欢迎[<shiro:principal/>]登录，<a href="/logout">点击退出</a><br/>
    </shiro:user>

    <shiro:user>
        <shiro:authenticated>
            用户[<shiro:principal/>]已身份验证通过<br/>
        </shiro:authenticated>

        <shiro:notAuthenticated>
            未身份验证（包括记住我）<br/>
        </shiro:notAuthenticated>

        <shiro:hasRole name="admin">
            用户[<shiro:principal/>]拥有admin角色<br/>
        </shiro:hasRole>

        <shiro:hasAnyRoles name="admin,user">
            用户[<shiro:principal/>]拥有admin或user角色<br/>
        </shiro:hasAnyRoles>

        <shiro:lacksRole name="abc">
            用户[<shiro:principal/>]没有abc角色<br/>
        </shiro:lacksRole>
        
        <shiro:hasPermission name="user:create">
            用户[<shiro:principal/>]拥有user:create权限<br/>
        </shiro:hasPermission>
        
        <shiro:lacksPermission name="user:create">
            用户[<shiro:principal/>]没有user:create权限<br/>
        </shiro:lacksPermission>

        <!-- 自定义标签 -->
        <majun:hasAllRoles name="admin-user" delimiter="-">
            用户[<shiro:principal/>]拥有角色admin和user<br/>
        </majun:hasAllRoles>

        <majun:hasAllPermissions name="user:create,user:update">
            用户[<shiro:principal/>]拥有权限user:create和user:update<br/>
        </majun:hasAllPermissions>
        
        <majun:hasAnyPermissions name="user:create,user:update">
            用户[<shiro:principal/>]拥有权限user:create或user:update<br/>
        </majun:hasAnyPermissions>

    </shiro:user>
</body>
</html>
