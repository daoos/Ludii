package app.display.dialogs.visual_editor.model;



import main.grammar.Clause;
import main.grammar.ClauseArg;
import main.grammar.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputInformation {

    private final int INDEX; // index of input in constructor


    private final Clause CLAUSE;
    private final NodeArgument NODE_INPUT;
    private final List<Symbol> SYMBOL_INPUTS;

    public InputInformation(Clause clause, NodeArgument nodeArgument){
        this.CLAUSE = clause;
        this.NODE_INPUT = nodeArgument;
        this.INDEX = getIndex(clause, nodeArgument.arg());
        this.SYMBOL_INPUTS = getPossibleSymbolInputs(nodeArgument);
    }

    private int getIndex(Clause clause, ClauseArg argument){
        return clause.args().indexOf(argument);
    }

    private List<Symbol> getPossibleSymbolInputs(NodeArgument argument){
        if(true){
            return argument.possibleSymbolInputsExpanded();
        }
        List<Symbol> inputs = new ArrayList<>();
        for(ClauseArg arg : argument.args()){
            System.out.println("---- " + arg);
            inputs.add(arg.symbol());
        }
        return inputs.stream().distinct().collect(Collectors.toList()); // remove duplicates
    }

    public List<Symbol> getPossibleSymbolInputs(){
        return SYMBOL_INPUTS;
    }

    public boolean optional(){
        return NODE_INPUT.arg().optional();
    }

    public boolean collection(){
        return NODE_INPUT.arg().nesting() > 0;
    }

    public boolean choice(){
        return NODE_INPUT.size() > 1;
    }

    public Clause clause(){
        return CLAUSE;
    }

    public NodeArgument nodeInput(){
        return NODE_INPUT;
    }

    public int getIndex(){
        return INDEX;
    }


    @Override
    public String toString(){
        return "[" + INDEX + ", " + nodeInput() + "| " + clause() + "]";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof InputInformation){
            InputInformation i = (InputInformation) o;
            return i.nodeInput().equals(nodeInput()) && i.clause().equals(clause()) && i.getIndex() == INDEX;
        }
        return false;
    }

}
