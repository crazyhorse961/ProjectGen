package net.zentya.projectgen.commands

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
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
                        return true
                    case "delete":
                    return true
                    case "start":
                        return true

                }
            case 3:
                switch(args[0]){
                    case "create":
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
}
