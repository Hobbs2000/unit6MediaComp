import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  
  /**
   * 
   */
  public void keepOnlyBlue()
  {
      Pixel[] pixels = this.getPixels();
      for (int i = 0; i < pixels.length; i++)
      {
          int newRed = 0;
          int newGreen = 0;
          int currentBlue = pixels[i].getBlue();
          pixels[i].setColor(new Color(newRed, newGreen, currentBlue));
      }
  }
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /**
   * 
   */
  public void mirrorVerticalRightToLeft()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int width = pixels[0].length;
      
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < width / 2; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[row][width - 1 - col];
              leftPixel.setColor(rightPixel.getColor());
          }
      }
  }
  
  
  /**
   * 
   */
  public void mirrorHorizontal()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel topPixel = null;
      Pixel bottomPixel = null;
      int height = pixels.length;
      
      for (int row = 0; row < height/2; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              topPixel = pixels[row][col];
              bottomPixel = pixels[height - 1 - row][col];
              bottomPixel.setColor(topPixel.getColor());
          }
      }
  }
  
  
  /**
   * 
   */
  public void mirrorHorizontalBottomToTop()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel topPixel = null;
      Pixel bottomPixel = null;
      int height = pixels.length;
      
      for (int row = 0; row < height/2; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              topPixel = pixels[row][col];
              bottomPixel = pixels[height - 1 - row][col];
              topPixel.setColor(bottomPixel.getColor());
          }
      }
  }
  
  
  public void mirrorDiagonal()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel rightPixel = null;
      Pixel leftPixel = null;
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              if (col < pixels.length)
              {
                  rightPixel = pixels[row][col];
                  leftPixel = pixels[col][row];
                  rightPixel.setColor(leftPixel.getColor());
              }
          }
      }
  }
  
  
  /**
   * 
   */
  public void dotFilter(int dot_seperation)
  {
      Pixel[][] pixels = this.getPixels2D();
      
      Pixel rightPixel = null;
      Pixel leftPixel = null;
      
      for (int row = 0; row < pixels.length; row+=dot_seperation)
      {
          for (int col = 0; col < pixels[row].length; col+=dot_seperation)
          {
              rightPixel = pixels[row][col];
              leftPixel = pixels[this.getHeight() - 1 - row][this.getWidth() - 1 - col];
              rightPixel.setColor(leftPixel.getColor());
          }
      }
  }
  
  
  /**
   * Brightens the image
   */
  public void brighten(double amount)
  {
      if (amount < 1)
      {
          amount = 1;
      }
      
      Pixel[] pixels = this.getPixels();
      for (int i = 0; i < pixels.length; i++)
      {
          int currentRed = pixels[i].getRed();
          int currentGreen = pixels[i].getGreen();
          int currentBlue = pixels[i].getBlue();

          //Brighten the whole image to make the details even more visible
          int newRed = (int)(currentRed * amount);
          int newGreen = (int)(currentGreen * amount);
          int newBlue = (int)(currentBlue * amount);
              
          if (newRed > 255)
            newRed = 255;
          if (newGreen > 255)
            newGreen = 255;
          if (newBlue > 255)
            newBlue = 255;
                
          pixels[i].setColor( new Color(newRed, newGreen, newBlue) );
      }
  }
  
  
  /**
   * 
   */
  public void verticalLineFilter(int line_seperation)
  {
      Pixel[][] pixels = this.getPixels2D();
      
      Pixel rightPixel = null;
      Pixel leftPixel = null;
      
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[row].length; col+=line_seperation)
          {
              rightPixel = pixels[row][col];
              leftPixel = pixels[this.getHeight() - 1 - row][this.getWidth() - 1 - col];
              rightPixel.setColor(Color.BLACK);
          }
      }
  }
  
  
  /**
   * 
   */
  public void horizontalLineFilter(int line_seperation)
  {
      Pixel[][] pixels = this.getPixels2D();
      
      Pixel rightPixel = null;
      Pixel leftPixel = null;
      
      for (int row = 0; row < pixels.length; row+=line_seperation)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              rightPixel = pixels[row][col];
              leftPixel = pixels[this.getHeight() - 1 - row][this.getWidth() - 1 - col];
              rightPixel.setColor(Color.BLACK);
          }
      }
  }
  
  
  /**
   * 
   */
  public void negate()
  {
      Pixel[] pixels = this.getPixels();
      for (int i = 0; i < pixels.length; i++)
      {
          int newRed = 255 - pixels[i].getRed();
          int newGreen = 255 - pixels[i].getGreen();
          int newBlue = 255 - pixels[i].getBlue();
          pixels[i].setColor(new Color(newRed, newGreen, newBlue));
      }
  }
  
  
  /**
   * 
   */
  public void grayScale()
  {
      Pixel[] pixels = this.getPixels();
      for (int i = 0; i < pixels.length; i++)
      {
          int average = (pixels[i].getRed() + pixels[i].getGreen() + pixels[i].getBlue()) / 3;
          pixels[i].setColor( new Color(average, average, average) );
      }
  }
  
  
  /**
   * 
   */
  public void flip(Picture original)
  {
      Pixel[][] changePixels = this.getPixels2D();
      Pixel[][] staticPixels = original.getPixels2D();
      int width = staticPixels[0].length;
      int height = staticPixels.length;
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      
      for (int row = 0; row < staticPixels.length; row++)
      {
          for (int col = 0; col < staticPixels[row].length; col++)
          {
              leftPixel = changePixels[row][col];
              rightPixel = staticPixels[row][width - col - 1];
              leftPixel.setColor(rightPixel.getColor());
          }
      }
  }
  
  
  /**
   * 
   */
  public void fixUnderWater()
  {
      Pixel[] pixels = this.getPixels();
      for (int i = 0; i < pixels.length; i++)
      {
          int currentRed = pixels[i].getRed();
          int currentGreen = pixels[i].getGreen();
          int currentBlue = pixels[i].getBlue();
          if (currentGreen > 100 || currentBlue > 100)
          {
              //Lower to the green and blue values to lower the underwater vision
              //And increase detail
              int newGreen = currentGreen - 100;
              int newBlue = currentBlue - 100;
              
              //Brighten the whole image to make the details even more visible
              int newRed = (int)(currentRed * 2);
              if (newRed > 255)
                newRed = 255;
              newGreen *= 2;
              if (newGreen > 255)
                newGreen = 255;
              newBlue *= 2;
              if (newBlue > 255)
                newBlue = 255;
                
              pixels[i].setColor( new Color(newRed, newGreen, newBlue) );
          } 
      }
  }
  
  
  /**
   * NOTE: The smaller the pixelSize the longer the pixelation process takes
   */
  public void pixelate(int pixelSize)
  {
      Pixel[][] pixels = this.getPixels2D();
      
      int blockSize = pixelSize;
     
      Color averageColor = null;
      for(int row = 0; row < pixels.length; row += blockSize)
      { 
          for (int col = 0; col < pixels[row].length; col += blockSize)
          {
              if (!((col + blockSize > pixels[0].length) || (row + blockSize > pixels.length)))
              {
                  averageColor = getAverageColor(row, col, row+blockSize, col+blockSize, pixels);
              }
              for (int row_2 = row; (row_2 < row + blockSize) && (row_2 < pixels.length); row_2++)
              {
                  for (int col_2 = col; (col_2 < col + blockSize) && (col_2 < pixels[0].length); col_2++)
                  {
                      pixels[row_2][col_2].setColor(averageColor);
                  }
              }
          }
      }
  }
  
  
  /**
   * 
   */
  public Color getAverageColor(int startRow, int startCol, int endRow, int endCol, Pixel[][] pixels)
  {
      //Pixel[][] pixels = this.getPixels2D();
      
      Color averageColor = null;
      int totalPixels = (endRow - startRow)*(endCol - startCol);
      
      int totalRed = 0;
      int averageRed = 0;
      int totalGreen = 0;
      int averageGreen = 0;
      int totalBlue = 0;
      int averageBlue = 0;
      
      for (int row = startRow; row < endRow; row++)
      {
          for (int col = startCol; col < endCol; col++)
          {
              totalRed += pixels[row][col].getRed();
              totalGreen += pixels[row][col].getGreen();
              totalBlue += pixels[row][col].getBlue();
          }
      }
      
      averageRed = totalRed / totalPixels;
      averageGreen = totalGreen / totalPixels;
      averageBlue = totalBlue / totalPixels;
      
      averageColor = new Color(averageRed, averageGreen, averageBlue);
      
      return averageColor;
  }
  
  
  /**
   * Meant to only be used in the cropAndCopy method
   * Go through all the pixels and sets any that are white to null
   */
  public Pixel[][] removeWhiteBackground(Pixel[][] pixels)
  {
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[row].length; col++)
          {
              if (pixels[row][col].getRed() > 200 &&
                  pixels[row][col].getGreen() > 200 &&
                  pixels[row][col].getBlue() > 200)
              {
                  pixels[row][col] = null;
              }
          }
      }
      
      return pixels;
  }
  
  /**
   * 
   */
  public void cropAndCopy(Picture sourcePicture, int startSourceRow, int endSourceRow,
  int startSourceCol, int endSourceCol, int startDestRow, int startDestCol, boolean removeWhite, double alpha)
  {
      Pixel[][] sourcePixels = sourcePicture.getPixels2D();
      if (removeWhite)
      {
          sourcePixels = removeWhiteBackground(sourcePixels);
      }
      
      Pixel[][] currentPicPixels = this.getPixels2D();
      int newRow = startDestRow;
      int newCol = startDestCol;
      for (int row = startSourceRow; row < endSourceRow; row++)
      {
          newRow++;
          for (int col = startSourceCol; col < endSourceCol; col++)
          {
              newCol++;
              
              if (sourcePixels[row][col] != null)
              {
                double transparency = alpha / 255;
                double baseRed = currentPicPixels[newRow][newCol].getRed();
                double overlayRed = sourcePixels[row][col].getRed();
                double baseGreen = currentPicPixels[newRow][newCol].getGreen();
                double overlayGreen = sourcePixels[row][col].getGreen();
                double baseBlue = currentPicPixels[newRow][newCol].getBlue();
                double overlayBlue = sourcePixels[row][col].getBlue();
             
                //Does the transparency calculations
                int newRed = (int)(transparency * overlayRed + ((1 - transparency) * baseRed));
                int newGreen = (int)(transparency * overlayGreen + ((1 - transparency) * baseGreen));
                int newBlue = (int)(transparency * overlayBlue + ((1 - transparency) * baseBlue));
              
                currentPicPixels[newRow][newCol].setColor(new Color(newRed, newGreen, newBlue));
              }
          }
          newCol = startDestCol;
      }
  } 
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
