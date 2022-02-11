import edgn.objects.format.api.IFormat;
import edgn.objects.format.ext.json.JsonDataFormat;

module edgn.objects.format.ext.json {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    requires transitive edgn.objects.format.core;
    requires com.google.gson;
    opens edgn.objects.format.ext.json;
    exports edgn.objects.format.ext.json;
    provides IFormat with JsonDataFormat;
}
