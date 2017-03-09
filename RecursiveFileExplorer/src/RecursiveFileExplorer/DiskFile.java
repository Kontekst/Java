/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecursiveFileExplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kontekstowy
 */
public class DiskFile extends DiskElement {
    public  DiskFile(String absolutePath) {
        File tempFile = new File(absolutePath);
        
        basename = tempFile.getName();
        file = new File(absolutePath);
        mtime = new Date(tempFile.lastModified());
    }
     
    @Override
      protected void print(int depth){

        String text = "";
        for (int i = 0; i < depth; i++) {
             text=text + " ";
        }

        if (isDir==true) {
            text=text + "\\";
        } else {
            text=text + "| ";
        }
        
        text=text + basename;
        
        text=text + "        ";
        
        text=text + new SimpleDateFormat("yyyy-MM-dd").format(mtime);
        
        System.out.println(text);
        
        
        
    }
}
