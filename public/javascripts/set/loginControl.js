//require.config({
//	baseUrl : 'assets/javascripts',
//	paths : {
//		jquery : 'plugins/jquery-1.11.1.min',
//		bootstrap : 'plugins/bootstrap.min',
//		bootstrapValidator : 'plugins/bootstrapValidator.min',
//		notify : 'plugins/notify.min'
//			
//	}
//});

/*
 * ! loginControl Copyright 2014 YonaxTics, Inc. Licensed under
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * ========================================================================
 * loginControl yonax73@gmail.com
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

function getId(id){
	
	return document.getElementById(id);
}

function appendClass(element,className){
	
	element.className +=' '+className;
}

function addClass(element,className){
	
	element.className =className;
}

function getClass(className){
	
	return document.getElementsByClassName(className)[0];
}

function addHtml(id,html){
	
	getId(id).innerHTML = html;
}

function serializeForm(form) {

	 
	  var elems = form.elements;
	  
	  var serialized = [], i, len = elems.length, str='';
	  
	  for(i = 0; i < len; i += 1) {
	  
	    var element = elems[i];
	    var type = element.type;
	    var name = element.name;
	    var value = element.value;
	    
	    switch(type) {
	    
	      case 'text':
	      case 'radio':
	      case 'checkbox':
	      case 'textarea':
	      case 'select-one':
	      case 'password':	  
	      
	      str = name + '=' + value;
	      
	      serialized.push(str);
	      
	      break;
	      
	      default:
	      
	      
	        break;
	        
	    }    
	  
	  }
	  
	  return serialized.join('&');

	}


var alertSuccess = 'alert alert-success alert-dismissible';
var alertInfo = 'alert alert-info alert-dismissible';
var alertWarning = 'alert alert-warning alert-dismissible';
var alertDanger = 'alert alert-danger alert-dismissible';

var hidden = 'hidden';
var show = 'show';

var alertSu = getId('alertSignUp');

addClass(alertSu,hidden);
addClass(alertSu,alertDanger);
appendClass(alertSu,show);

var btnClose = getClass('close');
btnClose.addEventListener('click', function(){addClass(alertSu,hidden);}, false);


getId('frmSignUp').onsubmit = function(e) {
	 
    e.preventDefault();   
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = alertContents;    
    xhr.open('POST','/createAccount');
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
    xhr.send(serializeForm(e.target));

    function alertContents (){

    	alertSu.innerHTML +='momemt please..';
     
          if (this.readyState === 4 && this.status === 200) {
            	
           	 
           	 alertSu.innerHTML = this.responseText;
          } 
    }
 
 
    



    

    
    
    
}


//requirejs(
//		[ 'jquery', 'bootstrap','bootstrapValidator','notify' ],
//		function($, bootstrap,bootstrapValidator) {
//
//			if (document.createStyleSheet) {
//				document
//						.createStyleSheet('assets/stylesheets/bootstrapValidator.min.css');
//			} else {
//				$('head')
//						.append(
//								$('<link rel="stylesheet" href="assets/stylesheets/bootstrapValidator.min.css" type="text/css" media="screen" />'));
//			}
//
//
//
//			$('#frmSignUp')
//					.bootstrapValidator(
//							{
//								message : 'This value is not valid',
//								fields : {
//									'txtName' : {
//										message : 'The name is not valid',
//										validators : {
//											notEmpty : {
//												message : 'The name is required and can\'t be empty'
//											},
//											stringLength : {
//												min : 6,
//												max : 40,
//												message : 'The name must be more than 6 and less than 30 characters long'
//											},
//											regexp : {
//												regexp : /^[a-zA-Z][a-zA-Z ]+$/,
//												message : 'The name can only consist of alphabetical, number, dot and underscore'
//											},
//											different : {
//												field : 'txtPassword',
//												message : 'The name and password can\'t be the same as each other'
//											}
//										}
//									},
//									txtEmail : {
//										validators : {
//											notEmpty : {
//												message : 'The email address is required and can\'t be empty'
//											},
//											emailAddress : {
//												message : 'The input is not a valid email address'
//											}
//										}
//									},
//									txtPassword : {
//										validators : {
//											notEmpty : {
//												message : 'The password is required and can\'t be empty'
//											},
//											identical : {
//												field : 'txtConfirmPassword',
//												message : 'The password and its confirm are not the same'
//											},
//											different : {
//												field : 'txtName',
//												message : 'The password can\'t be the same as username'
//											}
//										}
//									},
//									txtConfirmPassword : {
//										validators : {
//											notEmpty : {
//												message : 'The confirm password is required and can\'t be empty'
//											},
//											identical : {
//												field : 'txtPassword',
//												message : 'The password and its confirm are not the same'
//											},
//											different : {
//												field : 'txtName',
//												message : 'The password can\'t be the same as username'
//											}
//										}
//									},
//									cbxTerms:{
//						                  validators: {
//						                    choice: {
//						                        min: 1,
//						                        max: 1,
//						                        message: 'Please choose Terms and Conditions.'
//						                    }
//						                }
//									},
//								}
//							})
//					        .on('success.form.bv', function(e) {
//					            
//					            e.preventDefault();					          
//					            var $form = $(e.target);					          
//					            $.post('/createAccount',$form.serialize(), function( data ) {
//					            	     
//					            	
//					            	$('#frmSignUp').notify(data,{ position:'top center' });
//					            	
//					            	alert(data);
//					            	});					
//					        });
//
//		});
