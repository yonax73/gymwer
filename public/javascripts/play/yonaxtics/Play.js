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

		element.className += ' ' + className;
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
	 * @param element
	 * @param html
	 */
	Play.addHtml = function(element, html) {

		element.innerHTML = html;
	}

	/**
	 * @param form
	 * @returns serialize data form
	 */
	Play.serialize = function(form) {

		var elems = form.elements;

		var serialized = [], i, len = elems.length, str = '';

		for (i = 0; i < len; i += 1) {

			var element = elems[i];
			var type = element.type;
			var name = element.name;
			var value = element.value;

			switch (type) {

			case 'text':
			case 'radio':
			case 'checkbox':
			case 'textarea':
			case 'select-one':
			case 'password':

				str =  name + '=' + value;

				serialized.push(str);

				break;

			default:
				break;

			}
		}

		return serialized.join('&');
	}

	return Play;

});
