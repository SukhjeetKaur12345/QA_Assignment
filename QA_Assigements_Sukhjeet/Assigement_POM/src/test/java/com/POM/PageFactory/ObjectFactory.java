package com.POM.PageFactory;

import com.POM.pages.HomePage;

public class ObjectFactory {
	private HomePage homep;

	public HomePage getHomeData() {
		if(homep==null) {
			homep = new HomePage();
		}
		return homep;
	}

}
