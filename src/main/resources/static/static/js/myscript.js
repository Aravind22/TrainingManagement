$(document).ready(function(){

	$("#uptProd").attr("disabled", "disabled");
	$("#dletProd").attr("disabled", "disabled");
	
	//search click 
	$('#search').click(function(){
		var productCode = $('#productCode').val();
		if(productCode.trim() == "") {
			$('#productCodeMsg').html('Please enter Product Code');
		}
		else{
			$('#productCodeMsg').hide();
			console.log("searchig...");
			$.ajax({
				type:"GET",
				url:"getProductAsJSON/" + $('#productCode').val(),
				dataType: "json",
				success: function(output) {
					console.log("received...");
					//$('#productCode').val(output.productCode);
					$('#productDescription').val(output.productDescription);
					$('#unitPrice').val(output.unitPrice);
					$('#qoh').val(output.qoh);
					$('#category').val(output.category);
					
					$("#uptProd").removeAttr("disabled");
					$("#dletProd").removeAttr("disabled");

				},
				error:function(req, status, error) {
					$('#productDescription').val("");
					$('#unitPrice').val("");
					$('#qoh').val("");
					$('#category').val("");
			
					$('#productCodeMsg').show();
					$('#productCodeMsg').html('Error loading product');
				}
			});
		}
	});
	
	//update click
	$('#uptProd').click(function(event){
		
		let validationStatus = true;
		
		if($('#productCode').val().trim() == ""){
			$('#productCodeMsg').html('Please enter Product Code');
			$('#productCodeMsg').show();
			validationStatus = false;
		}
		else
			$('#productCodeMsg').hide();
		
		if($('#productDescription').val().trim() == ""){
			$('#productDescriptionMsg').html('Please enter Product Description');
			$('#productDescriptionMsg').show();
			validationStatus = false;
		}
		else
			$('#productDescriptionMsg').hide();
		
		if($('#unitPrice').val().trim()  == "") {
			$('#unitPriceMsg').html('Please enter UnitPrice');
			$('#unitPriceMsg').show();	
			validationStatus = false;
		}
		else
			$('#unitPriceMsg').hide();
		
		if($('#qoh').val().trim() == ""){
			$('#qohMsg').html('Please enter Quantity');
			$('#qohMsg').show();		
			validationStatus = false;
		}
		else
			$('#qohMsg').hide();
		
		if(validationStatus) {
			console.log("Updating...");
			
			let formData = '{"productCode":"' + $('#productCode').val().trim() + '"' + 
						 ',"productDescription":"' + $('#productDescription').val() + '"' +
						 ',"unitPrice":' + $('#unitPrice').val() +
						 ',"qoh":' + $('#qoh').val() + 
						 ',"category":"' + $('#category').val() + '"}';
			
			console.log(formData);
			
			
			$.ajax({
				type:"PUT",
				url:"updateProductAsJSON",
				data : formData,
				contentType: "application/json", //sending type
				dataType: "text",   //receiving type
				success: function(output) {  
					$('#statusMessage').html(output);
				},
				error:function(req, status, error) {
					$('#statusMessage').html("Error while updation");
					console.log(status, error );
				}
			});			
		}
	});
	
	
	//delete click
	
	$('#dletProd').click(function(event){
		
		let validationStatus = true;
		
		if($('#productCode').val().trim() == ""){
			$('#productCodeMsg').html('Please enter Product Code');
			$('#productCodeMsg').show();
			validationStatus = false;
		}
		else
			$('#productCodeMsg').hide();
		
		if(validationStatus) {
			console.log("Deleting...");
				
			$.ajax({
				type:"DELETE",
				url:"deleteProductUsingAJAX/" + $('#productCode').val(),
				dataType: "text", 
				success: function(output) {  
					$('#statusMessage').html(output);
					$('#productCode').val("");
					$('#productDescription').val("");
					$('#unitPrice').val("");
					$('#qoh').val("");
					$('#category').val("");
				},
				error:function(req, status, error) {
					$('#statusMessage').html("Error while deletion");
					console.log(status, error );
				}
			});			
		}
	});
	
	
});
