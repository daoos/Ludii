package other.action.move;

import java.util.BitSet;
import java.util.List;

import game.rules.play.moves.Moves;
import game.types.board.RelationType;
import game.types.board.SiteType;
import game.util.directions.AbsoluteDirection;
import game.util.directions.DirectionFacing;
import game.util.graph.Radial;
import gnu.trove.list.array.TIntArrayList;
import main.Constants;
import other.action.Action;
import other.action.BaseAction;
import other.concept.Concept;
import other.context.Context;
import other.state.container.ContainerState;
import other.topology.Topology;
import other.topology.TopologyElement;

/**
 * Copies a component from a site to another.
 *
 * @author Eric.Piette
 */
public final class ActionCopy extends BaseAction
{
	private static final long serialVersionUID = 1L;

	//-------------------------------------------------------------------------

	/** The graph element type of the from site. */
	private final SiteType typeFrom;

	/** From site index. */
	private final int from;

	/** From level index (e.g. stacking game). */
	private int levelFrom;

	/** The graph element type of the to site. */
	private final SiteType typeTo;

	/** To site index. */
	private final int to;

	/** To level index (e.g. stacking game). */
	private final int levelTo;

	/** Site state value of the to site. */
	private final int state;

	/** Rotation value of the to site. */
	private final int rotation;

	/** piece value of the to site. */
	private final int value;

	/** Stacking game or not. */
	private final boolean onStacking;

	//-------------------------------------------------------------------------

	/**
	 * @param typeFrom   The graph element type of the from site.
	 * @param from       From site index.
	 * @param levelFrom  From level index.
	 * @param typeTo     The graph element type of the to site.
	 * @param to         To site index.
	 * @param levelTo    To level index.
	 * @param state      The state site of the to site.
	 * @param rotation   The rotation value of the to site.
	 * @param value      The piece value of the to site.
	 * @param onStacking True if we move a full stack.
	 */
	public ActionCopy
	(
		final SiteType typeFrom,
		final int from,
		final int levelFrom,
		final SiteType typeTo,
		final int to,
		final int levelTo,
		final int state,
		final int rotation,
		final int value,
		final boolean onStacking
	)
	{
		this.typeFrom = typeFrom;
		this.from = from;
		this.levelFrom = levelFrom;
		this.typeTo = typeTo;
		this.to = to;
		this.levelTo = levelTo;
		this.state = state;
		this.rotation = rotation;
		this.value = value;
		this.onStacking = onStacking;
	}

	/**
	 * Reconstructs an ActionCopy object from a detailed String
	 * (generated using toDetailedString())
	 * @param detailedString
	 */
	public ActionCopy(final String detailedString)
	{
		assert (detailedString.startsWith("[Copy:"));

		final String strTypeFrom = Action.extractData(detailedString, "typeFrom");
		typeFrom = (strTypeFrom.isEmpty()) ? null : SiteType.valueOf(strTypeFrom);

		final String strFrom = Action.extractData(detailedString, "from");
		from = Integer.parseInt(strFrom);

		final String strLevelFrom = Action.extractData(detailedString, "levelFrom");
		levelFrom = (strLevelFrom.isEmpty()) ? Constants.UNDEFINED : Integer.parseInt(strLevelFrom);

		final String strTypeTo = Action.extractData(detailedString, "typeTo");
		typeTo = (strTypeTo.isEmpty()) ? null : SiteType.valueOf(strTypeTo);

		final String strTo = Action.extractData(detailedString, "to");
		to = Integer.parseInt(strTo);

		final String strLevelTo = Action.extractData(detailedString, "levelTo");
		levelTo = (strLevelTo.isEmpty()) ? Constants.UNDEFINED : Integer.parseInt(strLevelTo);

		final String strState = Action.extractData(detailedString, "state");
		state = (strState.isEmpty()) ? Constants.UNDEFINED : Integer.parseInt(strState);

		final String strRotation = Action.extractData(detailedString, "rotation");
		rotation = (strRotation.isEmpty()) ? Constants.UNDEFINED : Integer.parseInt(strRotation);

		final String strValue = Action.extractData(detailedString, "value");
		value = (strValue.isEmpty()) ? Constants.UNDEFINED : Integer.parseInt(strValue);

		final String strStack = Action.extractData(detailedString, "stack");
		onStacking = (strStack.isEmpty()) ? false : Boolean.parseBoolean(strStack);

		final String strDecision = Action.extractData(detailedString, "decision");
		decision = (strDecision.isEmpty()) ? false : Boolean.parseBoolean(strDecision);
	}

