package com.pridemc.games.pluginevents;

import com.pridemc.games.arena.ArenaManager;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JoinArena implements Listener{
	
	@EventHandler
	public void onPortalStep(PlayerMoveEvent event){
		
		Player newplayer = event.getPlayer();
		
		for(Integer i = 0; i <= 3; i++){
			
			if(newplayer.getLocation().add(0, -i, 0).getBlock().getType().equals(Material.SIGN_POST)){
				
				Sign sign = (Sign) newplayer.getLocation().add(0, -i, 0).getBlock().getState();
				
				if(sign.getLine(0).equalsIgnoreCase("[PridePort]")){
				
					String arenaName = sign.getLine(1); //This is the arena name right here
                    ArenaManager.addPlayerToArena(newplayer, arenaName);
						
				}
			}
		}
	}
}
