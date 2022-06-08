package app.display.dialogs.visual_editor.view.panels;


import app.display.dialogs.visual_editor.LayoutManagement.LayoutManager.LayoutHandler;
import app.display.dialogs.visual_editor.model.DescriptionGraph;
import app.display.dialogs.visual_editor.model.LudemeNode;
import app.display.dialogs.visual_editor.view.components.ludemenodecomponent.LudemeNodeComponent;
import app.display.dialogs.visual_editor.view.components.ludemenodecomponent.inputs.LConnectionComponent;
import app.display.dialogs.visual_editor.view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;
import main.grammar.Symbol;

public interface IGraphPanel {
    void drawGraph(DescriptionGraph graph);
    DescriptionGraph getGraph();
    void startNewConnection(LConnectionComponent source);
    void cancelNewConnection();
    void addConnection(LConnectionComponent source, LIngoingConnectionComponent target);
    LudemeNodeComponent getNodeComponent(LudemeNode node);
    LudemeNode addNode(Symbol symbol, int x, int y, boolean connect);
    void showAllAvailableLudemes(int x, int y);
    void removeAllConnections(LudemeNode node);
    void removeConnection(LudemeNode node, LConnectionComponent connection);
    void clickedOnNode(LudemeNodeComponent lnc);
    void removeNode(LudemeNode node);
    LayoutHandler getLayoutHandler();
    void updateGraph();
    void repaint();
}
