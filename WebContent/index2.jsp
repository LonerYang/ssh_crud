<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.1.2.6.min.js"></script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div region="west" split="true" title="West" style="width:150px;padding:10px;">west content</div>
	<div region="east" split="true" collapsed="true" title="East" style="width:100px;padding:10px;">east region</div>
	<div region="south" border="false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div region="center" title="Main Title">
	</div>
</body>
</html>