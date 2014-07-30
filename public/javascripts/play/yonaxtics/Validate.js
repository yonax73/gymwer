
/*
 * ========================================================================
 * Play Validate yonax73@gmail.com
 * ========================================================================
 * Copyright 2014 yonaxTics, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the 'License'); you may not
 * use Constants file except in compliance with the License. You may obtain a copy of
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

define(['./Play','./Constants'], function(Play,Constants) {
	
	
	
	function Validate(){}
	
	
	
	/**
	 * @param input
	 * @param msg
	 * @returns isValid
	 */
	Validate.fullName = function (input,message,value) {

		if (input.value.match(/^[a-zA-Z][a-zA-Z ]+$/)) {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_SUCCESS);
			Play.addClass(message, Constants.HIDDEN);
			return true;

		} else {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_ERROR);
			Play.addClass(message, Constants.TEXT_DANGER);
			Play.addHtml(message,value);
			return false;
		}
	}
	
	
	/**
	 * @param input
	 * @param msg
	 * @returns isValid
	 */
	Validate.email = function (input,message,value) {

		if (input.value.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_SUCCESS);
			Play.addClass(message, Constants.HIDDEN);
			return true;

		} else {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_ERROR);
			Play.addClass(message, Constants.TEXT_DANGER);
			Play.addHtml(message,value);
			return false;
		}
	}
	
	
	
	/**
	 * @param input
	 * @param msg
	 * @returns isValid
	 */
	Validate.empty = function (input,message,value) {

		if (input.value.match(/^\d*\.?\d+$/)) {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_SUCCESS);
			Play.addClass(message, Constants.HIDDEN);
			return true;

		} else {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_ERROR);
			Play.addClass(message, Constants.TEXT_DANGER);
			Play.addHtml(message,value);
			return false;
		}
	}
	
	
	
	/**
	 * @param input
	 * @param input1
	 * @param msg
	 * @returns isValid
	 */
	Validate.equals = function (input,input1,message,value) {

		if (input.value === input1.value) {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_SUCCESS);
			Play.addClass(message, Constants.HIDDEN);
			return true;

		} else {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_ERROR);
			Play.addClass(message, Constants.TEXT_DANGER);
			Play.addHtml(message,value);
			return false;
		}
	}
	

	
	/**
	 * @param input
	 * @param msg
	 * @returns isValid
	 */
	Validate.isChecked = function (input,message,value) {

		if (input.checked) {

			
			Play.addClass(message, Constants.HIDDEN);
			return true;

		} else {

			
			Play.addClass(message, Constants.TEXT_DANGER);
			Play.addHtml(message,value);
			return false;
		}
	}	
	
	
	return Validate;
	
	
});