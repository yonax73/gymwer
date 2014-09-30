/*
 * ! Play List.js Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play List.js yonax73@gmail.com
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
 * Version 0.1: 21-August-2014 Created on : 21-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */

define(['./Constants','./Json','./Select'], function(Constants,Json,Select) {
	
	/**
	 * The lists are loaded in local storage, but when this  changed is necessary reload the list again.
	 */	
	function List(){}
	
	
	/**
	 * Actions Load List, this list is persistent in cache server
	 */
	List.actionsLoad = function(callback){	
			var xhr = new XMLHttpRequest();		
			xhr.onreadystatechange = function () {		       
				  if (this.readyState === Constants.READYSTATE_COMPLETE) {				  						
					  if(this.status === Constants.STATUS_OK){					  
						  callback(this);
					   } 					  
				  }		  
			}
			xhr.open('GET','/actionsLoad');
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=UTF-8');
			xhr.send();
			xhr.timeout = Constants.TIME_OUT;
			 xhr.ontimeout = function () {
				 console.error('Time out!!!');										
			}			 
	}	 
	
	
	/**
	 * entity States list,this list is persistent in local storage
	 */
	List.entityStates = function(callback){			
		
			var xhr = new XMLHttpRequest();		
			xhr.onreadystatechange = function () {		       
				  if (this.readyState === Constants.READYSTATE_COMPLETE) {				  						
					  if(this.status === Constants.STATUS_OK){									  
						  callback(this);					
					   } 					  
				  }		  
			}
			xhr.open('GET','/entityStates');
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=UTF-8');
			xhr.send();
			xhr.timeout = Constants.TIME_OUT;
			 xhr.ontimeout = function () {
				 console.error('Time out!!!');										
			}			 
	} 		 
	
	
	
	
	return List;
	
});