<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- api -->
	<script>
		fetch('https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&returnType=json&serviceKey=zLzDOKLTH0MnOy%2FJvMhvAEg1nkrknsHN1qxM%2BolnBQqGf0Ode1qcka7A9PfgCO9UK8u4F%2By1PD1yntKEheP83Q%3D%3D')
	.then(result => result.json())
	.then(result => {
		console.log(result);
	})
	.catch(err => console.log(err))
	</script>
</body>
</html>