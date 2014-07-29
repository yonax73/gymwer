
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
	
	
	Validate.validateName = function (input,msg) {

		if (input.value.match(/^[a-zA-Z][a-zA-Z ]+$/)) {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_SUCCESS);
			Play.addClass(msgName, Constants.HIDDEN);

		} else {

			Play.addClass(input.parentNode.parentNode,Constants.HAS_ERROR);
			Play.addClass(msgName, Constants.HELP_TEXT_DANGER);
			Play.addHtml(msgName,msg);
		}
	}
	
	
	
	
	return Validate;
	
	
});