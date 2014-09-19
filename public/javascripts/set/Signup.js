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




requirejs(['Aes','Constants','Play','Notify','FormOk'],function(Aes,Constants, Play,Notify,FormOk) {
	var btnSignUp = Play.getId('btnSignUp');	
	var frmSingUp = Play.getId('frmSignUp');	
	var notify = new Notify(Play.getId('altSignUp'));	
	var form = new FormOk(frmSingUp);
	frmSingUp.onsubmit = function(e){		
		e.preventDefault();		
		if(form.isValid()){		    
			 var xhr = new XMLHttpRequest();			 
			 xhr.onreadystatechange = function () {				 
				 notify.wait('Loading...');					 
				 btnSignUp.disabled = true;			        
				  if (this.readyState === Constants.READYSTATE_COMPLETE) {					  						
					  if(this.status === Constants.STATUS_OK && this.responseText === Constants.SUCCESS_REQUEST){						  
						  sessionStorage.setItem(Constants.SESSIONSTORAGE_MESSAGE, 'Your account has been created successfully');
						  window.location = '/login';						  
					  }else {						  
						    notify.danger(this.responseText);
							btnSignUp.disabled = false;											  
					  }
				  }
			 }			 
			 xhr.open('POST','/createAccount');
			 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			 xhr.send(Play.serialize(e.target));			
			 xhr.timeout = Constants.TIME_OUT;
			 xhr.ontimeout = function () {
				 notify.danger('Timed Out!!!');
				 btnSignUp.disabled = false;				
			}			
		}		
	}			
});


