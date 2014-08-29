/*
 * ! Play FormOk Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play FormOk yonax73@gmail.com
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
 * Version 0.1: 29-August-2014 Created on : 29-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */

define([], function() {	

    FormOk.element;
    FormOk.inputs;
    FormOk.length;
    FormOk.msgRequired;
    FormOk.msgFullName;
    FormOk.msgEmail;
    FormOk.msgEquals;
    FormOk.msgCheck;
    FormOk.msgAccept;
    FormOk.hasSuccess;
    FormOk.hasError;
    FormOk.result;
    
	function FormOk(element){
		
		FormOk.element = element;
		FormOk.inputs = element.getElementsByTagName('input');
		FormOk.length = FormOk.inputs.length;
		FormOk.result = false;
		FormOk.msgRequired = 'This field is required and can\'t be empty!!!';
		FormOk.msgFullName = 'This field is not a valid name!!!';
		FormOk.msgEmail = 'This field is not a valid email address!!!';
		FormOk.msgEquals = 'This field and field confirm are not the same!!!';
		FormOk.msgCheck = 'Plase check!!!;'
		FormOk.msgAccept = 'Please accept!!!'
		FormOk.hasSuccess = 'has-success';
		FormOk.hasError = 'has-error';
	
		return FormOk;
	}
	

	
	
	FormOk.init = function(){
		
		for (var i = 0; i < FormOk.length; i++) {			
			var input = FormOk.inputs[i];		
			var small = document.createElement('small');
			small.className='hidden';        	
        	input.parentNode.appendChild(small);   
			if(input.dataset.blur ==='true'){				
				input.onblur = function(){					
					FormOk.validate(this);
				}
			}			
			if(input.dataset.keyup ==='true'){				
				input.onblur = function(){					
					FormOk.validate(this);
				}
			}			
		}		
	}
	
	FormOk.validate = function(input){
		
      switch (input.type) {
      
          case 'text':
    	  case 'search':
    	  case 'email':
    	  case 'url':
    	  case 'tel':
    	  case 'number':
    	  case 'range':
    	  case 'date':
    	  case 'month':
    	  case 'week':
    	  case 'time':
    	  case 'datetime':
    	  case 'datetime-local':
    	  case 'color':
    	  case 'textarea':
    	  case 'password':
    	  case 'select':
    		  
    		  if(input.dataset.required==='true') return  FormOk.isEmpty(input);
    		  if(input.dataset.fullName==='true') return  FormOk.isFullName(input);
    		  if(input.dataset.email==='true')    return  FormOk.isEmail(input);
    		  //pdte is equals
    		  
		
		               break;
		
		 case 'radio':
		 case 'checkbox':			
			
                       break;

		default:
			break;
	}
		
	}
	
	FormOk.isFullName = function(input){		
		if (input.value.match(/^[a-zA-Z][a-zA-Z ]+$/)) return FormOk.success(input);
		return FormOk.error(input,FormOk.msgFullName);	         
	}
	
	FormOk.isEmail = function(input){
		if (input.value.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))return FormOk.success(input);
		return FormOk.error(input, FormOk.msgEmail);			
	}
	
	FormOk.isEmpty = function(input){
		if (input.value.match(/^\S+$|[^\s]+$/))return FormOk.error(input, FormOk.msgRequired);
		return FormOk.success(input);
	}
	
	
	FormOk.isEquals = function(input,input1){
		if(input.value === input1.value)return FormOk.success(input) & FormOk.success(input1);
		return FormOk.error(input, FormOk.msgEquals) & FormOk.error(input1, FormOk.msgCheck); 
	}
	
	FormOk.isChecked = function(input){
		if(input.checked) {
			FormOk.hiddeMessage(input);
			return true
		}
		return FormOk.error(input, FormOk.msgAccept); 
	}
	
	
	
    FormOk.showMessage = function(input,message){    	
        var small = input.nextSibling;
        small.textContent = message;       
        small.className = 'show text-danger';
    }    
    
    
    FormOk.hiddeMessage = function(input){    	
           input.nextSibling.className='hidden';        
    }
    
    
    FormOk.success = function(input){
		if(!input.parentNode.parentNode.classList.contains(FormOk.hasSuccess)){
			input.parentNode.parentNode.classList.add(FormOk.hasSuccess);				
		}
		if(input.parentNode.parentNode.classList.contains(FormOk.hasError)){
			input.parentNode.parentNode.classList.remove(FormOk.hasError);				
		}
		FormOk.hiddeMessage(input);
		return true;
    }
        
        
    FormOk.error = function(input,message){
		if(input.parentNode.parentNode.classList.contains(FormOk.hasSuccess)){
			input.parentNode.parentNode.classList.remove(FormOk.hasSuccess);				
		}
		if(!input.parentNode.parentNode.classList.contains(FormOk.hasError)){
			input.parentNode.parentNode.classList.add(FormOk.hasError);				
		}
		FormOk.showMessage(input,message);
		return false;
   }
	
	
	
	

	
	
   
	
	return FormOk;
	
});