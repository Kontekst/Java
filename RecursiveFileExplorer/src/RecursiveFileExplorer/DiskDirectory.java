/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecursiveFileExplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author Kontekstowy
 */
public class DiskDirectory extends DiskElement{
    Set<DiskElement> children =new HashSet<>();
    private final Set<DiskFile> files;
    
    public DiskDirectory(String path, boolean sorted) {
        File tempFile = new File(path);
        
        basename = tempFile.getName();
        
        if (tempFile.isDirectory()) {
            isDir=true;
        } else {
            isDir=false;
        }

        mtime = new Date(tempFile.lastModified());
        
        permissions = "";
        permissions += tempFile.canRead() ? "r" : "-";
        permissions += tempFile.canWrite() ? "w" : "-";
        permissions += tempFile.canExecute() ? "x" : "-";
        
        if (sorted) {
            files = new TreeSet();
        } else {
            files = new HashSet(); // Chwilowo nie używam
        }
        
        File[] subfiles = tempFile.listFiles();
        if (subfiles != null) {
            for (File subfile : subfiles) {
                if (subfile.isDirectory()){
                   // System.out.println("Początek zapisu folderu do listy");
                    children.add(new DiskDirectory(subfile.getAbsolutePath(),sorted));
                  //  System.out.println("Koniec zapisu folderu do listy");
                }else{
                  //   System.out.println("Początek zapisu pliku do listy");
                   children.add(new DiskFile(subfile.getAbsolutePath()));
                // System.out.println("Koniec zapisu pliku do listy");
                }
              //   System.out.println("Koniec pętli wpisującej subfiles do listy");
            }
             
        }
       //  System.out.println("Koniec pracy konstruktora");
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
        
        text=text + "        ";
        
        text=text + new SimpleDateFormat("yyyy-MM-dd").format(mtime);
        
        System.out.println(text);
        for (DiskElement filee : children) {
            filee.print(depth + 1);
        }
    }    
       @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.basename);
        hash = 53 * hash + Objects.hashCode(this.files);
        hash = 53 * hash + Objects.hashCode(this.permissions);
        hash = 53 * hash + Objects.hashCode(this.isDir);
        hash = 53 * hash + Objects.hashCode(this.mtime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final DiskFile other = (DiskFile) obj;
        if (!Objects.equals(this.basename, other.basename)) {
            return false;
        }
        if (this.isDir != other.isDir) {
            return false;
        }
        if (!Objects.equals(this.mtime, other.mtime)) {
            return false;
        }
        if (!Objects.equals(this.permissions, other.permissions)) {
            return false;
        }
      //  if (!Objects.equals(this.files, other.files)) { // Usunąłem pole klasy z DiskElement
      //      return false;
      //  }
        return true;
    }
    
    public int compareTo(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return 1;
        }
        
        final DiskFile other = (DiskFile) obj;
        return basename.compareTo(other.basename);
    }
    
    
    
    
    
}
