package view.container.aspects.designs;

import java.awt.Graphics2D;

import bridge.Bridge;
import other.context.Context;

public class ContainerDesign 
{
	public String createSVGImage(final Bridge bridge, final Context context) 
	{
		return null;
	}
	
	public void drawPuzzleHints(final Graphics2D g2d, final Context context) 
	{
		// do nothing
	}
	
	public void drawPuzzleCandidates(final Graphics2D g2d, final Context context) 
	{
		// Do nothing.
	}

	public boolean ignorePieceSelectionLimit() 
	{
		return false;
	}
}
