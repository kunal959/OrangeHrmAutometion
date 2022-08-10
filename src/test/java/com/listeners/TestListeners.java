package com.listeners;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener, ISuiteListener{
	public void onStart(ISuite suite)
	{
		System.out.println("this is onStart suite method....");
		String reportFolderPath = System.getProperty("user.dir") + "/ExtentReport";
		createFolder(reportFolderPath);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");
		String dateTime = formatter.format(LocalDateTime.now());
		String reportPath = "ExecutionReport_"+dateTime;
		createFolder(reportFolderPath+"/"+reportPath);
	}
	public void onFinish(ISuite suite)
	{
		System.out.println("this is onFinish suite method....");
	}
	public void onStart(ITestContext context)
	{
		System.out.println("this is onStart test method...."+context.getName());
	}
	public void onFinish(ITestContext context)
	{
		System.out.println("this is onFinish test method..."+context.getName());
	}
	public void onTestStart(ITestResult result)
	{
		System.out.println("this is onTest start method..."+result.getName());
	}
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("this is onTestSuccess method...."+result.getName());
	}
	public void onTestFailure(ITestResult result)
	{
		System.out.println("this is onTestFailure method...."+result.getName());
		System.out.println("failure resone  "+result.getThrowable());
	}
	public void onTestSkip(ITestResult result)
	{
		System.out.println("this is onTestSkip method....."+result.getName());
	}
	public void createFolder(String filePath)
	{
		File file = new File(filePath);
		if(!file.exists())
		{
			file.mkdir();
		}
	}

}
