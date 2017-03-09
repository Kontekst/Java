package RecursiveFileExplorer;

// @author Kontekstowy
 
public class Lab1Moje {

    public static void main(String[] args) {
      //  args[1]="C:\\Program Files\\Intel";
        args[1]="C:\\Program Files\\Intel";
        DiskDirectory temp =new DiskDirectory(args[1], true);
        temp.print(0);
    }
}