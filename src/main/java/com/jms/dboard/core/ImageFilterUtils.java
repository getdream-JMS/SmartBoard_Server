package com.jms.dboard.core;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


public class ImageFilterUtils  extends ImageFilter {
	
	/**
	 *
	 * @param sourceImage
	 * @param amount recommended amount is between 1 and 10
	 * @return
	 */
	static public BufferedImage sharpen(BufferedImage sourceImage, int amount) {
	  BufferedImage resultImage =  new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), sourceImage.getType());
	  float sharpenAmmount = amount * 0.2f;
	  float[] data = new float[]{
	      0.0f, -sharpenAmmount, 0.0f,
	      -sharpenAmmount, 4f * sharpenAmmount + 1f, -sharpenAmmount,
	      0.0f, -sharpenAmmount, 0.0f
	  };
	  Kernel kernel = new Kernel(3, 3, data);
	  BufferedImageOp bufferedImageOp = new ConvolveOp(kernel);
	  bufferedImageOp.filter(sourceImage, resultImage);
	  return resultImage;
	}

	@Override
	public BufferedImage filter(BufferedImage image, BufferedImage output) {
		// TODO Auto-generated method stub
		return null;
	}
}
