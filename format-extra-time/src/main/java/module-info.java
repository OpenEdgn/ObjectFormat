import edgn.objects.format.api.IFormat;
import edgn.objects.format.ext.time.DateTimeFormat;

module edgn.objects.format.ext.time {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    requires transitive edgn.objects.format.core;
    opens edgn.objects.format.ext.time;
    exports edgn.objects.format.ext.time;
    provides IFormat with DateTimeFormat;
}
