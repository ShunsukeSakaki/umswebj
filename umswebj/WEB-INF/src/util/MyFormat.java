package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class MyFormat {

	//数値を\**,***のフォーマットに変換する
	public String moneyFormat(int price) {
		DecimalFormat money = new DecimalFormat("\u00A4###,##0");

		return money.format(price);
	}

}
