/*
 * ! Play Nav Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Play Nav yonax73@gmail.com
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
 * Version 0.1: 13-August-2014 Created on : 13-August-2014 Author : Yonatan Alexis
 * Quintero Rodriguez
 * ========================================================================
 */

define(['./Play'], function(Play) {
	
	
	function Nav(){}
	
	
	
	/**
	 * Toogle Nav
	 */
     Nav.toogle = function(){
  	  
	   	   var menuToggle = Play.getId('menu-toggle');	   
		   var nav = Play.getId('nav');
		   var content = Play.getId('content');
	    	  
		   var descriptionMenu = Play.getClasses('description-menu');
		   
		   var clsFullNav = 'col-xs-3 col-sm-3 col-md-2';
		   var clsFullContent = 'col-xs-9 col-sm-9 col-md-10';
		   var clsMinNav = 'col-xs-3 col-sm-1 col-md-1';
		   var clsMinContent = 'col-xs-9 col-sm-11 col-md-11';	
		   var descriptionMenuHide =  'description-menu hidden-xs hidden';
		   var descriptionMenuShow =  'description-menu hidden-xs';
		   
		   var n = descriptionMenu.length;	
		   
		   var open = true;
 	  
		   menuToggle.onclick = function(){	  
			   
			   if(open){
				   
				    for ( var i = n-1; i > -1; i--) {			
				    	
				    	Play.addClass(descriptionMenu[i],descriptionMenuHide);
					}
				    
				    Play.addClass(nav,clsMinNav);
				    Play.addClass(content,clsMinContent);
				    open = false;
				   
			   } else {
				   
				    for ( var i = n-1; i > -1; i--) {			
				    	
				    	Play.addClass(descriptionMenu[i],descriptionMenuShow);
					}	
				    
				    Play.addClass(nav,clsFullNav);
				    Play.addClass(content,clsFullContent);
				    open = true;
			   }		   
			   
		   }
  }




 /**
  * Toogle Resources
  */
   Nav.toogleResources = function(){
 	 
 	  var lstResources = Play.getId('lstResources');
 	  
 	  var clsSubListHidden = 'sub-list hidden';
 	  var clsSubList = 'sub-list';
 	  
 	  var open = false;	    	  
 	 
 	 lstResources.onclick = function(){
 		 
 		 var subList =  this.parentNode.childNodes[3];
 		 
 		 if(open){
 			 
 			 Play.addClass(subList,clsSubListHidden);
 			 open = false;
 			 
 		 } else {
 			 
 			 Play.addClass(subList,clsSubList);
 			 open = true;	    			 
 		 }	    		 
 	 }
  }	
   
   
   /**
    * Load data Nav
    */
   Nav.load() = function(){
	   
	   
   }
	
	
	return Nav;
	
});