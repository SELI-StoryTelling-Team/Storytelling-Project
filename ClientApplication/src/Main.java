import installer.Installer;

public class Main {

	public static void main(String[] args) {
		if(Installer.isWindows()) {
			System.out.println("You are in Windows");
		}
	}
}
