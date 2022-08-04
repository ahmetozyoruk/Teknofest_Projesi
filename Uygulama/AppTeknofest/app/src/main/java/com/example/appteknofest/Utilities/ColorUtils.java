package com.example.appteknofest.Utilities;
import java.util.ArrayList;

/**
 * Java Code to get a color name from rgb/hex value/awt color
 *
 * The part of looking up a color name from the rgb values is edited from
 * https://gist.github.com/nightlark/6482130#file-gistfile1-java (that has some errors) by Ryan Mast (nightlark)
 *
 * @author Xiaoxiao Li
 *
 */
public class ColorUtils {

	/**
	 * Initialize the color list that we have.
	 */
	private ArrayList<ColorName> initColorList() {
		ArrayList<ColorName> colorList = new ArrayList<ColorName>();
		colorList.add(new ColorName("Alice Mavi", 0xF0, 0xF8, 0xFF));
		colorList.add(new ColorName("Antik Beyaz", 0xFA, 0xEB, 0xD7));
		colorList.add(new ColorName("Su rengi", 0x00, 0xFF, 0xFF));
		colorList.add(new ColorName("Akuamarin", 0x7F, 0xFF, 0xD4));
		colorList.add(new ColorName("Gök mavisi", 0xF0, 0xFF, 0xFF));
		colorList.add(new ColorName("Bej", 0xF5, 0xF5, 0xDC));
		colorList.add(new ColorName("Bisküvi", 0xFF, 0xE4, 0xC4));
		colorList.add(new ColorName("Siyah", 0x00, 0x00, 0x00));
		colorList.add(new ColorName("Beyazlatılmış Badem", 0xFF, 0xEB, 0xCD));
		colorList.add(new ColorName("Mavi", 0x00, 0x00, 0xFF));
		colorList.add(new ColorName("Mavi menekşe", 0x8A, 0x2B, 0xE2));
		colorList.add(new ColorName("Kahverengi", 0xA5, 0x2A, 0x2A));
		colorList.add(new ColorName("Açık kahve", 0xDE, 0xB8, 0x87));
		colorList.add(new ColorName("Harbiyeli Mavi", 0x5F, 0x9E, 0xA0));
		colorList.add(new ColorName("Açık fıstık yeşili", 0x7F, 0xFF, 0x00));
		colorList.add(new ColorName("Çikolata", 0xD2, 0x69, 0x1E));
		colorList.add(new ColorName("Mercan", 0xFF, 0x7F, 0x50));
		colorList.add(new ColorName("Peygamber Çiçeği Mavisi", 0x64, 0x95, 0xED));
		colorList.add(new ColorName("Mısır püskülü", 0xFF, 0xF8, 0xDC));
		colorList.add(new ColorName("Kızıl", 0xDC, 0x14, 0x3C));
		colorList.add(new ColorName("Cam göbeği", 0x00, 0xFF, 0xFF));
		colorList.add(new ColorName("Koyu mavi", 0x00, 0x00, 0x8B));
		colorList.add(new ColorName("Koyu cam göbeği", 0x00, 0x8B, 0x8B));
		colorList.add(new ColorName("Koyu altın", 0xB8, 0x86, 0x0B));
		colorList.add(new ColorName("Koyu gri", 0xA9, 0xA9, 0xA9));
		colorList.add(new ColorName("koyu yeşil", 0x00, 0x64, 0x00));
		colorList.add(new ColorName("Koyu Haki", 0xBD, 0xB7, 0x6B));
		colorList.add(new ColorName("koyu eflatun", 0x8B, 0x00, 0x8B));
		colorList.add(new ColorName("Koyu Zeytin Yeşili", 0x55, 0x6B, 0x2F));
		colorList.add(new ColorName("Koyu turuncu", 0xFF, 0x8C, 0x00));
		colorList.add(new ColorName("kara orkide", 0x99, 0x32, 0xCC));
		colorList.add(new ColorName("Koyu kırmızı", 0x8B, 0x00, 0x00));
		colorList.add(new ColorName("koyu somon", 0xE9, 0x96, 0x7A));
		colorList.add(new ColorName("Karanlık Deniz Yeşili", 0x8F, 0xBC, 0x8F));
		colorList.add(new ColorName("Koyu Arduvaz Mavi", 0x48, 0x3D, 0x8B));
		colorList.add(new ColorName("Koyu Arduvaz Grisi", 0x2F, 0x4F, 0x4F));
		colorList.add(new ColorName("Koyu turkuaz", 0x00, 0xCE, 0xD1));
		colorList.add(new ColorName("koyu mor", 0x94, 0x00, 0xD3));
		colorList.add(new ColorName("Derin pembe", 0xFF, 0x14, 0x93));
		colorList.add(new ColorName("kapalı gökyüzü", 0x00, 0xBF, 0xFF));
		colorList.add(new ColorName("soluk gri", 0x69, 0x69, 0x69));
		colorList.add(new ColorName("Atlayan mavi", 0x1E, 0x90, 0xFF));
		colorList.add(new ColorName("Tuğla rengi", 0xB2, 0x22, 0x22));
		colorList.add(new ColorName("Çiçek beyazı", 0xFF, 0xFA, 0xF0));
		colorList.add(new ColorName("Orman yeşili", 0x22, 0x8B, 0x22));
		colorList.add(new ColorName("Fuşya", 0xFF, 0x00, 0xFF));
		colorList.add(new ColorName("Gray", 0xDC, 0xDC, 0xDC));
		colorList.add(new ColorName("Beyaz", 0xF8, 0xF8, 0xFF));
		colorList.add(new ColorName("Altın", 0xFF, 0xD7, 0x00));
		colorList.add(new ColorName("Altın", 0xDA, 0xA5, 0x20));
		colorList.add(new ColorName("Gri", 0x80, 0x80, 0x80));
		colorList.add(new ColorName("Yeşil", 0x00, 0x80, 0x00));
		colorList.add(new ColorName("Yeşilimsi", 0xAD, 0xFF, 0x2F));
		colorList.add(new ColorName("Balrengi", 0xF0, 0xFF, 0xF0));
		colorList.add(new ColorName("ateşli penmbe", 0xFF, 0x69, 0xB4));
		colorList.add(new ColorName("Kırmızı", 0xCD, 0x5C, 0x5C));
		colorList.add(new ColorName("Çivit", 0x4B, 0x00, 0x82));
		colorList.add(new ColorName("fildişi", 0xFF, 0xFF, 0xF0));
		colorList.add(new ColorName("haki", 0xF0, 0xE6, 0x8C));
		colorList.add(new ColorName("lavanta", 0xE6, 0xE6, 0xFA));
		colorList.add(new ColorName("lavnata", 0xFF, 0xF0, 0xF5));
		colorList.add(new ColorName("çimen yeşil", 0x7C, 0xFC, 0x00));
		colorList.add(new ColorName("limon rengi", 0xFF, 0xFA, 0xCD));
		colorList.add(new ColorName("açık mavi", 0xAD, 0xD8, 0xE6));
		colorList.add(new ColorName("açık mercan", 0xF0, 0x80, 0x80));
		colorList.add(new ColorName("açık mercan göbeği", 0xE0, 0xFF, 0xFF));
		colorList.add(new ColorName("açık altın sarısı", 0xFA, 0xFA, 0xD2));
		colorList.add(new ColorName("açık gri", 0xD3, 0xD3, 0xD3));
		colorList.add(new ColorName("açık yeşil", 0x90, 0xEE, 0x90));
		colorList.add(new ColorName("açık pembe", 0xFF, 0xB6, 0xC1));
		colorList.add(new ColorName("ten rengi", 0xFF, 0xA0, 0x7A));
		colorList.add(new ColorName("açık deniz yeşili", 0x20, 0xB2, 0xAA));
		colorList.add(new ColorName("açık gökyüzü rengi", 0x87, 0xCE, 0xFA));
		colorList.add(new ColorName("açık gri", 0x77, 0x88, 0x99));
		colorList.add(new ColorName("açık çelik mavisi", 0xB0, 0xC4, 0xDE));
		colorList.add(new ColorName("açık sarı", 0xFF, 0xFF, 0xE0));
		colorList.add(new ColorName("laym", 0x00, 0xFF, 0x00));
		colorList.add(new ColorName("limon yeşili", 0x32, 0xCD, 0x32));
		colorList.add(new ColorName("Linen", 0xFA, 0xF0, 0xE6));
		colorList.add(new ColorName("Macenta", 0xFF, 0x00, 0xFF));
		colorList.add(new ColorName("bordo", 0x80, 0x00, 0x00));
		colorList.add(new ColorName("yeşil mavi", 0x66, 0xCD, 0xAA));
		colorList.add(new ColorName("mavi", 0x00, 0x00, 0xCD));
		colorList.add(new ColorName("orkide", 0xBA, 0x55, 0xD3));
		colorList.add(new ColorName("mor", 0x93, 0x70, 0xDB));
		colorList.add(new ColorName("deniz yeşili", 0x3C, 0xB3, 0x71));
		colorList.add(new ColorName("mavi", 0x7B, 0x68, 0xEE));
		colorList.add(new ColorName("yeşil", 0x00, 0xFA, 0x9A));
		colorList.add(new ColorName("turkuaz", 0x48, 0xD1, 0xCC));
		colorList.add(new ColorName("mor", 0xC7, 0x15, 0x85));
		colorList.add(new ColorName("gece yarısı mavisi", 0x19, 0x19, 0x70));
		colorList.add(new ColorName("açık yeşil", 0xF5, 0xFF, 0xFA));
		colorList.add(new ColorName("puslu gül", 0xFF, 0xE4, 0xE1));
		colorList.add(new ColorName("mokasen", 0xFF, 0xE4, 0xB5));
		colorList.add(new ColorName("Navajo Beyaz", 0xFF, 0xDE, 0xAD));
		colorList.add(new ColorName("Askeri yeşil", 0x00, 0x00, 0x80));
		colorList.add(new ColorName("Eski dantel", 0xFD, 0xF5, 0xE6));
		colorList.add(new ColorName("zeytin yeşili", 0x80, 0x80, 0x00));
		colorList.add(new ColorName("Zeytin yeşili", 0x6B, 0x8E, 0x23));
		colorList.add(new ColorName("turuncu", 0xFF, 0xA5, 0x00));
		colorList.add(new ColorName("turuncu", 0xFF, 0x45, 0x00));
		colorList.add(new ColorName("orkide", 0xDA, 0x70, 0xD6));
		colorList.add(new ColorName("soluk altın", 0xEE, 0xE8, 0xAA));
		colorList.add(new ColorName("soluk altın", 0x98, 0xFB, 0x98));
		colorList.add(new ColorName("soluk turkuaz", 0xAF, 0xEE, 0xEE));
		colorList.add(new ColorName("soluk pembe", 0xDB, 0x70, 0x93));
		colorList.add(new ColorName("ten rengi", 0xFF, 0xEF, 0xD5));
		colorList.add(new ColorName("ten rengi", 0xFF, 0xDA, 0xB9));
		colorList.add(new ColorName("koyu ten rengi", 0xCD, 0x85, 0x3F));
		colorList.add(new ColorName("Pembe", 0xFF, 0xC0, 0xCB));
		colorList.add(new ColorName("Koyu mor", 0xDD, 0xA0, 0xDD));
		colorList.add(new ColorName("Toz pembe", 0xB0, 0xE0, 0xE6));
		colorList.add(new ColorName("mor", 0x80, 0x00, 0x80));
		colorList.add(new ColorName("kırmızı", 0xFF, 0x00, 0x00));
		colorList.add(new ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
		colorList.add(new ColorName("ten rengi", 0x41, 0x69, 0xE1));
		colorList.add(new ColorName("kahverengi", 0x8B, 0x45, 0x13));
		colorList.add(new ColorName("Somon", 0xFA, 0x80, 0x72));
		colorList.add(new ColorName("ten rengi", 0xF4, 0xA4, 0x60));
		colorList.add(new ColorName("deniz yeşili", 0x2E, 0x8B, 0x57));
		colorList.add(new ColorName("deniz kabuğu", 0xFF, 0xF5, 0xEE));
		colorList.add(new ColorName("Sienna", 0xA0, 0x52, 0x2D));
		colorList.add(new ColorName("gümüş", 0xC0, 0xC0, 0xC0));
		colorList.add(new ColorName("gökyüzü", 0x87, 0xCE, 0xEB));
		colorList.add(new ColorName("mavi", 0x6A, 0x5A, 0xCD));
		colorList.add(new ColorName("gri", 0x70, 0x80, 0x90));
		colorList.add(new ColorName("kar", 0xFF, 0xFA, 0xFA));
		colorList.add(new ColorName("bahar yeşili", 0x00, 0xFF, 0x7F));
		colorList.add(new ColorName("çelik mavisi", 0x46, 0x82, 0xB4));
		colorList.add(new ColorName("Ten rengi", 0xD2, 0xB4, 0x8C));
		colorList.add(new ColorName("deniz mavisi", 0x00, 0x80, 0x80));
		colorList.add(new ColorName("devedikeni", 0xD8, 0xBF, 0xD8));
		colorList.add(new ColorName("domates rengi", 0xFF, 0x63, 0x47));
		colorList.add(new ColorName("Turkuaz", 0x40, 0xE0, 0xD0));
		colorList.add(new ColorName("menekşe", 0xEE, 0x82, 0xEE));
		colorList.add(new ColorName("buğday", 0xF5, 0xDE, 0xB3));
		colorList.add(new ColorName("beyaz", 0xFF, 0xFF, 0xFF));
		colorList.add(new ColorName("duman rengi", 0xF5, 0xF5, 0xF5));
		colorList.add(new ColorName("sarı", 0xFF, 0xFF, 0x00));
		colorList.add(new ColorName("sarı", 0x9A, 0xCD, 0x32));
		return colorList;
	}

	/**
	 * Get the closest color name from our list
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public String getColorNameFromRgb(int r, int g, int b) {
		ArrayList<ColorName> colorList = initColorList();
		ColorName closestMatch = null;
		int minMSE = Integer.MAX_VALUE;
		int mse;
		for (ColorName c : colorList) {
			mse = c.computeMSE(r, g, b);
			if (mse < minMSE) {
				minMSE = mse;
				closestMatch = c;
			}
		}

		if (closestMatch != null) {
			return closestMatch.getName();
		} else {
			return "No matched color name.";
		}
	}

	public class ColorName {
		public int r, g, b;
		public String name;

		public ColorName(String name, int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.name = name;
		}

		public int computeMSE(int pixR, int pixG, int pixB) {
			return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
					* (pixB - b)) / 3);
		}

		public int getR() {
			return r;
		}

		public int getG() {
			return g;
		}

		public int getB() {
			return b;
		}

		public String getName() {
			return name;
		}
	}
}
