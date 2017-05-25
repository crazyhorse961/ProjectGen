package net.zentya.projectgen

import com.google.common.io.Files
import net.zentya.projectgen.cooldown.Cooldown
import net.zentya.projectgen.listeners.OpenListener
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by nini7 on 08.05.2017.
 */
class ProjectGen extends JavaPlugin
{

    static def instance
    private def cooldown
    def storageFile
    def storage


    static{
        instance = this
    }

    @Override
    def onEnable(){
        saveDefaultConfig()
        server.pluginManager.registerEvents(new OpenListener(), this)
        cooldown = new Cooldown()
        createFiles()
    }

    @Override
    def onDisable(){
        saveConfig()
    }

    static ProjectGen getInstance(){
        return instance
    }
    def getCooldown(){
        return cooldown
    }

    def getStorage(){
        return storage
    }

    def createFiles(){
        storageFile = new File(dataFolder, "storage.yml")
        if(!storageFile.exists()){
            storageFile.createNewFile()
        }
        storage = YamlConfiguration.loadConfiguration(storageFile)
    }
}
