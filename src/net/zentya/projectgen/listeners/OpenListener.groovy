package net.zentya.projectgen.listeners

import net.zentya.projectgen.ProjectGen
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

/**
 * Created by nini7 on 08.05.2017.
 */
class OpenListener implements Listener
{

    @EventHandler
    def onOpen(PlayerInteractEvent e){
        Player p = e.player
        if(e.clickedBlock != null && e.clickedBlock.type == Material.CHEST && e.action == Action.RIGHT_CLICK_BLOCK){
            String meta = e.clickedBlock.hasMetadata("generator") ? e.clickedBlock.getMetadata("generator") : ""
            if(!meta.empty){
                if(ProjectGen.instance.cooldown.hasCooldown(p)){
                    e.setCancelled(true)
                    p.sendMessage(ChatColor.RED + "You cannot open the generator! You have to wait some seconds.")
                    return
                }
                ProjectGen.instance.cooldown.activeCooldown(p, 3)
                return
            }
        }
    }
}
