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
	
	
	 Select.element;
	 Select.items;	 
     Select.span;    
     Select.input;
     Select.i;     
     Select.ul;
     Select.oldItemLi;
     Select.currentItemLi;     
     Select.disabled;
    
	
	function Select(element,items){
		
		Select.element = element;
		Select.items = items;				
		Select.element.classList.add('select');
		Select.element.classList.add('background');		
		Select.disabled = false;		
		return Select;
	}
	
	
	
	
	Select.init = function(option){
	     
		Select.create();
		Select.fill();
		Select.selectItem(option);		
	}
	
	
	Select.create =function(){
		
	     Select.span = 	document.createElement('span');
	     Select.input = document.createElement('input');
	     Select.ul=  document.createElement('ul');	
	     Select.i = document.createElement('i');	     
	     Select.input.type = 'button';	     
	     Select.i.className = 'fa fa-chevron-circle-down';	     
	     
	     Select.span.appendChild(Select.input);
	     Select.span.appendChild(Select.i);	  
	     Select.span.onclick = function(){				 
	    	  Select.toggle();
	    	  Select.currentItemLi.focus();
		 }
	     Select.ul.classList.add('select-list');	          
	     Select.element.appendChild(Select.span);
	     Select.element.appendChild(Select.ul);	  	
		
	}
	
	
	
	Select.fill = function(){
		
		var n = Select.items.length;
		
		for (var i = 0; i < n; i++) {
			
			  var item = Select.items[i];
			  var li = document.createElement('li');
			  li.textContent= item.value;							
			  li.classList.add('hidden');  
			  li.tabIndex = i;
			  li.dataset.option = item.option;
			  li.onclick = function(){				
				  	
				  Select.oldItemLi = Select.currentItemLi;
				  Select.currentItemLi = this;
				  Select.input.value = this.textContent;
				  Select.input.dataset.option = this.dataset.option;
				  Select.toggle();	
				  this.classList.add('selected');	
				  Select.oldItemLi.classList.remove('selected');	
					 
		     }
			  
			  Select.ul.appendChild(li);
			
		}
		
	}
	
	
	Select.selectItem = function(option){
		
		  var itemsLi = Select.ul.getElementsByTagName('li');
		  var n = itemsLi.length;		  
		  
		  if(n > 0){			 
			  var flag = true;
			  var i = 0;	
			  do{				  
				  var item = itemsLi[i++];			  
				  if(item.dataset.option == option){					  
					  Select.currentItemLi = item;
					  flag = false;					  
				  }				  
			  }while(flag || i < n);			  
		  }
		  
		 Select.input.value =  Select.currentItemLi.textContent;
		 Select.input.dataset.option = Select.currentItemLi.dataset.option;
		 Select.currentItemLi.focus();
		 Select.currentItemLi.classList.add('selected');
	}
	
	
	
    Select.toggle = function(){   	 
   	 
    	 if(!Select.disabled){	    		 
	   		  var itemsLi = Select.ul.getElementsByTagName('li');
			  var n = itemsLi.length;				  
			  for (var i = 0; i < n; i++) itemsLi[i].classList.toggle('hidden');				  
	    }  	 
   }
    
    
    
   Select.getItem = function(){
	   
	   return {value:Select.input.value, option:Select.input.dataset.option};
   } 

   
   Select.getValue = function(){
	   
	   return Select.input.value;
   }
   
   
   Select.getOption = function(){
	   
	   return  Select.input.dataset.option;
   } 
	
   Select.setDisabled = function(disabled){
	   Select.disabled = disabled;
	   
	   if(Select.disabled){
		   Select.element.classList.remove('background');		
		   Select.element.classList.add('disabled');	
	   }else {
		   Select.element.classList.remove('disabled');		   	
		   Select.element.classList.add('background');	
	   }
   }
	
	
	
	
	return Select;
	
});