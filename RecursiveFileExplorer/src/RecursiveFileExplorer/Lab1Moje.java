package RecursiveFileExplorer;

// @author Kontekstowy
// "2" HashSet z sortowaniem
// "3" TreeSet z sorotwaniem

public class Lab1Moje {

   public static void main(String[] args) {
        args[1]="C:\\GNAT";
        args[2]="3"; 
        
        DiskDirectory temp =new DiskDirectory(args[1], true,args[2]);
        temp.print(0);
    }
}