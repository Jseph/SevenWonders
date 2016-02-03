
public class HelloGithub {
	public enum Type{
		//AGE 1
		LumberYard, StonePit, ClayPool, OreVein, TreeFarm, 
		Excavation, ClayPit, TimberYard, ForrestCave, Mine,
		Pawnshop, Baths, Altar, Theater, Tavern, EastTradingPost, 
		WestTradingPost, Marketplace, Stockade, Barracks, 
		GuardTower, Apothecary, Workshop, Scriptorium,
		//AGE 1 AND 2
		Loom, Glassworks, Press,
		//AGE2
		Sawmill, Quarry, Brickyard, Foundry, Aqueduct, Temple,
		Statue, Courthouse, Forum, Caravansery, Vineyard, Bazar, 
		Walls, TrainingGround, Stables, ArcheryRange, Dispensary, 
		Laboratory, Library, School,
		//Age3
		Pantheon, Gardens, TownHall, Palace, Senate, Haven,
		Lighthouse, ChamberOfCommerce, Arena, Fortifications,
		Circus, Arsenal, SiegeWorkshop, Lodge, Observatory,
		University, Academy, Study, WorkersGuild,
		CraftmensGuild, TradersGuild, PhilosophersGuild,
		SpyGuild, StrategyGuild, ShipownersGuild, 
		ScientistsGuild, MagistratesGuild, BuildersGuild
	}
	public static void main(String [] args)
	{
		for(Type t: Type.values())
		{
			System.out.println("case "+t.name()+": return new int[]{3};");
		}
	}
}
