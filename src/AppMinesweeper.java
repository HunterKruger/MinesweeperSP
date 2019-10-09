
import javax.swing.*;


public class AppMinesweeper extends JFrame {

    public static final int PORT = 10000;
    public static final String HOSTNAME = "localhost";
    public static final String PSEUDO = "pseudo";

    private MineField mineField = new MineField("EASY");
    private int numMineDiscovered = 0;
    private IhmMinesweeper ihmMinesweeper;
    private boolean started = false;
    private boolean lost = false;

    public void resetNumMineDiscovered() {
        this.numMineDiscovered = 0;
    }

    public int getNumMineDiscovered() {
        return numMineDiscovered;
    }

    public void increaseNumMineDiscovered() {
        numMineDiscovered++;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean isLost() {
        return lost;
    }

    public IhmMinesweeper getIhmMinesweeper() {
        return ihmMinesweeper;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public AppMinesweeper() {
        mineField.showText();
        ihmMinesweeper = new IhmMinesweeper(this);
        setContentPane(ihmMinesweeper);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        mineField.showTextWithMinesNum();
    }

    public MineField getMineField() {
        return mineField;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Minesweeper!");
        new AppMinesweeper();
    }

    public void quit() {
        int response = JOptionPane.showConfirmDialog(null,
                "Are you sure to quit the game?", "Bye-Bye",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void newgame() {
        newgame(mineField.getLevel());
    }

    public void newgame(String level) {
        mineField.initChamp(level);
        mineField.showText();
        ihmMinesweeper = new IhmMinesweeper(this);
        setContentPane(ihmMinesweeper);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        ihmMinesweeper.getTime().stopCounter();
        this.setLost(false);
        this.setStarted(false);

        setVisible(true);
        mineField.showTextWithMinesNum();
        for (int i = 0; i < mineField.getDimension(); i++) {
            for (int j = 0; j < mineField.getDimension(); j++) {
                ihmMinesweeper.getTabCases()[i][j].newgame();
            }
        }
        resetNumMineDiscovered();
    }

    public void levelInformation() {
        int response = JOptionPane.showConfirmDialog(null,
                "Easy:10*10, 20 mines \nNormal:20*20, 80 mines\nHard:30*30, 350 mines", "Level information",
                JOptionPane.CLOSED_OPTION);
        if (response == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    public void aboutAuthor() {
        int response = JOptionPane.showConfirmDialog(null,
                "FENG Yuan\nform EMSE", "About author",
                JOptionPane.CLOSED_OPTION);
        if (response == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    public boolean isWin() {
        System.out.println("numMineDiscovered=" + getNumMineDiscovered());
        boolean win = numMineDiscovered + mineField.getNumMines() + 1 == mineField.getDimension() * mineField.getDimension();
//        if(win){
//            saveResult();
//        }
        return win;
    }


}
