/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package buildahouse;
public class MyHouse {
  public static void build_me() {
   int width;
   width = 3;
   int height;
   height = 50;
   for (int build=0; build < 20; build++) {	
     BuildAHouse.buildMyHouse(width, height);}
  }
}
