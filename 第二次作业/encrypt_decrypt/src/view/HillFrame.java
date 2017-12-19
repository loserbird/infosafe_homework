package view;


import service.Hill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class HillFrame extends JPanel{

    JPanel textPanel,encryptPanel,decryptPanel,matrixPanel,contentPanel;

    JLabel textLable,matrixLabel;
    JButton encryptButton,decryptButton;

    JTextField textField,encryptField,decryptField;
    JTextArea matrixArea;

   Hill hill = new Hill();

    public int[][] matrix;;//加密矩阵
    List<Integer[]> encryptStr;//密文


    public HillFrame(){
        textPanel = new JPanel();
        encryptPanel = new JPanel();
        decryptPanel = new JPanel();
        matrixPanel = new JPanel();
        contentPanel = new JPanel();
        textLable = new JLabel("明文:");
        matrixLabel = new JLabel("加密矩阵");
        encryptButton = new JButton("加密");
        decryptButton = new JButton("解密");
        textField = new JTextField(24);
        textField.setText("pay more money");
        encryptField = new JTextField(24);
        decryptField = new JTextField(24);
        matrixArea = new JTextArea(5,20);

        textPanel.add(textLable);
        textPanel.add(textField);


        encryptPanel.add(encryptButton);
        encryptPanel.add(encryptField);
        decryptPanel.add(decryptButton);
        decryptPanel.add(decryptField);
        matrixPanel.add(matrixLabel);
        matrixPanel.add(matrixArea);

        contentPanel.setLayout(new GridLayout(4,1));
        contentPanel.add(textPanel);
        contentPanel.add(encryptPanel);
        contentPanel.add(decryptPanel);
        contentPanel.add(matrixPanel);
        this.add(contentPanel);
       /* this.setSize(400,400);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);*/

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if(text == null || "".equals(text.trim())){
                    JOptionPane.showMessageDialog(null, "明文不能为空");
                }else{
                   //随机生成加密矩阵
                    matrix = hill.generateSec();
                    //将矩阵显示到界面中
                    StringBuilder content = new StringBuilder();
                    for(int i=0;i<matrix.length;i++){
                        for(int j=0;j<matrix[0].length;j++){
                            content.append(matrix[i][j]+"  ");
                        }
                        content.append("\n");
                    }
                    matrixArea.setText(content.toString());
                    //加密
                    List<Integer[]> list = hill.encrypt(text);
                    List<String> result = hill.change(list);
                    StringBuilder sb = new StringBuilder();
                    for(String s : result){
                        sb.append(s+" ");
                    }
                    String encryptText = sb.toString();
                    encryptStr = list;
                    encryptField.setText(encryptText);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(encryptStr == null){
                    JOptionPane.showMessageDialog(null, "请先加密");
                }else if(matrix == null){
                    JOptionPane.showMessageDialog(null, "请先加密");
                } else{
                    List<Integer[]> list = hill.decrypt(encryptStr);
                    List<String> result = hill.change(list);
                    StringBuilder sb = new StringBuilder();
                    for(String s : result){
                        sb.append(s+" ");
                    }
                    String decryptText = sb.toString();
                    decryptField.setText(decryptText);
                }
            }
        });
    }

    public static void main(String[] args) {
        new HillFrame();
    }

}
