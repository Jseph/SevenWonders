import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//Ok so this is not a clean way to do this but I am going to add
//every type of card in the card class.  Get ready for a giant file
public class Card implements Comparable<Card>
{
	public Type t;
	public enum Type{
		//AGE 1
		LumberYard, StonePit, ClayPool, OreVein, TreeFarm, 
		Excavation, ClayPit, TimberYard, ForestCave, Mine,
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
		CraftsmensGuild, TradersGuild, PhilosophersGuild,
		SpiesGuild, StrategistsGuild, ShipownersGuild, 
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
			case ClayPit: case TimberYard: case ForestCave:
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
			case CraftsmensGuild: case TradersGuild: 
			case PhilosophersGuild: case SpiesGuild: 
			case StrategistsGuild: case ShipownersGuild:
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
			case ForestCave:
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
			case TimberYard: case ForestCave: case Mine:
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
			case CraftsmensGuild:
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
			case SpiesGuild:
				cost[CLAY]+=3;
				cost[GLASS]++;
				break;
			case StrategistsGuild:
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
			        break;
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
                       default:
                            break;
		}
		return ans;
	}
	//Check if the card belongs in the current age
	public boolean matchesAge(int age)
	{
		if(age == 1) switch(t)
		{
			case LumberYard: case StonePit: case ClayPool: case OreVein: case TreeFarm:  
			case Excavation: case ClayPit: case TimberYard: case ForestCave: case Mine:
			case Pawnshop: case Baths: case Altar: case Theater: case Tavern: 
			case EastTradingPost: case WestTradingPost: case Marketplace: case Stockade: 
			case Barracks: case GuardTower: case Apothecary: case Workshop: 
			case Scriptorium: case Loom: case Glassworks: case Press:
				return true;
		default:
			return false;
		}
		if(age == 2) switch(t)
		{
			case Loom: case Glassworks: case Press: case Sawmill: case Quarry: 
			case Brickyard: case Foundry: case Aqueduct: case Temple: case Statue: 
			case Courthouse: case Forum: case Caravansery: case Vineyard: case Bazar: 
			case Walls: case TrainingGround: case Stables: case ArcheryRange: 
			case Dispensary: case Laboratory: case Library: case School:
				return true;
		default:
			return false;
		}
		if(age == 3) switch(t)
		{
			case Pantheon: case Gardens: case TownHall: case Palace: case Senate: 
			case Haven: case Lighthouse: case ChamberOfCommerce: case Arena: 
			case Fortifications: case Circus: case Arsenal: case SiegeWorkshop: case Lodge: 
			case Observatory: case University: case Academy: case Study: case WorkersGuild: 
			case CraftsmensGuild: case TradersGuild: case PhilosophersGuild: case SpiesGuild: 
			case StrategistsGuild: case ShipownersGuild: case ScientistsGuild: 
			case MagistratesGuild: case BuildersGuild:
				return true;
		default:
			return false;
		}
		return false;
	}
	//Get how many players a card is for
	public int[] getNumPlayers()
	{
		switch(t)
		{
			case LumberYard: return new int[]{3,4};
			case StonePit: return new int[]{3,5};
			case ClayPool: return new int[]{3,4};
			case OreVein: return new int[]{3,4};
			case TreeFarm: return new int[]{6};
			case Excavation: return new int[]{4};
			case ClayPit: return new int[]{3};
			case TimberYard: return new int[]{3};
			case ForestCave: return new int[]{5};
			case Mine: return new int[]{6};
			case Pawnshop: return new int[]{4,7};
			case Baths: return new int[]{3,7};
			case Altar: return new int[]{3,5};
			case Theater: return new int[]{3,6};
			case Tavern: return new int[]{4,5,7};
			case EastTradingPost: return new int[]{3,7};
			case WestTradingPost: return new int[]{3,7};
			case Marketplace: return new int[]{3,6};
			case Stockade: return new int[]{3,7};
			case Barracks: return new int[]{3,5};
			case GuardTower: return new int[]{3,4};
			case Apothecary: return new int[]{3,5};
			case Workshop: return new int[]{3,7};
			case Scriptorium: return new int[]{3,4};
			case Loom: return new int[]{3,6};
			case Glassworks: return new int[]{3,6};
			case Press: return new int[]{3,6};
			case Sawmill: return new int[]{3,4};
			case Quarry: return new int[]{3,4};
			case Brickyard: return new int[]{3,4};
			case Foundry: return new int[]{3,4};
			case Aqueduct: return new int[]{3,7};
			case Temple: return new int[]{3,6};
			case Statue: return new int[]{3,7};
			case Courthouse: return new int[]{3,5};
			case Forum: return new int[]{3,6,7};
			case Caravansery: return new int[]{3,5,6};
			case Vineyard: return new int[]{3,6};
			case Bazar: return new int[]{4,7};
			case Walls: return new int[]{3,7};
			case TrainingGround: return new int[]{4,6,7};
			case Stables: return new int[]{3,5};
			case ArcheryRange: return new int[]{3,6};
			case Dispensary: return new int[]{3,4};
			case Laboratory: return new int[]{3,5};
			case Library: return new int[]{3,6};
			case School: return new int[]{3,7};
			case Pantheon: return new int[]{3,6};
			case Gardens: return new int[]{3,4};
			case TownHall: return new int[]{3,5,6};
			case Palace: return new int[]{3,7};
			case Senate: return new int[]{3,5};
			case Haven: return new int[]{3,4};
			case Lighthouse: return new int[]{3,6};
			case ChamberOfCommerce: return new int[]{4,6};
			case Arena: return new int[]{3,5,7};
			case Fortifications: return new int[]{3,7};
			case Circus: return new int[]{4,5,6};
			case Arsenal: return new int[]{3,4,7};
			case SiegeWorkshop: return new int[]{3,5};
			case Lodge: return new int[]{3,6};
			case Observatory: return new int[]{3,7};
			case University: return new int[]{3,4};
			case Academy: return new int[]{3,7};
			case Study: return new int[]{3,5};
			case WorkersGuild: return new int[]{};
			case CraftsmensGuild: return new int[]{};
			case TradersGuild: return new int[]{};
			case PhilosophersGuild: return new int[]{};
			case SpiesGuild: return new int[]{};
			case StrategistsGuild: return new int[]{};
			case ShipownersGuild: return new int[]{};
			case ScientistsGuild: return new int[]{};
			case MagistratesGuild: return new int[]{};
			case BuildersGuild: return new int[]{};
		}
		return new int[]{};
	}
	public Image getImage()
	{
		try {
			System.out.println("/images/cards/" + t.toString().toLowerCase()+".png");
			return ImageIO.read(getClass().getResourceAsStream("/images/cards/" + t.toString().toLowerCase()+".png"));
		} catch (Exception e) {
			System.err.println("Could not find image for "+t);
			return new BufferedImage(180, 275, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	@Override
	public int compareTo(Card o) 
	{
		if(this.getColor().compareTo(o.getColor())!=0)
			return this.getColor().compareTo(o.getColor());
		for(int i=1; i<3; i++)
		{
			if(this.matchesAge(i) && !o.matchesAge(i))
				return -1;
			if(o.matchesAge(i) && !this.matchesAge(i))
				return 1;
		}
		return t.name().compareTo(o.t.name());
	}
}
