import edgn.objects.format.api.IFormat;
import edgn.objects.format.rules.BasicDataFormat;
import edgn.objects.format.rules.DefaultDataFormat;

module edgn.objects.format.core {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    exports edgn.objects.format;
    exports edgn.objects.format.api;
    opens edgn.objects.format.rules;
    exports edgn.objects.format.rules;
    provides IFormat with BasicDataFormat, DefaultDataFormat;
}
