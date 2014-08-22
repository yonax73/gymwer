require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :       'play/yonaxtics/Nav'	,
		Select :    'play/yonaxtics/Select',
		List :      'set/List'

			
	}
});

/*
 * ! Profile.js Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Profile.js yonax73@gmail.com
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




requirejs(['Aes', 'Constants', 'Play','Json','Nav','Select','List'],function(Aes,Constants, Play, Json,Nav,Select,List ) {

	
	
	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/

	
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
					  
					  
					  var profile = Json.parse(this.responseText);
					  localStorage.setItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE,JSON.stringify(profile));
					  fill(profile);		
				
				}					  
					  
		  }else {
			  
               //error message   									  
		  }
			  
		}
		xhr.open('GET','/loadProfile');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		//set time out	
     }
	
	
	
	function fill(profile){
		
		  Play.getId('txtNameUser').value = profile.user.name;
		  Play.getId('txtRole').value = profile.user.role.name;					 
	      var selectPageHome = new Select(Play.getId('selectPageHome'),List.url());
	      selectPageHome.init(profile.user.defaultAction.id);	
		  Play.getId('txtEmail').value = profile.user.email;
		  Play.getId('nameUser').textContent = profile.user.name;
		  Play.getId('txtFullName').value = profile.name;
		  Play.getId('txtDocument').value = profile.document;
		  Play.getId('txtAddress').value = profile.location.address.address;
		  Play.getId('txtPhone').value = profile.location.phone.phone;	
		
	}
	

/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */	
	
	function init(){	
		
		  Nav.init();
		
		  if(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE) == null){			  
			  load();
		  }else {
			  fill(JSON.parse(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE)));
		  }		  
		;
	}
	
	
	
	
	

	
	
	
});





