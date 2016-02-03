public class Wonder
{
	public WonderBoard wb;
	public enum WonderBoard{
		TheColossusOfRhodesA, TheLighthouseOfAlexandriaA,
		TheTempleOfArtemisInEphesusA, TheHangingGardensOfBabylonA,
		TheStatueOfZeusInOlympiaA, TheMausoleumOfHalicarnassusA,
		ThePyramidsOfGizaA,
		//For the B sides
		TheColossusOfRhodesB, TheLighthouseOfAlexandriaB,
		TheTempleOfArtemisInEphesusB, TheHangingGardensOfBabylonB,
		TheStatueOfZeusInOlympiaB, TheMausoleumOfHalicarnassusB,
		ThePyramidsOfGizaB
	}
	
	public Wonder(WonderBoard wb)
	{
		this.wb = wb;
	}
	
	public static int WOOD  = 0;
	public static int STONE = 1;
	public static int CLAY  = 2;
	public static int ORE   = 3;
	public static int CLOTH = 4;
	public static int GLASS = 5;
	public static int PAPER = 6;
	public static int COIN  = 7;
	public static int NUMBER_OF_RESOURCES = 8;
	
	public int[] getInitialResource()
	{
		int [] res = new int[NUMBER_OF_RESOURCES];
		switch(wb)
		{
			case TheColossusOfRhodesA:
			case TheColossusOfRhodesB:
				res[ORE]++;
				break;
			case TheLighthouseOfAlexandriaA:
			case TheLighthouseOfAlexandriaB:
				res[GLASS]++;
				break;
			case TheTempleOfArtemisInEphesusA:
			case TheTempleOfArtemisInEphesusB:
				res[PAPER]++;
				break;
			case TheHangingGardensOfBabylonA:
			case TheHangingGardensOfBabylonB:
				res[CLAY]++;
				break;
			case TheStatueOfZeusInOlympiaA:
			case TheStatueOfZeusInOlympiaB:
				res[WOOD]++;
				break;
			case TheMausoleumOfHalicarnassusA:
			case TheMausoleumOfHalicarnassusB:
				res[CLOTH]++;
				break;
			case ThePyramidsOfGizaB:
			case ThePyramidsOfGizaA:
				res[STONE]++;
				break;
			default:
				break;
		}
		return res;
	}
	public int NumberOfWonderTiers()
	{
		switch(wb)
		{
			case TheColossusOfRhodesB:
				return 2;
			case ThePyramidsOfGizaB:
				return 4;
			default:
				return 3;
		}
	}
	public int[] WonderTierCost(int tier)//1 to 4
	{
		if(tier < 1)
			return null;
		if(tier > NumberOfWonderTiers())
			return null;
		
		int [] cost = new int[NUMBER_OF_RESOURCES];
		
		switch(wb)
		{
		case TheColossusOfRhodesA:
			if(tier == 1)
				cost[WOOD] = 2;
			if(tier == 2)
				cost[CLAY] = 3;
			if(tier == 3)
				cost[ORE] = 4;
			break;
		case TheColossusOfRhodesB:
			//2 only
			if(tier == 1)
				cost[STONE] = 3;
			if(tier == 2)
				cost[ORE] = 4;
			break;
		case TheLighthouseOfAlexandriaA:
			if(tier == 1)
				cost[STONE] = 2;
			if(tier == 2)
				cost[ORE] = 2;
			if(tier == 3)
				cost[GLASS] = 2;
			break;
		case TheLighthouseOfAlexandriaB:
			if(tier == 1)
				cost[CLAY] = 2;
			if(tier == 2)
				cost[WOOD] = 2;
			if(tier == 3)
				cost[STONE] = 3;
			break;
		case TheTempleOfArtemisInEphesusA:
			if(tier == 1)
				cost[STONE] = 2;
			if(tier == 2)
				cost[WOOD] = 2;
			if(tier == 3)
				cost[PAPER] = 2;
			break;
		case TheTempleOfArtemisInEphesusB:
			if(tier == 1)
				cost[STONE] = 2;
			if(tier == 2)
				cost[WOOD] = 2;
			if(tier == 3)
			{
				cost[PAPER] = 1;
				cost[GLASS] = 1;
				cost[CLOTH] = 1;
			}
			break;
		case TheHangingGardensOfBabylonA:
			if(tier == 1)
				cost[CLAY] = 2;
			if(tier == 2)
				cost[WOOD] = 3;
			if(tier == 3)
				cost[CLAY] = 4;
			break;
		case TheHangingGardensOfBabylonB:
			if(tier == 1)
			{
				cost[CLOTH] = 1;
				cost[CLAY] = 1;
			}
			if(tier == 2)
			{
				cost[GLASS] = 1;
				cost[WOOD] = 2;
			}
			if(tier == 3)
			{
				cost[PAPER] = 1;
				cost[CLAY] = 3;
			}
			break;
		case TheStatueOfZeusInOlympiaA:
			if(tier == 1)
				cost[WOOD] = 2;
			if(tier == 2)
				cost[STONE] = 2;
			if(tier == 3)
				cost[ORE] = 2;
			break;
		case TheStatueOfZeusInOlympiaB:
			if(tier == 1)
				cost[WOOD] = 2;
			if(tier == 2)
				cost[STONE] = 2;
			if(tier == 3)
			{
				cost[PAPER] = 1;
				cost[GLASS] = 1;
				cost[CLOTH] = 1;
			}
			break;
		case TheMausoleumOfHalicarnassusA:
			if(tier == 1)
				cost[CLAY] = 2;
			if(tier == 2)
				cost[ORE] = 3;
			if(tier == 3)
				cost[CLOTH] = 2;
			break;
		case TheMausoleumOfHalicarnassusB:
			if(tier == 1)
				cost[ORE] = 2;
			if(tier == 2)
				cost[CLAY] = 3;
			if(tier == 3)
			{
				cost[PAPER] = 1;
				cost[GLASS] = 1;
				cost[CLOTH] = 1;
			}
			break;
		case ThePyramidsOfGizaA:
			if(tier == 1)
				cost[STONE] = 2;
			if(tier == 2)
				cost[WOOD] = 3;
			if(tier == 3)
				cost[STONE] = 4;
			break;
		case ThePyramidsOfGizaB:
			//4 of em
			if(tier == 1)
				cost[WOOD] = 2;
			if(tier == 2)
				cost[STONE] = 3;
			if(tier == 3)
				cost[CLAY] = 3;
			if(tier == 4)
			{
				cost[PAPER] = 1;
				cost[STONE] = 4;
			}
			break;
		default:
			break;
		}
		return cost;
	}
}
