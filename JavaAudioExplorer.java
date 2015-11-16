import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

/**
 * A program for exploring the Mixers, TargetDataLines, and SourceDataLines
 * available to java on the current system.
 * 
 * I originally created this tool with the hope of making a Java audio 
 * application. However, with the help of the tool, I realized that the 
 * application I was trying to create wasn't well suited to the Java language.
 */
public class JavaAudioExplorer {
	public static final long serialVersionUID = 42l;
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[m";

	private Mixer mixer;

	public JavaAudioExplorer() {
		Mixer.Info[] installedMixers = AudioSystem.getMixerInfo();
		for(int n = 0; n < installedMixers.length; n++) {
			System.out.println(ANSI_GREEN + "[" + n + "] " + ANSI_RESET + installedMixers[n].toString());
		}
		while(mixer == null) {
			int choice;
			try {
				System.out.print("Choose a mixer: ");
				Scanner s = new Scanner(System.in);
				choice = s.nextInt();
				if(choice >= 0 && choice < installedMixers.length)
					mixer = AudioSystem.getMixer(installedMixers[choice]);
				else
					System.out.println(ANSI_RED + "Invalid Choice!" + ANSI_RESET);
			} catch(RuntimeException e) {
				System.out.println(ANSI_RED + "Please input an integer corresponding to your mixer choice." + ANSI_RESET);
			}
		}
		System.out.println(ANSI_RED + "Source Lines:" + ANSI_RESET);
		Line.Info[] sourceLines = mixer.getSourceLineInfo();
		if(sourceLines.length == 0) {
			System.out.println("None");
		}
		for(int n = 0; n < sourceLines.length; n++) {
			System.out.println(ANSI_GREEN + "[" + n + "] " + ANSI_RESET + sourceLines[n].toString());
		}
		System.out.println(ANSI_RED + "Target Lines:" + ANSI_RESET);
		Line.Info[] targetLines = mixer.getTargetLineInfo();
		if(targetLines.length == 0) {
			System.out.println("None");
		}
		for(int n = 0; n < targetLines.length; n++) {
			System.out.println(ANSI_GREEN + "[" + n + "] " + ANSI_RESET + targetLines[n].toString());
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			JavaAudioExplorer recording = new JavaAudioExplorer();

			System.out.println("\n" + "Choose another mixer? (y/n)");
			String input = scanner.nextLine();

			if(input.equals("n"))
				break;
		}
	}
}