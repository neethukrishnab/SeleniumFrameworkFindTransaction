package demos;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utils.NGListeners.class)	

public class ListenersDemo {

	@Test
	public void toPass() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void toFail() {
		Assert.assertTrue(false);
	}
}