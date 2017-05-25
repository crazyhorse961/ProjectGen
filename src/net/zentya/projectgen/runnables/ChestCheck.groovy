package net.zentya.projectgen.runnables

import net.zentya.projectgen.generator.Generator
import net.zentya.projectgen.generator.GeneratorManager
import org.bukkit.Location
import org.bukkit.block.Chest

/**
 * Created by nini7 on 21.05.2017.
 */
class ChestCheck implements Runnable
{
    void run(){
        for(Generator gen : GeneratorManager.getInstance().getGenerators()){
            if(gen.active){
                gen.increaseLived()
                if(gen.lived == gen.seconds){
                    gen.resetLived()
                    Chest chest = (Chest) ((Location)gen.location).getBlock()
                    chest.getInventory().addItem(gen.item)
                }
            }
        }
    }
}
