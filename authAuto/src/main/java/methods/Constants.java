package methods;

import static methods.FirstConnect.cutTime;

public class Constants {

//    public static String api = "https://api.auto.ru";
//    public static String api2 = "https://api2.auto.ru";
//    public static String url_api2_search = "https://api2.auto.ru/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + cutTime;

    public static String api = "http://api2.test.autoru.yandex.net";
    public static String api2 = "http://auto-api.test.autoru.yandex.net";
    public static String url_api2_search = "http://auto-api.test.autoru.yandex.net/1.1/search?category_id=15&page_num=1&page_size=50&creation_date_to=" + cutTime;

    public static final int AUTO_CATEGORY = 15; //авто
    public static final int COMM_CATEGORY = 29; //коммерческий транспорт
    public static final int MOTO_CATEGORY = 17; //мото транспорт
    public static final int LIGHT_TRUCKS = 31; //легкие коммерческие
    public static final int TRUCKS = 32; //грузовики
    public static final int ARTIC = 33; //тягачи
    public static final int BUS = 34; // автобусы
    public static final int DRAGS = 16; //прицепы
    public static final int MOTORCYCLE = 1; //Мотоциклы
    public static final int SCOOTERS = 55; //Скутеры
    public static final int SNOWMOBILE = 4; //Снегоходы
    public static final int ATV = 3; //Мотовездеходы
    public static final String[] GROUPS_CONST_LIST = {"Любая марка", "Отечественные", "Иномарки"};
}
