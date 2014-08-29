require.config({
	
	
	baseUrl :       'assets/javascripts',
		
	
	paths : {
			
		
		Aes :       'play/yonaxtics/google/aes',		
		Constants : 'play/yonaxtics/Constants',    	
		Play :      'play/yonaxtics/Play',
		Validate:   'play/yonaxtics/Validate',
		Notify:     'play/yonaxtics/Notify',
		FormOk:     'play/yonaxtics/FormOk'
				
				
	}
});

/*
 * ! signup Copyright 2014 YonaxTics, Inc. Licensed under
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




requirejs(['Aes','Constants','Play','Validate','Notify','FormOk'],function(Aes,Constants, Play, Validate,Notify,FormOk) {

/* ==================================================================================================================
 * REGION ATRIBUTOS
 * ===================================================================================================================
 */

	var notify = null;	
	var form = null;
	
	//var txtName = Play.getId('txtName');
//	var msgName = Play.getId('msgName');	
	//var txtNameMessage = 'The name can only consist of alphabetical,dot andunderscore';
	
	//var txtEmail = Play.getId('txtEmail');
	//var msgEmail = Play.getId('msgEmail');
	//var txtEmailMessage = 'The input is not a valid email address';
	
	//var txtPassword = Play.getId('txtPassword');
	//var msgPassword = Play.getId('msgPassword');
	//var txtPasswordMessage = 'The confirm password is required and can\'t be empty';
	
	//var txtConfirmPassword = Play.getId('txtConfirmPassword');
	//var msgConfirmPassword = Play.getId('msgConfirmPassword');
	//var txtConfirmPasswordMessage = 'The password and its confirm are not the same';
	
	//var cbxTerms = Play.getId('cbxTerms');
	//var msgTerms  = Play.getId('msgTerms');
	//var txtTermsMessage = 'Please choose Terms and Conditions.';
	
	var btnSignUp = Play.getId('btnSignUp');
	
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
			
	notify = new Notify(Play.getId('altSignUp'));

	
	
/* ==================================================================================================================
 * END REGION ALERTS
 * ===================================================================================================================
 */
			

/* ==================================================================================================================
 * REGION FORM
 * ===================================================================================================================
 */
		form = new FormOk(frmSingUp);

	/**
	 * Reset messages input form
	 */
//	Play.addClass(msgName, Constants.HIDDEN);
//	Play.addClass(msgEmail, Constants.HIDDEN);
//	Play.addClass(msgPassword, Constants.HIDDEN);
//	Play.addClass(msgConfirmPassword, Constants.HIDDEN);
//	Play.addClass(msgTerms, Constants.HIDDEN);
	
	/**
	 * Validate inputs
	 */
	
//	txtName.onblur = function(){ valid =	Validate.fullName(this,msgName,txtNameMessage); }	
//	txtName.onkeyup = function() { valid =	Validate.fullName(this,msgName,txtNameMessage); }
//	
//	txtEmail.onblur = function(){ valid =	Validate.email(this,msgEmail,txtEmailMessage); }	
//	txtEmail.onkeyup = function() { valid =	Validate.email(this,msgEmail,txtEmailMessage); }
//	
//	txtPassword.onblur = function(){ valid =	Validate.empty(this,msgPassword,txtPasswordMessage); }
//	txtPassword.onkeyup = function(){ valid =	Validate.empty(this,msgPassword,txtPasswordMessage); }
//	
//	txtConfirmPassword.onblur = function(){ valid =  Validate.equals(this,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage) }
//	txtConfirmPassword.onkeyup = function(){ valid = Validate.equals(this,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage) }
	
	
	frmSingUp.onsubmit = function(e){
		
		e.preventDefault();
		
		if(form.isValid()){
		      
			alert('do request');
		}else {
			alert('error');
		}
		
		
	}
			
//	frmSignUp.onsubmit = function(e) {
//		
//		e.preventDefault();
//		
//		if(valid){
//			
//			if(Validate.fullName(txtName,msgName,txtNameMessage)){
//				
//				if(Validate.email(txtEmail,msgEmail,txtEmailMessage)){
//					
//					if(Validate.empty(txtPassword,msgPassword,txtPasswordMessage)){
//						
//						if(Validate.equals(txtConfirmPassword,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage)){
//			              
//							if(Validate.isChecked(cbxTerms,msgTerms,txtTermsMessage)){
//							     
//								 var xhr = new XMLHttpRequest();
//								 
//								 xhr.onreadystatechange = function () {	
//									 
//									 notify.wait('Loading...');	
//									 
//									 btnSignUp.disabled = true;
//								        
//									  if (this.readyState === Constants.READYSTATE_COMPLETE) {
//										  						
//										  if(this.status === Constants.STATUS_OK && this.responseText === Constants.REQUEST_SUCCESS){
//											  
//											  sessionStorage.setItem(Constants.SESSIONSTORAGE_MESSAGE, 'Your account has been created successfully');
//											  window.location = '/login';
//											  
//										  }else {
//											  
//											    notify.danger(this.responseText);
//
//												btnSignUp.disabled = false;											  
//										  }
//									  }
//								 }
//								 
//								 xhr.open('POST','/createAccount');
//								 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//								 xhr.send(Play.serialize(e.target));			
//								 xhr.timeout = Constants.TIME_OUT;
//								 xhr.ontimeout = function () {
//									 notify.danger('Timed Out!!!');
//									btnSignUp.disabled = false;
//									
//								}
//						
//						}
//					}					
//				}				
//			}	
//	    }   
//	} else {		
//		notify.danger('All fields are required and can\'t be empty!!!');
//	}
//}
	

/* ==================================================================================================================
 * END REGION FORM
 * ===================================================================================================================
 */
			
			
			
			
			
});


