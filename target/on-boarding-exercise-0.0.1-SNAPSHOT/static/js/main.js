$(document).ready(function(){
	let language = $('.language');
	var tmp = $('.language').click(function(event) {
		let lang = $(event.target).html().toLowerCase();
		let currentLocationPage = window.location.href;
		
		if(currentLocationPage.includes('?language=en')){
			currentLocationPage = currentLocationPage.replace('?language=en','');
		}else if(currentLocationPage.includes('?language=fr')){
			currentLocationPage = currentLocationPage.replace('?language=fr','');
		}
		
		if(lang === 'en'){
			window.location.replace(currentLocationPage + '?language=' + lang);
		}else if(lang === 'fr'){
			window.location.replace(currentLocationPage + '?language=' + lang);
		}
	});
	
	var currentLanguage = getParameterURL('language');
	if(typeof currentLanguage === 'undefined' ){
		currentLanguage = 'en';
	}
		
	for(var i =0; i < language.length; i++){
		let tmpLanguage = $(language[i]).context.innerHTML.toLowerCase();
		if(tmpLanguage === currentLanguage){
			$(language[i]).css("font-weight","Bold");
		}
	}
		
	function getParameterURL(param){
		var url = window.location.search.substring(1);
		var urlVariables = url.split('&');
		for(var i = 0; i < urlVariables.length; i++){
			var parameterName = urlVariables[i].split('=');
			if(parameterName[0] === param){
				return parameterName[1];
			}
		}
	}
});
	