package com.epam.jwd.app;

import com.epam.jwd.model.Point;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class PointHolder{
	
	private static final Logger LOG = LogManager.getLogger(PointHolder.class);
	private Point [] points;

	public PointHolder(int length){
		points = new Point[length];
		for (int i = 0; i < length; i++) {
			points[i] = new Point(i, i*i);
		}
	}

	public void showPoints(){
		for(Point tmp: points){
			LOG.info(tmp);
		}
	}

	public static void main(String args[]){
		LOG.trace("Program star.");
		if(args.length < 1){
			LOG.error("Not enough argument!");
			System.exit(-1);
		}

		try{
			PointHolder ph = new PointHolder(Integer.parseInt(args[0]));
			ph.showPoints();
		}
		catch(NumberFormatException exc){
			LOG.error("Argument is not a number!");
			System.exit(-1);
		}
		LOG.trace("Program end.");
	}
}
