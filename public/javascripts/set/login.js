require.config({
	baseUrl : 'assets/javascripts',
	paths : {
		Constants : 'play/yonaxtics/Constants',
		aes : 'play/yonaxtics/aes1',
    	pbkdf2 : 'play/yonaxtics/pbkdf2',
    	AesUtil : 'play/yonaxtics/AesUtil',
		Play : 'play/yonaxtics/Play',
		Validate: 'play/yonaxtics/Validate' 
			
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




requirejs([ 'Constants', 'Play','Validate','aes','pbkdf2','AesUtil' ],function(Constants, Play, Validate) {

/* ==================================================================================================================
 * REGION ATRIBUTOS
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
	
	var txtEmptyMessage = "All fileds are required and can\'t be empty";
	var valid = false;
	
	init();
	
	formLoginAction();
			
/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */
	
	
	
	
	function init(){
				
	    btnClose.onclick = function (){Play.addClass(altLogin, Constants.HIDDEN);};    				
				
		if(sessionStorage.length > 0){
			
			var message = sessionStorage.getItem("msgLoginSession");
			sessionStorage.clear();
			
		    msgLogin.textContent  = message;
		    Play.addClass(icoLogin, Constants.ICO_SUCCESS);
			Play.addClass(altLogin, Constants.ALERT_SUCCESS);			
				
		} else {
			
			Play.addClass(altLogin, Constants.HIDDEN);
		}
				
	}
	
			
	
	
/* ==================================================================================================================
 * END REGION INIT
 * ===================================================================================================================
 */
				
	
	
	
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
									        
										  if (this.readyState === 4) {
											  
											  console.log(this.responseText);
											  
											  if(this.status === 200 && this.responseText === Constants.REQUEST_SUCCESS){
												 
												  
												  window.location = '/home';
												  
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
	
	
	
/* ==================================================================================================================
 * END REGION FORM
 * ===================================================================================================================
 */

	
	
	
	
	
	var iv = "F27D5C9927726BCEFE7510B1BDD3D137";
	var salt = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
	var plainText = 'hello word';
	var keySize = 128;
	var iterations = iterationCount = 10000;
	var passPhrase = "the quick brown fox jumps over the lazy dog";
	
	
	  var aesUtil = new AesUtil(keySize, iterationCount)
	    var encrypt = aesUtil.encrypt(salt, iv, passPhrase, plainText);
	  var cipherText = encrypt;
	    
	    console.log('******************************');
	    console.log(encrypt);
	    console.log('******************************');
	    
	    var aesUtil = new AesUtil(keySize, iterationCount)
	    var decrypt = aesUtil.decrypt(salt, iv, passPhrase, cipherText);
	   
	    console.log(decrypt);
	
});


