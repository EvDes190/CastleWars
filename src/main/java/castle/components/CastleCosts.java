package castle.components;

import arc.struct.OrderedMap;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.type.Item;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.blocks.defense.turrets.Turret;

public class CastleCosts {

    public static OrderedMap<UnitType, Moneys> units;
    public static OrderedMap<Turret, Integer> turrets;
    public static OrderedMap<Item, Integer> items;
    public static OrderedMap<StatusEffect, Integer> effects;

    public static void load() {
        units = OrderedMap.of(
                UnitTypes.dagger, new Moneys(60, 0, 15),
                UnitTypes.mace, new Moneys(170, 1, 50),
                UnitTypes.fortress, new Moneys(550, 4, 200),
                UnitTypes.scepter, new Moneys(3000, 20, 750),
                UnitTypes.reign, new Moneys(10000, 60, 1500),

                UnitTypes.crawler, new Moneys(50, 0, 10),
                UnitTypes.atrax, new Moneys(200, 1, 60),
                UnitTypes.spiroct, new Moneys(600, 4, 200),
                UnitTypes.arkyid, new Moneys(4300, 20, 1000),
                UnitTypes.toxopid, new Moneys(13000, 50, 1750),

                UnitTypes.nova, new Moneys(75, 0, 15),
                UnitTypes.pulsar, new Moneys(190, 1, 50),
                UnitTypes.quasar, new Moneys(600, 4, 200),
                UnitTypes.vela, new Moneys(3800, 22, 750),
                UnitTypes.corvus, new Moneys(15000, 70, 1500),

                UnitTypes.risso, new Moneys(175, 0, 24),
                UnitTypes.minke, new Moneys(250, 1, 70),
                UnitTypes.bryde, new Moneys(1000, 5, 200),
                UnitTypes.sei, new Moneys(5500, 24, 900),
                UnitTypes.omura, new Moneys(15000, 65, 2000),

                UnitTypes.retusa, new Moneys(130, 0, 50),
                UnitTypes.oxynoe, new Moneys(570, 2, 150),
                UnitTypes.cyerce, new Moneys(1400, 6, 200),
                UnitTypes.aegires, new Moneys(7000, 16, 3000),
                UnitTypes.navanax, new Moneys(13500, 70, 1350),

                UnitTypes.flare, new Moneys(60, 0, 20),
                UnitTypes.horizon, new Moneys(200, 1, 70),
                UnitTypes.zenith, new Moneys(700, 4, 150),
                UnitTypes.antumbra, new Moneys(4100, 23, 850),
                UnitTypes.eclipse, new Moneys(12000, 60, 1250),

                UnitTypes.stell, new Moneys(360, 2, 100),
                UnitTypes.locus, new Moneys(900, 4, 250),
                UnitTypes.precept, new Moneys(2200, 12, 600),
                UnitTypes.vanquish, new Moneys(5000, 27, 1000),
                UnitTypes.conquer, new Moneys(10000, 60, 1700),

                UnitTypes.merui, new Moneys(360, 2, 100),
                UnitTypes.cleroi, new Moneys(1000, 4, 400),
                UnitTypes.anthicus, new Moneys(2500, 12, 750),
                UnitTypes.tecta, new Moneys(5700, 25, 1100),
                UnitTypes.collaris, new Moneys(11000, 55, 1900),

                UnitTypes.elude, new Moneys(380, 2, 110),
                UnitTypes.avert, new Moneys(900, 4, 300),
                UnitTypes.obviate, new Moneys(2500, 10, 750),
                UnitTypes.quell, new Moneys(6000, 22, 1500),
                UnitTypes.disrupt, new Moneys(13000, 45, 2300)
        );

        turrets = OrderedMap.of(
                Blocks.duo, 50,
                Blocks.scatter, 150,
                Blocks.scorch, 200,
                Blocks.hail, 250,
                Blocks.wave, 250,
                Blocks.lancer, 300,
                Blocks.arc, 100,
                Blocks.swarmer, 1450,
                Blocks.salvo, 600,
                Blocks.tsunami, 800,
                Blocks.fuse, 1350,
                Blocks.ripple, 1400,
                Blocks.cyclone, 2000,
                Blocks.foreshadow, 4500,
                Blocks.spectre, 4000,
                Blocks.meltdown, 3500,

                Blocks.breach, 750,
                Blocks.diffuse, 1750,
                Blocks.sublimate, 2000,
                Blocks.titan, 1900,
                Blocks.disperse, 3000,
                Blocks.afflict, 2250,
                Blocks.lustre, 4000,
                Blocks.scathe, 4250,
                Blocks.smite, 4500,
                Blocks.malign, 12500
        );

        items = OrderedMap.of(
                Items.copper, 250,
                Items.lead, 300,
                Items.metaglass, 500,
                Items.graphite, 400,
                Items.titanium, 750,
                Items.thorium, 1000,
                Items.silicon, 500,
                Items.plastanium, 1300,
                Items.phaseFabric, 1250,
                Items.surgeAlloy, 1850,
                Items.sporePod, 500,
                Items.blastCompound, 100,
                Items.pyratite, 100,

                Items.beryllium, 300,
                Items.tungsten, 1000,
                Items.oxide, 1500,
                Items.carbide, 2000
        );

        effects = OrderedMap.of(
                StatusEffects.overclock, 4250,
                StatusEffects.overdrive, 11500,
                StatusEffects.boss, 20000
        );
    }

    public static int drop(UnitType type) {
        return units.containsKey(type) ? units.get(type).drop : -1;
    }

    public record Moneys(int cost, int income, int drop) {}
}
