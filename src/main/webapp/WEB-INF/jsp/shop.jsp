<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring boot 视图解析器</title>

    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript">
    <%-- for模拟高并发，在高并发的情况下，库存stock变成了负数 --%>
    for(var i=1; i<1500; i++) {
        var params={
          userId:1,
          productId:1,
          quantity:1
        };
        $.post(
          "/springboot/shop/purchase",
          params,
          function(result){
            <%--alert(result.message);--%>
          }
        );
    }
    </script>
</head>
<body>
<h1>抢购产品测试</h1>
</body>
</html>