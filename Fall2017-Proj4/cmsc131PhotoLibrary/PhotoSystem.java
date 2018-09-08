package cmsc131PhotoLibrary;

import javax.swing.JRadioButton;

import p4_student.PhotoTools;

/**
 * Manages photo editing system.  A dialog box is displayed which allows the
 * user to load an image from the local machine or using an Internet URL.  
 * 
 * Once the image is loaded, it is displayed and the user will be given several 
 * options for editing the image.  After displaying the edited image, the user
 * may choose to edit the image further, or to load a different image.
 * 
 * @author (c)2007 Fawzi Emad {with later changes by Evan Golub}
 */
public class PhotoSystem {

	static final int NUM_RADIO_BUTTONS = 12;

	/**
	 * Begin the application.
	 */
	public static void begin(String initialPhoto) {


		JRadioButton[] radioButtons = new JRadioButton[NUM_RADIO_BUTTONS];
		radioButtons[0] = new JRadioButton("Copy");
		radioButtons[2] = new JRadioButton("Red Only");
		radioButtons[4] = new JRadioButton("Blue Only"); 
		radioButtons[6] = new JRadioButton("Grayscale");
		radioButtons[8] = new JRadioButton("Artistic");
		radioButtons[10] = new JRadioButton("Censor It");
		
		radioButtons[1] = new JRadioButton("Horizontal Stretch");
		radioButtons[3] = new JRadioButton("Vertical Stretch");
		radioButtons[5] = new JRadioButton("Mirror It");
		radioButtons[7] = new JRadioButton("Me and My Mirror");
		radioButtons[9] = new JRadioButton("Rotated");
		radioButtons[11] = new JRadioButton("Upside Down");

		ImageSelectionBox isb = new ImageSelectionBox(initialPhoto);


		while(true) {
			isb.setVisible(true);
			try {
				synchronized(isb) {
					isb.wait();
					isb.setVisible(false);
				}
				Photograph masterPhoto = new Photograph(isb.getTextValue());
				Photograph displayedPhoto = new Photograph(isb.getTextValue());
				while(true) {
					synchronized(displayedPhoto) {
						int[] flag = new int[1]; //a hack for a mutable integer
						new PhotoFrame(flag, radioButtons, displayedPhoto, 
								isb.getTextValue(), 0, 0);
						displayedPhoto.wait();
						if (flag[0] == 1) {
							displayedPhoto = PhotoTools.copy(masterPhoto);
						}
						if (flag[0] == 2)
							break;

					}
					if (radioButtons[0].isSelected()) {
						displayedPhoto = PhotoTools.copy(displayedPhoto);
					} else if (radioButtons[2].isSelected()) {
						displayedPhoto = PhotoTools.isolateColor(displayedPhoto, 0);					
					} else if (radioButtons[4].isSelected()) {
						displayedPhoto = PhotoTools.isolateColor(displayedPhoto, 1);
					} else if (radioButtons[6].isSelected()) {
						displayedPhoto = PhotoTools.makeGrayscale(displayedPhoto);
					}  else if (radioButtons[8].isSelected()) {
						displayedPhoto = PhotoTools.makeArtistic(displayedPhoto);
					} else if (radioButtons[10].isSelected()) {
						displayedPhoto = PhotoTools.censorIt(displayedPhoto);
					}  else if (radioButtons[1].isSelected()) {
						displayedPhoto = PhotoTools.stretched(displayedPhoto, 0);
					} else if (radioButtons[3].isSelected()) {
						displayedPhoto = PhotoTools.stretched(displayedPhoto, 1);
					} else if (radioButtons[5].isSelected()) {
						displayedPhoto = PhotoTools.mirrorIt(displayedPhoto);
					} else if (radioButtons[7].isSelected()) {
						displayedPhoto = PhotoTools.makeDoubleWithMirror(displayedPhoto);
					} else if (radioButtons[9].isSelected()) {
						displayedPhoto = PhotoTools.rotated(displayedPhoto);
					} else if (radioButtons[11].isSelected()) {
						displayedPhoto = PhotoTools.upsideDown(displayedPhoto);
					} else {
						throw new RuntimeException("error -- no radio button selected");
					}
				}
			}
			catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		} 
	}

}
