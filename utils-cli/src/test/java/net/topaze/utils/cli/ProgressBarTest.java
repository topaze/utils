package net.topaze.utils.cli;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ProgressBarTest {

    private ProgressBar progressBar;

    @Before
    public void prepare() {
	progressBar = new ProgressBar(ProgressBar.DEFAULT_MAX);
    }

    @Test
    public void doTest() {	
	for(int i=10;i<100;i=i+10) {
	    progressBar.setMax(i);
	    progressBar.reset();
	    while(progressBar.progress()) {
		try {
		    TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {		
		    e.printStackTrace();
		}
	    }
	    System.out.println();
	}
	Assert.assertTrue(true);
    }

}