<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">

<link rel="stylesheet" href="/static/lib/css/bootstrap.css">
<link rel="stylesheet" href="/static/lib/css/bootstrap-theme.css">
<link rel="stylesheet" href="/static/css/main.css" />
<link rel="stylesheet" href="/static/css/error.css" />

<title>List Project</title>
</head>

<body>
	<!-- Header: icon, name, language, help, logout -->
	<div class="mt-10 border-bootom-gray height-70">
		<div class="container-fluid header-top">

			<div class="row ">
				<!-- icon -->
				<div class="col-lg-1 col-md-1 col-sm-1">
					<img src="/static/images/logo_elca.png" />
				</div>

				<!-- name -->
				<div class="col-lg-7 col-md-7 col-sm-7">
					<h3>
						<spring:message code="project.header.title" text="default" />
					</h3>
				</div>

				<!-- language -->
				<div class="col-lg-2 col-md-2 col-sm-2 vcenter text-center">
					<a href="#" class="ml-5 mr-5 language">EN</a>| <a href="#"
						class="ml-5 mr-5 language">FR</a>
				</div>

				<!-- Help -->
				<div class="col-lg-1 col-md-1 col-sm-1 vcenter">
					<b class="blue-color"> <spring:message
							code="project.header.help" />
					</b>
				</div>

				<!-- Log-out -->
				<div class="col-lg-1 col-md-1 col-sm-1 vcenter">
					<span class="gray-color"> <spring:message
							code="project.header.logout" />
					</span>
				</div>
			</div>
		</div>
	</div>

	<!-- Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<img alt="error.png" src="/static/images/error.png">
			</div>
			<div class="col-md-4 font-text-normal">

				<div><spring:message code="error.page.message" /></div>
				<div>
					Please <span class="font-text-normal origin"> contact your
						administrator</span>
				</div>
				<br /> or <a href="/" class="font-text-normal blue"> back to
					search project</a>
			</div>
		</div>
	</div>

	<script src="/static/lib/js/jquery.min.js"></script>
	<script src="/static/lib/js/knockout-3.1.0.js"></script>
	<script src="/static/lib/js/jquery-1.12.4.js"></script>
	<script src="/static/lib/js/jquery-ui.js"></script>
	<script src="/static/lib/js/bootstrap.js"></script>
	<script src="/static/js/main.js"></script>
</body>

</html>