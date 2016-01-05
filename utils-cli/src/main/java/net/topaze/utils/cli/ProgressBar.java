package net.topaze.utils.cli;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProgressBar{

    public static final int DEFAULT_MAX = 100;
    
    private int progress = 0;
    private int max = DEFAULT_MAX;
    
    public ProgressBar(int max) {
	this.max = max;
    }

    public boolean progress() {
	progress++;
	if(progress<max+1) {
	    DecimalFormat df = new DecimalFormat("000");

	    String data = "\r["; 
	    for(int i=0; i<progress-1; i++) {
		data = data +"=";
	    }
	    if(progress!=max) {
		data = data +">";
	    } else {
		data = data +"=";
	    }
	    for(int i=1; i<max+1-progress; i++) {
		data = data +" ";
	    }
	    data = data + "] " + df.format(100*progress/max) + " %" ;
	    try {
		System.out.write(data.getBytes());
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }

	    
	    return true;
	} else {
	    return false;
	}
    }

    public void reset() {
	progress = 0;    
    }
    
    public void fill() {
	progress = max;
    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}