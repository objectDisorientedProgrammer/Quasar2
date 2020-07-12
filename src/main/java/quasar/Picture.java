/*
 * Created: May 28, 2013
 * 
    The MIT License (MIT)
    
    Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
 */

package quasar;

public class Picture extends Data
{
    private String path;
    private String photographer;
    private int imageWidth;
    private int imageHeight;
    
    /**
     * Width and height set to -1 to indicate absence.
     * @param path - location of file
     */
    public Picture(String path)
    {
        this(path, " ", -1, -1);
    }
    
    /**
     * Width and height set to -1 to indicate absence.
     * @param path - location of file
     * @param photographer
     */
    public Picture(String path, String photographer)
    {
        this(path, photographer, -1, -1);
    }
    
    /**
     * 
     * @param path - location of file
     * @param w - image width
     * @param h - image height
     */
    public Picture(String path, int w, int h)
    {
        this(path, " ", w, h);
    }
    
    /**
     * 
     * @param path - location of file
     * @param photographer
     * @param w - image width
     * @param h - image height
     */
    public Picture(String path, String photographer, int w, int h)
    {
        super();
        this.path = path;
        this.photographer = photographer;
        this.imageWidth = w;
        this.imageHeight = h;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPublisher() {
        return photographer;
    }

    public void setPublisher(String publisher) {
        this.photographer = publisher;
    }

    public int getWidth() {
        return imageWidth;
    }

    public void setWidth(int w) {
        if(w >= 0)
            this.imageWidth = w;
    }

    public int getHeight() {
        return imageHeight;
    }

    public void setHeight(int h) {
        if(h >= 0)
            this.imageHeight = h;
    }
    
    @Override
    public String toSaveString()
    {
        return super.toSaveString() + Quasar.sep +
                path + Quasar.sep + photographer + Quasar.sep + imageWidth + Quasar.sep + imageHeight;  
    }
}
