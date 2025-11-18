package com.homecenter.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.homecentre.Base_Test;

//import static com.base.homecenter.base_class.*;

public class ItestListener implements ITestListener {

	private static final Logger log=LogManager.getLogger(ItestListener.class);

	@Override
	public void onTestFailure(ITestResult result) {
		
		log.info("Failed Test Case Name Is:  "+result.getMethod().getMethodName());
		Base_Test.captureScreenshot(result.getMethod().getMethodName()+".png");	
		
	}

	
}
