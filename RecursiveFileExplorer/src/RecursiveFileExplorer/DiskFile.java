package RecursiveFileExplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiskFile extends DiskElement {
    public  DiskFile(String absolutePath) { 
        File tempFile = new File(absolutePath);
        
        isDir=false; 
        basename = tempFile.getName();
        file = new File(absolutePath);
        lastModifiedMS = file.lastModified();
        lastModified = new Date(lastModifiedMS);
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
        
        text=text + "  P         ";

        text=text + new SimpleDateFormat("yyyy-MM-dd").format( lastModified);
        
        System.out.println(text);
        
    }

    @Override
    public int compareTo(DiskElement secondObject) {
      //  boolean alphabetical=false;
        //System.out.println(this.basename + " " + secondObject.basename);
       // if(this.basename.compareTo(secondObject.basename)<0)
       //     alphabetical=true;
      //      if(alphabetical ){
      ///          System.out.println(this.basename + " " + secondObject.basename);
      //          return -1;
       //     }else{
                 return 1;
       //     }

    }
}
