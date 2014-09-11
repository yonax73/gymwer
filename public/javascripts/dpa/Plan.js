require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :      'play/yonaxtics/Nav',
		List :      'set/List',	
		Select :    'play/yonaxtics/Select'

			
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




requirejs(['Aes', 'Constants', 'Play','Json','Nav','List','Select'],function(Aes,Constants, Play, Json,Nav,List,Select ) {	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/
	var frmFilters = Play.getId('frmFilters');	
	var selectStatus= null;
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
	if(Play.ready()){		
		init();		
	}	
/* ==================================================================================================================
 * REGION FILTERS
 * ===================================================================================================================
 */	
	
	function filters(){
		var filters = Play.getId('filters'); 

		var pnlFilter = Play.getId('pnlFilter');
		filters.onclick = function(){			
		if(pnlFilter.classList.contains('hidden')){
			pnlFilter.classList.remove('hidden');
			}else{
				pnlFilter.classList.add('hidden');
			}
		}
	}
	
	
/* ==================================================================================================================
 * REGION NEW PLAN
 * ===================================================================================================================
 */	
	
	
	function addEvent(){
		var tabs = Play.getId('tabs').getElementsByTagName('li');
	    var n = tabs.length;
		for (var i = 0; i < n; i++) {
		      tabs[i].onclick = function(){
		    	  clear();
		    	  clearTabsContent();
		    	  this.classList.add('active');
		    	  Play.getId(this.dataset.tab).classList.add('active');  
		      }		
		}
	}
	
	function clear(){
		var tabs = Play.getId('tabs').getElementsByTagName('li');
	    var n = tabs.length;
	    var i =0;
	    var flag = false;
	    while(!flag || i<n){
	    	var tab = tabs[i++];
	    	if(tab.classList.contains('active')){
	             tab.classList.remove('active');
	             flag = true;
	    	}	    	
	    }
	}
	
	function clearTabsContent(){
		var tabs = Play.getId('tab-content').getElementsByTagName('li');
	    var n = tabs.length;
	    var i =0;
	    var flag = false;
	    while(!flag || i<n){
	    	var tab = tabs[i++];
	    	if(tab.classList.contains('active')){
	             tab.classList.remove('active');
	             flag = true;
	    	}	    	
	    }
	}
	
	function createTab(){
		Play.getId('create').onclick = function(){
               addTab();
		}
	}
	
	function addTab(){
		var tabs = Play.getId('tabs').getElementsByTagName('li');
		var tab = document.createElement('li');
		var a = document.createElement('a');
		 clear();
		a.href='#0';
		tab.dataset.tab = 0;
		a.textContent = 'New Plan';		
		tab.appendChild(a);
		tab.classList.add('active');
	    tab.onclick = function(){
    	  clear();
    	  clearTabsContent();	
    	  this.classList.add('active');
    	  Play.getId(this.dataset.tab).classList.add('active');    	  
	    }	    
	    Play.getId('tabs').appendChild(tab);
	    var tabContent = Play.getId('tab-content');
	    var tabsContent = tabContent.getElementsByTagName('li');	    
	    clearTabsContent();		
		var newTabContent = document.createElement('li');
		newTabContent.id="0";
		newTabContent.classList.add('tab-pane');
		newTabContent.classList.add('active');
		tabContent.appendChild(newTabContent);
	    var xhr= new XMLHttpRequest();
	    xhr.open('GET', '/plan');
	    xhr.onreadystatechange= function() {
	        if (this.readyState!==4) return;
	        if (this.status!==200) return; // or whatever error handling you want
	        newTabContent.innerHTML= this.responseText;
	    };
	    xhr.send();
	}
	
	function tabInit(){
		
		createTab();
		addEvent();
		
		

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
						selectStatus = new Select(Play.getId('selectStatus'),List.entityStates());
						selectStatus.init(Constants.ENTYTY_STATES_ALL);	
				  }else {
					  
		               //error message   									  
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
		  loadList();
		  load();
		  Nav.init();
		  filters();
		  tabInit();
	}
	
	
	
	
	

	
	
	
});





