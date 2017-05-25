package net.zentya.projectgen.generator

import net.zentya.projectgen.ProjectGen
import org.bukkit.Location
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.inventory.ItemStack

/**
 * Created by nini7 on 24.05.2017.
 */
class GeneratorManager
{
    def generators = new ArrayList<Generator>()
    static GeneratorManager instance

    static{
        instance = new GeneratorManager()
    }

    def initGenerators(){
        for(String names : ((FileConfiguration) ProjectGen.getInstance().getStorage()).getConfigurationSection("chests").getKeys(false)){
            Generator gen = Generator.fromConfig(names)
            new GeneratorManager().generators.add(gen)
        }
    }
    def deleteGenerator(Generator gen){
        generators.remove(gen)
        String name = gen.name
        FileConfiguration storage = ProjectGen.getInstance().getStorage()
        storage.set("chests." + name + ".seconds", null)
        storage.set("chests." + name + ".item", null)
        storage.set("chests." + name + ".location", null)
        storage.set("chests." + name, null)
        storage.save(ProjectGen.getInstance().getStorageFile())
    }

    def deleteGenerator(String generator){
        deleteGenerator(Generator.fromConfig(generator))
    }
    def getGenerators(){
        return generators
    }

    def createGenerator(String name, int seconds, Location location, ItemStack item){
        Generator gen = new Generator(name, seconds, location , item)
        generators.add(gen)
        FileConfiguration storage = ProjectGen.getInstance().getStorage()
        storage.set("chests." + name + ".seconds", seconds)
        storage.set("chests." + name + ".item", item)
        storage.set("chests." + name + ".location", location)
        storage.save(ProjectGen.getInstance().getStorageFile())
    }
    static def getInstance(){
        return instance
    }
}
