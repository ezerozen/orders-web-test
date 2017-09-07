$(function() {	
	
    $("#order-form input").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
        },
        submitSuccess: function($form, event) {
            event.preventDefault();
            
    		var $name = $("#order-form #name");
    	    var $amount = $("#order-form #amount");
    	    var $discount = $("#order-form #discount");
    	 
    	    $.ajax({
    	        type: "POST",
    	        url: "/orders/save",
    	        contentType: "application/json",
    	        data: JSON.stringify({ "name": $name.val(), "amount" : $amount.val(), "discount" : $discount.val() }),
    	        success : function(response){
    	        	$name.val("");
    	        	$amount.val("");
    	        	$discount.val("");
    	        }
    	    });
        }
    });
	
});