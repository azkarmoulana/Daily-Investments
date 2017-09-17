/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class_Files;

import java.awt.Toolkit;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Ishan Darshana
 */
public class MessageIcons {

    private ImageIcon error = new ImageIcon(MessageIcons.class.getResource("/Images/error.png"));
    private ImageIcon info = new ImageIcon(MessageIcons.class.getResource("/Images/info.png"));
    private ImageIcon ok = new ImageIcon(MessageIcons.class.getResource("/Images/ok.png"));
    private String frameIcon = "/Images/LogoIcon.png";
    
   
    


    /**
     * @return the error
     */
    public ImageIcon getError() {
        return error;
    }

    /**
     * @return the info
     */
    public ImageIcon getInfo() {
        return info;
    }

    /**
     * @return the ok
     */
    public ImageIcon getOk() {
        return ok;
    }

    /**
     * @return the frameIcon
     */
    public String getFrameIcon() {
        return frameIcon;
    }

}
