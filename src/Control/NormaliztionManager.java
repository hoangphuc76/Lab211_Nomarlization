package Control;

import Common.Inputter;
import View.Menu;
import View.NormalizationView;

import java.io.File;
import java.util.Scanner;

public class NormaliztionManager extends Menu {

    Scanner scanner = new Scanner(System.in);
    Inputter inputter = new Inputter();
    NormalizationView normalizationView = new NormalizationView();

    public NormaliztionManager() {
        super("\t\tNORMALIZED TEXT", new String[]{
                "\tNormalize Text by Entering",
                "\tNormalize Text by File",
                "\tExit"
        });
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                normalizationView.display(inputter.getString("Data"));
                break;
            case 2:
                normalizationView.loadData(inputter.getString("FilePath"));
                normalizationView.writeStringToFile(normalizationView.nomarlizeSpace(normalizationView.getText()),
                        "src/Model/output.txt" );
                break;
            default:
                System.out.println("THANK YOU !!!");
        }
    }
}
