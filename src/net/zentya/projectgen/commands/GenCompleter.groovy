package net.zentya.projectgen.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil

/**
 * Created by nini7 on 24.05.2017.
 */
class GenCompleter implements TabCompleter
{

    public static final def ARR = ["delete","stop","create","start"]
    @Override
    List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> toReturn = new ArrayList<>()
        StringUtil.copyPartialMatches(args[0], ARR, toReturn)
        return Collections.sort(toReturn)
    }
}
