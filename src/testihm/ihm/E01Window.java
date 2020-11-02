/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testihm.ihm;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 *
 * @author yann
 */
public class E01Window extends Frame {

    private int canvasWidth=800;
    private int canvasHeight=600;
    private Canvas canvas;
    private boolean running=true;
    
    public E01Window()  {
            System.out.println("salut1");
        this.init();
    }

    public void init() {
        this.setTitle("affichage et controle de ta m_re");
        this.setLocationRelativeTo(null);
        System.out.println("salut");
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running=false;
            }
});
        createCanvas();
        this.setVisible(true);
        this.run();
      
        
    }

    private void createCanvas() {
        canvas= new Canvas();
        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        canvas.setMaximumSize(new Dimension(canvasWidth,canvasHeight));
        canvas.setMinimumSize(new Dimension(canvasWidth,canvasHeight));
        this.add(canvas);
        this.pack();
    }
    
    public void run() {
        int fps=25;
        long nanoPerFrame=(long) (1000000000/fps);
        long lastTime=System.nanoTime();
        while (running) {
            long nowTime=System.nanoTime();
            if ((nowTime-lastTime)<nanoPerFrame) {
                continue;
            }
            lastTime=nowTime;
            render();
            
            long elapsed=System.nanoTime()-lastTime;
            long milliSleep=(nanoPerFrame-elapsed)/1000000;
            if (milliSleep>0) {
                try {
                    Thread.sleep(milliSleep);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.dispose();
    }
    
    public void render() {
        BufferStrategy bs=canvas.getBufferStrategy();
        if (bs==null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g=null;
        try {
            g=bs.getDrawGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
            bs.show();
        }
        finally {
            if (g!=null) {
                g.dispose();
            }
        }
    }

}
