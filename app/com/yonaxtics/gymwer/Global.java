package com.yonaxtics.gymwer;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

import java.lang.reflect.Method;

import com.yonaxtics.gymwer.sec.Filter;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;


public class Global extends GlobalSettings {	
	
	private static final String AUTHENTICATED = "Authenticated";
	
    public void onStart(Application app) {  
        Logger.info("Application has started");
    }    
    
    public void onStop(Application app) {    
        Logger.info("Application shutdown...");
    }	
    
    public Promise<Result> onBadRequest(RequestHeader request, String error) {
    	Logger.error("Bad Request uri: "+request.uri());
    	Logger.error("Bad Request Error: "+error);
        return Promise.<Result>pure(badRequest("Don't try to hack the URI!"));
    }
    
    public Promise<Result> onHandlerNotFound(RequestHeader request) {
    	Logger.error("Not found Page: "+request.uri());
        return Promise.<Result>pure(notFound(
            views.html.sec.error.notFoundPage.render()
        ));
    }
    
    public Promise<Result> onError(RequestHeader request, Throwable t) {
    	Logger.error(t.getMessage(),t);
        return Promise.<Result>pure(internalServerError(        		  
            views.html.sec.error.errorPage.render()        
        ));
    }
    
    public Promise<Result> onUnauthorized(RequestHeader request,String cause) {
    	 Logger.error("Unauthorized by " +cause);
    	 return Promise.<Result>pure(play.mvc.Results.unauthorized(        		  
    	            views.html.sec.error.unauthorized.render()      
    	 ));
    }
    
    @SuppressWarnings("rawtypes")
	public Action onRequest(Request request, Method actionMethod) {  
        if(Filter.filter_action(actionMethod)){
        	final String result = Filter.authorized_request(actionMethod); 
        	if(result.equals(AUTHENTICATED)){
        		return super.onRequest(request, actionMethod);	
        	}else{
           	 return new Action.Simple() {
                    @Override
                    public Promise<Result> call(play.mvc.Http.Context ctx) throws Throwable {
                        return onUnauthorized(request,result);
                    }
                };
        	}        	
        }else {
        	return super.onRequest(request, actionMethod);	
        }        
    }


    




}
