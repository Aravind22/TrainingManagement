$(document).ready(function(){
	$('#startDate').click(function(){
		$(this).addClass('active');	
		$('#endDate').removeClass('active');
	})
	
	$('#endDate').click(function(){
		$(this).addClass('active');	
		$('#startDate').removeClass('active');
	})
})