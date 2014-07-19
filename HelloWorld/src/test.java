

public class test extends Alpha {
	
	public static void main(String[] args) {
		for (int i = 0; i <= 2; i++) {
			try {
				int num = 1/i;
				System.out.println("" + num);
			} catch (NullPointerException n) {
				System.out.println("n");
			} catch (NumberFormatException f) {
				System.out.println("f");
			} catch (Exception e) {
				System.out.println("e");
			}
		}
		
	}
	
//		Scanner scanner = new Scanner(System.in);
////		playSound("toad.wav");
//		try {
//            Clip clip = AudioSystem.getClip();
//            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("mkOpen.mid"));
//            clip.open(inputStream);
//            clip.start();
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//		String test = scanner.next();
//	}
//	
//	public static synchronized void playSound(final String url) {
//	    new Thread(new Runnable() { 
//	    	
//	                public void run() {
//	                    try {
//	                        Clip clip = AudioSystem.getClip();
//	                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
//	                        clip.open(inputStream);
//	                        clip.start();
//	                    } catch (Exception e) {
//	                        System.err.println(e.getMessage());
//	                        System.out.println("no go jo");
//	                    }
//	                }
//	            }).start();
//	}
}