	//-------------------------------------------------------------------------

	@Override
	public Action apply(final Context context, final boolean store)
	{
		final int contIdA = typeFrom.equals(SiteType.Cell) ? context.containerId()[from] : 0;
		final ContainerState csA = context.state().containerStates()[contIdA];

		final int originalCount = csA.count(from, typeFrom);

		final ActionMove actionMove = new ActionMove(typeFrom, from, levelFrom, typeTo, to, levelTo, state, rotation,
				value, onStacking);
		actionMove.apply(context, store);

		final int contIdB = typeTo.equals(SiteType.Cell) ? context.containerId()[to] : 0;
		final ContainerState csB = context.state().containerStates()[contIdB];

		final boolean requiresStack = context.game().isStacking();
		if (!requiresStack)
		{
			csA.setSite(context.state(), from, csB.who(to, typeTo), csB.what(to, typeTo), originalCount,
					csB.state(to, typeTo),
					csB.rotation(to, typeTo), csB.value(to, typeTo), typeFrom);
			context.state().owned().add(csB.who(to, typeTo), csB.what(to, typeTo), from, typeFrom);
		}
		else
		{
			final int what = csB.what(to, typeTo);
			final int who = csB.who(to, typeTo);
			final int sizeStack = csB.sizeStack(to, typeTo);

			if (levelFrom == Constants.UNDEFINED || sizeStack == levelFrom)
			{
				csA.addItemGeneric(context.state(), from, what, who, context.game(), typeFrom);
				context.state().owned().add(who, what, from, csA.sizeStack(from, typeFrom) - 1, typeFrom);
			}
			else
			{
				csA.insert(context.state(), typeFrom, from, levelFrom, what, who, state, rotation, value,
						context.game());
				context.state().owned().add(who, what, from, levelFrom, typeFrom);
			}
		}

		return this;
	}

	//-------------------------------------------------------------------------

