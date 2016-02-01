import java.util.ArrayList;

//Ok so this is not a clean way to do this but I am going to add
//every type of card in the card class.  Get ready for a giant file
public class Card 
{
	public Type t;
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
	//Consider reformatting to be all caps
	public enum Color 
	{
		Brown, Gray, Blue, Yellow, Red, Green, Purple
	}
	//These will go in a constants class eventually
	public static int WOOD  = 0;
	public static int STONE = 1;
	public static int CLAY  = 2;
	public static int ORE   = 3;
	public static int CLOTH = 4;
	public static int GLASS = 5;
	public static int PAPER = 6;
	public Card(Type t)
	{
		this.t = t;
	}
	public Color getColor()
	{
		switch(t)
		{
			case LumberYard: case StonePit: case ClayPool:
			case OreVein: case TreeFarm: case Excavation: 
			case ClayPit: case TimberYard: case ForrestCave:
			case Mine: case Sawmill: case Quarry: 
			case Brickyard: case Foundry:
				return Color.Brown;
			case Loom: case Glassworks: case Press:
				return Color.Gray;
			case Pawnshop: case Baths: case Altar: 
			case Theater: case Aqueduct: case Temple:
			case Statue: case Courthouse: case Pantheon:
			case Gardens: case TownHall: case Palace:
			case Senate:
				return Color.Blue;
			case Tavern: case EastTradingPost: 
			case WestTradingPost: case Marketplace:
			case Forum: case Caravansery: case Vineyard:
			case Bazar: case Haven: case Lighthouse:
			case ChamberOfCommerce: case Arena:
				return Color.Yellow;
			case Stockade: case Barracks: case GuardTower:
			case Walls: case TrainingGround: case Stables:
			case ArcheryRange: case Fortifications:
			case Circus: case Arsenal: case SiegeWorkshop:
				return Color.Red;
			case Apothecary: case Workshop: case Scriptorium:
			case Dispensary: case Laboratory: case Library:
			case School: case Lodge: case Observatory: 
			case University: case Academy: case Study: 
				return Color.Green;
			case WorkersGuild:
			case CraftmensGuild: case TradersGuild: 
			case PhilosophersGuild: case SpyGuild: 
			case StrategyGuild: case ShipownersGuild:
			case ScientistsGuild: case MagistratesGuild:
			case BuildersGuild:
				return Color.Purple;
				
		}
		throw new RuntimeException("NO COLOR CASE FOR TYPE " + t);
	}
	public ArrayList<int[]> getResources()
	{
		int [] res = new int[7];
		ArrayList<int[]> ans = new ArrayList<int[]>();
		switch(t)
		{
			//AGE 1
			case LumberYard:
				res[WOOD]++;
				break;
			case StonePit:
				res[STONE]++;
				break;
			case ClayPool: 
				res[CLAY]++;
				break;
			case OreVein:
				res[ORE]++;
				break;
			case TreeFarm:
				res[WOOD]++;
				int[] res2 = new int[7];
				res2[CLAY]++;
				ans.add(res2);
				break;
			case Excavation:
				res[STONE]++;
				res2 = new int[7];
				res2[CLAY]++;
				ans.add(res2);
				break;
			case ClayPit:
				res[CLAY]++;
				res2 = new int[7];
				res2[ORE]++;
				ans.add(res2);
				break;
			case TimberYard:
				res[STONE]++;
				res2 = new int[7];
				res2[WOOD]++;
				ans.add(res2);
				break;
			case ForrestCave:
				res[WOOD]++;
				res2 = new int[7];
				res2[ORE]++;
				ans.add(res2);
				break;
			case Mine:
				res[ORE]++;
				res2 = new int[7];
				res2[STONE]++;
				ans.add(res2);
				break;
			//AGE 1 & 2
			case Loom: 
				res[CLOTH]++;
				break;
			case Glassworks:
				res[GLASS]++;
				break;
			case Press:
				res[PAPER]++;
				break;
			//AGE 2
			case Sawmill:
				res[WOOD]+=2;
				break;
			case Quarry:
				res[STONE]+=2;
				break;
			case Brickyard:
				res[CLAY]+=2;
				break;
			case Foundry:
				res[ORE]+=2;
				break;
			case Forum:
				res[CLOTH]++;
				res2 = new int[7];
				res2[GLASS]++;
				ans.add(res2);
				int [] res3 = new int[7];
				res3[PAPER]++;
				ans.add(res3);
				break;
			case Caravansery:
				res[CLAY]++;
				res2 = new int[7];
				res2[STONE]++;
				ans.add(res2);
				res3 = new int[7];
				res3[ORE]++;
				ans.add(res3);
				int [] res4 = new int[7];
				res4[WOOD]++;
				ans.add(res4);
				break;
			//AGE 3 gives no resources
			default:
				break;
		}
		return ans;
	}
}
