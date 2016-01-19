

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
        Picture galaxy_1 = new Picture("galaxy.jpg");
        galaxy_1.mirrorVerticalRightToLeft();
        
        Picture galaxy_2 = new Picture("galaxy.jpg");
        galaxy_2.mirrorVertical();
        Pixel[] g2_pixels = galaxy_2.getPixels();
        /*
        for (int i = 0; i < g2_pixels.length; i++)
        {
            g2_pixels[i].setAlpha(50);
        }
        */
        
        galaxy_1.cropAndCopy(galaxy_2, 0, galaxy_2.getHeight()-1, 0, galaxy_2.getWidth()-1, 0, 0);
        
        galaxy_1.explore();
        
    }
}
