package RecursiveFileExplorer;

import java.io.File;

public abstract class DiskElement {
   public File file; 
   public Object mtime;
   public Object permissions;
   public   String basename;
   boolean isDir;

protected abstract void print( int depth);

}

