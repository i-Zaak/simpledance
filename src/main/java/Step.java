/**
 * @author Standard
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Step
{
	static public String N_(String str){return str;};

	private Foot [] feets = new Foot[4];
	
	static String [] stepTypes = new String []
	{
		N_("Unspecified"),
		N_("Foot doesn't move"),
		N_("Heel step"),//"Fersenschritt",
		N_("Ball step"),//"Ballenschritt",
		N_("Ball step (stay)"),//"Ballenschritt (oben bleiben)",
		N_("Stay on ball"),//"Auf Ballen bleiben",
		N_("Foot in the air"),//"Fu� in der Luft",
		N_("Heel turn"),//"Fersendrehung",
		N_("Stand on foot"),//"Fu� belasten",
		N_("Stand on bale"),//"Ballen belasten",
		N_("Kick"),//"Kick",
		N_("Appell"),//"Appell",
		N_("Bale Tap"),// "Ballen-Tap",
		N_("Whole foot"),//"Ganzen Fu� setzen",
		N_("Heel Tap"),//"Fersen-Tap",
		N_("Toe Tap"),//"Zehen-Tap",
		N_("Jump"),//"Sprung",
		N_("Without weight"),//"Fu� nicht belasten"
	};

	
	private String count;
	private String description;
	private String duration = "slow";
	
	public Step()
	{
		feets[0] = new Foot(590,580,0,true,false);
		feets[1] = new Foot(610,580,0,false,false);
		feets[2] = new Foot(610,620,180,true,true);
		feets[3] = new Foot(590,620,180,false,true); 
	}
	
	public Step(boolean zero)
	{
		feets[0] = new Foot(0,0,0,true,false);
		feets[1] = new Foot(0,0,0,false,false);
		feets[2] = new Foot(0,0,0,true,true);
		feets[3] = new Foot(0,0,0,false,true); 
	}
	
	WayPoint getStartingWayPoint(int feetNum)
	{
		return feets[feetNum].getStartingWayPoint();
	}
	
	public boolean isFeetFemale(int feetNum)
	{
		return feets[feetNum].isFemale();
	}
	
	public boolean isFeetLeft(int feetNum)
	{
		return feets[feetNum].isLeft();
	}
	
	public int getNumberOfFeets()
	{
		return 4;
	}
	/**
	 * Method getFeet.
	 * @param i
	 * @return Foot
	 */
	public Foot getFeet(int i)
	{
		if (i<0 || i>4) return null;
		return feets[i];
	}

	public int [] getStepBounds()
	{
		int [] bounds = new int[4];

		bounds[0] = bounds[3] = 0x7fff;

		for (int i=0;i<4;i++)
		{
			for (int j=0;j<feets[i].getNumOfWayPoints();j++)
			{
				WayPoint feetCoord = feets[i].getFeetCoord(j);
				if (feetCoord.x < bounds[0]) bounds[0] = feetCoord.x;
				if (feetCoord.x > bounds[2]) bounds[2] = feetCoord.x;
				if (feetCoord.y > bounds[1]) bounds[1] = feetCoord.y;
				if (feetCoord.y < bounds[3]) bounds[3] = feetCoord.y;
			}
		}
		return bounds;
	}

	/**
	 * Method setDescription.
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = new String(string);
	}
	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription()
	{
		if (description == null) description = "";
		return description;
	}
	/**
	 * Method setCount.
	 * @param string
	 */
	public void setCount(String string)
	{
		count = new String(string);
	}

	public String getCount()
	{
		if (count == null) count = "";
		return count;
	}

	public boolean isQuick()
	{
		return duration.equalsIgnoreCase("quick");
	}

	public boolean isSlow()
	{
		return duration.equalsIgnoreCase("slow");
	}

	public Step duplicate(boolean wayPoints)
	{
		Step step = new Step();

		for (int i=0;i<4;i++)
		{
			Foot foot = step.getFeet(i);
			WayPoint wp = foot.getStartingWayPoint();
			wp.x = feets[i].getStartingWayPoint().x;
			wp.y = feets[i].getStartingWayPoint().y;
			wp.a = feets[i].getStartingWayPoint().a;
			step.count = new String(count);
			step.duration = new String(duration);
			step.description = new String(description);
		}
		return step;
	}
	/**
	 * Returns the duration.
	 * @return String
	 */
	public String getDuration()
	{
		return duration;
	}

	/**
	 * Sets the duration.
	 * @param duration The duration to set
	 */
	public void setDuration(String duration)
	{
		this.duration = new String(duration);
	}
}
