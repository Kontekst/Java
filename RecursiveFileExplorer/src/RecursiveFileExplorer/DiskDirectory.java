package RecursiveFileExplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiskDirectory extends DiskElement{
    Set<DiskElement> children =new HashSet<>();
    
    public DiskDirectory(String path, boolean sorted) {
        File tempFile = new File(path);
        basename = tempFile.getName();
        
        if (tempFile.isDirectory()) {
            isDir=true;
        } else {
            isDir=false;
        }

        mtime = new Date(tempFile.lastModified());
      
        File[] subfiles = tempFile.listFiles();
        if (subfiles != null) {
            for (File subfile : subfiles) {
                if (subfile.isDirectory()){ 
                    children.add(new DiskDirectory(subfile.getAbsolutePath(),sorted));
                }else{
                   children.add(new DiskFile(subfile.getAbsolutePath()));
                }
            }
             
        }
    }
    
    
    @Override
    protected void print( int depth){
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
        
        text=text + "  F         ";
        
        text=text + new SimpleDateFormat("yyyy-MM-dd").format(mtime);
        
        System.out.println(text);
        for (DiskElement filee : children) {
            filee.print(depth + 1);
        }
    }    
    
    
    
    
    
}
