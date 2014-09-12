/*
 * ! Play Tab Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play Tab yonax73@gmail.com
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
 * Version 0.1: 11-September-2014 Created on : 11-September-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */

define([], function() {	
	
	 Tab.tabItems;
	 Tab.tab;	 
     Tab.tabContentItems;    
     Tab.tabContent;
	
	function Tab(tabElement,tabContentElement){		
		Tab.tab = tabElement;
		Tab.tabContent = tabContentElement;
		Tab.init();
		return Tab;
	}	
	
	Tab.init = function(){	     
	   Tab.tabItems = Tab.tab.getElementsByTagName('li');
	   Tab.tabContentItems = Tab.tabContent.getElementsByTagName('li');
	   Tab.addEvent(Tab.tabItems[0]);
	}

	Tab.addEvent=function(tabItem){
        tabItem.onclick = function(){
        	Tab.clear(Tab.tabItems);
        	Tab.clear(Tab.tabContentItems);
        	this.classList.add('active');
        	document.getElementById(this.dataset.tab).classList.add('active');
        }
	}

	Tab.add = function(href,tabContentId,title,url,callBackFn){		
       if(Tab.exists(tabContentId)){
           Tab.getItem(tabContentId).onclick();
       }else{
	        var tabItem = document.createElement('li');
	        var tabContentItem = document.createElement('li');
			var a = document.createElement('a');
			Tab.clear(Tab.tabItems);
	        Tab.clear(Tab.tabContentItems);		 
			a.href=href;
			tabItem.dataset.tab = tabContentId;		
			a.textContent = title;		
			tabItem.appendChild(a);
			tabItem.classList.add('active');
			Tab.addEvent(tabItem);
			Tab.tab.appendChild(tabItem);
			tabContentItem.id = tabContentId;
			tabContentItem.classList.add('tab-pane');
			tabContentItem.classList.add('active');
			Tab.tabContent.appendChild(tabContentItem);
			var xhr = new XMLHttpRequest();		
			xhr.onreadystatechange = function () {		       
				  if (this.readyState === 4) {				  						
					  if(this.status === 200){									  
						 tabContentItem.innerHTML = this.responseText;	
						 callBackFn();				  
					   } 					  
				  }		  
			}
			xhr.open('GET',url);
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			xhr.send();		
			xhr.timeout = 10000;
			xhr.ontimeout = function () {
				 console.error('Time out!!!');									
			}
       }
	}

	Tab.exists= function(tabContentId){
		var result = false;
		var n = Tab.tabItems.length;
		var i = 0;		
		while(i<n && !result){
			result = (Tab.tabItems[i++].dataset.tab == tabContentId);
		}
		return result;		
	}

	Tab.getItem= function(tabContentId){		
		var n = Tab.tabItems.length;
		var i = 0;
		var flag = false;
		var item = null;
		while(!flag && i<n){
            var itemAux = Tab.tabItems[i++]; 
			if(itemAux.dataset.tab == tabContentId){
                 item = itemAux;
                 flag = true;
			}
		}
		return item;		
	}

	Tab.clear =  function(tabs){
		var n = tabs.length;
		var i = 0;
		var flag = false;
		while(!flag || i<n){
			var item = tabs[i++];
			if(item.classList.contains('active')){
				item.classList.remove('active');
				flag = true;
			}
		}
	}	
	
	return Tab;
	
});