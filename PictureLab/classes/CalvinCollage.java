

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
        galaxy_1.cropAndCopy(galaxy_2, 0, galaxy_2.getHeight()-1, 0, galaxy_2.getWidth()-1, 0, 150, 150);
        
        Picture butterfly = new Picture("blue_butterfly.jpg");
        butterfly.mirrorVertical();
        galaxy_1.cropAndCopy(butterfly, 0, butterfly.getHeight()-1, 0, butterfly.getWidth()-1, 130, 270, 100);
        
        galaxy_1.explore();
        
    }
}