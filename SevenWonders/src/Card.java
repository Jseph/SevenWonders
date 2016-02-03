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
	public static int COIN  = 7;
	public static int NUMBER_OF_RESOURCES = 8;
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
		int [] res = new int[NUMBER_OF_RESOURCES];
		ArrayList<int[]> ans = new ArrayList<int[]>();
		ans.add(res);
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
				int[] res2 = new int[NUMBER_OF_RESOURCES];
				res2[CLAY]++;
				ans.add(res2);
				break;
			case Excavation:
				res[STONE]++;
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[CLAY]++;
				ans.add(res2);
				break;
			case ClayPit:
				res[CLAY]++;
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[ORE]++;
				ans.add(res2);
				break;
			case TimberYard:
				res[STONE]++;
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[WOOD]++;
				ans.add(res2);
				break;
			case ForrestCave:
				res[WOOD]++;
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[ORE]++;
				ans.add(res2);
				break;
			case Mine:
				res[ORE]++;
				res2 = new int[NUMBER_OF_RESOURCES];
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
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[GLASS]++;
				ans.add(res2);
				int [] res3 = new int[NUMBER_OF_RESOURCES];
				res3[PAPER]++;
				ans.add(res3);
				break;
			case Caravansery:
				res[CLAY]++;
				res2 = new int[NUMBER_OF_RESOURCES];
				res2[STONE]++;
				ans.add(res2);
				res3 = new int[NUMBER_OF_RESOURCES];
				res3[ORE]++;
				ans.add(res3);
				int [] res4 = new int[NUMBER_OF_RESOURCES];
				res4[WOOD]++;
				ans.add(res4);
				break;
			//AGE 3 gives no resources
			default:
				break;
		}
		return ans;
	}
	//The will display the total cost of building something
	public int[] getCost()
	{
		int [] cost = new int[NUMBER_OF_RESOURCES];
		switch(t)
		{
			case TreeFarm: case Excavation: case ClayPit: 
			case TimberYard: case ForrestCave: case Mine:
				cost[COIN]++;
				break;
			case Baths:
				cost[STONE]++;
				break;
			case Stockade:
				cost[WOOD]++;
				break;
			case Barracks:
				cost[STONE]++;
				break;
			case Apothecary:
				cost[CLOTH]++;
				break;
			case Workshop:
				cost[GLASS]++;
				break;
			case Scriptorium:
				cost[PAPER]++;
				break;
			//AGE 2
			case Sawmill: case Quarry: case Brickyard:
			case Foundry:
				cost[COIN]++;
				break;
			case Aqueduct:
				cost[STONE]+=3;
				break;
			case Temple:
				cost[WOOD]++;
				cost[CLAY]++;
				cost[GLASS]++;
				break;
			case Statue:
				cost[ORE]+=2;
				cost[WOOD]++;
				break;
			case Courthouse:
				cost[CLAY]+=2;
				cost[CLOTH]++;
				break;
			case Forum:
				cost[CLAY]+=2;
				break;
			case Caravansery:
				cost[WOOD]+=2;
				break;
			case Walls:
				cost[STONE]+=3;
				break;
			case TrainingGround:
				cost[ORE]+=2;
				cost[WOOD]++;
				break;
			case Stables:
				cost[ORE]++;
				cost[CLAY]++;
				cost[WOOD]++;
				break;
			case ArcheryRange:
				cost[WOOD]+=2;
				cost[ORE]++;
				break;
			case Dispensary:
				cost[ORE]+=2;
				cost[GLASS]++;
				break;
			case Laboratory:
				cost[CLAY]+=2;
				cost[PAPER]++;
				break;
			case Library:
				cost[STONE]+=2;
				cost[CLOTH]++;
				break;
			case School:
				cost[WOOD]++;
				cost[PAPER]++;
				break;
			//AGE 3 (ugh)
			case Pantheon:
				cost[CLAY]+=2;
				cost[ORE]++;
				cost[PAPER]++;
				cost[CLOTH]++;
				cost[GLASS]++;
				break;
			case Gardens:
				cost[CLAY]+=2;
				cost[WOOD]++;
				break;
			case TownHall:
				cost[STONE]+=2;
				cost[ORE]++;
				cost[GLASS]++;
				break;
			case Palace:
				cost[CLAY]++;
				cost[WOOD]++;
				cost[ORE]++;
				cost[STONE]++;
				cost[GLASS]++;
				cost[PAPER]++;
				cost[CLOTH]++;
				break;
			case Senate:
				cost[WOOD]+=2;
				cost[ORE]++;
				cost[STONE]++;
				break;
			case Haven:
				cost[WOOD]++;
				cost[STONE]++;
				cost[CLOTH]++;
				break;
			case Lighthouse:
				cost[STONE]++;
				cost[GLASS]++;
				break;
			case ChamberOfCommerce:
				cost[CLAY]+=2;
				cost[PAPER]++;
				break;
			case Arena:
				cost[STONE]+=2;
				cost[ORE]++;
				break;
			case Fortifications:
				cost[ORE]+=3;
				cost[STONE]++;
				break;
			case Circus:
				cost[STONE]+=3;
				cost[ORE]++;
				break;
			case Arsenal:
				cost[ORE]++;
				cost[WOOD]+=2;
				cost[CLOTH]++;
				break;
			case SiegeWorkshop:
				cost[WOOD]++;
				cost[CLAY]+=3;
				break;
			case Lodge:
				cost[CLAY]+=2;
				cost[CLOTH]++;
				cost[PAPER]++;
				break;
			case Observatory:
				cost[ORE]+=2;
				cost[GLASS]++;
				cost[CLOTH]++;
				break;
			case University:
				cost[WOOD]+=2;
				cost[PAPER]++;
				cost[GLASS]++;
				break;
			case Academy:
				cost[STONE]+=3;
				cost[GLASS]++;
				break;
			case Study:
				cost[WOOD]++;
				cost[PAPER]++;
				cost[CLOTH]++;
				break;
			case WorkersGuild:
				cost[ORE]+=2;
				cost[CLAY]++;
				cost[STONE]++;
				cost[WOOD]++;
				break;
			case CraftmensGuild:
				cost[ORE]+=2;
				cost[STONE]+=2;
				break;
			case TradersGuild:
				cost[CLOTH]++;
				cost[PAPER]++;
				cost[GLASS]++;
				break;
			case PhilosophersGuild:
				cost[CLAY]+=3;
				cost[CLOTH]++;
				cost[PAPER]++;
				break;
			case SpyGuild:
				cost[CLAY]+=3;
				cost[GLASS]++;
				break;
			case StrategyGuild:
				cost[ORE]+=2;
				cost[STONE]++;
				cost[CLOTH]++;
				break;
			case ShipownersGuild:
				cost[WOOD]+=3;
				cost[PAPER]++;
				cost[GLASS]++;
				break;
			case ScientistsGuild:
				cost[WOOD]+=2;
				cost[ORE]+=2;
				cost[PAPER]++;
				break;
			case BuildersGuild:
				cost[STONE]+=2;
				cost[CLAY]+=2;
				cost[GLASS]++;
			
			default:
				break;
		}
		return cost;
	}
	//need to know the game state
	public int onPlayCoinBonus()
	{
		int ans = 0;
		switch(t)
		{
			case Tavern:
				ans+=5;
				break;
		}
		return ans;
	}
	
}
