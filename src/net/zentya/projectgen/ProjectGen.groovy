package net.zentya.projectgen

import net.zentya.projectgen.cooldown.Cooldown
import net.zentya.projectgen.listeners.OpenListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by nini7 on 08.05.2017.
 */
class ProjectGen extends JavaPlugin
{

    static def instance
    private def cooldown;

    static{
        instance = this
    }

    @Override
    def onEnable(){
        saveDefaultConfig()
        server.pluginManager.registerEvents(new OpenListener(), this)
        cooldown = new Cooldown()
    }

    @Override
    def onDisable(){
        saveConfig()
    }

    static ProjectGen getInstance(){
        return instance
    }
    Cooldown getCooldown(){
        return cooldown
    }
}
