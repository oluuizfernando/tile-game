package tilegame.display;

import javax.swing.JFrame;
import java.awt.*;


public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;
    // width, height -> pixels

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        // para o jogo nao continuar rodando ao fechar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // centraliza a exibição da tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack(); // pra ver o canvas
    }


    public Canvas getCanvas() {
        return canvas;
    }

}
