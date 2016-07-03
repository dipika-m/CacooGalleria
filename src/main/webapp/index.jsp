<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link href="<c:url value="/main.css"/>" rel="stylesheet" type="text/css"/>
  <title>Cacoo Demo</title>
</head>
<body>
<div id="container">

    <ul id="mainlinks">
      <li><a href="<c:url value="/index.jsp"/>" class="selected">home</a></li>
      <authz:authorize ifNotGranted="ROLE_USER">
        <li><a href="<c:url value="/login.jsp"/>">login</a></li>
      </authz:authorize>
      <li><a href="<c:url value="/cacoo/diagrams"/>">cacoo Diagrams</a></li>
    </ul>

  <div id="content">
    <h1>Welcome to Cacoo Demo!</h1>
    
    <p>This is a website that will allow you to print your photos that you've uploaded to <a href="https://cacoo.com">cacoo.com</a>!
      And since this site uses <a href="http://oauth.net">OAuth</a> to access your photos, we will never ask you
      for your Sparklr credentials.</p>

    <p>cacoo Demo has only one user: "dipika" . The password for "dipika" is password is "cacoo" </p>

    <authz:authorize ifNotGranted="ROLE_USER">
      <p><a href="<c:url value="login.jsp"/>">Login </a></p>
    </authz:authorize>
    <authz:authorize ifAllGranted="ROLE_USER">
      <p><a href="<c:url value="/cacoo/diagrams"/>">View my Cacoo Diagrams</a></p>
    </authz:authorize>

 
  </div>
</div>
</body>
</html>
