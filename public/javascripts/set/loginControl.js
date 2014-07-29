require.config({
	baseUrl : 'assets/javascripts',
	paths : {
		Constants : 'play/yonaxtics/Constants',
		Play : 'play/yonaxtics/Play',
		Validate: 'play/yonaxtics/Validate' 
	}
});

/*
 * ! loginControl Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * loginControl yonax73@gmail.com
 * ========================================================================
 * Copyright 2014 yonaxTics, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the 'License'); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * ========================================================================
 */
/*
 * ========================================================================
 * Version 0.1: 28-june-2014 Created on : 28-june-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */




requirejs([ 'Constants', 'Play','Validate' ],function(Constants, Play, Validate) {

/* ==================================================================================================================
 * REGION ATRIBUTOS
 * ===================================================================================================================
 */
	var altSignUp = Play.getId('alertSignUp');
	var btnClose = Play.getClass('close');
	
	
	var txtName = Play.getId('txtName');
	var msgName = Play.getId('msgName');	
	var txtNameMessage = 'The name can only consist of alphabetical,dot andunderscore';
	
	var txtEmail = Play.getId('txtEmail');
	var msgEmail = Play.getId('msgEmail');
	var txtEmailMessage = 'The input is not a valid email address';
	
	var txtPassword = Play.getId('txtPassword');
	var msgPassword = Play.getId('msgPassword');
	var txtPasswordMessage = 'The confirm password is required and can\'t be empty';
	
	var txtConfirmPassword = Play.getId('txtConfirmPassword');
	var msgConfirmPassword = Play.getId('msgConfirmPassword');
	var txtConfirmPasswordMessage = 'The password and its confirm are not the same';
	
	var cbxTerms = Play.getId('cbxTerms');
	var msgTerms  = Play.getId('msgTerms');
	var txtTermsMessage = 'Please choose Terms and Conditions.';
	
	var frmSingUp = Play.getId('frmSignUp');
	
	var valid = false;
	
/* ==================================================================================================================
 *  END REGION ATRIBUTOS
 * ===================================================================================================================
 */
	
	
	
	
/* ==================================================================================================================
 * REGION ALERTS
 * ===================================================================================================================
 */
			

	Play.addClass(altSignUp, Constants.HIDDEN);

	Play.addClass(altSignUp, Constants.ALERT_DANGER);

	Play.appendClass(altSignUp, Constants.SHOW);

	btnClose.addEventListener('click', function() {Play.addClass(altSignUp, Constants.HIDDEN);}, false);

	
	
/* ==================================================================================================================
 * END REGION ALERTS
 * ===================================================================================================================
 */
			

/* ==================================================================================================================
 * REGION FORM
 * ===================================================================================================================
 */
		

	/**
	 * Reset messages input form
	 */
	Play.addClass(msgName, Constants.HIDDEN);
	Play.addClass(msgEmail, Constants.HIDDEN);
	Play.addClass(msgPassword, Constants.HIDDEN);
	Play.addClass(msgConfirmPassword, Constants.HIDDEN);
	Play.addClass(msgTerms, Constants.HIDDEN);
	
	/**
	 * Validate inputs
	 */
	
	txtName.onblur = function(){ valid =	Validate.fullName(this,msgName,txtNameMessage); }	
	txtName.onkeyup = function() { valid =	Validate.fullName(this,msgName,txtNameMessage); }
	
	txtEmail.onblur = function(){ valid =	Validate.email(this,msgEmail,txtEmailMessage); }	
	txtEmail.onkeyup = function() { valid =	Validate.email(this,msgEmail,txtEmailMessage); }
	
	txtEmail.onblur = function(){ valid =	Validate.email(this,msgEmail,txtEmailMessage); }	
	
			
	frmSignUp.onsubmit = function(e) {
		
		e.preventDefault();
		
		if(valid){
			
			alert('ok');
		
		} else {
			
			alert('error');
		}
	}
   

	

/* ==================================================================================================================
 * END REGION FORM
 * ===================================================================================================================
 */
			
			
			
			
			
});

// getId('frmSignUp').onsubmit = function(e) {
//	 
//	
//	
//	
// e.preventDefault();
//    
// var xhr = new XMLHttpRequest();
// xhr.onreadystatechange = alertContents;
// xhr.open('POST','/createAccount');
// xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;
// charset=UTF-8");
// xhr.send(serializeForm(e.target));
//
// function alertContents (){
//
// altSignUp.innerHTML +='momemt please..';
//     
// if (this.readyState === 4 && this.status === 200) {
//            	
//           	 
// altSignUp.innerHTML = this.responseText;
// }
// }
// 
// 
//    
//
//
//
//    
//
//    
//    
//    
// }

// requirejs(
// [ 'jquery', 'bootstrap','bootstrapValidator','notify' ],
// function($, bootstrap,bootstrapValidator) {
//
// if (document.createStyleSheet) {
// document
// .createStyleSheet('assets/stylesheets/bootstrapValidator.min.css');
// } else {
// $('head')
// .append(
// $('<link rel="stylesheet"
// href="assets/stylesheets/bootstrapValidator.min.css" type="text/css"
// media="screen" />'));
// }
//
//
//
// $('#frmSignUp')
// .bootstrapValidator(
// {
// message : 'This value is not valid',
// fields : {
// 'txtName' : {
// message : 'The name is not valid',
// validators : {
// notEmpty : {
// message : 'The name is required and can\'t be empty'
// },
// stringLength : {
// min : 6,
// max : 40,
// message : 'The name must be more than 6 and less than 30 characters long'
// },
// regexp : {
// regexp : /^[a-zA-Z][a-zA-Z ]+$/,
// message : 'The name can only consist of alphabetical, number, dot and
// underscore'
// },
// different : {
// field : 'txtPassword',
// message : 'The name and password can\'t be the same as each other'
// }
// }
// },
// txtEmail : {
// validators : {
// notEmpty : {
// message : 'The email address is required and can\'t be empty'
// },
// emailAddress : {
// message : 'The input is not a valid email address'
// }
// }
// },
// txtPassword : {
// validators : {
// notEmpty : {
// message : 'The password is required and can\'t be empty'
// },
// identical : {
// field : 'txtConfirmPassword',
// message : 'The password and its confirm are not the same'
// },
// different : {
// field : 'txtName',
// message : 'The password can\'t be the same as username'
// }
// }
// },
// txtConfirmPassword : {
// validators : {
// notEmpty : {
// message : 'The confirm password is required and can\'t be empty'
// },
// identical : {
// field : 'txtPassword',
// message : 'The password and its confirm are not the same'
// },
// different : {
// field : 'txtName',
// message : 'The password can\'t be the same as username'
// }
// }
// },
// cbxTerms:{
// validators: {
// choice: {
// min: 1,
// max: 1,
// message: 'Please choose Terms and Conditions.'
// }
// }
// },
// }
// })
// .on('success.form.bv', function(e) {
//					            
// e.preventDefault();
// var $form = $(e.target);
// $.post('/createAccount',$form.serialize(), function( data ) {
//					            	     
//					            	
// $('#frmSignUp').notify(data,{ position:'top center' });
//					            	
// alert(data);
// });
// });
//
// });
