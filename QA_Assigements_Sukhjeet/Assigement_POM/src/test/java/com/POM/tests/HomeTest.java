package com.POM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.testBase.TestBase;

public class HomeTest extends TestBase {
	String actualTitle;

	public HomeTest() {
		super();

	}

	@BeforeSuite
	public void launch() {
		intialization();
	}

	@Test(priority = 1)
	public void checkTitle() {
		actualTitle = obj.getHomeData().verifyTitle();
		Assert.assertEquals(actualTitle, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}

	@Test(priority = 2)
	public void verifyTheSearchBar() {
		obj.getHomeData().verifySearchBarShouldBeAvailable();
	}

	@Test(priority = 3)
	public void verifyAfterSearchedKeyword() throws InterruptedException {
		obj.getHomeData().addKeywordForSearchAndclickButton();
	}

	@Test(priority = 4)
	public void verifyTheRelavantProduct() throws InterruptedException {
		obj.getHomeData().chechRelevantProduct();
	}

	@Test(priority = 5)
	public void clickOnRandomLinkOfProduct() throws InterruptedException {
		obj.getHomeData().clickOnProduct();
	}

	@Test(priority = 6)
	public void verifyTheLinkOpenInNewTab() throws InterruptedException {
		obj.getHomeData().verifyLinkOpenAnotherTab();
	}


}

