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
 * ! Profile.js Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * Profile.js yonax73@gmail.com
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
	var frmProfile = Play.getId('frmProfile');	
	var notify = new Notify(Play.getId('notify'));	
	var frmProfileOk = new FormOk(frmProfile);
	var selectPageHome= null;
	var  profile = null;
/* ==================================================================================================================
 * REGION READY
 * ===================================================================================================================
 */	
	if(Play.ready()){		
		init();		
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
					  profile = Json.parse(this.responseText);
					  localStorage.setItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE,JSON.stringify(profile));
					  fill(profile);						
				   } 					  
			  }		  
		}
		xhr.open('GET','/loadProfile');
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xhr.send();		
		xhr.timeout = Constants.TIME_OUT;
		xhr.ontimeout = function () {
			 notify.danger('Time out!!!');									
		}	
     }	
	function loadList(){
		List.url();	
	}	
	function fill(profile){          
		  if(profile.picture != null){
			Play.getId('userPicture').src =  Play.base64Blob(profile.picture.mime, profile.picture.src);
		  }
		  Play.getId('txtNameUser').value = profile.user.name;
		  Play.getId('txtRole').textContent = profile.user.role.name;	
		  selectPageHome = new Select(Play.getId('selectPageHome'),List.url());
	      selectPageHome.init(profile.user.defaultAction.id);	
		  Play.getId('txtEmail').value = profile.user.email;
		  Play.getId('nameUser').textContent = profile.user.name;
		  Play.getId('txtFullName').value = profile.name;
		  Play.getId('txtDocument').value = profile.document;
		  Play.getId('txtAddress').value = profile.location.address.address;
		  Play.getId('txtPhone').value = profile.location.phone.phone;		
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
					    xhr = new XMLHttpRequest();						
						xhr.onreadystatechange = function () {	
							  notify.wait('Upload picture	...');	
							  Play.getId('fileselect').disabled = true;
							  if (this.readyState === Constants.READYSTATE_COMPLETE) {	
								  Play.getId('fileselect').disabled = false;
								  if(this.status === Constants.STATUS_OK && this.responseText === Constants.REQUEST_SUCCESS){									  
								    	reader = new FileReader();
									    reader.onload = (function(theFile) {
								        return function(e) {
								          Play.getId('userPicture').src =  e.target.result;		         
								        };
								      })(picture);		      
								      reader.readAsDataURL(picture);
								      localStorage.removeItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE);	
								      notify.success('The picture has been changed successfully!!!');	
								}									  
						  }							  
						}
						xhr.open('POST','/uploadPicture');				
						var formData = new FormData();
						formData.append('picture', picture);
						xhr.send(formData);
						xhr.timeout = Constants.TIME_OUT;
						xhr.ontimeout = function () {
							notify.danger('Time out!!!');	
							Play.getId('fileselect').disabled = false;
						}		    		
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
		 frmProfile.onsubmit = function(e){
			 e.preventDefault();
			 if(frmProfileOk.isValid()){	
				    var data = [
				        {name:'txtLocationId',value:profile.location.id},
				        {name:'txtUserId',value:profile.user.id},
				        {name:'txtDefaultActionId',value:selectPageHome.getOption()},
				        {name:'txtRoleId',value:profile.user.role.id},
				        {name:'txtPhoneId',value:profile.location.phone.id},
				        {name:'txtAddressId',value:profile.location.address.id}
				    ];				    
					var inputs = Play.appendInputHidden(data,e.target);					
					var xhr = new XMLHttpRequest();		
					xhr.onreadystatechange = function () {	
						  notify.wait('Loading...');	
						  btnSave.disabled = true;
						  if (this.readyState === Constants.READYSTATE_COMPLETE) {
							  Play.removeInputHidden(inputs);					
							  btnSave.disabled = false;
							  if(this.status === Constants.STATUS_OK  && this.responseText === Constants.REQUEST_SUCCESS){									  
								  localStorage.removeItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE);
								  notify.success('Your profile has been saved successfully!');								  
							   }else{
								   notify.danger(this.responseText); 
							   } 					  
						  }		  
					}
					xhr.open('POST','/saveProfile');
					xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
					xhr.send(Play.serialize(e.target));		
					xhr.timeout = Constants.TIME_OUT;
					xhr.ontimeout = function () {
						 notify.danger('Time out!!!');
						 btnSave.disabled = false;
					}					 
			 }			 
		 }			 
	}
/* ==================================================================================================================
 * REGION INIT
 * ===================================================================================================================
 */		
	function init(){		
	  loadList();
	  Nav.init();		
	  if(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE) == null){			  
		  load();
	  }else {
		  profile = JSON.parse(localStorage.getItem(Constants.LOCALSTORAGE_REQUEST_LOAD_PROFILE)); 
		  fill(profile);
	  }		  
	  uploadPicture();	
	  save();		  
	}
	

	  
	  
	  
	  
	  
	  
	  
	  
})
		  







