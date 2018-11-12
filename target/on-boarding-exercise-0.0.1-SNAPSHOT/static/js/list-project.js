function ProjectViewModel() {
	var self = this;

	self.searchName = ko.observable($('#searchName').val());
	self.searchStatus = ko.observable($('#searchStatus').val());

	self.projects = ko.observableArray([]);
	self.projectsDelete = ko.observableArray([]);
	
	self.totalPage = ko.observable('0');
	self.page = ko.observable('0');
	
	/**
	 * function handle search project
	 * */
	self.search = function(pageInput) {
		$.ajax({
			method : 'GET',
			url : '/projects/search',
			data : {
				name : self.searchName,
				status : self.searchStatus,
				page : pageInput
			}
		}).then(function(data) {
			self.projects(data.projects);
			self.totalPage(data.totalPage);
			self.page(data.page);
			self.projectsDelete.removeAll();
		});
	};

	/**
	 * function reset data search
	 */
	self.resetFormSearch = function() {
		self.searchName('');
		self.searchStatus('');
		self.search();
	};
	/**
	 * function handle delete project
	 * */
	self.deleteProject = function(project) {
		if (confirm('Are you sure you want to delete this project?')) {
			$.ajax({
				method : 'DELETE',
				url : '/projects/deleteSingleProject',
				contentType : 'application/json',
				data : JSON.stringify(project),
			}).done(function(data) {
				if (data.success) {
					self.search();
				} else {
					if(data.errorType === 'ConcurrencyUpdateException'){
						alert(data.errorMessage);
					}else if (data.errorType === 'InvalidStatusOfProjectDeleteException') {
						alert(data.errorMessage);
					}
				}
			});
		}
	};
	
	/**
	 * function handle delete multiple project
	 * */
	self.deleteMultipleProject = function() {
		if (confirm('Are you sure you want to delete these project?')) {
			$.ajax({
				method : 'DELETE',
				url : '/projects/deleteMultipleProject',
				contentType : 'application/json',
				data : JSON.stringify(self.projectsDelete()),
			}).done(function(data) {
				if (data.success) {
					self.search();
				} else {
					alert(data.errorMessage);
				}
			});
		}
	};
};

/**
 * when document ready call function search to fetch data to table
 * */
$(document).ready(function() {
	var projectViewModel = new ProjectViewModel();
	projectViewModel.search();
	ko.applyBindings(projectViewModel);
});
