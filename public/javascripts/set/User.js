require.config({
	
	
	baseUrl : 'assets/javascripts',
	
	
	paths : {
		
	    Aes :       'play/yonaxtics/google/aes',	
		Constants : 'play/yonaxtics/Constants',
		Play :      'play/yonaxtics/Play',		
		Json :      'play/yonaxtics/Json',
		Nav :       'play/yonaxtics/Nav'	,
		Select :    'play/yonaxtics/Select',
		List :      'set/List',
		Notify:     'play/yonaxtics/Notify',
		FormOk:     'play/yonaxtics/FormOk'

			
	}
});

/*
 * ! User.js Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * User.js yonax73@gmail.com
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




requirejs(['Aes', 'Constants', 'Play','Json','Nav','Select','List','Notify','FormOk'],function(Aes,Constants, Play, Json,Nav,Select,List,Notify,FormOk ) {

	
	
/* ==================================================================================================================
 * REGION ATTRIBUTES
 * ===================================================================================================================*/
	var btnSave = Play.getId('btnSave');	
	var frmUser = Play.getId('frmUser');	
	var notify = new Notify(Play.getId('notify'));	
	var frmUserOk = new FormOk(frmUser);
	var selectPageHome= null;
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
	init();		

/* ==================================================================================================================
 * REGION LOAD
 * ===================================================================================================================
 */		
	function load(){
		Play.getRequest('/loadUser',function(xhr){							
			 fill(Json.parse(xhr.responseText))	
		});
     }	

	function fill(user){
		  if(user.picture != null){
			Play.getId('userPicture').src =  Play.base64Blob(user.picture.mime, user.picture.src);
		  }
		  Play.getId('txtNameUser').value = user.login.name;
		  Play.getId('txtRole').textContent = user.role.name;
		  Play.getId('txtEmail').textContent = user.login.email;
		  Play.getId('nameUser').textContent = user.login.name;
		  Play.getId('txtFirstName').value = user.name;
		  Play.getId('txtLastName').value = user.lastName;
		  Play.getId('txtDocument').value = user.document;
		  Play.getId('txtAddress').value = user.location.address.address;
		  Play.getId('txtPhone').value = user.location.phone.phone;
		  List.actionsLoad(function(xhr){
			  selectPageHome = new Select(Play.getId('selectPageHome'),Json.parse(xhr.responseText));
			  selectPageHome.init(user.defaultAction.id);	
		  });
	}
/* ==================================================================================================================
 * REGION UPLOAD PICTURE
 * ===================================================================================================================
 */		
   function uploadPicture(){	   
		  Play.getId('uploadImage').onclick = function(){			  
			  Play.getId('fileselect').click();
		  }		  
		  Play.getId('userPicture').onclick = function(){			  
			  Play.getId('fileselect').click();
		  }		  
		  Play.getId('fileselect').onchange = function(e){			  
			  handleFileSelect(e);
		  }
   }	
   function handleFileSelect(evt) {		  
		    var picture = evt.target.files[0];
		    var xhr = null;
		    var reader = null;		    
		    if(picture.type.match('image.*')){		    	
		    	if(picture.size <= 65535){		    		
					var formData = new FormData();
					formData.append('picture', picture);
					Play.sendPost(formData,'/uploadPicture',function(){
						  notify.wait('Upload picture	...');	
						  Play.getId('fileselect').disabled = true;
						},function(xhr){
							  Play.getId('fileselect').disabled = false;
							  if(xhr.responseText === Constants.SUCCESS_REQUEST){
							    	reader = new FileReader();
								    reader.onload = (function(theFile) {
							        return function(e) {
							          Play.getId('userPicture').src =  e.target.result;		         
							        };
							      })(picture);		      
							      reader.readAsDataURL(picture);								   
							      notify.success('The picture has been changed successfully!!!');	
							}else{
								notify.danger(xhr.responseText);
							}
						});		    		    		
		    	} else {		    		
		    		 notify.danger('The image can be maximun of 64 KB');
		    	}		    	
		    } else {		    	
		    	notify.danger('The file not is a image valid!');
		    }			    
	  }	  
 /* ==================================================================================================================
 * REGION SAVE
 * ===================================================================================================================
 */		  
	 function save(){	 
		 frmUser.onsubmit = function(e){
			 e.preventDefault();
			 if(FormOk.hasChanged()){
				 if(frmUserOk.isValid()){
					 Play.sendRequest(e.target,function(){
						  notify.wait('Loading...');	
						  btnSave.disabled = true; 
					 },function(xhr){	
						 btnSave.disabled = false;
						 if(xhr.responseText === Constants.SUCCESS_REQUEST){
							 notify.success('Your User has been saved successfully!');
						 }else{
							 notify.danger(xhr.responseText); 
						 }						
					 });
			 }		 
		 }else{
			 notify.warning('No changes to save!!!'); 
		 }				 
	}
}
/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */		
	function init(){
	  Nav.init();	 		  
	  load();  
	  uploadPicture();	
	  save();	  
	}
	

	  
	  
	  
	  
	  
	  
	  
	  
});
		  







