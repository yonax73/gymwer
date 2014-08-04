package com.yonaxtics.gymwer;
import play.Application;
import play.GlobalSettings;
import play.Logger;


public class Global extends GlobalSettings {


	
    public void onStart(Application app) {
  
        Logger.info("Application has started");
    }

    
    
    
    public void onStop(Application app) {    	
 
        Logger.info("Application shutdown...");
    }
    
    
    
    
//    public Promise<Result> onError(RequestHeader request, Throwable t) {
//        return Promise.<Result>pure(internalServerError(
//            
//        		
//        ));
//    }
	
}
