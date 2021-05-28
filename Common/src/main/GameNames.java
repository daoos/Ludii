package main;

/**
 * Game Names.
 *
 * @author Matthew.Stephenson
 */
public enum GameNames
{
	GAME_War("War" , "", ""),
	GAME_Chopsticks("Chopsticks" , "", ""),
	GAME_RockPaperScissors("Rock-Paper-Scissors" , "", ""),
	GAME_Nim("Nim" , "", ""),
	GAME_Bricks("Bricks" , "", ""),
	GAME_FillAPix("Fill A Pix" , "", ""),
	GAME_Futoshiki("Futoshiki" , "", ""),
	GAME_Hashiwokakero("Hashiwokakero" , "", ""),
	GAME_InshinoHeya("Inshi no Heya" , "", ""),
	GAME_Juosan("Juosan" , "", ""),
	GAME_Kakuro("Kakuro" , "", ""),
	GAME_KillerSudoku("Killer Sudoku" , "", ""),
	GAME_LatinSquare("Latin Square" , "", ""),
	GAME_MagicHexagon("Magic Hexagon" , "", ""),
	GAME_MagicSquare("Magic Square" , "", ""),
	GAME_NQueens("N Queens" , "", ""),
	GAME_Nawabari("Nawabari" , "", ""),
	GAME_Slitherlink("Slitherlink" , "", ""),
	GAME_Squaro("Squaro" , "", ""),
	GAME_Sudoku("Sudoku" , "", ""),
	GAME_Takuzu("Takuzu" , "", ""),
	GAME_ChessPuzzle("Chess Puzzle" , "", ""),
	GAME_Insanity("Insanity" , "", ""),
	GAME_KnightsTour("Knight's Tour" , "", ""),
	GAME_NPuzzles("N Puzzles" , "", ""),
	GAME_PegSolitaire("Peg Solitaire" , "", ""),
	GAME_SafePassage("Safe Passage" , "", ""),
	GAME_ShogiPuzzle("Shogi Puzzle" , "", ""),
	GAME_TowerofHanoi("Tower of Hanoi" , "", ""),
	GAME_Chatauranga("Chatauranga" , "", ""),
	GAME_Chaturaji("Chaturaji" , "", ""),
	GAME_Shatranj("Shatranj" , "", ""),
	GAME_AmazonChess("Amazon Chess" , "", ""),
	GAME_CapturetheQueen("Capture the Queen" , "", ""),
	GAME_Chess("Chess" , "", ""),
	GAME_Chex("Chex" , "", ""),
	GAME_DoubleChess("Double Chess" , "", ""),
	GAME_HalfChess("Half Chess" , "", ""),
	GAME_HordeChess("Horde Chess" , "", ""),
	GAME_LoopChess("Loop Chess" , "", ""),
	GAME_LosAlamoschess("Los Alamos chess" , "", ""),
	GAME_MaharajahChess("Maharajah Chess" , "", ""),
	GAME_SkirmishGDL("Skirmish (GDL)" , "", ""),
	GAME_AtariGo("Atari Go" , "", ""),
	GAME_Castello("Castello" , "", ""),
	GAME_Fanorona("Fanorona" , "", ""),
	GAME_Lattaque("L'attaque" , "", ""),
	GAME_LudusLatrunculorum("Ludus Latrunculorum" , "", ""),
	GAME_Mehen("Mehen" , "", ""),
	GAME_Ploy("Ploy" , "", ""),
	GAME_PressUps("Press Ups" , "", ""),
	GAME_Seega("Seega" , "", ""),
	GAME_Surakarta("Surakarta" , "", ""),
	GAME_Triad("Triad" , "", ""),
	GAME_HasamiShogi("Hasami Shogi" , "", ""),
	GAME_Minishogi("Minishogi" , "", ""),
	GAME_Shogi("Shogi" , "", ""),
	GAME_TaikyokuShogi("Taikyoku Shogi" , "", ""),
	GAME_Janggi("Janggi" , "", ""),
	GAME_Manzhouqi("Manzhouqi" , "", ""),
	GAME_Xiangqi("Xiangqi" , "", ""),
	GAME_Adugo("Adugo" , "", ""),
	GAME_BaghChal("Bagh-Chal" , "", ""),
	GAME_Coyote("Coyote" , "", ""),
	GAME_DeCercarLaLiebre("De Cercar La Liebre" , "", ""),
	GAME_Demaladiviyankeliya("Demala diviyan keliya" , "", ""),
	GAME_DiviyanKeliya("Diviyan Keliya" , "", ""),
	GAME_FoxandGeese("Fox and Geese" , "", ""),
	GAME_GowiththeFloe("Go with the Floe" , "", ""),
	GAME_HatDiviyanKeliya("Hat Diviyan Keliya" , "", ""),
	GAME_Koapppawna("Ko-app-paw-na" , "", ""),
	GAME_Komikan("Komikan" , "", ""),
	GAME_Merimuengrimuengdo("Merimueng-rimueng-do" , "", ""),
	GAME_Ponchochotl("Pon chochotl" , "", ""),
	GAME_WolfandSheep("Wolf and Sheep" , "", ""),
	GAME_AleaEvangelii("Alea Evangelii" , "", ""),
	GAME_ArdRi("ArdRi" , "", ""),
	GAME_Brandub("Brandub" , "", ""),
	GAME_Hnefatafl("Hnefatafl" , "", ""),
	GAME_Tablut("Tablut" , "", ""),
	GAME_Tawlbwrdd("Tawlbwrdd" , "", ""),
	GAME_20Squares("20 Squares" , "", ""),
	GAME_58Holes("58 Holes" , "", ""),
	GAME_RoyalGameofUr("Royal Game of Ur" , "", ""),
	GAME_Senet("Senet" , "", ""),
	GAME_XIIScripta("XII Scripta" , "", ""),
	GAME_Camelot("Camelot" , "", ""),
	GAME_ChineseCheckers("Chinese Checkers" , "", ""),
	GAME_Grasshopper("Grasshopper" , "", ""),
	GAME_Halma("Halma" , "", ""),
	GAME_OPatKono("O-Pat-Kono" , "", ""),
	GAME_Salta("Salta" , "", ""),
	GAME_Arimaa("Arimaa" , "", ""),
	GAME_Ashtapada("Ashtapada" , "", ""),
	GAME_BreakThrough("BreakThrough" , "", ""),
	GAME_Conspirateurs("Conspirateurs" , "", ""),
	GAME_GameoftheGoose("Game of the Goose" , "", ""),
	GAME_GyanChaupar("Gyan Chaupar" , "", ""),
	GAME_Jungle("Jungle" , "", ""),
	GAME_KnightThrough("KnightThrough" , "", ""),
	GAME_Pachisi("Pachisi" , "", ""),
	GAME_Sneakthrough("Sneakthrough" , "", ""),
	GAME_Amazons("Amazons" , "", ""),
	GAME_BlueNile("Blue Nile" , "", ""),
	GAME_Contagion("Contagion" , "", ""),
	GAME_FrenchMilitaryGame("French Military Game" , "", ""),
	GAME_Horseshoe("Horseshoe" , "", ""),
	GAME_LGame("L Game" , "", ""),
	GAME_Madelinette("Madelinette" , "", ""),
	GAME_MuTorere("Mu Torere" , "", ""),
	GAME_NoGo("NoGo" , "", ""),
	GAME_QuantumLeap("Quantum Leap" , "", ""),
	GAME_Snowpaque("Snowpaque" , "", ""),
	GAME_Spots("Spots" , "", ""),
	GAME_Susan("Susan" , "", ""),
	GAME_Tron("Tron" , "", ""),
	GAME_Chameleon("Chameleon" , "", ""),
	GAME_Cross("Cross" , "", ""),
	GAME_Gonnect("Gonnect" , "", ""),
	GAME_Havannah("Havannah" , "", ""),
	GAME_Hex("Hex" , "", ""),
	GAME_Trax("Trax" , "", ""),
	GAME_YHex("Y (Hex)" , "", ""),
	GAME_FeedtheDucks("Feed the Ducks" , "", ""),
	GAME_Groups("Groups" , "", ""),
	GAME_LinesofAction("Lines of Action" , "", ""),
	GAME_Manalath("Manalath" , "", ""),
	GAME_Odd("Odd" , "", ""),
	GAME_Omega("Omega" , "", ""),
	GAME_Achi("Achi" , "", ""),
	GAME_AllQueensChess("All Queens Chess" , "", ""),
	GAME_Andantino("Andantino" , "", ""),
	GAME_Connect4("Connect 4" , "", ""),
	GAME_Connect6("Connect6" , "", ""),
	GAME_DaiHasamiShogi("Dai Hasami Shogi" , "", ""),
	GAME_Dala("Dala" , "", ""),
	GAME_Dra("Dra" , "", ""),
	GAME_FiveMensMorris("Five Men's Morris" , "", ""),
	GAME_Gomoku("Gomoku" , "", ""),
	GAME_NineMensMorris("Nine Men's Morris" , "", ""),
	GAME_Pentalath("Pentalath" , "", ""),
	GAME_Picaria("Picaria" , "", ""),
	GAME_PushingmeXO("Pushing me XO" , "", ""),
	GAME_RoundMerels("Round Merels" , "", ""),
	GAME_Shisima("Shisima" , "", ""),
	GAME_SixMensMorris("Six Men's Morris" , "", ""),
	GAME_Spline("Spline" , "", ""),
	GAME_Squava("Squava" , "", ""),
	GAME_TantFant("Tant Fant" , "", ""),
	GAME_ThreeMensMorris("Three Men's Morris" , "", ""),
	GAME_TicTacFour("Tic-Tac-Four" , "", ""),
	GAME_TicTacMo("Tic-Tac-Mo" , "", ""),
	GAME_TicTacToe("Tic-Tac-Toe" , "", ""),
	GAME_TwelveMensMorris("Twelve Men's Morris" , "", ""),
	GAME_Yavalade("Yavalade" , "", ""),
	GAME_Yavalanchor("Yavalanchor" , "", ""),
	GAME_Yavalath("Yavalath" , "", ""),
	GAME_Kensington("Kensington" , "", ""),
	GAME_Ataxx("Ataxx" , "", ""),
	GAME_DotsandBoxes("Dots and Boxes" , "", ""),
	GAME_Go("Go" , "", ""),
	GAME_Lotus("Lotus" , "", ""),
	GAME_Reversi("Reversi" , "", ""),
	GAME_Alquerque("Alquerque" , "", ""),
	GAME_HewakamKeliya("Hewakam Keliya" , "", ""),
	GAME_Kharbaga("Kharbaga" , "", ""),
	GAME_KotuEllima("Kotu Ellima" , "", ""),
	GAME_MogulPutthan("Mogul Putt'han" , "", ""),
	GAME_Peralikatuma("Peralikatuma" , "", ""),
	GAME_Terhuchu("Terhuchu" , "", ""),
	GAME_Tuknanavuhpi("Tuknanavuhpi" , "", ""),
	GAME_Zamma("Zamma" , "", ""),
	GAME_BrazilianDraughts("Brazilian Draughts" , "", ""),
	GAME_CanadianDraughts("Canadian Draughts" , "", ""),
	GAME_Dama("Dama" , "", ""),
	GAME_EnglishDraughts("English Draughts" , "", ""),
	GAME_HexDame("HexDame" , "", ""),
	GAME_InternationalDraughts("International Draughts" , "", ""),
	GAME_Lasca("Lasca" , "", ""),
	GAME_Awithlaknannai("Awithlaknannai" , "", ""),
	GAME_Dashguti("Dash-guti" , "", ""),
	GAME_EgaraGuti("Egara Guti" , "", ""),
	GAME_Felli("Felli" , "", ""),
	GAME_Golekuish("Gol-ekuish" , "", ""),
	GAME_Konane("Konane" , "", ""),
	GAME_LauKataKati("Lau Kata Kati" , "", ""),
	GAME_NeiPatKono("Nei-Pat-Kono" , "", ""),
	GAME_Pretwa("Pretwa" , "", ""),
	GAME_BaokiarabuZanzibar1("Bao ki arabu (Zanzibar 1)" , "", ""),
	GAME_BaokiarabuZanzibar2("Bao ki arabu (Zanzibar 2)" , "", ""),
	GAME_Hawalis("Hawalis" , "", ""),
	GAME_HusDamara("Hus (Damara)" , "", ""),
	GAME_Mefuvha("Mefuvha" , "", ""),
	GAME_MuvalavalaB("Muvalavala B" , "", ""),
	GAME_OwelaBenguela("Owela (Benguela)" , "", ""),
	GAME_Akookwe("Ako okwe" , "", ""),
	GAME_AwliOnnamOttjin("Aw-li On-nam Ot-tjin" , "", ""),
	GAME_Bosh("Bosh" , "", ""),
	GAME_Dabuda("Dabuda" , "", ""),
	GAME_DasBohnenspiel("Das Bohnenspiel" , "", ""),
	GAME_EnGehe("En Gehe" , "", ""),
	GAME_EnglishWariStLucia("English Wari (St. Lucia)" , "", ""),
	GAME_FrenchWari("French Wari" , "", ""),
	GAME_Halusa("Halusa" , "", ""),
	GAME_Kalah("Kalah" , "", ""),
	GAME_Kara("Kara" , "", ""),
	GAME_MangalaTurkey("Mangala (Turkey)" , "", ""),
	GAME_Mbangbi("Mbangbi" , "", ""),
	GAME_Meusueb("Meusueb" , "", ""),
	GAME_Oware("Oware" , "", ""),
	GAME_ToguzKumalak("Toguz Kumalak" , "", ""),
	GAME_UmelBagara("Um el-Bagara" , "", "");

	//-------------------------------------------------------------------------

	private String ludName;
	private String nativeName;
	private String[] aliases;

	//--------------------------------------------------------------------------

	GameNames(final String ludName, final String nativeName, final String[] aliases)
	{
		this.ludName = ludName;
		this.nativeName = nativeName;
		this.aliases = aliases;
	}
	
	GameNames(final String ludName, final String nativeName, final String aliases)
	{
		this.ludName = ludName;
		this.nativeName = nativeName;
		this.aliases = new String[]{aliases};
	}

	//-------------------------------------------------------------------------

    public String ludName()
    {
        return ludName;
    }

    public String nativeName()
    {
        return nativeName;
    }

    public String[] aliases()
    {
    	return aliases;
    }

}
