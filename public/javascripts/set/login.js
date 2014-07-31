require.config({
	baseUrl : 'assets/javascripts',
	paths : {
		Constants : 'play/yonaxtics/Constants',
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




requirejs([ 'Constants', 'Play','Validate' ],function(Constants, Play, Validate) {

/* ==================================================================================================================
 * REGION ATRIBUTOS
 * ===================================================================================================================
 */
	var altLogin = Play.getId('altLogin');
	var icoLogin = Play.getId('icoLogin');
	var msgLogin = Play.getId('msgLogin');
	var btnClose = Play.getClass('close');	

	init();
			
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
				
			

			
});


