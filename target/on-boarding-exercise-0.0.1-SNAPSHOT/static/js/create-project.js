
function CreateProjectViewModel() {
	let self = this;
	self.project = ko.observable('');
	
	self.projectNumber = ko.observable('');
	self.projectName = ko.observable('');
	self.customer = ko.observable('');
	self.group = ko.observable('NEW');
	self.members = ko.observable('');
	self.status = ko.observable('NEW');
	self.version = ko.observable('0');
	
	self.errorVisa = ko.observable(false);
	self.createOrEditSuccess = ko.observable(false);
	
	self.groups = ko.observableArray([]);

	let resetForm = function(){
		$('#form-create-project')[0].reset();
	};

	let change = false;
			
	 $("form :input").change(function() {
		 change = true;
	 });

	 window.addEventListener("beforeunload", function(event) {
		if( change) {
			event.preventDefault();
			event.returnValue = 'anything';
		} 
	});

	window.addEventListener('unload', function(event) {
		resetForm();
	});
	
	/**
	 * function handle create multiple project
	 * */
	self.createOrUpdateProject = function() {
		$('.help-block').css('display','none');
		$('#alert-custom').css('display','none');
		validProjectNumber();
		validProjectName();
		validProjetCustomer();
		validProjectGroup();
		validProjectStatus();
		validStartDate();
		validEndDate();
		
		if (arrayError.length === 0){
			let project = {
					id: $('#projectId').val(),
					projectNumber: self.projectNumber(),
					name: self.projectName(),
					customer: self.customer(),
					status: self.status(),
					member: self.members(),
					startDate: $('#startDate').val(),
					endDate: $('#endDate').val(),
					group: self.group(),
					version: self.version()
				};
			
			$.ajax({
				method : 'POST',
				url : '/projects/createOrUpdateProject',
				contentType : 'application/json',
				data : JSON.stringify(project),
			}).done(function(data) {
				if (data.success) {
					change = false;
					location.replace("http://localhost:8080/");
				} else {
					if (data.errorType === 'ProjectNumberAlreadyExistsException'){
						$('#error-project-number-existed').css('display','block');
						$('#div-project-number').addClass('has-error');
					} else if( data.errorType === 'VisaNotExistedException'){
						self.errorVisa(data.errorMessage);
						$('#error-invalid-visa').css('display','block');
						$('#div-members').addClass('has-error');
					} else if( data.errorType === 'ConcurrencyUpdateException'){
						alert(data.errorMessage);
					} else if( data.errorType === 'NotEnoughEmployeeToCreateNewGroupException'){
						$('#div-members').addClass('has-error');
						$('#error-not-enough-employee-to-create-new-group').css('display','block');
					}else {
						alert(data.errorMessage);
					}
				}
			});
		}
	};
	
	self.findProjectById = function() {
		let id = $('#projectId').val();
		if (id == 0){
			return;
		} else {
			$('#projectNumber').blur();
			$('#projectName').focus();
			$.ajax({
				method : 'GET',
				url : '/projects/findProjectById',
				contentType : 'application/json',
				data: {
					id: $('#projectId').val()
				}
			}).done(function(data) {
				if (data.success) {
					self.projectNumber(data.item.projectNumber);
					self.projectName(data.item.name);
					self.customer(data.item.customer);
					self.group(data.item.group);
					self.members(data.item.member);
					self.status(data.item.status);
					self.version(data.item.version);
					$('#startDate').val(data.item.startDate);
					$('#endDate').val(data.item.endDate);
				} else {
					location.replace("http://localhost:8080/error");
				}
			});
		}
	};
	
	self.getAllGroup = function() {
		return $.ajax({
			method : 'GET',
			url : '/groups/',
			contentType : 'application/json'
			
		}).done(function(data) {
			self.groups(data);
		});
	};
	
	let arrayError = [];
	
	let regNotEmpty = /[\w\s]+/;
	self.validate = function(field, value, extraValidator) {
		arrayError = arrayError.filter(item => item.field !== field); 
		
		if (field !== 'endDate'){
			if (!regNotEmpty.test(value)) {
				arrayError.push({ field: field, msg: 'empty'});
			}
		}
		
		extraValidator();
	}
	
	self.checkError = function(field, formDiv, messageDiv, messageDate) {		
		if (arrayError.filter(item => item.field === field).length > 0) {
			formDiv.addClass('has-error');
		} else {
			formDiv.removeClass('has-error');
		}
		
		if (arrayError.filter(item => item.msg === 'empty').length > 0) {
			$('#alert-custom').css('display', 'block');
		} else {
			$('#alert-custom').css('display', 'none');
		}
		
		if (messageDiv){
			if (field === 'projectNumber'){
				if (arrayError.filter(item => item.msg === 'not-number').length > 0) {
					messageDiv.css('display', 'block');
				} else {
					messageDiv.css('display', 'none');
				}
			} else {
				if (arrayError.filter(item => item.msg === 'not-date').length > 0 && arrayError.filter(item => item.field === field).length > 0) {
					messageDiv.css('display', 'block');
				} else {
					messageDiv.css('display', 'none');
				}
			}
		}
		
		if (messageDate){
			if (arrayError.filter(item => item.msg === 'end-date-less-than-start-date').length > 0) {
				messageDate.css('display', 'block');
			} else {
				messageDate.css('display', 'none');
			}
		}
	}
	
	let regNumber = /^[0-9]{1,4}$/;
	let projectNumber = $("#projectNumber");
	projectNumber.change(function() {
		validProjectNumber();
	});
	let validProjectNumber = function() {
		let field = 'projectNumber';
		let value = projectNumber.val();
		let formDiv = $('#div-project-number');
		let messageDiv = $('#error-invalid-project-number');
		self.validate(field, value, function() {
			if (!regNumber.test(value)) {
				arrayError.push({ field: field, msg: 'not-number'});
			}
		});
		self.checkError(field, formDiv, messageDiv);
	};
	
	let projectName = $("#projectName");
	projectName.change(function() {
		validProjectName();
	});
	let validProjectName = function() {
		let field = 'projectName';
		let value = projectName.val();
		let formDiv = $('#div-project-name');
		self.validate(field, value, function() {});
		self.checkError(field, formDiv);
	}
	
	let customer = $("#customer");
	customer.change(function(){
		validProjetCustomer();
	});
	
	let validProjetCustomer = function() {
		let field = 'customer';
		let value = customer.val();
		let formDiv = $('#div-customer');
		self.validate(field, value, function() {});
		self.checkError(field, formDiv);
	};
	
	let group = $("#group");
	group.change(function() {
		validProjectGroup();
	});
	let validProjectGroup = function(){
		let field = 'group';
		let value = group.val();
		let formDiv = $('#div-group');
		self.validate(field, value, function() {});
		self.checkError(field, formDiv);
	};
	
	let projectStatus = $("#projectStatus");
	projectStatus.change(function() {
		validProjectStatus();
	});
	let validProjectStatus = function() {
		let field = 'projectStatus';
		let value = projectStatus.val();
		let formDiv = $('#div-project-status')
		self.validate(field, value, function() {});
		self.checkError(field, formDiv);
	};
	
	let regValidDateFormat = /^\d{2}-\d{2}-\d{4}$/;
	let startDate = $("#startDate");
	let dateOfStart;
	
	let validStartDate = function(){
		let field = 'startDate';
		let value = startDate.val();
		let formDiv = $('#div-start-date');
		let messageDiv = $('#error-invalid-start-date');
		
		self.validate(field, value, function() {
			if (regValidDateFormat.test(value)){
				dateOfStart = new Date(value.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));

				if (dateOfStart.toDateString() == 'Invalid Date'){
					arrayError.push({ field: field, msg: 'not-date'});
				}
			} else {
				arrayError.push({ field: field, msg: 'not-date'});
			}
		});
		self.checkError(field, formDiv, messageDiv);
	};
	
	startDate.change(function() {
		validStartDate();
	});
	
	let endDate = $("#endDate");
	let dateOfEnd;
	
	let validEndDate = function() {
		let field = 'endDate';
		let value = endDate.val();
		let formDiv = $('#div-end-date');
		let messageDiv =$('#error-invalid-end-date');
		let messageDate = $('#error-end-date-less-than-start-date');
		
		self.validate(field, value, function() {
			if(value === ''){
				
			} else if (regValidDateFormat.test(value)){
				dateOfEnd = new Date(value.replace(/(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));

				if (dateOfEnd.toDateString() == 'Invalid Date'){
					arrayError.push({ field: field, msg: 'not-date'});
				} else {
					if (dateOfEnd < dateOfStart){
						arrayError.push({ field: field, msg: 'end-date-less-than-start-date'});
					}
				}
			} else{
				arrayError.push({ field: field, msg: 'not-date'});
			}
		});
		self.checkError(field, formDiv, messageDiv, messageDate);
	};
	
	endDate.change(function() {
		validEndDate();
	});
};

$(document).ready(function() {
	
	let createProjectViewModel = new CreateProjectViewModel();
	
	createProjectViewModel.getAllGroup()
		.then(createProjectViewModel.findProjectById.bind(createProjectViewModel));
	ko.applyBindings(createProjectViewModel);

	$(function() {
		$('.datepicker').datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
	
	let checkClose = false;
	
	$('#close').click(function(){
		$('#alert-custom').css('display','none');
	});
	
});

