package view;


import service.PlayFire;

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
public class PlayfireFrame extends JPanel{

    JPanel textPanel,keyPanel,encryptPanel,decryptPanel,matrixPanel,contentPanel;

    JLabel textLable,keyLable,matrixLabel;
    JButton encryptButton,decryptButton;

    JTextField textField,keyField,encryptField,decryptField;
    JTextArea matrixArea;

    PlayFire palyFire = new PlayFire();

    public char[][] matrix;;//加密矩阵
    List<String> encryptStr;//密文


    public PlayfireFrame(){
        textPanel = new JPanel();
        keyPanel = new JPanel();
        encryptPanel = new JPanel();
        decryptPanel = new JPanel();
        matrixPanel = new JPanel();
        contentPanel = new JPanel();
        textLable = new JLabel("明文:");
        keyLable = new JLabel("关键词:");
        matrixLabel = new JLabel("加密矩阵");
        encryptButton = new JButton("加密");
        decryptButton = new JButton("解密");
        textField = new JTextField(24);
        keyField = new JTextField(24);
        textField.setText("we are discovered save yourself");
        keyField.setText("monarchy");
        encryptField = new JTextField(24);
        decryptField = new JTextField(24);
        matrixArea = new JTextArea(5,20);

        textPanel.add(textLable);
        textPanel.add(textField);

        keyPanel.add(keyLable);
        keyPanel.add(keyField);

        encryptPanel.add(encryptButton);
        encryptPanel.add(encryptField);
        decryptPanel.add(decryptButton);
        decryptPanel.add(decryptField);
        matrixPanel.add(matrixLabel);
        matrixPanel.add(matrixArea);

        contentPanel.setLayout(new GridLayout(5,1));
        contentPanel.add(textPanel);
        contentPanel.add(keyPanel);
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
                String keystr = keyField.getText();
                if(text == null || "".equals(text.trim())){
                    JOptionPane.showMessageDialog(null, "明文不能为空");
                }else if(keystr == null || "".equals(keystr.trim())){
                    JOptionPane.showMessageDialog(null, "关键词不能为空");
                } else{
                   //构造加密矩阵
                    matrix = palyFire.makeMatrix(keystr);
                    //将矩阵显示到界面中
                    StringBuilder content = new StringBuilder();
                    for(int i=0;i<matrix.length;i++){
                        for(int j=0;j<matrix[0].length;j++){
                            content.append(matrix[i][j]+" ");
                        }
                        content.append("\n");
                    }
                    matrixArea.setText(content.toString());
                    //加密
                    List<String> result = palyFire.encrypt(text);
                    StringBuilder sb = new StringBuilder();
                    for(String s : result){
                        sb.append(s+" ");
                    }
                    String encryptText = sb.toString();
                    encryptStr = result;
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
                    List<String> result = palyFire.decrypt(encryptStr);
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
        new PlayfireFrame();
    }

}
