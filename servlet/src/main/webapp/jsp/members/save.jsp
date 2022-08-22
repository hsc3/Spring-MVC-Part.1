<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%
    // HttpServletRequest, HttpServletReponse 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("회원 저장 Servlet 실행");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age")); // string -> int 변환

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>