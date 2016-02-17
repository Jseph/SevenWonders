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
			case ThePyramidsOfGizaA:
			case ThePyramidsOfGizaB:
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
	
	//provides a resource
	public int[] ProvidedResources(int tier)//1 to 4
	{
		int [] resources = new int[NUMBER_OF_RESOURCES];
		
		switch(wb)
		{
		case TheLighthouseOfAlexandriaA:
			if(tier == 2)
			{
				resources[WOOD]++;
				resources[CLAY]++;
				resources[ORE]++;
				resources[STONE]++;
			}
			break;
		case TheLighthouseOfAlexandriaB:
			if(tier == 1)
			{
				resources[WOOD]++;
				resources[CLAY]++;
				resources[ORE]++;
				resources[STONE]++;
			}
			if(tier == 2)
			{
				resources[GLASS]++;
				resources[PAPER]++;
				resources[CLOTH]++;
			}
			break;
		default:
			break;
		}
		return resources;
	}
	//provides a trade change.  This is always a cheaper brown trade to left and right
	public boolean ProvidedTrade(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheStatueOfZeusInOlympiaB:
			if(tier == 1)
				return true;
		default:
			return false;
		}
	}
	//provides military
	public int ProvidedMilitary(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheColossusOfRhodesA:
			if(tier == 2)
				return 2;
			return 0;
		case TheColossusOfRhodesB:
			if(tier == 1)
				return 1;
			if(tier == 2)
				return 1;
			return 0;
		default:
			return 0;
		}
	}
	//provides science
	public boolean ProvidedScience(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheHangingGardensOfBabylonA:
			if(tier == 2)
				return true;
			return false;
		case TheHangingGardensOfBabylonB:
			if(tier == 3)
				return true;
			return false;
		default:
			return false;
		}
	}
	//provides points
	public int ProvidedPoints(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheColossusOfRhodesA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheColossusOfRhodesB:
			if(tier == 1)
				return 3;
			if(tier == 2)
				return 4;
			break;
		case TheLighthouseOfAlexandriaA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheLighthouseOfAlexandriaB:
			if(tier == 3)
				return 7;
			break;
		case TheTempleOfArtemisInEphesusA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheTempleOfArtemisInEphesusB:
			if(tier == 1)
				return 2;
			if(tier == 2)
				return 3;
			if(tier == 3)
				return 5;
			break;
		case TheHangingGardensOfBabylonA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheHangingGardensOfBabylonB:
			if(tier == 1)
				return 3;
			break;
		case TheStatueOfZeusInOlympiaA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheStatueOfZeusInOlympiaB:
			if(tier == 2)
				return 5;
			break;
		case TheMausoleumOfHalicarnassusA:
			if(tier == 1)
				return 3;
			if(tier == 3)
				return 7;
			break;
		case TheMausoleumOfHalicarnassusB:
			if(tier == 1)
				return 2;
			if(tier == 2)
				return 1;
			break;
		case ThePyramidsOfGizaA:
			if(tier == 1)
				return 3;
			if(tier == 2)
				return 5;
			if(tier == 3)
				return 7;
			break;
		case ThePyramidsOfGizaB:
			if(tier == 1)
				return 3;
			if(tier == 2)
				return 5;
			if(tier == 3)
				return 5;
			if(tier == 4)
				return 7;
			break;
		default:
			break;
		}
		return 0;
	}
	//mimics a purple card
	public boolean ProvidedPurple(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheStatueOfZeusInOlympiaB:
			if(tier == 3)
				return true;
		default:
			return false;
		}
	}
	//give coins when built
	public int ProvidedCoins(int tier)//1 to 4
	{
		switch(wb)
		{
		case TheColossusOfRhodesB:
			if(tier == 1)
				return 3;
			if(tier == 2)
				return 4;
			break;
		case TheTempleOfArtemisInEphesusA:
			if(tier == 2)
				return 9;
			break;
		case TheTempleOfArtemisInEphesusB:
			if(tier == 1)
				return 4;
			if(tier == 2)
				return 4;
			if(tier == 3)
				return 4;
			break;
		default:
			break;
		}
		return 0;
	}
	//play a card when built
	public boolean ProvidedDiscardPlay(int tier)//1 to 4
	{
		switch(wb)
		{
			case TheMausoleumOfHalicarnassusA:
				if(tier == 2)
					return true;
				return false;
			case TheMausoleumOfHalicarnassusB:
				if(tier == 1)
					return true;
				if(tier == 2)
					return true;
				if(tier == 3)
					return true;
				return false;
			default:
				return false;
		}
	}
	//allow a free construction once per age
	public boolean ProvidedFreePlay(int tier)//1 to 4
	{
		switch(wb)
		{
			case TheStatueOfZeusInOlympiaA:
				if(tier == 2)
					return true;
			default:
				return false;
		}
	}
	//allow construction of card 7 once per age
	public boolean ProvidedSevenPlay(int tier)//1 to 4
	{
		switch(wb)
		{
			case TheHangingGardensOfBabylonB:
				if(tier == 2)
					return true;
			default:
				return false;
		}
	}
	public double[] LeftPointsOfWonderCards()
	{
		double[] points = new double[NumberOfWonderTiers()];
		if(NumberOfWonderTiers() == 2)
		{
			points[1] = (0.61+2.93)/9.81;
			points[2] = (0.61+2.93+2.90)/9.81;
		}
		if(NumberOfWonderTiers() == 3)
		{
			points[1] = (0.61)/9.81;
			points[2] = (0.61+2.93)/9.81;
			points[3] = (0.61+2.93+2.90)/9.81;
		}
		if(NumberOfWonderTiers() == 4)
		{
			points[1] = -0.30/9.81;
			points[2] = 2.35/9.81;
			points[3] = (3.81+1.15)/9.81;
			points[4] = (3.81+3.75)/9.81;
		}
		return points;
	}
	public double WidthOfWonderCards()
	{
		return 2.555/9.81;
	}
}