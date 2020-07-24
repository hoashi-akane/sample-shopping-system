package other;

public class SystemPath {
	static String SYSTEM_PATH = System.getProperty("user.home");
	
	public static String getImagePath(int goodsId) {
		return "/output_imgfile/"+goodsId;
	}
	
	public static String getImageFullPath(String imagePath) {
		return SYSTEM_PATH+imagePath;
	}
}
