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

define(['./Play','./Json','./Constants'], function(Play,Json,Constants) {
	
	
	function Nav(){}
	
	
	
	
	Nav.init = function(){
		
		
		
		if(localStorage.getItem(Constants.LOCALSTORAGE_NAV_CONTACT) === null){
			
			 Nav.load();
			 
		} else {			
			  
			  Nav.create(JSON.parse(localStorage.getItem(Constants.LOCALSTORAGE_NAV_CONTACT)));		
		}
		
	}
	
	
	
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
		   
		   nav.style.height = Play.getHeightPx();
		   
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
    * Load data Nav
    */
   Nav.load = function(){
	   
		var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function () {		
		       
			  if (this.readyState === Constants.READYSTATE_COMPLETE) {
				  						
				  if(this.status === Constants.STATUS_OK){				 
					  
					  var contact = Json.parse(this.responseText);
					  localStorage.setItem(Constants.LOCALSTORAGE_NAV_CONTACT,JSON.stringify(contact));
					  Nav.create(contact);					   
					  
				  }else {
					  
		               //error message   									  
				  }
			  }
		}
		xhr.open('GET','/loadNav');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		//set time out	
   }
   
   
   
   
   Nav.create = function(contact){
	   
	   var gym = contact.gym;
		  var user = contact.user;
		  var permissions = user.role.permissions;
		  var n = permissions.length;
		  var i = 0;
		  var action = Play.getId('actions');	
		  
		  if(n > 0){
			  
			  var act = permissions[i++].action;
			  action.appendChild(Nav.item(act.ico,act.module.description,act.url));
			  
			  do{
				  
				  act = permissions[i++].action;
				  
				  if(act.module.rolId === Constants.MASTER_VALUE_MODULE_PARENT){								  
					  
					  action.appendChild(Nav.itemParent(act));								  
					  
				  }else {								
					  
					  action.appendChild(Nav.item(act.ico,act.module.description,act.url));
					  
				  }
				  
			  }while(i < n)
		  }
		  
		  action.appendChild(Nav.divider());
		  action.appendChild(Nav.itemLogOut('fa fa-sign-out fa-fw','Sign Out','/signOut'));
		  
		   Nav.toogle();	   
   }
   
   
   
   
   Nav.item = function(ico,description,url){
	   
		  var i = document.createElement('i');
		  var span = document.createElement('span');
		  var a = document.createElement('a');
		  var li = document.createElement('li');
		  
		  i.className = ico;		  
		  
		  span.className = 'description-menu hidden-xs';
		  span.textContent = description;
		  
		  a.href = url;		  
		  a.target ='_blank';
		  a.appendChild(i);
		  a.appendChild(span);	
		  
		  a.onclick = function(e){
			  
			  e.preventDefault();
			  li.className = 'active';
			  
			  if(localStorage.getItem(this.href)!= null){
				  
				  var win = JSON.parse(localStorage.getItem(this.href));
				  win.focus();
				  alert('this window is open');
				  
			  }else {
				  
				  var win = window.open(this.href, this.target);
				  localStorage.setItem(this.href,JSON.stringify(win));
				  win.focus();
			  }
			
		  }
		  	  
		  li.appendChild(a);
		  
		  return li	   
   }
   
   
   
   
   Nav.itemActive = function(ico,description,url){//quitar
	   
		  var i = document.createElement('i');
		  var span = document.createElement('span');
		  var a = document.createElement('a');
		  var li = document.createElement('li');
		  
		  i.className = ico;		  
		  
		  span.className = 'description-menu hidden-xs';
		  span.textContent = description;
		  
		  a.href = url;		  
		  a.target ='_blank';
		  a.appendChild(i);
		  a.appendChild(span);
		  		  
		  li.className = 'active';		  
		  li.appendChild(a);
		  
		  return li	   
}
   
   

   
   Nav.itemLogOut = function(ico,description,url){
	   
		  var i = document.createElement('i');
		  var span = document.createElement('span');
		  var a = document.createElement('a');
		  var li = document.createElement('li');
		  
		  i.className = ico;		  
		  
		  span.className = 'description-menu hidden-xs';
		  span.textContent = description;
		  
		  a.href = url;		  
		  a.appendChild(i);
		  a.appendChild(span);		  
		  
		  a.onclick = function(){
			  
			  localStorage.clear();		
			  sessionStorage.clear();
		  }
		  
		  li.appendChild(a);
		  
		  return li	   
}
   
   
   
   
   Nav.itemParent =  function(action){
	   
	  var li  = document.createElement('li');
	  var a = document.createElement('a');	  
	  var ul =  document.createElement('ul');
	  var i = document.createElement('i');
	  var span = document.createElement('span');
	  var n = action.module.children.length;
 	  var clsSubListHidden = 'sub-list hidden';
 	  var clsSubList = 'sub-list'; 	  
 	  var open = false;	 
	  	  
	  i.className = action.ico;		  
	  
	  span.className = 'description-menu hidden-xs';
	  span.textContent = action.module.description;
	  
	  a.href = '#';		  
	  a.appendChild(i);
	  a.appendChild(span);
	  
	 	 a.onclick = function(){
	 		

	 		 if(open){
	 			 
	 			 Play.addClass(ul,clsSubListHidden);
	 			 open = false;
	 			 
	 		 } else {
	 			 
	 			 Play.addClass(ul,clsSubList);
	 			 open = true;	    			 
	 		 }	    		 
	 	 }	  
	  
	  ul.className = clsSubListHidden;
	  
	  for(var i = 0; i < n; i++){
		  
		  var act =  action.module.children[i];		  
		  ul.appendChild(Nav.item(act.ico,act.module.description,act.url));		  
	  }
	  
	  li.appendChild(a);
	  li.appendChild(ul);
	  

	  return li;
	   
   }
   
   
   
   
   Nav.divider = function(){
	   
	   var li = document.createElement('li'); 
	   li.className = 'divider';
	   
	   return li;
   }
   
	
	
	return Nav;
	
});