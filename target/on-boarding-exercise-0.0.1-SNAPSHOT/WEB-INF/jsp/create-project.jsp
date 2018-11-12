<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">

<head>
<meta charset="utf-8">

<link rel="stylesheet" href="/static/lib/css/bootstrap.css">
<link rel="stylesheet" href="/static/lib/css/bootstrap-theme.css">
<link rel="stylesheet" href="/static/lib/css/jquery-ui.css" />
<link rel="stylesheet" href="/static/css/main.css" />
<link rel="stylesheet" href="/static/css/create-new-project.css" />
	
<title>${titles }</title>
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
					<h3><spring:message code="project.header.title"/></h3>
				</div>

				<!-- language -->
				<div class="col-lg-2 col-md-2 col-sm-2 vcenter text-center">
					<a href="#" class="ml-5 mr-5 language">EN</a>| <a href="#" class="ml-5 mr-5 language">FR</a>
				</div>

				<!-- Help -->
				<div class="col-lg-1 col-md-1 col-sm-1 vcenter">
					<b class="blue-color">
						<spring:message code="project.header.help"/>
					</b>
				</div>

				<!-- Log-out -->
				<div class="col-lg-1 col-md-1 col-sm-1 vcenter">
					<span class="gray-color">
						<spring:message code="project.header.logout"/>
					</span>
				</div>
			</div>
		</div>
	</div>

	<!-- Content -->
	<div class="container-fluid">
		<div class="row">

			<!-- navbar-left -->
			<div class="col-md-2 col-sm-2" id="navbar-left">
				<div class="navbar-left-strong">
					<a href="/">
						<spring:message code="project.left.bar.projects.list"/>
					</a>
				</div>
				
				<div class="navbar-left-blue">
					<strong><spring:message code="project.left.bar.new"/></strong>
				</div>
				
				<div class="navbar-left-item navbar-left-active">
					<c:if test="${'edit' == pageStatus }">
						<a href="/newProject"><spring:message code="project.left.bar.project" /></a>
					</c:if>
					
					<c:if test="${'edit' != pageStatus }">
						<spring:message code="project.left.bar.project" />
					</c:if>
				</div>
				
				<div class="navbar-left-item navbar-left-normal">
					<spring:message code="project.left.bar.customer" />
				</div>
				
				<div class="navbar-left-item navbar-left-normal">
					<spring:message code="project.left.bar.supplier" />
				</div>
			</div>

			<!-- Main content -->
			<div class="col-md-10 col-sm-10 border-left-gray" id="main-content">

				<!-- Header content -->
				<h4 class="h4-cs">
					<strong>${titles }</strong>
				</h4>

				<div class="row">
					<!-- tag-divide -->
					<hr class="hr-width border-color-gray" />
				</div>

				<form action="#" class="form-horizontal" id="form-create-project">
					<div class="row ">
						<div class="alert alert-danger alert-dismissible alert-custom" id="alert-custom"
							role="alert">
							<button type="button" id="close" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<spring:message code="create.update.page.error.empty" /> 
						</div>
					</div>
					
					<input type="hidden" value="${projectId }" id="projectId"/>

					<!-- project-number -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-project-number">
								<label for="projectNumber"
									class="control-label text-left col-md-2">
									<spring:message code="create.update.page.main.project.number" />
									<font color="red">*</font>
								</label>
								<div class="col-md-9" >
									<div class="row">
										<div class="col-md-3">
											<input type="text" class="form-control input-sm text-number" 
												id="projectNumber" name="projectNumber" autofocus
												data-bind="value: projectNumber" maxlength="4" 
												${"edit" == pageStatus ? "disabled='disabled'" : ''} />
											
										</div>
										<span id="error-invalid-project-number" class="help-block">
											<spring:message code="create.update.page.error.number.format" />
										</span>
										<span id="error-project-number-existed" class="help-block">
											<spring:message code="create.update.page.error.project.number.existed" />
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- project-name -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-project-name">
								<label for="projectName" class="control-label text-left col-md-2">
									<spring:message code="create.update.page.main.project.name" />
									<font color="red">*</font>
								</label>
								<div class="col-md-8">
									<div class="row">
										<div class="col-md-10">
											<input type="text" class="form-control input-sm"
												id="projectName" name="projectName" maxlength="50"
												data-bind="value: projectName" 
												${"edit" == pageStatus ? "autofocus=true" : ''}/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- customer -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-customer">
								<label for="customer" class="control-label text-left col-md-2">
								<spring:message code="create.update.page.main.customer" />
									<font color="red">*</font>
								</label>
								<div class="col-md-8">
									<div class="row">
										<div class="col-md-10">
											<input type="text" class="form-control input-sm"
												id="customer" name="customer" maxlength="50" required
												data-bind="value: customer" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- group-project -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-group">
								<label for="group" class="control-label text-left col-md-2">
									<spring:message code="create.update.page.main.group" />
									<font color="red">*</font>
								</label>
								<div class="col-md-3 val-required">
									<div class="row">
										<div class="col-md-9">
											<select class="form-control input-sm" id="group" name="group" 
												data-bind="value: group">
												<c:if test="${'edit' != pageStatus }">
												<option value="NEW" selected="selected">
													<spring:message code="group.option.NEW" />
												</option>
												</c:if>
												
												<!--  ko foreach: groups  -->
												<option data-bind="value: id,text: id"></option>
												<!-- /ko -->
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- members -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-members">
								<label for="members" class="control-label text-left col-md-2">
									<spring:message code="create.update.page.main.members" />
								</label>
								<div class="col-md-8">
									<div class="row">
										<div class="col-md-10">
											<input type="text" class="form-control input-sm" id="members" name="members" 
												data-bind="value: members" />
												<span id="error-invalid-visa" class="help-block">
													<spring:message code="create.update.page.error.visas" /> <span
													data-bind="text: errorVisa"></span>.
												</span>
												<span id="error-not-enough-employee-to-create-new-group"
													class="help-block"> <spring:message
														code="error.not.enough.employee.to.create.new.group" />
												</span>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-2"></div>
									<div class="col-md-8">
										
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- status -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="form-group mb-0" id="div-project-status">
								<label for="status" class="control-label text-left col-md-2">
									<spring:message code="create.update.page.main.status" />
									<font color="red">*</font>
								</label>
								<div class="col-md-3">
									<div class="row">
										<div class="col-md-9">
											<select class="form-control input-sm" id="projectStatus" 
												data-bind="value: status">
												<option value="FIN"><spring:message code="project.status.FIN" /></option>
												<option value="INP"><spring:message code="project.status.INP" /></option>
												<option value="NEW" selected><spring:message code="project.status.NEW" /></option>
												<option value="PLA"><spring:message code="project.status.PLA" /></option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Start date -->
					<div class="row row-form">
						<div class="col-md-12">
							<div class="row"></div>
							<div class="form-group">
								<label for="startDate" class="control-label text-left col-md-2 col-sm-2">
									<spring:message code="create.update.page.main.start.date" />
									<font color="red">*</font>
								</label>
								<div class="col-md-3 col-sm-3" id="div-start-date">
									<div class="row">
										<div class="col-md-9">
											<div class="inner-addon right-addon">
												<i class="glyphicon glyphicon-calendar icon-color-gray"></i>
												<input type="text" id="startDate" name="startDate" required
													class="form-control input-sm datepicker text-date" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-9">
											<span id="error-invalid-start-date" class="help-block">
												<spring:message code="create.update.page.error.invalid.date" />
											</span>
										</div>
									</div>
								</div>

								<label for="endDate" class="control-label text-left col-md-1 col-sm-2 ml-30">
										<spring:message code="create.update.page.main.end.date" />
									</label>
								<div class="col-md-4 col-sm-4" id= "div-end-date">
									<div class="row">
										<div class="col-md-7">
											<div class="inner-addon right-addon">
												<i class="glyphicon glyphicon-calendar icon-color-gray"></i>
												<input type="text" id="endDate" name="endDate"
													class="form-control input-sm datepicker text-date" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-9">
											<span id="error-invalid-end-date" class="help-block">
												<spring:message code="create.update.page.error.invalid.date" />
											</span>
										</div>
									</div>
									<div class="row">
										<div class="col-md-9">
											<span id="error-end-date-less-than-start-date" class="help-block">
												<spring:message code="create.update.page.error.end.date.less.than.start.date" />
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<input type="hidden" id="projectVersion" data-bind="value: version"/>

					<!-- tag-divide -->
					<div class="row">
						<hr class="hr-width border-color-gray" />
					</div>

					<div class="row mt-30 mb-20">
						<div class="col-md-10">
							<div class="form-group">
								<div class="col-md-5"></div>
								<div class="col-md-2">
									<a href="/" class="btn btn-default btn-normal"><spring:message code="create.update.page.button.cancel" /></a>
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-4">
									<button type="submit" class="btn btn-normal blue"
										data-bind="click: createOrUpdateProject">${titleButtonSave }</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="/static/lib/js/jquery.min.js"></script>
	<script src="/static/lib/js/knockout-3.1.0.js"></script>
	<script src="/static/lib/js/jquery-1.12.4.js"></script>
	<script src="/static/lib/js/jquery-ui.js"></script>
	<script src="/static/lib/js/bootstrap.js"></script>
	<script src="/static/js/main.js"></script>
	<script src="/static/js/create-project.js"></script>
</body>

</html>