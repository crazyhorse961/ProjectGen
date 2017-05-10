package net.zentya.projectgen.cooldown

import org.bukkit.entity.Player

/**
 * Created by nini7 on 10.05.2017.
 */
class Cooldown
{
    private Map<String, Long> map = new HashMap<>()

    def seconds = 0

    def hasCooldown(Player player){
        if(map.get(player.name()) < (System.currentTimeMillis() - seconds*1000))
            return false
        else
            return true
    }

    def activeCooldown(Player player, int seconds){
        this.seconds = seconds
        map.put(player, System.currentTimeMillis())
    }
}
