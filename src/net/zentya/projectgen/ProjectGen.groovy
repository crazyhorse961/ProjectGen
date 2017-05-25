package net.zentya.projectgen

import net.zentya.projectgen.cooldown.Cooldown
import net.zentya.projectgen.generator.GeneratorManager
import net.zentya.projectgen.listeners.OpenListener
import net.zentya.projectgen.runnables.ChestCheck
import net.zentya.projectgen.utils.ClickActions
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
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
    void onEnable(){
        saveDefaultConfig()
        server.pluginManager.registerEvents(new OpenListener(), this)
        cooldown = new Cooldown()
        createFiles()
        GeneratorManager.getInstance().initGenerators()
        Bukkit.scheduler.scheduleSyncRepeatingTask(this, new ChestCheck(), 0, 20)
        ClickActions.init(this)
    }

    @Override
    void onDisable(){
        saveConfig()
        ((FileConfiguration) storage).save(storageFile)
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
