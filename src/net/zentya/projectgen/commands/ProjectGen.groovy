package net.zentya.projectgen.commands

import com.google.common.collect.Sets
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.zentya.projectgen.generator.Generator
import net.zentya.projectgen.generator.GeneratorManager

import org.bukkit.Location
import org.bukkit.block.Chest
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

import static org.bukkit.ChatColor.*



/**
 * Created by nini7 on 21.05.2017.
 */
class ProjectGen implements CommandExecutor
{
    @Override
    boolean onCommand(CommandSender cs, Command c, String label, String[] args){
        if(!(cs instanceof Player)){
            cs.sendMessage(RED + "You need to be a player to use ProjectGen's commands!")
            return true
        }
        Player player = cs
        switch (args.length){
            case 0:
                sendHelp(player)
                return true
            case 1:
                sendHelp(player)
                return true
            case 2:
                switch(args[0]){
                    case "stop":
                        if(!player.hasPermission("pg.stop." + args[1])){
                            player.sendMessage(RED + "You don't have the permission !")
                            return true
                        }
                        for(Generator gen : GeneratorManager.getInstance().getGenerators()){
                            if(args[1].equals(gen.name)){
                                gen.stop()
                                player.sendMessage(GREEN + "You stopped the " + gen.name + " Generator")
                                return true
                            }
                        }
                        player.sendMessage(RED + "No generators found for the name " + gen.name)
                        return true
                    case "delete":
                        if(!player.hasPermission("pg.delete." + args[1])){
                            player.sendMessage(RED + "You don't have the permission ! ")
                            return true
                        }
                        for(Generator gen : GeneratorManager.getInstance().getGenerators()){
                            if(args[1].equals(gen.name)){
                                GeneratorManager.getInstance().deleteGenerator(gen)
                                ((Location) gen.location).getBlock().breakNaturally()
                                player.sendMessage(GREEN  + "Generator deleted with success!")
                                return true
                            }
                        }
                        player.sendMessage(RED + "No generators found for the name " + gen.name)
                    return true
                    case "start":
                        if(!player.hasPermission("pg.start." + args[1])){
                            player.sendMessage(RED + "You don't have the permission !")
                            return true
                        }
                        for(Generator gen : GeneratorManager.getInstance().getGenerators()){
                            if(args[1].equals(gen.name)){
                                gen.start()
                                player.sendMessage(GREEN + "You started the " + gen.name + " Generator")
                                return true
                            }
                        }
                        player.sendMessage(RED + "No generators found for the name " + gen.name)
                        return true

                }
            case 3:
                switch(args[0]){
                    case "create":
                        String name = args[1]
                        int seconds = Integer.valueOf(args[2])
                        Location loc = player.getTargetBlock(Sets.newHashSet(), 100)
                        if(!(loc.block.state instanceof Chest)){
                            player.sendMessage(RED + "You aren't facing a chest!")
                            return true
                        }
                        Chest chest = (Chest) loc.block.state
                        if(isEmpty(chest.inventory)){
                            player.sendMessage(RED + "Chest is empty !")
                            return true
                        }
                        ItemStack item = chest.inventory.getItem(chest.inventory.firstEmpty() - 1)
                        GeneratorManager.getInstance().createGenerator(name, seconds, loc, item)
                        player.sendMessage(GREEN + "Created the generator " + name + " !")
                        return true
                }
        }
        return false
    }

    protected def sendHelp(Player player){
        TextComponent lines = new TextComponent("--+-------------------+--")
        lines.setBold(true)
        lines.setStrikethrough(true)
        lines.setColor(GREEN)
        player.spigot().sendMessage(tc)
        TextComponent create = new TextComponent("/projectgen create <name> <seconds>")
        create.setColor(BLUE)
        create.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/projectgen create <name> <seconds>"))
        create.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, "Create a chest generator. Make sure you're pointing to a chest and it is not empty"))
        player.spigot().sendMessage(create)
        TextComponent delete = new TextComponent("/projectgen delete <name>")
        delete.setColor(BLUE)
        delete.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/proejctgen delete <name>"))
        delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, "Deletes a chest generator."))
        player.spigot().sendMessage(delete)
        TextComponent stop = new TextComponent("/projectgen stop <name>")
        stop.setColor(BLUE)
        stop.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/projectgen stop <name>"))
        stop.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, "Stops a chest generator. You are able to restart it."))
        player.spigot().sendMessage(stop)
        TextComponent start = new TextComponent("/projectgen start <name>")
        start.setColor(BLUE)
        start.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/projectgen start <name>"))
        start.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, "Starts a chest generator."))
        player.spigot().sendMessage(start)
        player.spigot().sendMessage(lines)
    }

    private boolean isEmpty(Inventory inventory){
        for(ItemStack item : inventory.getContents())
        {
            if(item != null)
                return false
        }
        return true
    }
}
