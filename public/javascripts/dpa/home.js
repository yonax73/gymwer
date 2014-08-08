require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json'

			
	}
});

/*
 * ! home Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * home yonax73@gmail.com
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




requirejs(['Aes', 'Constants', 'Play','Json'],function(Aes,Constants, Play, Json ) {

	

//	  var password = '123';
//	  var plaintext = 'Hello word!!';
//	  var ciphertext = Ctr.encrypt(plaintext, password, 256);
//	  var origtext = Ctr.decrypt(ciphertext, password, 256);
//	  
//	  console.log('ciphertext' +ciphertext)
//	  console.log('text plain '+origtext);

	var xhr = new XMLHttpRequest();


	
	xhr.onreadystatechange = function () {	
		 
		
	       
		  if (this.readyState === 4) {
			  						
			  if(this.status === 200){
				  
				
				 console.log(this.responseText);
				  
				  var gym = Json.parse(this.responseText);
				  console.log(gym.id);
				  
			  }else {
				  
	                  									  
			  }
		  }
	}
	xhr.open('GET','/load');
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	 xhr.send();		
			
			
			
});





