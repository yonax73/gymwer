require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :      'play/yonaxtics/Nav'	

			
	}
});

/*
 * ! dashboard Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * dashboard yonax73@gmail.com
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
 * Version 0.1: 1-August-2014 Created on : 1-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */




requirejs(['Aes', 'Constants', 'Play','Json','Nav'],function(Aes,Constants, Play, Json,Nav ) {

	
	
	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/

	
	if(Play.ready()){ 	
	
		load();
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
					  
					  var gym = Json.parse(this.responseText);				  
					  
				  }else {
					  
		               //error message   									  
				  }
			  }
		}
		xhr.open('GET','/load');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		//set time out	
     }
	

/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */	
	
	  function init(){
		
		  Nav.init();
	}
	
	
	
	
	

	
	
	
});





