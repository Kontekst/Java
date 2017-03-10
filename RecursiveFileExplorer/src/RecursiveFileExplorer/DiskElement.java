package RecursiveFileExplorer;

import java.io.File;
import java.util.Date;

public abstract class DiskElement implements Comparable<DiskElement>{
   public File file; 
   public  String basename;
   boolean isDir;
   long lastModifiedMS;
   Date lastModified;
   double bytes;
   
protected abstract void print(int depth);

}

