package com.edems;

import com.sun.imageio.plugins.bmp.BMPImageReader;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by edems on 2016.01.03..
 */
public class GUITest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( command.getName().equalsIgnoreCase("fire") ) {
            if( sender instanceof  Player )
                fireProj((Player) sender);

            return true;
        }
        else if( command.getName().equalsIgnoreCase("arrow") ) {
            if( sender instanceof  Player )
                arrowProj((Player) sender);

            return true;
        }
        else if( command.getName().equalsIgnoreCase("spawnbmp") ) {
            if( sender instanceof  Player )
                spawnBMP((Player) sender);

            return true;
        }

        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage(ChatColor.RED + "Welcome to the server");
    }

    private void fireProj(Player player) {
        //player.sendMessage(String.format("dir: %f %f %f", player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));

        player.launchProjectile(Fireball.class, player.getLocation().getDirection().multiply(2.5f));
        //player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2.5f));
    }

    private void arrowProj(Player player) {
        player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(5));
    }

    private void spawnBMP(Player player) {
        BufferedImage img;

        try {
            img = ImageIO.read(new File("image2.bmp"));
        } catch (IOException e) {
            getLogger().info("e: "+ e.getLocalizedMessage());
            return;
        }

        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 0; x < img.getWidth(); x++) {
                Block block = player
                        .getLocation()
                        .getBlock()
                        .getRelative(BlockFace.NORTH)
                        .getLocation()
                        .add(x, img.getHeight() - y, 0).getBlock();

                block.setType(Material.WOOL);
                block.setData(getDyecolor(new Color(img.getRGB(x, y))).getData());
            }
        }
    }

    private DyeColor getDyecolor(Color color) {
        if( color == null )
            return DyeColor.WHITE;

        if( color.equals(Color.BLACK) )
            return DyeColor.BLACK;
        else if( color.equals(Color.WHITE) )
            return DyeColor.WHITE;
        else if( color.equals(Color.RED) )
            return DyeColor.RED;
        else if( color.equals(Color.GREEN) )
            return DyeColor.GREEN;
        else if( color.equals(Color.BLUE) )
            return DyeColor.BLUE;
        else if( color.equals(Color.CYAN) )
            return DyeColor.CYAN;
        else if( color.equals(Color.GRAY) )
            return DyeColor.GRAY;
        else if(color.equals(Color.MAGENTA) )
            return DyeColor.MAGENTA;
        else if( color.equals(Color.YELLOW) )
            return DyeColor.YELLOW;
        else if( color.equals(Color.ORANGE) )
            return DyeColor.ORANGE;
        else if( color.equals(Color.PINK) )
            return DyeColor.PINK;
        else if( color.equals(Color.LIGHT_GRAY) )
            return DyeColor.SILVER;

        return DyeColor.WHITE;
    }
}
