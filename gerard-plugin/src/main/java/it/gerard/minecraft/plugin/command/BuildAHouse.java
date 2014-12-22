/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package it.gerard.minecraft.plugin.command;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

//
// This is a bit convoluted, but it will let you call the "buildMyHouse"
// function without having to see or read this code.
// You might want to pull out the "makeCube" function to use
// in another plugin; it's a triple-nested for-loop
// to make a 3D block.
//

public class BuildAHouse implements CommandExecution {
  public static Logger log = Logger.getLogger("Minecraft");
  public void onEnable() {
    log.info("[BuildAHouse] Start up.");
  }
  public void onReload() {
    log.info("[BuildAHouse] Server reloaded.");
  }
  public void onDisable() {
    log.info("[BuildAHouse] Server stopping.");
  }
  
  public static Location origin = null;
  public static boolean firstHouse = true;
  
  // Create a 3D cube, offset from the saved "origin"
  private static void makeCube(int offsetX, int offsetY, int offsetZ,
                            int width, int height, Material what) {
    int i, j, k;
    Location loc = new Location(origin.getWorld(), 0,0,0);
    
    // Base is i X j, k goes up for height
    for (i=0; i< width; i++) {
      for (j=0; j<width; j++) {
        for (k=0; k< height; k++) {
          loc.setX(origin.getX() + offsetX + i);
          loc.setZ(origin.getZ() + offsetZ + j);
          loc.setY(origin.getY() + offsetY + k);
          origin.getWorld().getBlockAt(loc).setType(what); 
        }
      }
    }
  }
  
  // Called by reader's code
  // Minimum of 5 X 5
  @SuppressWarnings("deprecation")
  public static void buildMyHouse(int width, int height) {
   
    // Floor the dimensions at no less than 5 X 5 
    if (width < 5) {
      width = 5;
    }
    if (height < 5) {
      height = 5;
    }
    
    if (firstHouse) {
      // Center the first house on the player
      origin.setY(origin.getY() - 1);
      origin.setZ(origin.getZ() - (width/2));  
      origin.setX(origin.getX() - (width/2));
      firstHouse = false;
    }
    
    // Set the whole area to diamond
    makeCube(0,0,0,width, height, Material.GLASS);
    // Set the inside of the cube to air
    makeCube(1,1,1,width-2, height-2, Material.AIR);
    
    // Pop a door in one wall
    Location door = new Location(origin.getWorld(), 
      origin.getX()+(width/2), origin.getY(), origin.getZ());
    
    // The door is two high, with a torch over the door
    door.setY(door.getY() +1);
    Block bottom = origin.getWorld().getBlockAt(door);
    door.setY(door.getY() +1);
    Block top = origin.getWorld().getBlockAt(door);
    door.setY(door.getY() +1);
    door.setZ(door.getZ() +1);
    Block over = origin.getWorld().getBlockAt(door);
    
    // Magic values to establish top and bottom of door
    top.setData((byte)0x8);  
    bottom.setData((byte)0x4);   
    // And normal material constants    
    top.setType(Material.WOODEN_DOOR);
    bottom.setType(Material.WOODEN_DOOR);
    over.setType(Material.TORCH);
 
    // Move over to make next house if called in a loop.
    origin.setX(origin.getX() + width);
  }

  @Override
  public boolean go(CommandSender sender, Command command, Iterable<String> args) {
    if (sender instanceof Player) {
      Player me = (Player)sender;
      // Put your code after this line:
      origin = me.getLocation();
      firstHouse = true;
      MyHouse.build_me();
      // ...and finish your code before this line.
      return true;
    } else {
      return false;
    }
  }
}
