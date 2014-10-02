/*
 * ========================================================================
 * Play Play yonax73@gmail.com
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
/* ========================================================================
 * Version 0.1: 28-june-2014 
 * Created on : 28-june-2014
 * Author     : Yonatan Alexis Quintero Rodriguez
 * ========================================================================
 */

	define(['./Constants'], function(Constants) {

	
	function Play() {}
	
	/**
	 * @param element
	 * @return element id
	 */
	Play.getId = function(element) {
		
		return document.getElementById(element);
	}

	/**
	 * @param element
	 * @param className
	 */
	Play.addClass = function(element, className) {

		element.className = className;
	}

	/**
	 * @param element
	 * @param className
	 */
	Play.appendClass = function(element, className) {

		element.classList.add(className);
	}

	/**
	 * 
	 * @param className
	 * @returns element of class
	 */
	Play.getClass = function(className) {
		
	        
			return document.getElementsByClassName(className)[0];			
	
	
	}
	
	/**
	 * 
	 * @param className
	 * @returns array de element of class
	 */
	Play.getClasses = function(className) {

		return document.getElementsByClassName(className);
	}
	

	/**
	 * @param element
	 * @param html
	 */
	Play.addHtml = function(element, html) {

		element.innerHTML = html;
	}
	
	
	/**
	 * @param element
	 * @param html
	 */
	Play.addText = function(element, text) {

		document.getElementById(element).textContent = text;
	}

	/**
	 * @param form
	 * @returns serialize data form
	 */
	Play.serialize = function(form) {
		var elems = form.elements;
		var serialized = [], i, len = elems.length;
		for (i = 0; i < len; i += 1) {
			var element = elems[i];
			var type = element.type;			
			var value = element.value;
			var name = element.name;			
			if(name !== null || name !== undefined || name !== ""){
				switch (type) {
				case 'text':
				case 'radio':
				case 'checkbox':					            
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
				case 'hidden':	
					serialized.push(Play.encd(name,value));
					break;
				default:
					break;
				}
			}
		}
		return serialized.join('&');
	}
	
	
	
	Play.padString = function(source){
		
	    var paddingChar = ' ';
	    var size = 16;
	    var x = source.length % size;
	    var padLength = size - x;
	    
	    for (var i = 0; i < padLength; i++) source += paddingChar;
	    
	    return source;
	}
	
	

	Play.enc = function(plainText){

		return CryptoJS.AES.encrypt(Play.padString(plainText), CryptoJS.enc.Latin1.parse(Constants.KEY), { iv:  CryptoJS.enc.Latin1.parse(Constants.IV), padding: CryptoJS.pad.NoPadding, mode: CryptoJS.mode.CBC});
		
	}
	
	
	
	Play.dec = function(cipher){
		
		return CryptoJS.AES.decrypt( cipher, CryptoJS.enc.Latin1.parse(Constants.KEY), {iv: CryptoJS.enc.Latin1.parse(Constants.IV),mode: CryptoJS.mode.CBC,padding: CryptoJS.pad.NoPadding} ).toString( CryptoJS.enc.Utf8 );		
	}
	
	
	
	Play.encd = function(name,value){		
			
		return name+'='+(''+Play.enc(value)).replace(/=/g,'?');
	}	
	
	Play.getHeightPx = function(){
		
		var body = document.body
		var html = document.documentElement;
		
		return  (Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight) +'px');
	}
	
	Play.base64Blob = function(mime,src){		
		return 'data:'+mime+';base64,'+ src;
	}

    
    Play.printForm = function(form){
    	var elems = form.elements;
		var  i, len = elems.length;
		for (i = 0; i < len; i += 1) {		
			var element = elems[i];
			console.info('element:');
			console.info(element);
			console.info('type: '+element.type);			
			console.info('value: '+element.value);			
			console.log('------*-------');
       }
    }

    Play.filterToggle = function(){
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
    
    Play.serializeToJson = function(form){
    	var elems = form.elements;	
		var data = {};
		var i=0;		
		var len = elems.length;				
		for (i = 0; i < len; i ++) {
			var element = elems[i];
			var type = element.type;			
			var value = element.value;
			var name = element.name;			
			if(name !== null && name !== undefined && name !== ""){
				switch (type) {
				case 'text':
				case 'radio':
				case 'checkbox':					            
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
				case 'hidden':					
					data[name] = value;
					break;				
				}
			}
		}
		return data;
    } 
    
    Play.sendPost = function(data,action,onReady,onBeforeSend){
		 var xhr = new XMLHttpRequest();								 
		 xhr.onreadystatechange = function () {
			 onBeforeSend();
			 if(this.readyState ===  Constants.READYSTATE_COMPLETE){
				 if(this.status === Constants.STATUS_OK){
					 onReady(this);
				 }else{					
					 document.body.innerHTML = this.responseText;  
				 }
			 }                 
		 }
		 xhr.open("POST",action);			 		 
		 xhr.send(data);	
		 xhr.timeout = Constants.TIME_OUT;
		 xhr.ontimeout = function () {
			 console.error("Timeout!");
			 document.body.innerHTML = this.responseText;  										
		}
  }
    
    Play.sendRequest = function(form,onBeforeSend,onReady){
		 var xhr = new XMLHttpRequest();								 
		 xhr.onreadystatechange = function () {
			 onBeforeSend();
			 if(this.readyState ===  Constants.READYSTATE_COMPLETE){
				 if(this.status === Constants.STATUS_OK){
					 onReady(this);
				 }else{
					 document.body.innerHTML = this.responseText;					
				 }
			 }                 
		 }
		 xhr.open(form.method,form.action);
		 xhr.setRequestHeader("Content-Type","text/plain");		 		 
		 xhr.send(Play.enc(JSON.stringify(Play.serializeToJson(form))).toString());	
		 xhr.timeout = Constants.TIME_OUT;
		 xhr.ontimeout = function () {
			 console.error("Timeout!");
			 document.body.innerHTML = this.responseText;  									
		}
    }
    
    Play.getRequest = function(action,onReady){
		 var xhr = new XMLHttpRequest();								 
		 xhr.onreadystatechange = function () {	
			 if(this.readyState ===  Constants.READYSTATE_COMPLETE){
				 if(this.status === Constants.STATUS_OK){
					 onReady(this);
				 }else{					
					 document.body.innerHTML = this.responseText;  
				 }
			 }                 
		 }		 
		 xhr.open('GET',action);
		 xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");		 		 
		 xhr.send();	
		 xhr.timeout = Constants.TIME_OUT;
		 xhr.ontimeout = function () {
			 console.error("Timeout!");
			 document.body.innerHTML = this.responseText;  										
		}
   }

	return Play;
});
