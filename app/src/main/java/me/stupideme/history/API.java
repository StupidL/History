package me.stupideme.history;

import android.os.Environment;

/**
 * Created by stupid on 16-4-25.
 */
public class API {
    public static final class REQUEST_URL{
        public static final String QUERY_EVENT = "http://v.juhe.cn/todayOnhistory/queryEvent.php";
        public static final String QUERY_DETAIL = "http://v.juhe.cn/todayOnhistory/queryDetail.php";
        public static final String API_KEY = "3edaf659d197df588395e20c9af99e79";
    }


    public static final class RESPONSE_KEY{
        public static final String REASON = "reason";
        public static final String ERROR_CODE = "error_code";
    }

    public static final class LOCAL{
        public static final String SD_CARD = Environment.getExternalStorageDirectory().getPath();
        public static final String APP_FILE_DIR =  SD_CARD + "/HistoryToday";
        public static final String IMAGE_DIR = APP_FILE_DIR +"/pics";
    }
}
