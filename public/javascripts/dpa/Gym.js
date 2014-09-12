require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :      'play/yonaxtics/Nav',
		Notify:     'play/yonaxtics/Notify',
		FormOk:     'play/yonaxtics/FormOk'

			
	}
});

/*
 * ! gym Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * gym yonax73@gmail.com
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
 * Version 0.1: 17-August-2014 Created on : 17-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */




requirejs(['Aes', 'Constants', 'Play','Json','Nav','Notify','FormOk'],function(Aes,Constants, Play, Json,Nav,Notify,FormOk  ) {	
	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/
	var btnSave = Play.getId('btnSave');	
	var frmGym = Play.getId('frmGym');	
	var notify = new Notify(Play.getId('notify'));	
	var frmGymOk = new FormOk(frmGym);	
	var gym = null;
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
		if(Play.ready()){		
			init();		
		}	
/* ==================================================================================================================
 * REGION LOAD
 * ===================================================================================================================
 */		
	function load(){
		var xhr = new XMLHttpRequest();		
		xhr.onreadystatechange = function () {		       
			  if (this.readyState === Constants.READYSTATE_COMPLETE) {				  						
				  if(this.status === Constants.STATUS_OK){									  
					  gym = Json.parse(this.responseText);
					  localStorage.setItem(Constants.LOCALSTORAGE_REQUEST_LOAD_GYM,JSON.stringify(gym));
					  fill(gym);						
				   } 					  
			  }		  
		}
		xhr.open('GET','/loadGym');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		xhr.timeout = Constants.TIME_OUT;
		xhr.ontimeout = function () {
			 notify.danger('Time out!!!');									
		}
     }	
	function fill(gym){
	      Play.getId('nameGym').textContent =gym.name;
		  Play.getId('txtNit').value = gym.nit;
		  Play.getId('txtName').value = gym.name;
		  Play.getId('txtAddress').value = gym.location.address.address;
		  Play.getId('txtPhone').value = gym.location.phone.phone;		
	}
 /* ==================================================================================================================
 * REGION SAVE
 * ===================================================================================================================
 */		  
	 function save(){		 
		 frmGym.onsubmit = function(e){
			 e.preventDefault();
			 if(FormOk.hasChanged()){
				 if(frmGymOk.isValid()){			    						
						var xhr = new XMLHttpRequest();		
						xhr.onreadystatechange = function () {	
							  notify.wait('Loading...');	
							  btnSave.disabled = true;
							  if (this.readyState === Constants.READYSTATE_COMPLETE) {								  
								  btnSave.disabled = false;
								  if(this.status === Constants.STATUS_OK  && this.responseText === Constants.REQUEST_SUCCESS){									  
									  localStorage.removeItem(Constants.LOCALSTORAGE_REQUEST_LOAD_GYM);
									  notify.success('Your data gym has been saved successfully!');								  
								   }else{
									   notify.danger(this.responseText); 
								   } 					  
							  }		  
						}
						xhr.open('POST','/saveGym');
						xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
						xhr.send(Play.serialize(e.target));		
						xhr.timeout = Constants.TIME_OUT;
						xhr.ontimeout = function () {
							 notify.danger('Time out!!!');
							 btnSave.disabled = false;
						}					 
				 }	
			 }else{
				 notify.warning('No changes to save!!!'); 
			 }		 
		 }			 
	}
	

/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================*/	
	
	  function init(){		
		  Nav.init();
		  if(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_GYM) == null){			  
			  load();
		  }else {
			  gym = JSON.parse(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_GYM)); 
			  fill(gym);
		  }
		  save();
	}	
	
});





