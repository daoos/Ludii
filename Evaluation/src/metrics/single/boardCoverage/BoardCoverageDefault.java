package metrics.single.boardCoverage;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.rng.RandomProviderState;

import game.Game;
import metrics.Evaluation;
import metrics.Metric;
import metrics.ReplayTrial;
import metrics.Utils;
import other.concept.Concept;
import other.context.Context;
import other.move.Move;
import other.topology.TopologyElement;

/**
 * Percentage of default board sites which a piece was placed on at some point.
 * 
 * @author matthew.stephenson
 */
public class BoardCoverageDefault extends Metric
{

	//-------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public BoardCoverageDefault()
	{
		super
		(
			"Board Coverage Default", 
			"Percentage of default board sites which a piece was placed on at some point.", 
			0.0, 
			1.0,
			Concept.BoardCoverageDefault
		);
	}
	
	//-------------------------------------------------------------------------
	
	@Override
	public double apply
	(
			final Game game,
			final Evaluation evaluation,
			final ReplayTrial[] trials,
			final RandomProviderState[] randomProviderStates
	)
	{
		double numSitesCovered = 0;
		for (int trialIndex = 0; trialIndex < trials.length; trialIndex++)
		{
			// Get trial and RNG information
			final ReplayTrial trial = trials[trialIndex];
			final RandomProviderState rngState = randomProviderStates[trialIndex];
			
			// Setup a new instance of the game
			final Context context = Utils.setupNewContext(game, rngState);
			
			// Record all sites covered in this trial.
			final Set<TopologyElement> sitesCovered = new HashSet<TopologyElement>();
			
			sitesCovered.addAll(Utils.boardDefaultSitesCovered(context));
			for (final Move m : trial.fullMoves())
			{
				context.game().apply(context, m);
				sitesCovered.addAll(Utils.boardDefaultSitesCovered(context));
			}
			
			numSitesCovered += ((double) sitesCovered.size()) / game.board().topology().getGraphElements(game.board().defaultSite()).size();
		}

		return numSitesCovered / trials.length;
	}

}
