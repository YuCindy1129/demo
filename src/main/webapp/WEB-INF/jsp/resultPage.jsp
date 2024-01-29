<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Result Page</title>
</head>
<body>
	<h2>ID：${id} </h2>
	<h2>NAME：${name} </h2>
	<h2>AGE：${age} </h2>
		<form action="/example/result">
			<input type="submit" value="返回">
		</form>
</body>
</html>
