require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :      'play/yonaxtics/Nav',
		List :      'set/List',	
		Select :    'play/yonaxtics/Select',
		Tab :    'play/yonaxtics/Tab'

			
	}
});

/*
 * ! Plan.js Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Plan.js yonax73@gmail.com
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




requirejs(['Aes', 'Constants', 'Play','Json','Nav','List','Select','Tab'],function(Aes,Constants, Play, Json,Nav,List,Select,Tab) {	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/
	var frmFilters = Play.getId('frmFilters');	
	var selectStatusFilter= null;
	var selectStatus = null;
	var tabs = null;
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
//	if(Play.ready()){		
//		init();		
//	}	
	init();	
/* ==================================================================================================================
 * REGION NAVEGATION TABS
 * ===================================================================================================================
 */	
   function navegationTabs(){
   	    tabs = new Tab(Play.getId('tabs'),Play.getId('tabs-content'));
   	    Play.getId('create').onclick = function(){
   	    	tabs.add('#newPlan',0,'New Plan','/plan',function(){
   	    		selectStatus =  new Select(Play.getId('selectStatus'),List.entityStates());
   	    		selectStatus.init(Constants.ENTYTY_STATES_ACTIVE);	
   	    	});   	    	
   	    }
   }

/* ==================================================================================================================
 * REGION FILTERS
 * ===================================================================================================================
 */		
	function filters(){          
          Play.filterToggle();
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
						selectStatusFilter = new Select(Play.getId('selectStatusFilter'),List.entityStates());						
						selectStatusFilter.init();							
						selectStatusFilter.addItem(Constants.ENTYTY_STATES_ALL,'ALL');
						selectStatusFilter.selectItem(Constants.ENTYTY_STATES_ALL);						
				     }else {								  
				       document.body.innerHTML = this.responseText; 
				   } 				  						
			  }
		}
		xhr.open('GET','/loadGym');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		//set time out	
     }
	
	function loadList(){	
		List.entityStates();
		
	}	
	

/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */	
	
	  function init(){
		  navegationTabs();
		  loadList();
		  load();
		  Nav.init();
		  filters();		
	}
	
	
	
	
	

	
	
	
});





