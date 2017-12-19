package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class MainFrame extends JFrame {

    JPanel contentPanel,jp1,jp2;
    JButton hillButton,playfireButton,caesarButton;

    JPanel caesarPanel = new CaesarFrame();
    JPanel hillPanel = new HillFrame();
    JPanel playfirePanel = new PlayfireFrame();

    int k = 1;

    public void removePanel(){
        if(k == 1){
            contentPanel.remove(caesarPanel);
        }else if(k== 2){
            contentPanel.remove(playfirePanel);
        }else {
            contentPanel.remove(hillPanel);
        }
    }

    public MainFrame(){
        contentPanel = new JPanel();
        jp1 = new JPanel();
        //jp2 = caesarPanel;//默认凯撒密码
        hillButton = new JButton("hill");
        playfireButton = new JButton("playfire");
        caesarButton = new JButton("caesar");

        jp1.add(caesarButton);
        jp1.add(playfireButton);
        jp1.add(hillButton);

        contentPanel.add(jp1);
        contentPanel.add(caesarPanel);

        caesarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel();
                contentPanel.add(caesarPanel);
                k =1;
                contentPanel.updateUI();
                contentPanel.repaint();
            }
        });


        playfireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel();
                contentPanel.add(playfirePanel);
                k =2;
                contentPanel.updateUI();
                contentPanel.repaint();
            }
        });

        hillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel();
                contentPanel.add(hillPanel);
                k =3;
                contentPanel.updateUI();
                contentPanel.repaint();
            }
        });

        this.add(contentPanel);
        this.setTitle("3115005304蔡炳钦");
        this.setSize(500,620);
        this.setLocation(300,100);
       this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
