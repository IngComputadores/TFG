
var nombre  = document.getElementById('nombre');
var email = document.getElementById('email');
var contraseya = document.getElementById('contraseya');
var telefonoEmpresa = document.getElementById('telefonoEmpresa');
var ayoEmpresa = document.getElementById("ayoFundo");

var errorNombreUsuario = document.getElementById('errorNombreUsuario');
errorNombreUsuario.style.color = 'red';
var errorEmail = document.getElementById('errorEmail');
errorEmail.style.color = 'red';
var errorPassword = document.getElementById('errorPassword');
errorPassword.style.color = 'red';
var errorTelefonoEmpresa = document.getElementById('errorTelefonoEmpresa');
errorTelefonoEmpresa.style.color = 'red';
var errorAyoEmpresa = document.getElementById('errorAyoEmpresa');
errorAyoEmpresa.style.color = 'red';


var currentTime = new Date();
var year = currentTime.getFullYear();

function espaciosVacios(c){
	var regexp = /\s/;
	return regexp.test(c);
}

function esEmail(c){
	var regexp=/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	return regexp.test(c);
}

function hasNumber(c){
	var regexp = /\d+/;
	return regexp.test(c);
}

function tieneMayusculas(c){
	var regexp = /[A-Z]/;
	return regexp.test(c);
}

function tieneMinusculas(c){
	var regexp = /[a-z]/;
	return regexp.test(c);
}

function enviarFormularioEmpresa(){
	console.log('Enviando formulario...');
	console.log(( espaciosVacios(nombre.value)));
	console.log(( esEmail(email.value)));
	console.log(year);

	
	var mensajesErrorNombreUsuario = [];
	var mensajesErrorEmail = [];
	var mensajeErrorPassword = [];
	var mensajesErrorTelefonoEmpresa = [];
	var mensajeErrorAyoEmpresa = [];

	
	if(nombre.value === '' || nombre.value === null){
		mensajesErrorNombreUsuario.push('Rellene el campo nombre de usuario');
		errorNombreUsuario.innerHTML = mensajesErrorNombreUsuario.join(', ');
		return false;
	}
	
	if(espaciosVacios(nombre.value) === true){
		mensajesErrorNombreUsuario.push('No se permite espacios vacios');
		errorNombreUsuario.innerHTML = mensajesErrorNombreUsuario.join(', ');
		return false;
	}
	errorNombreUsuario.innerHTML = mensajesErrorNombreUsuario.join(', ');


	if(email.value === '' || email.value === null){
		mensajesErrorEmail.push('Rellene el campo email');
		errorEmail.innerHTML = mensajesErrorEmail.join(', ');
		return false;
	}
	
 	if(esEmail(email.value) === false){
		mensajesErrorEmail.push('Introduzca una direccion de email correcta');
		errorEmail.innerHTML = mensajesErrorEmail.join(', ');
		return false;
	}
	errorEmail.innerHTML = mensajesErrorEmail.join(', ');
	
	
	if(contraseya.value === '' || contraseya.value === null){
		mensajeErrorPassword.push('Rellene el campo contraseña');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(contraseya.value.length <8){
		mensajeErrorPassword.push('La contraseña debe tener como minimo 8 caracteres.');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(hasNumber(contraseya.value) === false){
		mensajeErrorPassword.push('Introduzca al menos un numero');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(tieneMayusculas(contraseya.value) === false){
		mensajeErrorPassword.push('Introduzca al menos una letra mayuscula');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(	tieneMinusculas(contraseya.value) === false){
		mensajeErrorPassword.push('Introduzca al menos una letra minuscula');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(espaciosVacios(contraseya.value) === true){
		mensajeErrorPassword.push('No se permite espacios vacios');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	errorPassword.innerHTML = mensajeErrorPassword.join(', ');
	
	
	if(telefonoEmpresa.value === '' || telefonoEmpresa.value === null){
		mensajesErrorTelefonoEmpresa.push('Rellene el campo telefono empresa');
		errorTelefonoEmpresa.innerHTML = mensajesErrorTelefonoEmpresa.join(', ');
		return false;
	}
	
	if(telefonoEmpresa.value.length <9 ){
		mensajesErrorTelefonoEmpresa.push('Introduzca 9 digitos');
		errorTelefonoEmpresa.innerHTML = mensajesErrorTelefonoEmpresa.join(', ');
		return false;
	}
	
	if(telefonoEmpresa.value.length >9 ){
		mensajesErrorTelefonoEmpresa.push('Introduzca 9 digitos');
		errorTelefonoEmpresa.innerHTML = mensajesErrorTelefonoEmpresa.join(', ');
		return false;
	}
	errorTelefonoEmpresa.innerHTML = mensajesErrorTelefonoEmpresa.join(', ');

	if(ayoEmpresa.value.length <4 ){
		mensajeErrorAyoEmpresa.push('Introduzca 4 digitos');
		errorAyoEmpresa.innerHTML = mensajeErrorAyoEmpresa.join(', ');
		return false;
	}
	
	if(ayoEmpresa.value.length >4 ){
		mensajeErrorAyoEmpresa.push('Introduzca 4 digitos');
		errorAyoEmpresa.innerHTML = mensajeErrorAyoEmpresa.join(', ');
		return false;
	}
	
	if(ayoEmpresa.value > year ){
		mensajeErrorAyoEmpresa.push('El año introducido no es correcto, ya que ese año no exite actualmente. El año actual es: '+year);		
		errorAyoEmpresa.innerHTML = mensajeErrorAyoEmpresa.join(', ');
		return false;
	}
	errorAyoEmpresa.innerHTML = mensajeErrorAyoEmpresa.join(', ');

	document.miFormulario.submit();
			
}