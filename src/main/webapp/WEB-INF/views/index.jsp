<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>spring project</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp"%>

    <div id="container">
        <div id="menuAdmin">

            <h2 id="menuAdminH2">공지사항</h2>

            <!-- 기능(jstl이라는 라이브러리를 이용해, 세션에 있는 변수의 세팅 조건을 준다.) -->
            <!-- 세션 공간에 저장된 "MANAGER"의 값이 true 일 경우 "작성"이라는 버튼이 보이게 함. -->
            <c:if test="${MANAGER == true}">
                <button type="button" onclick="location.href=`${pageContext.request.contextPath}/noticeAdd`">작성</button>
            </c:if>


            <div id="menuList">
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp"%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>