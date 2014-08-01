require.config({
	baseUrl : 'assets/javascripts',
	paths : {
		Constants : 'play/yonaxtics/Constants',
		Play : 'play/yonaxtics/Play',
		Validate: 'play/yonaxtics/Validate' 
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




requirejs([ 'Constants', 'Play','Validate' ],function(Constants, Play, Validate) {

/* ==================================================================================================================
 * REGION ATRIBUTOS
 * ===================================================================================================================
 */
	var altSignUp = Play.getId('altSignUp');
	var icoSignUp = Play.getId('icoSignUp');
	var msgSignUp = Play.getId('msgSignUp');
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
			

	Play.addClass(altSignUp, Constants.HIDDEN);
	btnClose.onclick = function (){Play.addClass(altSignUp, Constants.HIDDEN);};
	
	
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
	
	txtPassword.onblur = function(){ valid =	Validate.empty(this,msgPassword,txtPasswordMessage); }
	txtPassword.onkeyup = function(){ valid =	Validate.empty(this,msgPassword,txtPasswordMessage); }
	
	txtConfirmPassword.onblur = function(){ valid =  Validate.equals(this,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage) }
	txtConfirmPassword.onkeyup = function(){ valid = Validate.equals(this,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage) }
	
	
	
			
	frmSignUp.onsubmit = function(e) {
		
		e.preventDefault();
		
		if(valid){
			
			if(Validate.fullName(txtName,msgName,txtNameMessage)){
				
				if(Validate.email(txtEmail,msgEmail,txtEmailMessage)){
					
					if(Validate.empty(txtPassword,msgPassword,txtPasswordMessage)){
						
						if(Validate.equals(txtConfirmPassword,txtPassword,msgConfirmPassword,txtConfirmPasswordMessage)){
			              
							if(Validate.isChecked(cbxTerms,msgTerms,txtTermsMessage)){
							     
								 var xhr = new XMLHttpRequest();
								 
								 xhr.onreadystatechange = function () {	
									 
									 msgSignUp.textContent = "Loading...";			
									 Play.addClass(icoSignUp, Constants.ICO_COG_SPIN);
									 Play.addClass(altSignUp, Constants.ALERT_INFO);
									 Play.appendClass(altSignUp, Constants.SHOW);
									 
									 btnSignUp.disabled = true;
								        
									  if (this.readyState === 4) {
										  						
										  if(this.status === 200 && this.responseText === Constants.REQUEST_SUCCESS){
											  
											  sessionStorage.setItem("msgLoginSession", "Your account has been created successfully");
											  window.location = '/login';
											  
										  }else {
											  
											    msgSignUp.textContent  = this.responseText;
											    Play.addClass(icoSignUp, Constants.ICO_ERROR);
												Play.addClass(altSignUp, Constants.ALERT_DANGER);
												btnSignUp.disabled = false;											  
										  }
									  }
								 }
								 
								 xhr.open('POST','/createAccount');
								 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
								 xhr.send(Play.serialize(e.target));								
						
						}
					}					
				}				
			}	
	    }   
	} else {
		
		msgSignUp.textContent = 'All fields are required and can\'t be empty';
		Play.addClass(icoSignUp, Constants.ICO_ERROR);
		Play.addClass(altSignUp, Constants.ALERT_DANGER);
	}
}
	

/* ==================================================================================================================
 * END REGION FORM
 * ===================================================================================================================
 */
			
			
			
			
			
});


