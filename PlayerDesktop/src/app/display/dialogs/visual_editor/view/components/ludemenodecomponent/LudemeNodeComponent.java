package app.display.dialogs.visual_editor.view.components.ludemenodecomponent;


import app.display.dialogs.visual_editor.handler.Handler;
import app.display.dialogs.visual_editor.model.LudemeNode;
import app.display.dialogs.visual_editor.view.DesignPalette;
import app.display.dialogs.visual_editor.view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;
import app.display.dialogs.visual_editor.view.components.ludemenodecomponent.inputs.LInputArea;
import app.display.dialogs.visual_editor.view.panels.IGraphPanel;
import main.grammar.Clause;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LudemeNodeComponent extends JPanel {

    protected int x, y;
    private ImmutablePoint position = new ImmutablePoint(x, y);
    private final LudemeNode LUDEME_NODE;
    private final IGraphPanel GRAPH_PANEL;

    public boolean dynamic;

    private LHeader header;
    private LInputArea inputArea;

    public LudemeNodeComponent(LudemeNode ludemeNode, IGraphPanel graphPanel){
        this.LUDEME_NODE = ludemeNode;
        this.GRAPH_PANEL = graphPanel;

        this.x = (int) ludemeNode.getPos().getX();
        this.y = (int) ludemeNode.getPos().getY();

        setLayout(new BorderLayout());

        System.out.println("[LNC] Provided Inputs");
        for(int i = 0; i < ludemeNode.providedInputs().length; i++){
            if(ludemeNode.providedInputs()[i] != null)
            System.out.println("["+i+", " +  ludemeNode.providedInputs()[i].getClass().getName() + "] " + ludemeNode.providedInputs()[i]);
            else System.out.println("["+i+", null]");
        }

        this.dynamic = ludemeNode.isDynamic();

        // LNC cannot be dynamic if its a terminal node or all constructors have size one
        if(!ludemeNode.canBeDynamic()) dynamic = false;


        // sync dynamic boolean with model node
        ludemeNode.setDynamic(dynamic); // TODO: Shouldnt be done here

        header = new LHeader(this);
        inputArea = new LInputArea(this);

        add(header, BorderLayout.NORTH);
        add(inputArea, BorderLayout.CENTER);

        setLocation(x,y);

        int preferredHeight = inputArea.getPreferredSize().height + header.getPreferredSize().height;

        setMinimumSize(new Dimension(getWidth(), preferredHeight));
        setPreferredSize(new Dimension(getMinimumSize().width, preferredHeight));
        setSize(new Dimension(getMinimumSize().width, preferredHeight));

        setSize(getPreferredSize());
        ludemeNode.setWidth(getWidth());
        ludemeNode.setHeight(getHeight());

        addMouseMotionListener(dragListener);
        addMouseListener(mouseListener);

        updatePositions();

        revalidate();
        repaint();
        setVisible(true);

    }


    public void changeClause(Clause clause){
        Handler.updateCurrentClause(getGraphPanel().getGraph(), node(), clause);
        inputArea.updateConstructor(); // TODO: update input area
        revalidate();
        repaint();

    }


    public void updatePositions() {
        if(inputArea == null || header == null) {
            return;
        }
        position.update(getLocation());
        inputArea.updatePosition();
        header.updatePosition();
    }

    public void updateLudemePosition()
    {
        LudemeNodeComponent.this.setLocation((int)LUDEME_NODE.getPos().getX(), (int)LUDEME_NODE.getPos().getY());
    }

    /**
     * Method which syncs the Ludeme Node Component with provided inputs (stored in the Ludeme Node).
     * Called when drawing a graph.
     */
    public void updateProvidedInputs(){
        inputArea.updateProvidedInputs();
    }

    public void updateComponent(){
        if(inputArea == null) {
            return;
        }

        int preferredHeight = inputArea.getPreferredSize().height + header.getPreferredSize().height;

        setPreferredSize(new Dimension(getMinimumSize().width, preferredHeight));
        setSize(getPreferredSize());

        repaint();
        revalidate();
    }

    public LudemeNode node(){
        return LUDEME_NODE;
    }

    public LInputArea getInputArea(){
        return inputArea;
    }

    public int getWidth(){
        return DesignPalette.NODE_WIDTH;
    }

    public IGraphPanel getGraphPanel(){
        return GRAPH_PANEL;
    }

    public ImmutablePoint getPosition(){
        return position;
    }

    public LIngoingConnectionComponent getIngoingConnectionComponent(){
        return header.getIngoingConnectionComponent();
    }

    public void changeDynamic(){
        dynamic = !dynamic;
        getInputArea().setDynamic(dynamic);
        node().setDynamic(dynamic);
    }

    // Drag Listener

    MouseMotionListener dragListener = new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            e.translatePoint(e.getComponent().getLocation().x - LudemeNodeComponent.this.x, e.getComponent().getLocation().y -LudemeNodeComponent.this.y);
            LudemeNodeComponent.this.setLocation(e.getX(),e.getY());
            updatePositions();
            getGraphPanel().repaint();
        }
    };

    // Mouse Listener
    MouseListener mouseListener = new MouseAdapter() {

        private void openPopupMenu(MouseEvent e){
            JPopupMenu popupMenu = new NodePopupMenu(LudemeNodeComponent.this, LudemeNodeComponent.this.getGraphPanel());
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }

        //TODO: do we need mousePressed listener?

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            LudemeNodeComponent.this.x = e.getX();
            LudemeNodeComponent.this.y = e.getY();
            Handler.updatePosition(getGraphPanel().getGraph(), node(), getX(), getY());

            if(e.getButton() == MouseEvent.BUTTON3){
                openPopupMenu(e);
                getGraphPanel().cancelNewConnection();
            }
            else {
                getGraphPanel().clickedOnNode(LudemeNodeComponent.this);
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            LudemeNodeComponent.this.x = e.getX();
            LudemeNodeComponent.this.y = e.getY();
            Handler.updatePosition(getGraphPanel().getGraph(), node(), getX(), getY());

            if(e.getButton() == MouseEvent.BUTTON3){
                openPopupMenu(e);
                getGraphPanel().cancelNewConnection();
            }
            else {
                getGraphPanel().clickedOnNode(LudemeNodeComponent.this);
            }
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int preferredHeight = inputArea.getPreferredSize().height + header.getPreferredSize().height;

        setMinimumSize(new Dimension(getWidth(), preferredHeight));
        setPreferredSize(new Dimension(getMinimumSize().width, preferredHeight));
        setSize(new Dimension(getMinimumSize().width, preferredHeight));

        setSize(getPreferredSize());
        LUDEME_NODE.setWidth(getWidth());
        LUDEME_NODE.setHeight(getHeight());

        setBackground(DesignPalette.BACKGROUND_LUDEME_BODY);
        setBorder(DesignPalette.LUDEME_NODE_BORDER);
    }

}