	@Override
	public String toTrialFormat(final Context context)
	{
		final StringBuilder sb = new StringBuilder();

		sb.append("[Copy:");

		if (typeFrom != null || (context != null && typeFrom != context.board().defaultSite()))
		{
			sb.append("typeFrom=" + typeFrom);
			sb.append(",from=" + from);
		}
		else
			sb.append("from=" + from);

		if (levelFrom != Constants.UNDEFINED)
			sb.append(",levelFrom=" + levelFrom);

		if (typeTo != null || (context != null && typeTo != context.board().defaultSite()))
			sb.append(",typeTo=" + typeTo);

		sb.append(",to=" + to);

		if (levelTo != Constants.UNDEFINED)
			sb.append(",levelTo=" + levelTo);

		if (state != Constants.UNDEFINED)
			sb.append(",state=" + state);

		if (rotation != Constants.UNDEFINED)
			sb.append(",rotation=" + rotation);

		if (value != Constants.UNDEFINED)
			sb.append(",value=" + value);

		if (onStacking)
			sb.append(",stack=" + onStacking);

		if (decision)
			sb.append(",decision=" + decision);

		sb.append(']');

		return sb.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (decision ? 1231 : 1237);
		result = prime * result + from;
		result = prime * result + levelFrom;
		result = prime * result + to;
		result = prime * result + levelTo;
		result = prime * result + state;
		result = prime * result + rotation;
		result = prime * result + value;
		result = prime * result + (onStacking ? 1231 : 1237);
		result = prime * result + ((typeFrom == null) ? 0 : typeFrom.hashCode());
		result = prime * result + ((typeTo == null) ? 0 : typeTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;

		if (!(obj instanceof ActionCopy))
			return false;

		final ActionCopy other = (ActionCopy) obj;

		return (decision == other.decision &&
				from == other.from &&
				levelFrom == other.levelFrom &&
				to == other.to &&
				levelTo == other.levelTo &&
				state == other.state &&
				rotation == other.rotation &&
				value == other.value &&
				onStacking == other.onStacking && typeFrom == other.typeFrom && typeTo == other.typeTo);
	}

	//-------------------------------------------------------------------------

	@Override
	public String getDescription()
	{
		return "Copy";
	}

	@Override
	public String toTurnFormat(final Context context, final boolean useCoords)
	{
		final StringBuilder sb = new StringBuilder();

		String newFrom = from + "";
		if (useCoords)
		{
			final int cid = (typeFrom == SiteType.Cell
					|| typeFrom == null && context.board().defaultSite() == SiteType.Cell) ? context.containerId()[from]
							: 0;
			if (cid == 0)
			{
				final SiteType realType = (typeFrom != null) ? typeFrom : context.board().defaultSite();
				newFrom = context.game().equipment().containers()[cid].topology().getGraphElements(realType).get(from)
						.label();
			}
		}

		if (typeFrom != null && !typeFrom.equals(context.board().defaultSite()))
			sb.append(typeFrom + " " + newFrom);
		else
			sb.append(newFrom);

		if (levelFrom != Constants.UNDEFINED && context.game().isStacking())
			sb.append("/" + levelFrom);

		String newTo = to + "";
		if (useCoords)
		{
			final int cid = (typeTo == SiteType.Cell
					|| typeTo == null && context.board().defaultSite() == SiteType.Cell) ? context.containerId()[to]
							: 0;
			if (cid == 0)
			{
				final SiteType realType = (typeTo != null) ? typeTo : context.board().defaultSite();
				newTo = context.game().equipment().containers()[cid].topology().getGraphElements(realType).get(to)
						.label();
			}
		}

		if (typeTo != null && !typeTo.equals(context.board().defaultSite()))
			sb.append("-" + typeTo + " " + newTo);
		else
			sb.append("-" + newTo);

		if (levelTo != Constants.UNDEFINED)
			sb.append("/" + levelTo);

		if (state != Constants.UNDEFINED)
			sb.append("=" + state);

		if (rotation != Constants.UNDEFINED)
			sb.append(" r" + rotation);

		if (value != Constants.UNDEFINED)
			sb.append(" v" + value);

		if (onStacking)
			sb.append(" ^");

		sb.append(" (Copy)");

		return sb.toString();
	}

	@Override
	public String toMoveFormat(final Context context, final boolean useCoords)
	{
		final StringBuilder sb = new StringBuilder();

		sb.append("(Copy ");

		String newFrom = from + "";
		if (useCoords)
		{
			final int cid = (typeFrom == SiteType.Cell
					|| typeFrom == null && context.board().defaultSite() == SiteType.Cell) ? context.containerId()[from]
							: 0;
			if (cid == 0)
			{
				final SiteType realType = (typeFrom != null) ? typeFrom : context.board().defaultSite();
				newFrom = context.game().equipment().containers()[cid].topology().getGraphElements(realType).get(from)
						.label();
			}
		}

		if (typeFrom != null && typeTo != null
				&& (!typeFrom.equals(context.board().defaultSite()) || !typeFrom.equals(typeTo)))
			sb.append(typeFrom + " " + newFrom);
		else
			sb.append(newFrom);

		if (levelFrom != Constants.UNDEFINED)
			sb.append("/" + levelFrom);

		String newTo = to + "";
		if (useCoords)
		{
			final int cid = (typeTo == SiteType.Cell
					|| typeTo == null && context.board().defaultSite() == SiteType.Cell) ? context.containerId()[to]
							: 0;
			if (cid == 0)
			{
				final SiteType realType = (typeTo != null) ? typeTo : context.board().defaultSite();
				if (to < context.game().equipment().containers()[cid].topology().getGraphElements(realType).size())
					newTo = context.game().equipment().containers()[cid].topology().getGraphElements(realType).get(to)
							.label();
				else // The site is not existing.
					newTo = "??";
			}
		}

		if (typeFrom != null && typeTo != null
				&& (!typeTo.equals(context.board().defaultSite()) || !typeFrom.equals(typeTo)))
			sb.append(" - " + typeTo + " " + newTo);
		else
			sb.append("-" + newTo);

		if (levelTo != Constants.UNDEFINED)
			sb.append("/" + levelTo);

		if (state != Constants.UNDEFINED)
			sb.append(" state=" + state);

		if (rotation != Constants.UNDEFINED)
			sb.append(" rotation=" + rotation);

		if (state != Constants.UNDEFINED)
			sb.append(" state=" + state);

		if (onStacking)
			sb.append(" stack=" + onStacking);

		sb.append(')');

		return sb.toString();
	}

	// -------------------------------------------------------------------------

	@Override
	public SiteType fromType()
	{
		return typeFrom;
	}

	@Override
	public SiteType toType()
	{
		return typeTo;
	}

	@Override
	public int from()
	{
		return from;
	}

	@Override
	public int to()
	{
		return to;
	}

	@Override
	public int levelFrom()
	{
		return (levelFrom == Constants.UNDEFINED) ? Constants.GROUND_LEVEL : levelFrom;
	}

	@Override
	public int levelTo()
	{
		return (levelTo == Constants.UNDEFINED) ? Constants.GROUND_LEVEL : levelTo;
	}

	@Override
	public int state()
	{
		return state;
	}

	@Override
	public int rotation()
	{
		return rotation;
	}

	@Override
	public int value()
	{
		return value;
	}

	@Override
	public int count()
	{
		return 1;
	}

	@Override
	public boolean isStacking()
	{
		return onStacking;
	}

	@Override
	public void setLevelFrom(final int levelA)
	{
		this.levelFrom = levelA;
	}

	// -------------------------------------------------------------------------

	@Override
	public BitSet concepts(final Context context, final Moves movesLudeme)
	{
		final BitSet ludemeConcept = (movesLudeme != null) ? movesLudeme.concepts(context.game()) : new BitSet();

		final int contIdA = typeFrom.equals(SiteType.Cell) ? context.containerId()[from] : 0;
		final int contIdB = typeTo.equals(SiteType.Cell) ? context.containerId()[to] : 0;

		final ContainerState csA = context.state().containerStates()[contIdA];
		final ContainerState csB = context.state().containerStates()[contIdB];

		final int whatA = csA.what(from, typeFrom);
		final int whatB = csB.what(to, typeTo);

		final int whoA = csA.who(from, typeFrom);
		final int whoB = csB.who(to, typeTo);

		final BitSet concepts = new BitSet();

		// ---- Hop concepts

		if (ludemeConcept.get(Concept.HopDecision.id()))
		{
			concepts.set(Concept.HopDecision.id(), true);

			if (whatA != 0)
			{
				final Topology topology = context.topology();
				final TopologyElement fromV = topology.getGraphElements(typeFrom).get(from);

				final List<DirectionFacing> directionsSupported = topology.supportedDirections(RelationType.All,
						typeFrom);
				AbsoluteDirection direction = null;
				int distance = Constants.UNDEFINED;

				for (final DirectionFacing facingDirection : directionsSupported)
				{
					final AbsoluteDirection absDirection = facingDirection.toAbsolute();
					final List<Radial> radials = topology.trajectories().radials(typeFrom, fromV.index(), absDirection);

					for (final Radial radial : radials)
					{
						for (int toIdx = 1; toIdx < radial.steps().length; toIdx++)
						{
							final int toRadial = radial.steps()[toIdx].id();
							if (toRadial == to)
							{
								direction = absDirection;
								distance = toIdx;
								break;
							}
						}
						if (direction != null)
							break;
					}
				}

				if (direction != null)
				{
					final List<Radial> radials = topology.trajectories().radials(typeFrom, fromV.index(), direction);

					for (final Radial radial : radials)
					{
						for (int toIdx = 1; toIdx < distance; toIdx++)
						{
							final int between = radial.steps()[toIdx].id();
							final int whatBetween = csA.what(between, typeFrom);
							final int whoBetween = csA.who(between, typeFrom);
							if (whatBetween != 0)
							{
								if (areEnemies(context, whoA, whoBetween))
								{
									if (whatB == 0)
										concepts.set(Concept.HopEnemyToEmpty.id(), true);
									else
									{
										if (areEnemies(context, whoA, whoB))
											concepts.set(Concept.HopEnemyToEnemy.id(), true);
										else
											concepts.set(Concept.HopEnemyToFriend.id(), true);
									}
								}
								else
								{
									if (whatB == 0)
										concepts.set(Concept.HopFriendToEmpty.id(), true);
									else
									{
										if (areEnemies(context, whoA, whoB))
											concepts.set(Concept.HopFriendToEnemy.id(), true);
										else
											concepts.set(Concept.HopFriendToFriend.id(), true);
									}
								}
							}
						}
					}
				}
			}
		}

		if (ludemeConcept.get(Concept.Hop.id()))
			concepts.set(Concept.Hop.id(), true);

		// ---- Step concepts

		if (ludemeConcept.get(Concept.Step.id()))
			concepts.set(Concept.Step.id(), true);

		if (ludemeConcept.get(Concept.StepDecision.id()))
		{
			concepts.set(Concept.StepDecision.id(), true);
			if (whatA != 0)
			{
				if (whatB == 0)
					concepts.set(Concept.StepToEmpty.id(), true);
				else
				{
					if (areEnemies(context, whoA, whoB))
						concepts.set(Concept.StepToEnemy.id(), true);
					else
						concepts.set(Concept.StepToFriend.id(), true);
				}
			}
		}

		// ---- Leap concepts

		if (ludemeConcept.get(Concept.Leap.id()))
			concepts.set(Concept.Leap.id(), true);

		if (ludemeConcept.get(Concept.LeapDecision.id()))
		{
			concepts.set(Concept.LeapDecision.id(), true);
			if (whatA != 0)
			{
				if (whatB == 0)
					concepts.set(Concept.LeapToEmpty.id(), true);
				else
				{
					if (areEnemies(context, whoA, whoB))
						concepts.set(Concept.LeapToEnemy.id(), true);
					else
						concepts.set(Concept.LeapToFriend.id(), true);
				}
			}
		}

		// ---- Slide concepts

		if (ludemeConcept.get(Concept.Slide.id()))
			concepts.set(Concept.Slide.id(), true);

		if (ludemeConcept.get(Concept.SlideDecision.id()))
		{
			concepts.set(Concept.SlideDecision.id(), true);
			if (whatA != 0)
			{
				if (whatB == 0)
					concepts.set(Concept.SlideToEmpty.id(), true);
				else
				{
					if (areEnemies(context, whoA, whoB))
						concepts.set(Concept.SlideToEnemy.id(), true);
					else
						concepts.set(Concept.SlideToFriend.id(), true);
				}
			}
		}

		// ---- FromTo concepts

		if (ludemeConcept.get(Concept.FromTo.id()))
		{
			if (contIdA == contIdB)
				concepts.set(Concept.FromToWithinBoard.id(), true);
			else
				concepts.set(Concept.FromToBetweenContainers.id(), true);

			if (whatA != 0)
			{
				if (whatB == 0)
					concepts.set(Concept.FromToEmpty.id(), true);
				else
				{
					if (areEnemies(context, whoA, whoB))
						concepts.set(Concept.FromToEnemy.id(), true);
					else
						concepts.set(Concept.FromToFriend.id(), true);
				}
			}
		}

		// ---- Swap Pieces concepts

		if (ludemeConcept.get(Concept.SwapPieces.id()))
			concepts.set(Concept.SwapPieces.id(), true);

		if (ludemeConcept.get(Concept.SwapPiecesDecision.id()))
			concepts.set(Concept.SwapPiecesDecision.id(), true);

		// ---- Sow capture concepts

		if (ludemeConcept.get(Concept.SowCapture.id()))
			concepts.set(Concept.SowCapture.id(), true);

		return concepts;
	}

	/**
	 * @param context The context.
	 * @param who1    The id of a player.
	 * @param who2    The id of a player.
	 * @return True if these players are enemies.
	 */
	public static boolean areEnemies(final Context context, final int who1, final int who2)
	{
		if (who1 == 0 || who2 == 0 || who1 == who2)
			return false;

		if (context.game().requiresTeams())
		{
			final TIntArrayList teamMembers = new TIntArrayList();
			final int tid = context.state().getTeam(who1);
			for (int i = 1; i < context.game().players().size(); i++)
				if (context.state().getTeam(i) == tid)
					teamMembers.add(i);
			return !teamMembers.contains(who2);
		}

		return who1 != who2;
	}

}
