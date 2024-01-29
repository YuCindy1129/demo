<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Name Selection Page</title>
</head>
<body>

    <h2>Select a Name:</h2>

    <form action="/example/getMemberByName" method="get" >
		<select id="name" name="name">
		    <option value="" selected>請選擇姓名查詢資料</option>
		    <option value="Eva">Eva</option>
		    <option value="Jane" >Jane</option>
		    <option value="Bob">Bob</option>
		    <option value="Alice">Alice</option>
			<option value="John">John</option>
			<option value="Kevin">Kevin</option>
			<option value="Frank">Frank</option>
			<option value="Mark">Mark</option>
			<option value="Tom">Tom</option>
    	</select>
    	<input type="submit" value="確認">
    </form>

</body>
</html>
