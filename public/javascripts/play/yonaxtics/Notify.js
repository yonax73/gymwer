/*
 * ! Play Notify Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play Notify yonax73@gmail.com
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
 * Version 0.1: 28-August-2014 Created on : 28-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */

define([], function() {	

    Notify.element;
    Notify.button;
    Notify.span;
    Notify.p;
    Notify.i;
    Notify.strong;
    
	function Notify(element){
		
		Notify.element = element;	
		Notify.create();
		return Notify;
	}
	
	
    Notify.create = function(){
    	
    	Notify.close();
    	Notify.button = document.createElement('button');
    	Notify.button.className = 'close';
    	Notify.button.type = 'button'; 
    	Notify.button.onclick = function(){Notify.close();}
    	Notify.span = document.createElement('span');
    	Notify.span.textContent ='×';    	
    	Notify.button.appendChild(Notify.span);    	
    	Notify.element.appendChild(Notify.button);
    	Notify.p = document.createElement('p');
    	Notify.p.className = 'text-center';
    	Notify.i = document.createElement('i');    	
    	Notify.p.appendChild(Notify.i);
    	Notify.strong = document.createElement('strong');    	
    	Notify.p.appendChild(Notify.strong);
    	Notify.element.appendChild(Notify.p);
    }
    
    
    Notify.close = function(){    	
    	Notify.element.className = 'hidden';
    }
	
	
    Notify.success = function(message){    	
    	Notify.i.className = 'fa fa-smile-o fa-lg pull-left';
    	Notify.element.className = 'alert alert-success alert-dismissible';
    	Notify.strong.textContent = message;    	
    }
    
    
    Notify.info = function(message){    	
    	Notify.i.className = 'fa fa-info fa-lg pull-left';
    	Notify.element.className = 'alert alert-info alert-dismissible';
    	Notify.strong.textContent = message;  
    } 
    
    
    Notify.warning = function(message){
    	Notify.i.className = 'fa fa-exclamation-triangle fa-lg pull-left';
    	Notify.element.className = 'alert alert-warning alert-dismissible';
    	Notify.strong.textContent = message;  
    }
    
    
    Notify.danger = function(message){
    	Notify.i.className = 'fa fa-frown-o fa-lg pull-left';
    	Notify.element.className = 'alert alert-danger alert-dismissible';
    	Notify.strong.textContent = message; 
    }
    
    
    Notify.wait = function(message){
    	Notify.i.className = 'fa fa-circle-o-notch fa-spin fa-lg pull-left';
    	Notify.element.className = 'alert alert-info alert-dismissible';
    	Notify.strong.textContent = message;  
    }	
	
	return Notify;
	
});