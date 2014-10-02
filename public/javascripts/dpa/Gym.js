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
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
		
	init();		

/* ==================================================================================================================
 * REGION LOAD
 * ===================================================================================================================
 */		
	function load(){
		Play.getRequest('/loadGym',function(xhr){							
			 fill(Json.parse(xhr.responseText));	
		});
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
					 Play.sendRequest(e.target,function(){
						  notify.wait('Loading...');	
						  btnSave.disabled = true; 
					 },function(xhr){	
						 btnSave.disabled = false;
						 if(xhr.responseText === Constants.SUCCESS_REQUEST){
							 notify.success('Your data gym has been saved successfully!');
						 }else{
							 notify.danger(xhr.responseText); 
						 }						
					 });
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
		  load();
		  save();
	}	
	
});





