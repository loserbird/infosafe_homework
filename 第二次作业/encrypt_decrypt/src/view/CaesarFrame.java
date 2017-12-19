package view;

import service.Caesar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class CaesarFrame extends JPanel{

    JPanel textPanel,keyPanel,encryptPanel,decryptPanel,contentPanel;

    JLabel textLable,keyLable;
    JButton encryptButton,decryptButton;

    JTextField textField,keyField,encryptField,decryptField;

    Caesar caesar = new Caesar();

    int key;//密钥，1-25
    String encryptStr;//密文


    public CaesarFrame(){
        textPanel = new JPanel();
        keyPanel = new JPanel();
        encryptPanel = new JPanel();
        decryptPanel = new JPanel();
        contentPanel = new JPanel();
        textLable = new JLabel("明文:");
        keyLable = new JLabel("密钥:");
        encryptButton = new JButton("加密");
        decryptButton = new JButton("解密");
        textField = new JTextField(24);
        keyField = new JTextField(24);
        textField.setText("abc");
        keyField.setText("3");
        encryptField = new JTextField(24);
        decryptField = new JTextField(24);

        textPanel.add(textLable);
        textPanel.add(textField);

        keyPanel.add(keyLable);
        keyPanel.add(keyField);

        encryptPanel.add(encryptButton);
        encryptPanel.add(encryptField);
        decryptPanel.add(decryptButton);
        decryptPanel.add(decryptField);

        contentPanel.setLayout(new GridLayout(4,1));
        contentPanel.add(textPanel);
        contentPanel.add(keyPanel);
        contentPanel.add(encryptPanel);
        contentPanel.add(decryptPanel);

        this.add(contentPanel);
        /*this.setSize(400,400);
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
                    JOptionPane.showMessageDialog(null, "密钥不能为空（1-25）");
                } else{
                    int k;
                    try {
                        k = Integer.parseInt(keystr);
                        if(k<1 || k>25) throw new Exception("密钥错误");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "密钥错误，为数字1-25");
                        ex.printStackTrace();
                        return;
                    }
                    String encryptText = caesar.encrypt(text,k);
                    key = k;
                    encryptStr = encryptText;
                    encryptField.setText(encryptText);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(encryptStr == null || "".equals(encryptStr.trim())){
                    JOptionPane.showMessageDialog(null, "请先加密");
                }else if(key == 0){
                    JOptionPane.showMessageDialog(null, "请先加密");
                } else{
                    String decryptText = caesar.decrypt(encryptStr,key);
                    decryptField.setText(decryptText);
                }
            }
        });
    }

    public static void main(String[] args) {
        new CaesarFrame();
    }

}
