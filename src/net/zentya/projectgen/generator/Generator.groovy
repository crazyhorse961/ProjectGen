package net.zentya.projectgen.generator

import net.zentya.projectgen.ProjectGen
import org.bukkit.Location
import org.bukkit.configuration.file.FileConfiguration

/**
 * Created by nini7 on 24.05.2017.
 */
class Generator {

    def seconds

    def name

    def location

    def item

    def active = true

    Generator(def seconds, def name, def location, def item){
        this.seconds = seconds
        this.name = name
        this.location = location
        this.item = item
    }
    static def fromConfig(String name){
        FileConfiguration storage = ProjectGen.getInstance().getStorage()
        return new Generator(storage.getInt("chests." + name + ".seconds"), name, (Location) storage.get("chests." + name + ".location"), storage.getItemStack("chests." + name + ".item"))
    }

    def saveTo(){
        FileConfiguration storage = ProjectGen.getInstance().getStorage()
        storage.set("chests." + name + ".seconds", seconds)
        storage.set("chests." + name + ".location", location)
        storage.set("chests." + name + ".item", item)
        storage.save(ProjectGen.getInstance().getStorageFile())
    }

    def stop(){
        active = false
    }
    def start(){
        active = true
    }
}
