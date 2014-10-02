/*
 * ! Play Select Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play Select yonax73@gmail.com
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

define([], function() {


	JSPlay.Select = function(element,items){
		
		 var element= element;
		 var items= items;	 
	     var span= null;    
	     var input= null;
	     var inputHidden= null;
	     var i= null;     
	     var ul= null;
	     var oldItemLi= null;
	     var currentItemLi= null;     
	     var disabled= false;
	    


		this.init = function(option){	  
	        element.classList.add('select');
		    element.classList.add('background');
			this.create();
			this.fill();
			if(option !== undefined){
			    this.selectItem(option);			
			}		
		}	
		
		this.create =function(){
			
		    span = 	document.createElement('span');
		    input = document.createElement('input');	     
		    ul=  document.createElement('ul');	
		    i = document.createElement('i');	    	     
		    input.type = 'text';	 
		    input.readOnly  = true;	     
		    input.className = 'form-control'
		    i.className = 'fa fa-chevron-circle-down';	     
		    input.onchange =function(){return true;}	
		    span.appendChild(input);
		    span.appendChild(i);
		    var self = this;
		    span.onclick = function(){				 
		    	self.toggle();
		    	currentItemLi.focus();
			 }	     
		     span.onkeydown = function checkKey(e) {
		         e = e || window.event;
		         if (e.keyCode === 9) {
		        	  e.preventDefault();
		        	  self.toggle();
		        	  currentItemLi.focus();
		         }
		     }	     
		     ul.classList.add('select-list');
		      ul.classList.add('hidden');
		      inputHidden = document.createElement('input'); 
		      element.appendChild( inputHidden); 	
		      inputHidden.type ='hidden';
		     if( element.dataset.name!==undefined &&  element.dataset.name!==null){           
	            inputHidden.name =  element.dataset.name;           
		     }	     
		      element.appendChild( span);
		      element.appendChild( ul);	 
		     
			
		}
		
		
		
		this.fill = function(){
			
			var n =  items.length;		
			for (var i = 0; i < n; i++) {			
				  var item =  items[i];
				  var li = document.createElement('li');				
				  li.textContent= item.value;			  
				  li.tabIndex = i;
				  li.dataset.option = item.option;
				  var self = this;
				  li.onclick = function(){					  	
					  self.changeValue(this);			  	  
			     }
				 li.onkeydown = function checkKey(e) {
			         e = e || window.event;
			         if (e.keyCode === 13) {
			        	  e.preventDefault();
			        	  self.changeValue(this);
			         }
			     }			  
				   ul.appendChild(li);			
			}		
		}
		
		
		this.selectItem = function(option){
			
			  var itemsLi =  ul.getElementsByTagName('li');
			  var n = itemsLi.length;		  
			  
			  if(n > 0){			 
				  var flag = true;
				  var i = 0;	
				  do{				  
					  var item = itemsLi[i++];			  
					  if(item.dataset.option == option){					  
						   currentItemLi = item;
						  flag = false;					  
					  }				  
				  }while(flag || i < n);			  
			  }
			  
			  input.value =   currentItemLi.textContent;
			 
			  input.dataset.option =  currentItemLi.dataset.option;
			  inputHidden.value=  currentItemLi.dataset.option;
			  currentItemLi.focus();
			  currentItemLi.classList.add('selected');
		}
		
		
		
	    this.toggle = function(){   	 
	    	 if(!disabled){
	    		 ul.classList.toggle('hidden');
		    }  	 
	   }

	   this.changeValue = function(li){
	       oldItemLi =  currentItemLi;
		   currentItemLi = li;
		   input.value = li.textContent;				  
		   input.dataset.option = li.dataset.option;
		   inputHidden.value= li.dataset.option;
		   input.onchange();	
		  this.toggle();	
		  li.classList.add('selected');	
		   oldItemLi.classList.remove('selected');	
	   }
	    
	   this.addItem = function(option,value){
	     	 var li = document.createElement('li');
	     	 var self = this;
			  li.textContent= value;			  
			  li.tabIndex =   items.length+1;
			  li.dataset.option = option;
			  li.onclick = function(){					  	
				  self.changeValue(this);			  	  
		     }
			 li.onkeydown = function checkKey(e) {
		         e = e || window.event;
		         if (e.keyCode === 13) {
		        	  e.preventDefault();
		        	  self.changeValue(this);	
		         }
		     }			  
			   ul.appendChild(li);			
	    }
	    
	   this.getItem = function(){
		   
		   return {value:input.value, option:input.dataset.option};
	   } 

	   
	   this.getValue = function(){
		   
		   return input.value;
	   }
	   
	   
	   this.getOption = function(){
		   
		   return  input.dataset.option;
	   } 
		
	   this.setDisabled = function(_disabled){
		    disabled = _disabled;
		   
		   if( disabled){
			    element.classList.remove('background');		
			    element.classList.add('disabled');	
		   }else {
			    element.classList.remove('disabled');		   	
			    element.classList.add('background');	
		   }
	   }
		
		
		
		
		
	}
	
});