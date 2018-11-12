<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">

<link rel="stylesheet" href="/static/lib/css/bootstrap.css">
<link rel="stylesheet" href="/static/lib/css/bootstrap-theme.css">
<link rel="stylesheet" href="/static/css/main.css" />
<link rel="stylesheet" href="/static/css/list-project.css" />

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
					<h3><spring:message code="project.header.title" text="default"/></h3>
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
					<spring:message code="project.left.bar.projects.list"/>
				</div>
				
				<div class="navbar-left-blue">
					<strong><spring:message code="project.left.bar.new"/></strong>
				</div>
				
				<div class="navbar-left-item">
					<a href="/newProject" class="navbar-left-active">
						<spring:message code="project.left.bar.project" />
					</a>
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
				<div class="row">
					<div class="col-md-11" id="div-content-top">
						<h4 class="h4-cs">
							<strong><spring:message code="list.project.page.projects.list" /></strong>
						</h4>
					</div>
				</div>

				<!-- Search-project -->
				<div class="row" id="div-search">
					<div class="col-lg-4 col-md-4 col-sm-6 mt-3">
						<div class="form-group">
							<input id="searchName" name="searchName" value="${name}" autofocus 
								placeholder="${placeholderSearch }" class="form-control input-sm"
								data-bind="value: searchName" />
						</div>
					</div>

					<!-- select project status -->
					<div class="col-lg-2 col-md-2 col-sm-6 mt-3" id="projectStatus">
						<select id="searchStatus" name="searchStatus" class="form-control input-sm"
							data-bind="value: searchStatus">
							
							<option id="selectAll" selected value="" ${"" == status ? "selected='selected'" : ''}>
								<spring:message code="project.status.DEFAULT" />
							</option>
								
							<option value="FIN" ${"FIN" == status ? "selected='selected'" : ''} >
								<spring:message code="project.status.FIN" />
							</option>
								
							<option value="INP" ${"INP" == status ? "selected='selected'" : ''}>
								<spring:message code="project.status.INP" />
							</option>
							
							<option value="NEW" ${"NEW" == status ? "selected='selected'" : ''}>
								<spring:message code="project.status.NEW" />
							</option>
								
							<option value="PLA" ${"PLA" == status ? "selected='selected'" : ''}>
								<spring:message code="project.status.PLA" />
							</option>
						</select>
					</div>

					<!-- button search -->
					<div class="col-lg-2 col-md-3 col-sm-6">
						<button type="button" class="btn" id="btn-search" data-bind="click: $root.search.bind($root, 0)">
							<spring:message code="list.project.page.button.search" />
						</button>
					</div>

					<!-- Link reset-search -->
					<div class="col-lg-4 col-md-3 col-sm-6">
						<div class="link-reset-search">
							<a href="#" data-bind="click: resetFormSearch">
								<spring:message code="list.project.page.link.reset.search" />
							</a>
						</div>
					</div>
				</div>

				<!-- table-project -->
				<table id="tblProject"
					data-bind="visible: $root.projects().length > 0">
					<thead>
						<tr>
							<th class="th-checkbox"></th>
							<th class="th-project-number"><spring:message code="list.project.page.table.header.number" /></th>
							<th class="th-project-name"><spring:message code="list.project.page.table.header.name" /></th>
							<th class="th-project-status"><spring:message code="list.project.page.table.header.status" /></th>
							<th class="th-project-customer"><spring:message code="list.project.page.table.header.customer" /></th>
							<th class="th-start-date"><spring:message code="list.project.page.table.header.start.date" /></th>
							<th class="th-delete"><spring:message code="list.project.page.table.header.delete" /></th>
						</tr>
					</thead>
					<tbody>
						<!--  ko foreach: projects  -->
						<tr>
							<td><input type="checkbox" class="checkboxDelete"
								data-bind="checked: $root.projectsDelete, checkedValue: $data,
									attr: {disabled: $data.status != 'NEW' }" />
							</td>
							<td class="text-number"><a
								data-bind="attr: {title: id, href: '/editProject/' + id}, text: projectNumber"></a></td>
							<td data-bind="text: name"></td>
							<td data-bind="text: statusLable"></td>
							<td data-bind="text: customer"></td>
							<td data-bind="text: startDate"></td>
							<td>
								<!-- ko if: $data.status === 'NEW' --> <i
								id="icon_delete-project"
								class="glyphicon glyphicon-trash icon_delete"
								data-bind="click: $root.deleteProject.bind($root, $data)"> </i>
								<!-- /ko -->
							</td>
						</tr>
						<!-- /ko -->
						<tr id="div-delete_multi" data-bind="visible: $root.projectsDelete().length > 0">
							<td colspan="7">
								<div class="row" data-bind="click: $root.deleteMultipleProject.bind($root)">
									<div class="col-lg-8 col-md-8" id="div-left-bottom-talble">
										<span data-bind="text: $root.projectsDelete().length"></span>
										<spring:message code="list.project.page.item" />
										<span data-bind="visible: $root.projectsDelete().length > 1">s</span>
										<spring:message code="list.project.page.item.selected"/>
									</div>

									<div class="col-lg-4 col-md-4 text-right" id="div-right-bottom-talble">
										<spring:message code="list.project.page.delete.selected.items" />
										<i id="icon_delete-project" class="glyphicon glyphicon-trash icon_delete">
										</i>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

				<div data-bind="visible: $root.projects().length === 0" class="text-center" id="message-no-record">
					<spring:message code="list.project.page.result.not.found" />
				</div>

				<nav aria-label="Page navigation" id="navPaging"
					data-bind="visible: $root.projects().length > 0">
					<ul class="pagination">
						<li class="page-item"><a href="#" aria-label="Previous"
							data-bind="visible: $root.page() > 0,
												click: $root.search.bind($root, 0)">
								<span aria-hidden="true">&laquo;</span>
						</a></li>
						<!--  ko foreach: new Array(totalPage())  -->

						<li class="page-item"
							data-bind="css: {'active' : $root.page() === $index()},
								if: $root.page() - 2 <= $index() && $index() <= $root.page() + 2">
							<a href="#"
							data-bind="text: $index() + 1, value: $index() + 1,
												    click: $root.search.bind($root, $index())"></a>
						</li>

						<!-- /ko -->
						<li class="page-item"
							data-bind="visible: $root.page() < $root.totalPage() - 1,
												click: $root.search.bind($root, $root.totalPage() - 1)">
							<a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<script src="/static/lib/js/jquery.min.js"></script>
	<script src="/static/lib/js/knockout-3.1.0.js"></script>
	<script src="/static/lib/js/jquery-1.12.4.js"></script>
	<script src="/static/lib/js/jquery-ui.js"></script>
	<script src="/static/lib/js/bootstrap.js"></script>
	<script src="/static/js/main.js"></script>
	<script src="/static/js/list-project.js"></script>
</body>

</html>