<!DOCTYPE html>
<html lang="es">
<head>
  	<title>Pedidos</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/jqBootstrapValidation.js"></script>
	<script src="/js/orders.js"></script>

</head>
	<body>
		<div class="container">
		  <h2>Pedidos</h2>
		  <form id="order-form" class="form-horizontal">
			<div class="control-group">
		    	<label class="control-label" for="name">Nombre</label>
		    	<div class="controls">
		      		<input type="text" name="name" id="name" maxlength="100" required/>
		      		<p class="help-block"></p>
		    	</div>
		  	</div>
			<div class="control-group">
		    	<label class="control-label for="amount">Monto</label>
		    	<div class="controls">
		      		<input type="number" name="amount" id="amount" required/>
		      		<p class="help-block"></p>
		    	</div>
		  	</div>
			<div class="control-group">
		    	<label class="control-label" for="discount">Descuento</label>
		    	<div class="controls">
		      		<input type="number" name="discount" id="discount"/>
		      		<p class="help-block"></p>
		    	</div>
		  	</div>  
		  	<button id="button-save" class="btn btn-default">Guardar</button>	  	  
		  </form>
		</div>
	</body>
</html>
