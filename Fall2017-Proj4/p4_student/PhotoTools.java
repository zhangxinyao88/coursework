package p4_student;

//import com.sun.prism.paint.Color;

import cmsc131PhotoLibrary.Photograph;
import cmsc131PhotoLibrary.Pixel;

/**
 * The methods in this class are to be implemented by you.  
 * This class starter file provides various static methods that take 
 * a photograph and produce a new one based on it, but with various 
 * modifications.
 * 
 * See the project description for details of method implementations.
 * 
 * @author PUT YOUR NAME HERE
 *
 */
public class PhotoTools {


	//THIS METHOD IS PROVIDED AS A STARTING POINT.  PLEASE READ THROUGH
	//  IT AND THINK ABOUT WHAT IT DOES AND WHY - IF YOU AREN'T SURE OF
	//  SOMETHING, ASK US IN OFFICE HOURS!
	public static Photograph copy(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for (int x=0; x<photo.getWd(); x++) {
			for (int y=0; y<photo.getHt(); y++) {
				newPhoto.setPixel(x, y, photo.getPixel(x, y));
			}
		}
		return newPhoto;
	}






	public static Photograph isolateColor(Photograph photo, int type) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for(int x = 0;x < photo.getWd(); x++)
		{
			for (int y = 0;y < photo.getHt();y++) {
				if (type == 0) {
					newPhoto.setPixel(x, y, new Pixel(photo.getPixel(x, y).getRed(),0,0));
				}
				else if(type == 1) {
				    newPhoto.setPixel(x, y, new Pixel(0,0,photo.getPixel(x, y).getBlue()));
				}
			}
		}
		return newPhoto;
	}





	public static Photograph makeGrayscale(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(),photo.getHt());
		for (int i = 0;i < photo.getWd();i++) {
			for (int j = 0;j < photo.getHt();j++) {
				int grayValue = (int)(photo.getPixel(i, j).getRed() * 0.2) +
			               (int)(photo.getPixel(i, j).getGreen() * 0.5) +
			               (int)(photo.getPixel(i, j).getBlue() * 0.3);
				newPhoto.setPixel(i, j,new Pixel(grayValue,grayValue,grayValue));
			}
		}
		return newPhoto;
	}



	public static Photograph makeArtistic(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for(int x = 0;x < photo.getWd(); x++)
		{
			for (int y = 0;y < photo.getHt();y++) {
				int sum = photo.getPixel(x, y).getRed()+photo.getPixel(x, y).getBlue()+photo.getPixel(x, y).getGreen();
				if (sum >= 0 && sum<=109) {
					newPhoto.setPixel(x, y,new Pixel(0,0,0));
				}
				else if(sum >109 && sum<= 218) {
					newPhoto.setPixel(x, y, new Pixel(42,42,42));
				}
				else if(sum >218 && sum <=327) {
					newPhoto.setPixel(x, y, new Pixel(85,85,85));
				}
				else if(sum > 327 && sum <= 436) {
					newPhoto.setPixel(x, y, new Pixel(127,127,127));
				}
				else if(sum >436 && sum <= 545) {
					newPhoto.setPixel(x, y, new Pixel(170,170,170));
				}
				else if(sum >545 && sum <=654) {
					newPhoto.setPixel(x, y, new Pixel(212,212,212));
				}
				else if(sum >654 && sum <= 765) {
					newPhoto.setPixel(x, y, new Pixel(255,255,255));
				}
			}
			
		}
		return newPhoto;
	}



	public static Photograph censorIt(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for(int i =0;i <photo.getWd();i+=10) {
			for(int j = 0;j<photo.getHt();j+=10) {
				int wd,ht;
				if(photo.getWd()-i<10) { 
					wd = photo.getWd();
				}else {
					wd = i + 10;
				}
				if(photo.getHt()-j<10) {
					ht = photo.getHt();
				}else {
					ht = j + 10;
				}
				int red,green,blue,count;
				red = green = blue = count = 0;
			for(int x = i;x < wd; x++) {
				for (int y = j;y < ht; y++) {
					red += photo.getPixel(x, y).getRed();
					green += photo.getPixel(x, y).getGreen();
					blue += photo.getPixel(x, y).getBlue();
					count ++;
				}
			}
			red /= count;
			green /= count;
			blue /= count;
			for (int x = i;x < wd; x++) {
				for(int y = j;y < ht; y++) {
					newPhoto.setPixel(x, y, new Pixel(red,green,blue));
				}
			}
			}
		}
		return newPhoto;
		
		


		//HINT: To deal with the edge cases, you'll want to 
		//      check you aren't going out of bounds in the
		//      middle of your nested nesting of loops...


	}
	/*public static Photograph split(Photograph photo, int wd, int ht) {
		if(photo.getWd()-wd < 10) {
	}*/


	public static Photograph stretched(Photograph photo, int type) {
		if(type == 0){		
			Photograph newPhoto = new Photograph(photo.getWd()*2, photo.getHt());
			for(int x = 0;x < photo.getWd(); x++)
			{
				for (int y = 0;y < photo.getHt();y++) {
					newPhoto.setPixel(2*x, y, photo.getPixel(x, y));
					newPhoto.setPixel(2*x+1, y, photo.getPixel(x, y));
				}
		}
			return newPhoto;
		}
		else if (type == 1) {
			Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt()*2);
			for(int x = 0;x < photo.getWd(); x++)
			{
				for (int y = 0;y < photo.getHt();y++) {
					newPhoto.setPixel(x, 2*y, photo.getPixel(x, y));
					newPhoto.setPixel(x, 2*y+1, photo.getPixel(x, y));
				}
			}
			return newPhoto;
		}
		return null;
	}



	public static Photograph mirrorIt(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for(int x = 0;x < photo.getWd(); x++)
		{
			for (int y = 0;y < photo.getHt();y++) {
				newPhoto.setPixel(x, y, photo.getPixel(photo.getWd()-1-x,y));
			}
		}
		return newPhoto;
	}



	public static Photograph makeDoubleWithMirror(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd()*2, photo.getHt());
		for (int x = 0;x < photo.getWd();x++) {
			for (int y =0; y <photo.getHt();y++) {
				newPhoto.setPixel(x, y, photo.getPixel(photo.getWd()-1-x,y));
			}
		}
		for(int x = photo.getWd();x < photo.getWd()*2;x++) {
			for (int y =0; y <photo.getHt();y++) {
				newPhoto.setPixel(x, y, photo.getPixel(x-photo.getWd(), y));
			}
		}
		return newPhoto;
	}

	public static Photograph rotated(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getHt(), photo.getWd());
		for (int x = 0;x < photo.getWd();x++) {
			for (int y =0; y <photo.getHt();y++) {
				newPhoto.setPixel(photo.getHt()-y-1, x, photo.getPixel(x, y));
			}
	}
		return newPhoto;
	}

	public static Photograph upsideDown(Photograph photo) {
		
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for (int x = 0;x < photo.getWd();x++) {
			for (int y =0; y <photo.getHt();y++) {
				newPhoto.setPixel(photo.getWd()-x-1, photo.getHt()-y-1, photo.getPixel(x, y));
			}
		}
		return newPhoto;
	
		
		
		//DON'T FORGET - THIS ONE SHOULD BE ONE LINE OF CODE!
		//  THIS CHALLENGE INVOLVES MORE THINKING THAN CODING!
	}



}
