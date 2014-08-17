require.config({	
	
	
	baseUrl :       'assets/javascripts',
		
	
	paths : {
			
		
		Aes :       'play/yonaxtics/google/aes',		
		Constants : 'play/yonaxtics/Constants',    	
		Play :      'play/yonaxtics/Play',
		Validate:   'play/yonaxtics/Validate' 
				
				
	}
});

/*
 * ! login Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * login yonax73@gmail.com
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
 * Version 0.1: 30-june-2014 Created on : 30-june-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */




requirejs(['Aes','Constants','Play','Validate'],function(Aes,Constants, Play, Validate) {

/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================
 */
	
		
	
	var altLogin = Play.getId('altLogin');
	var icoLogin = Play.getId('icoLogin');
	var msgLogin = Play.getId('msgLogin');
	var btnClose = Play.getClass('close');	
	
	var frmLogin = Play.getId('frmLogin');
	
	var txtName = Play.getId('txtName');
	var msgName = Play.getId('msgName');
	
	var txtEmail = Play.getId('txtEmail');
	var msgEmail = Play.getId('msgEmail');
	
	var txtPassword = Play.getId('txtPassword');
	var msgPassword = Play.getId('msgPassword');
	
	var btnLogin = Play.getId('btnSignin');    
	
	var txtEmptyMessage = "All fileds are required and can\'t be empty!!!";
	var valid = false;
	
	init();
	
	formLoginAction();
			
/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */
	
	
	
	
	 function init() {		
				
	    btnClose.onclick = function (){Play.addClass(altLogin, Constants.HIDDEN);};    				
				
		if(sessionStorage.length > 0){
			
			var message = sessionStorage.getItem(Constants.SESSIONSTORAGE_MESSAGE);
			sessionStorage.clear();
			
		    msgLogin.textContent  = message == null ? 'Hi, please sign in!' : message;
		    Play.addClass(icoLogin, Constants.ICO_SUCCESS);
			Play.addClass(altLogin, Constants.ALERT_SUCCESS);			
				
		} else {
			
			Play.addClass(altLogin, Constants.HIDDEN);
		}
				
	}
			
	
	

/* ==================================================================================================================
 * REGION FORM
 * ===================================================================================================================
 */

	
	 function formLoginAction(){
		
		/**
		 * Reset messages input form
		 */
		Play.addClass(msgName, Constants.HIDDEN);
		Play.addClass(msgEmail, Constants.HIDDEN);
		Play.addClass(msgPassword, Constants.HIDDEN);
		
		/**
		 * Validate inputs
		 */		
		txtName.onblur = function(){ valid =	Validate.empty(this,msgName,txtEmptyMessage); }
		txtEmail.onblur = function(){ valid =	Validate.empty(this,msgEmail,txtEmptyMessage); }
		txtPassword.onblur = function(){ valid =	Validate.empty(this,msgPassword,txtEmptyMessage); }		
				
		
		frmLogin.onsubmit = function(e) {
			
			e.preventDefault();
			
			if(valid){
				
				if(Validate.empty(txtName,msgName,txtEmptyMessage)){
					
					if(Validate.empty(txtEmail,msgEmail,txtEmptyMessage)){
						
						if(Validate.empty(txtPassword,msgPassword,txtEmptyMessage)){
							
							
									 var xhr = new XMLHttpRequest();
									 
									 xhr.onreadystatechange = function () {	
										 
										 msgLogin.textContent = "Loading...";			
										 Play.addClass(icoLogin, Constants.ICO_COG_SPIN);
										 Play.addClass(altLogin, Constants.ALERT_INFO);
										 Play.appendClass(altLogin, Constants.SHOW);
										 
										 btnLogin.disabled = true;
									        
										  if (this.readyState === Constants.READYSTATE_COMPLETE) {
											  											  											  
											  if(this.status === Constants.STATUS_OK && this.responseText === Constants.REQUEST_SUCCESS){
																				
												  	
												  localStorage.clear();		
												  sessionStorage.clear();
												  btnLogin.disabled = false;
												  sessionStorage.setItem(Constants.SESSIONSTORAGE_OK,Constants.OK);
												  window.location = '/users';												  
												  
											  }else {
												  
												    msgLogin.textContent  = this.responseText;
												    Play.addClass(icoLogin, Constants.ICO_ERROR);
													Play.addClass(altLogin, Constants.ALERT_DANGER);
													btnLogin.disabled = false;											  
											  }
										  }
									 }
									 xhr.open('POST','/signIn');
									 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
									 xhr.send(Play.serialize(e.target));	
									 xhr.timeout = Constants.TIME_OUT;
									 xhr.ontimeout = function () {
										 
										msgLogin.textContent = "Timed Out!!!";
										Play.addClass(icoLogin, Constants.ICO_ERROR);
										Play.addClass(altLogin, Constants.ALERT_DANGER);
										btnLogin.disabled = false;
										
									}
						}
					}
				}
				
			} else {
								
				msgLogin.textContent = txtEmptyMessage;
				Play.addClass(icoLogin, Constants.ICO_ERROR);
				Play.addClass(altLogin, Constants.ALERT_DANGER);
					
			}
		}
		
	}
	
	
	


	
	
	
	
	

	
});


