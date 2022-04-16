package me.funwithalbi.infinityhax.manager;

import me.funwithalbi.infinityhax.util.DisplayUtil;
import me.funwithalbi.infinityhax.util.NoStackTraceThrowable;
import me.funwithalbi.infinityhax.util.SystemUtil;
import me.funwithalbi.infinityhax.util.URLReader;

import java.util.ArrayList;
import java.util.List;

public class HWIDManager {

    /**
     * Your pastebin URL goes inside the empty string below.
     * It should be a raw pastebin link, for example: pastebin.com/raw/pasteid
     */

    public static final String pastebinURL = "https://pastebin.com/R6cWV1vc";

    public static List<String> hwids = new ArrayList<>();

    public static void hwidCheck() {
        hwids = URLReader.readURL();
        boolean isHwidPresent = hwids.contains(SystemUtil.getSystemInfo());
        if (!isHwidPresent) {
            DisplayUtil.Display();
            throw new NoStackTraceThrowable("");
        }
    }
}
