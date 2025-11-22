package test_cases_package;

import org.testng.annotations.Test;

import com.homecenter.utils.Data_Provider;

public class datap {

	@Test(dataProvider = "Pincodes Data" , dataProviderClass = Data_Provider.class)
	public void pincodes(int pincode) {
		
		System.out.println(pincode);
	}
}
