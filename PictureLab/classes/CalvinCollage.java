

/**
 * Write a description of class CalvinCollage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CalvinCollage
{
    
    public static void main(String[] args)
    {
        Picture galaxy_1 = new Picture("SmallerGalaxy.jpg");
        galaxy_1.mirrorVerticalRightToLeft();
        
        Picture galaxy_2 = new Picture("galaxy_2.jpg");
        galaxy_2.mirrorVertical();
        Pixel[] g2_pixels = galaxy_2.getPixels();
        galaxy_1.cropAndCopy(galaxy_2, 0, galaxy_2.getHeight()-1, 0, galaxy_2.getWidth()-1, 0, 150, false, 150);
        
        Picture butterfly = new Picture("blue_butterfly.jpg");
        Pixel[] Bpixels = butterfly.getPixels();
        butterfly.mirrorVertical();
        galaxy_1.cropAndCopy(butterfly, 0, butterfly.getHeight()-1, 0, butterfly.getWidth()-1, 130, 270, true, 120);
        
        Picture neil = new Picture("scienceMan.jpg");
        galaxy_1.cropAndCopy(neil, 0, neil.getHeight()-1, 0, neil.getWidth()-1, 360, 0, false, 100);
        neil.flip(new Picture("scienceMan.jpg"));
        galaxy_1.cropAndCopy(neil, 0, neil.getHeight()-1, 0, neil.getWidth()-1, 360, galaxy_1.getWidth() - neil.getWidth(), false, 100);
        
        galaxy_1.brighten(1.3);
        //galaxy_1.horizontalLineFilter(2);
        
        galaxy_1.explore();
        
    }
}
