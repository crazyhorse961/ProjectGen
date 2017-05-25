package net.zentya.projectgen.listeners

import net.zentya.projectgen.ProjectGen
import net.zentya.projectgen.generator.Generator
import net.zentya.projectgen.utils.ClickActions
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
class OpenListener implements Listener {

    @EventHandler
    def onOpen(PlayerInteractEvent e) {
        Player p = e.player
        if (e.clickedBlock != null && e.clickedBlock.type == Material.CHEST && e.action == Action.RIGHT_CLICK_BLOCK) {
            String meta = e.clickedBlock.hasMetadata("generator") ? e.clickedBlock.getMetadata("generator") : ""
            if (!meta.empty) {
                if (p.sneaking) {
                    e.setCancelled(true)
                    Generator gen = Generator.fromConfig(meta)
                    p.sendMessage(ChatColor.AQUA + "Generator is " + gen.active ? ChatColor.GREEN + "ACTIVE" : ChatColor.RED + "DISABLED")
                    ClickActions.instance.sendActionMessage(p, ChatColor.BLUE  + "Click me to stop the Generator", true, {
                        if(!p.hasPermission("pg.stop." + meta)){
                            p.sendMessage(ChatColor.RED + "You don't have the permission to stop the Generator")
                            return
                        }
                        gen.stop()
                        p.sendMessage(ChatColor.GREEN + "Generator stopped!")
                    })
                    ClickActions.instance.sendActionMessage(p, ChatColor.BLUE + "Click me to start the Generator", true, {
                        if(!p.hasPermission("pg.start." + meta)){
                            p.sendMessage(ChatColor.RED + "You don't have the permission to start the Generator")
                            return
                        }
                        gen.start()
                        p.sendMessage(ChatColor.GREEN + "Generator started!")
                    })
                    return
                } else {
                    if (ProjectGen.instance.cooldown.hasCooldown(p)) {
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
}

