package utils;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.internal.IResultListener;

public class TestNgListeners implements IResultListener{

	@Override
	public void onTestStart(ITestResult result) {
		Log.info("==============================================================");
		Log.info("Started test case: " + result.getMethod().getMethodName());
		String timestamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		Log.info("Start time: " + timestamp);
		Log.info("Browser: " + Driver.runningBrowser.get());
		Log.info("==============================================================");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("==============================================================");
		Log.info("Passed test case: " + result.getMethod().getMethodName());
		Log.info("Browser: " + Driver.runningBrowser.get());
		String timestamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		Log.info("End time: " + timestamp);
		Log.info(String.valueOf(Thread.currentThread().getId()));
		Log.info("==============================================================");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.error("==============================================================");
		Log.error("Failed test case: " + result.getMethod().getMethodName());
		Log.info("Browser: " + Driver.runningBrowser.get());
		String timestamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		Log.error("Failed time: " + timestamp);
		Log.error(result.getThrowable());
		Log.error("==============================================================");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Log.info("==============================================================");
		Log.info("Skipped test case: " + result.getMethod().getMethodName());
		String timestamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		Log.info("Skip time: " + timestamp);
		Log.info("Browser: " + Driver.runningBrowser.get());
		Log.info("==============================================================");
	}

	
	
}
