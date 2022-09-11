
var nombre  = document.getElementById('nombre');
var email = document.getElementById('email');
var contraseya = document.getElementById('contraseya');

var errorNombreUsuario = document.getElementById('errorNombreUsuario');
errorNombreUsuario.style.color = 'red';
var errorEmail = document.getElementById('errorEmail');
errorEmail.style.color = 'red';
var errorPassword = document.getElementById('errorPassword');
errorPassword.style.color = 'red';


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


function enviarFormularioCandidato(){
	console.log('Enviando formulario...');
	console.log(( espaciosVacios(nombre.value)));
	console.log(( esEmail(email.value)));

	
	var mensajesErrorNombreUsuario = [];
	var mensajesErrorEmail = [];
	var mensajeErrorPassword = [];


	
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

	if(contraseya.value.length <8){
		mensajeErrorPassword.push('La contraseña debe tener como minimo 8 caracteres.');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	
	if(espaciosVacios(contraseya.value) === true){
		mensajeErrorPassword.push('No se permite espacios vacios');
		errorPassword.innerHTML = mensajeErrorPassword.join(', ');
		return false;
	}
	errorPassword.innerHTML = mensajeErrorPassword.join(', ');
	
	
	document.miFormulario.submit();
			
}