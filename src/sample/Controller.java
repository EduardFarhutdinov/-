package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    private int bias = 1072;
    private int letters = 33;

    @FXML
    private TextArea areaEncrypt;

    @FXML
    private TextField textFieldForKey;

    @FXML
    private TextArea areaOriginal;


    @FXML
    void encryptButton(ActionEvent event) {

        String enc = "";
        String key = textFieldForKey.getText();
        String str = areaOriginal.getText();
        String[] words = str.split("\\s");

        for (String
                subStr : words) {
            enc = enc + encrypt(subStr, key) + " ";
        }

        areaEncrypt.setText(enc);

    }

    @FXML
    void decryptButton(ActionEvent event) {

        String dec = "";
        String key = textFieldForKey.getText();
        String str = areaOriginal.getText();
        String[] words = str.split("\\s");

        for (String
                subStr : words) {
            dec = dec + decrypt(subStr, key) + " ";
        }

        areaEncrypt.setText(dec);
//        areaEncrypt.setText(decrypt(areaOriginal.getText(), textFieldForKey.getText().trim()));
    }

    public String encrypt(final String text, final String key) {

        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * bias) % letters) + bias);
        }
        return encrypt;
    }

    public String decrypt(final String cipher, final String key) {
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = cipher.length(); i < len; i++) {
            decrypt += (char) (((cipher.charAt(i) - key.charAt(i % keyLen) + letters) % letters) + bias);
        }
        return decrypt;
    }

}
