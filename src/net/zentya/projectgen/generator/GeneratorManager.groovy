package net.zentya.projectgen.generator

import net.zentya.projectgen.ProjectGen
import org.bukkit.configuration.file.FileConfiguration

/**
 * Created by nini7 on 24.05.2017.
 */
class GeneratorManager
{
    List<Generator> generators = new ArrayList<>()

    static def getGenerators(){
        return this.generators
    }
    static def initGenerators(){
        for(String names : ((FileConfiguration) ProjectGen.getInstance().getStorage()).getConfigurationSection("chests").getKeys(false)){
            Generator gen = Generator.fromConfig(names)
            ((List<Generator>) generators).add(gen)
        }
    }
    def deleteGenerator(Generator gen){
        generators.remove(gen)
        String name = gen.name
        FileConfiguration storage = ProjectGen.getInstance().getStorage()
        storage.set("chests." + name + ".seconds", null)
        storage.set("chests." + name + ".item", null)
        storage.set("chests." + name + ".location", null)
        storage.save(ProjectGen.getInstance().getStorageFile())
    }

    def deleteGenerator(String generator){
        deleteGenerator(Generator.fromConfig(generator))
    }
}
