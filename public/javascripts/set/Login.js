require.config({	
	
	
	baseUrl :       'assets/javascripts',
		
	
	paths : {
			
		
		Aes :       'play/yonaxtics/google/aes',		
		Constants : 'play/yonaxtics/Constants',    	
		Play :      'play/yonaxtics/Play',		
		Notify:     'play/yonaxtics/Notify', 
		FormOk:     'play/yonaxtics/FormOk'
				
				
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




requirejs(['Aes','Constants','Play','FormOk','Notify'],function(Aes,Constants, Play, FormOk,Notify) {

/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/	
	var notify = null;	
	var form = null;
	var frmLogin = Play.getId('frmLogin');		
	var btnLogin = Play.getId('btnSignin');			
	init();		
/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */	
	 function init() {	  
	    notify = new Notify(Play.getId('altLogin'));
		form = new FormOk(frmLogin);
		if(sessionStorage.length > 0){			
			var message = sessionStorage.getItem(Constants.SESSIONSTORAGE_MESSAGE);
			sessionStorage.clear();			
			if( message === null){
				notify.close();				
			}else {
				notify.success(message);				
			}				
		} else {	
			notify.close();
		}
		formLoginAction();	
	}
/* ==================================================================================================================
 * REGION FORM
 * ===================================================================================================================
 */
	 function formLoginAction(){	
		frmLogin.onsubmit = function(e) {			
			e.preventDefault();		
			if(form.isValid()){	
				 var xhr = new XMLHttpRequest();									 
				 xhr.onreadystatechange = function () {
					 notify.wait('Loading...');										 
					 btnLogin.disabled = true;									        
					  if (this.readyState === Constants.READYSTATE_COMPLETE) {											  											  											  
						  if(this.status === Constants.STATUS_OK){												  	
							  localStorage.clear();		
							  sessionStorage.clear();
							  btnLogin.disabled = false;
							  if(Constants.REQUEST_BAD === this.responseText){	
								  notify.danger('The name, password or user are incorrect!!!');
							  }else {
								  sessionStorage.setItem(Constants.SESSIONSTORAGE_OK,Constants.OK);
								  window.location = this.responseText;																		  
							  }												  
						  }else {
							    notify.danger(this.responseText);
								btnLogin.disabled = false;											  
						  }
					  }
				 }
				 xhr.open('POST','/signIn');
				 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				 xhr.send(Play.serialize(e.target));	
				 xhr.timeout = Constants.TIME_OUT;
				 xhr.ontimeout = function () {
					notify.danger('Timed Out!!!');
					btnLogin.disabled = false;										
				}
			}
		}
	}	
});


