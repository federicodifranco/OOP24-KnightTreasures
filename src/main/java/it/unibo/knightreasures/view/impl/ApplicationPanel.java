

public class ApplicationPanel extends JPanel {

    private MouseInputs MouseInputs;
    private float xDelta = 10, yDelta = 100;
    private BufferedImage img;

    public ApplicationPanel() {
        mouseInputs = new MouseInputs(this);

        importImg();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg(){
        img = ResourceFuncUtilities.loadSources("menu");
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value; 
    }

    public void resetPosition(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 100, 100, null);

    }

}
