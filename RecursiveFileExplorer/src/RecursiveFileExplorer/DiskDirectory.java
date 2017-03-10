package RecursiveFileExplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

class SizeComparator implements Comparator<DiskElement> {


    @Override
    public int compare(DiskElement firstObject, DiskElement secondObject) {
        //  boolean alphabetical=false;
        if(firstObject.lastModified.compareTo(secondObject.lastModified)<0){
           
            return -1;
        }
       
        
        return 1;
    }
}


public class DiskDirectory extends DiskElement {
    HashSet<DiskElement> notSortedHashChildren =new HashSet<>();
    TreeSet<DiskElement> TreeChildren =new TreeSet<DiskElement>();
    Set<DiskElement> ComparatorTreeChildren =new TreeSet<>( new SizeComparator() );
    
    File[] files;
    String mode;
    
    public DiskDirectory(String path, boolean sorted,String argMode) {
        mode=argMode;
        file = new File(path);
        basename =  file .getName();
        files = file.listFiles();
        isDir=true;
        lastModifiedMS = file.lastModified();
        lastModified = new Date(lastModifiedMS);
       bytes=file.length();
       
        File[] subfiles =  file .listFiles();
        if (subfiles != null) {
            for (File subfile : subfiles) {
                 switch(mode){
                     case "1" :
                         if (subfile.isDirectory()){ 
                             notSortedHashChildren.add(new DiskDirectory(subfile.getAbsolutePath(),sorted,mode));
                         }else{
                             notSortedHashChildren.add(new DiskFile(subfile.getAbsolutePath()));
                         }
                         break;
                         
                     case "2":
                          if (subfile.isDirectory()){ 
                             TreeChildren.add(new DiskDirectory(subfile.getAbsolutePath(),sorted,mode));
                         }else{
                             TreeChildren.add(new DiskFile(subfile.getAbsolutePath()));
                         }
                         break;
                     case "3":
                         if (subfile.isDirectory()){ 
                             ComparatorTreeChildren.add(new DiskDirectory(subfile.getAbsolutePath(),sorted,mode));
                         }else{
                             ComparatorTreeChildren.add(new DiskFile(subfile.getAbsolutePath()));
                         }
                         break;
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
        
        text=text + new SimpleDateFormat("yyyy-MM-dd").format( lastModified);
        
        text=text +"  " +bytes;
        
        System.out.println(text);
        switch(mode){
            
            case "1":
                for (DiskElement filee : notSortedHashChildren) {
                    filee.print(depth + 1);
                }
                break;
                
            case "2":
                  for (DiskElement filee : TreeChildren) {
                    filee.print(depth + 1);
                }
                break;
            case "3":
                  for (DiskElement filee : ComparatorTreeChildren) {
                    filee.print(depth + 1);
                }
                break;
                
                    
                
        }
    }   
    
     @Override public int hashCode() {
         int hash = 3; hash = 53 * hash + Objects.hashCode(this.basename);
         hash = 53 * hash + Objects.hashCode(this.files); 
         hash = 53 * hash + Objects.hashCode(this.isDir);
         hash = 53 * hash + Objects.hashCode(this.lastModifiedMS );
         hash = 53 * hash + Objects.hashCode(this.file);
         return hash; 
     } 
     
     
       
     @Override public boolean equals(Object obj) {
         if (obj == null || getClass() != obj.getClass()) {
             return false; 
         } 
         
         final DiskFile other = (DiskFile) obj;
         
         if (!Objects.equals(this.basename, other.basename)) {
             return false; 
         } 
         
          if (!Objects.equals(this.lastModifiedMS, other.lastModifiedMS)) {
             return false; 
         } 
           
         if (this.isDir != other.isDir) {
             return false; 
         }
         
         return Objects.equals(this.file, other.file);
     }

    @Override
    public int compareTo(DiskElement secondObject) {
        
        boolean alphabetical=false;
        if(this.basename.compareTo(secondObject.basename)<0){
            alphabetical=true;
        }
        
        if(this.isDir && !secondObject.isDir){
            return -1;
        }
        
        if(this.isDir && secondObject.isDir && alphabetical){
        /* (this.file.getTotalSpace()>t.file.getTotalSpace())*/
            return -1;
        }
       
        
        return 1;
    }
}
