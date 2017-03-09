package RecursiveFileExplorer;

// @author Kontekstowy
 
public class Lab1Moje {

    public static void main(String[] args) {
        args[1]="C:\\Program Files";
        DiskDirectory temp =new DiskDirectory(args[1], false);
        temp.print(0);
    }
}